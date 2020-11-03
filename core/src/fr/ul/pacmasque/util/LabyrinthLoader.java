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
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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

	/**
	 * Instance partagée, standard
	 */
	private static LabyrinthLoader _shared = null;

	/**
	 * @return l'instance partagée, standard
	 */
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

	/**
	 * Tag délimitant le début et la fin des métadonnées
	 */
	private final String metaTag;

	/**
	 * Délimiteur identifiant le début des métadonnées
	 */
	private final String delimiterIn;

	/**
	 * Délimiteur identifiant la fin des métadonnées
	 */
	private final String delimiterOut;

	/**
	 * Séparateur de ligne des métadonnées
	 */
	private final String lineSeparator;

	/**
	 * Séparateur de champs de métadonnées
	 */
	private final String fieldSeparator;

	/**
	 * Instancie un nouveau LabyrinthLoader, avec des paramètres définis
	 * @param metaTag Tag délimitant le début et la fin des métadonnées
	 * @param delimiterIn Délimiteur identifiant le début des métadonnées, après le metaTag
	 * @param delimiterOut Délimiteur identifiant la fin des métadonnées, avant le metaTag
	 * @param lineSeparator Séparateur de ligne des métadonnées
	 * @param fieldSeparator Séparateur de champs de métadonnées
	 */
	@Contract(pure = true)
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

	/**
	 * Charge un fichier content un labyrinthe, ainsi que ses métadonnées en entête
	 * @param path le chemin du fichier, relatif au dossier des assets
	 * @return Le labyrinthe correspondant, construit avec le builder définit dans son entête
	 * @throws LabyrinthLoaderException si le fichier n'existe pas, ou si le labyrinthe ne peut être chargé ou construit
	 */
	public Labyrinth loadFile(String path) throws LabyrinthLoaderException {
		FileHandle fileHandle = Gdx.files.internal(path);

		if (!fileHandle.exists()) {
			throw new LabyrinthLoaderException("Le fichier n'existe pas");
		}

		// Contenu du fichier
		String content = this.contentOf(fileHandle);

		// Séparation du fichier en deux: le header, et le payload (le labyrinthe), selon les délimiteurs définis
		String[] splitContent = this.splitHeaderAndPayload(content, inputDelimiter(), outputDelimiter());

		// Transformation du header en map
		Map<String, String> header = keyedHeader(splitContent[0], this.lineSeparator, this.fieldSeparator);

		String payload = splitContent[1];

		// Récupération de la classe du builder du labyrinthe
		Class<? extends LabyrinthBuilder> builderClass = builderClass(header.get("builder"));

		// Création d'une instance du builder
		LabyrinthBuilder builderInstance = instanceOfBuilder(builderClass);
		try {

			// On essaie de construire le labyrinthe
			return builderInstance.build(payload);
		} catch (PacmasqueException e) {
			throw new LabyrinthLoaderException("Impossible de créer le labyrinthe, le builder à renvoyé une erreur lors de sa construction: " + e.getLocalizedMessage());
		}
	}

	/**
	 * Récupère la classe du builder
	 * @param className une classe de builder
	 * @return la classe du builder
	 * @throws LabyrinthLoaderException si la classe n'a pas été trouvée, ou si la classe n'implémente pas l'interface `LabyrinthBuilder`
	 */
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

	/**
	 * Crée une instance de la classe du builder par réflexion.
	 * @param builderClass une classe de builder de labyrinthe
	 * @return une instance du builder de labyrinthe
	 * @throws LabyrinthLoaderException si la classe de builder ne possède pas de constructeur vide (sans paramètres) publique, si le constructeur à renvoyé une erreur, ou si l'instanciation du constructeur n'a pas pu se faire.
	 */
	private @NotNull LabyrinthBuilder instanceOfBuilder(Class<? extends LabyrinthBuilder> builderClass) throws LabyrinthLoaderException {
		Constructor<? extends LabyrinthBuilder> cons;
		try {
			cons = builderClass.getConstructor();
			return cons.newInstance();
		} catch (NoSuchMethodException e) {
			throw new LabyrinthLoaderException("Impossible d'instancier le builder " + builderClass.getSimpleName() + ", il ne possède pas de constructeur standard");
		} catch (IllegalAccessException e) {
			throw new LabyrinthLoaderException("Impossible d'instancier le builder " + builderClass.getSimpleName() + ", il ne possède pas de constructeur accessible");
		} catch (InvocationTargetException e) {
			throw new LabyrinthLoaderException("Impossible d'instancier le builder " + builderClass.getSimpleName() + ", son constructeur a renvoyé une erreur");
		} catch (InstantiationException e) {
			throw new LabyrinthLoaderException("Impossible d'instancier le builder " + builderClass.getSimpleName() + ", une erreur est survenue lors de sa création");
		}
	}

	/**
	 * Retourne le contenu de l'handle
	 * @param handle un accesseur de fichier
	 * @return son contenu, avec le charset UTF-8
	 */
	private String contentOf(@NotNull FileHandle handle) {
		return handle.readString("UTF-8");
	}

	/**
	 * Sépare les métadonnées et le payload du fichier
	 * @param fileContent le contenu du fichier
	 * @param inputDelimiter le délimiteur du début des métadonnées
	 * @param outputDelimiter le délimiteur de fin des métadonnées
	 * @return le header (indice 0) et le payload (indice 1)
	 * @throws LabyrinthLoaderException si les métadonnées ne sont pas présente, ou si le fichier est mal formé
	 */
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

	/**
	 * Transforme les métadonnées du header en map
	 * @param headerContent le contenu du header
	 * @param lineSeparator le séparateur de lignes des métadonnées
	 * @param fieldSeparator le séparateur de champ des métadonnées
	 * @return les métadonnées sous forme de map
	 */
	private @NotNull Map<String, String> keyedHeader(String headerContent, String lineSeparator, String fieldSeparator) {
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
