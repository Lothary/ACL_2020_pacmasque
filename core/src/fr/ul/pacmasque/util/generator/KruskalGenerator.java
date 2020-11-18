/*
 * KruskalGenerator.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 18/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.generator;

import fr.ul.pacmasque.model.Labyrinth;
import org.jetbrains.annotations.Range;

import java.util.List;
import java.util.Stack;

public class KruskalGenerator implements LabyrinthGenerator {

	/**
	 * Seed de génération
	 */
	private int seed;

	/**
	 * Instancie un générateur selon un seed
	 * @param seed un seed
	 */
	public KruskalGenerator(int seed) {
		this.seed = seed;
	}

	/**
	 * Génère un labyrinthe selon l'algorithme de Kruskal
	 * @param width  largeur du labyrinthe
	 * @param height hauteur du labyrinthe
	 * @apiNote la hauteur `width` et la hauteur `height` doivent être impair, supérieur à 3
	 * @return un labyrinthe
	 */
	@Override
	public Labyrinth generate(@Range(from = 3, to = 99) int width, @Range(from = 3, to = 99) int height) throws LabyrinthGeneratorException {

		if (!isSizeValid(width) || !isSizeValid(height)) {
			throw new LabyrinthGeneratorException(this, width, height, "La taille demandée est invalide");
		}

		Matrix<Tree> matrix = new Matrix<>(width, height, new Tree());

		List<Edge> edgesStack = new Stack<Edge>();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {

				if (y < height)
					edgesStack.add(new Edge(x, y, Edge.Direction.up));
				if (x < width) {
					edgesStack.add(new Edge(x, y, Edge.Direction.right));
				}
			}
		}

		System.out.println(edgesStack.size());
		edgesStack.forEach(System.out::println);

		return null;
	}

	private boolean isSizeValid(int size) {
		return (size >= 3) && (size % 2 == 1);
	}
}
