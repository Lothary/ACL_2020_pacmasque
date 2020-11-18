/*
 * LabyrinthGeneratorException.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 18/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.generator;

public class LabyrinthGeneratorException extends Exception {

	private LabyrinthGenerator generator;
	private int width;
	private int height;

	public LabyrinthGeneratorException(LabyrinthGenerator generator, int width, int height, String message) {
		super("Une erreur est survenue lors de la génération du labyrinthe: " + message);
	}

	public LabyrinthGenerator getGenerator() {
		return generator;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
