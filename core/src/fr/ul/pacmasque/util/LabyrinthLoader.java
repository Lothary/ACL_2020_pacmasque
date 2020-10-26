/*
 * LabyrinthLoader.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 20/10/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import fr.ul.pacmasque.exception.LabyrinthLoaderException;
import fr.ul.pacmasque.exception.PacmasqueException;
import fr.ul.pacmasque.model.Labyrinth;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Le but de cette classe est de charger un fichier de
 * configuration de labyrinthes, et de sélectionner le
 * builder adapté
 */
public class LabyrinthLoader {

	private static LabyrinthLoader _shared = null;

	public static LabyrinthLoader shared() {
		if (_shared == null) {
			_shared = new LabyrinthLoader(
					"meta",
					"{",
					"}",
					"\r?\n",
					":"
			);
		}

		return _shared;
	}

	private final String metaTag;
	private final String delimiterIn;
	private final String delimiterOut;
	private final String lineSeparator;
	private final String fieldSeparator;

	public LabyrinthLoader(String metaTag, String delimiterIn, String delimiterOut, String lineSeparator, String fieldSeparator) {
		this.metaTag = metaTag;
		this.delimiterIn = delimiterIn;
		this.delimiterOut = delimiterOut;
		this.lineSeparator = lineSeparator;
		this.fieldSeparator = fieldSeparator;
	}

	private String inputDelimiter() {
		return this.metaTag + " " + this.delimiterIn;
	}

	private String outputDelimiter() {
		return this.delimiterOut + " " + this.metaTag;
	}

	public Labyrinth loadFile(String path) throws LabyrinthLoaderException {
		FileHandle fileHandle = Gdx.files.internal(path);

		String content = this.contentOf(fileHandle);

		String[] splitContent = this.splitHeaderAndPayload(content, inputDelimiter(), outputDelimiter());

		Map<String, String> header = keyedHeader(splitContent[0], this.lineSeparator, this.fieldSeparator);
		String payload = splitContent[1];

		Class<? extends LabyrinthBuilder> builderClass = builderClass(header.get("builder"));
		LabyrinthBuilder builderInstance = instanceOfBuilder(builderClass);
		try {
			return builderInstance.build(payload);
		} catch (PacmasqueException e) {
			throw new LabyrinthLoaderException("Impossible de créer le labyrinthe, le builder à renvoyé une erreur lors de sa construction: " + e.getLocalizedMessage());
		}
	}

	private Class<? extends LabyrinthBuilder> builderClass(String className) throws LabyrinthLoaderException {
		Class<?> builderClass;
		try {
			builderClass = Class.forName(className, false, ClassLoader.getSystemClassLoader());
		} catch (ClassNotFoundException e) {
			throw new LabyrinthLoaderException("La class du builder n'a pas pu être trouvée: " + e.getLocalizedMessage());
		}

		if (!LabyrinthBuilder.class.isAssignableFrom(builderClass)) {
			throw new LabyrinthLoaderException("La class du builder ne correspond pas à un constructeur de labyrinth");
		}

		//noinspection unchecked
		return (Class<? extends LabyrinthBuilder>) builderClass;
	}

	private LabyrinthBuilder instanceOfBuilder(Class<? extends LabyrinthBuilder> builderClass) throws LabyrinthLoaderException {
		Constructor<? extends LabyrinthBuilder> cons;
		try {
			cons = builderClass.getConstructor();
			return cons.newInstance();
		} catch (NoSuchMethodException e) {
			throw new LabyrinthLoaderException("Impossible d'instancier le builder " + builderClass.getSimpleName() + ", il ne possède pas de constructeur standart");
		} catch (IllegalAccessException e) {
			throw new LabyrinthLoaderException("Impossible d'instancier le builder " + builderClass.getSimpleName() + ", il ne possède pas de constructeur accessible");
		} catch (InvocationTargetException e) {
			throw new LabyrinthLoaderException("Impossible d'instancier le builder " + builderClass.getSimpleName() + ", son constructeur a renvoyé une erreur");
		} catch (InstantiationException e) {
			throw new LabyrinthLoaderException("Impossible d'instancier le builder " + builderClass.getSimpleName() + ", une erreur est survenue lors de sa création");
		}
	}

	private String contentOf(FileHandle handle) {
		return handle.readString("UTF-8");
	}

	private String[] splitHeaderAndPayload(String fileContent, String inputDelimiter, String outputDelimiter) throws LabyrinthLoaderException {
		int startIndex = fileContent.indexOf(inputDelimiter);
		if (startIndex == -1) {
			throw new LabyrinthLoaderException("Starting meta tag missing, excepted `" + inputDelimiter + "`");
		}

		int endIndex = fileContent.lastIndexOf(outputDelimiter);
		if (endIndex == -1) {
			throw new LabyrinthLoaderException("Ending meta tag missing, excepted `" + outputDelimiter + "`");
		}

		int length = inputDelimiter.length();
		String header = fileContent.substring(startIndex + length + 1, endIndex - 1);
		String payload = fileContent.substring(endIndex + length + 1);

		return new String[]{header, payload};
	}

	private Map<String, String> keyedHeader(String headerContent, String lineSeparator, String fieldSeparator) {
		String[] headerFields = headerContent.split(lineSeparator);
		Map<String, String> headerMap = new HashMap<>(headerFields.length);

		for (String headersField : headerFields) {
			String header = headersField.trim();
			if (!header.isEmpty()) {
				String[] splitHeader = header.split(String.valueOf(fieldSeparator));
				headerMap.put(splitHeader[0].trim(), splitHeader[1].trim());
			}
		}

		return headerMap;
	}
}
