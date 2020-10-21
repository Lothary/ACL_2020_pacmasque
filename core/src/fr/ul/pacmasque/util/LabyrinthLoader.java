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
					"\n",
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

	private Map<String, String> lastHeader = null;

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

	public void loadFile(String path) throws LabyrinthLoaderException {
		FileHandle fileHandle = Gdx.files.internal(path);

		String content = this.contentOf(fileHandle);

		String[] splitContent = this.splitHeaderAndPayload(content, inputDelimiter(), outputDelimiter());

		this.lastHeader = keyedHeader(splitContent[0], this.lineSeparator, this.fieldSeparator);
	}

	public Class<?> builderClass() throws ClassNotFoundException {
		if (this.lastHeader == null) {
			return null;
		}

		String className = this.lastHeader.get("builder");
		//return this.getClass().getClassLoader().loadClass(className);
		return Class.forName(className, false, ClassLoader.getSystemClassLoader());
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
			String[] splitHeader = header.split(String.valueOf(fieldSeparator));
			headerMap.put(splitHeader[0].trim(), splitHeader[1].trim());
		}

		return headerMap;
	}
}
