/*
 * KruskalGenerator.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 18/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.generator;

import fr.ul.pacmasque.model.Labyrinth;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 * Génère un labyrinthe selon l'algorithme de Kruskal
 */
public class KruskalGenerator implements LabyrinthGenerator {

	/**
	 * Seed de génération
	 */
	private final int seed;

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
	public @NotNull Labyrinth generate(@Range(from = 3, to = 99) int width, @Range(from = 3, to = 99) int height) throws LabyrinthGeneratorException {

		if (isSizeInvalid(width) || isSizeInvalid(height)) {
			throw new LabyrinthGeneratorException(this, width, height, "La taille demandée est invalide");
		}

		int vWidth = (width + 1) / 2;
		int vHeight = (height + 1) / 2;

		// Matrice contenant les sets
		Matrix<Tree> matrix = new Matrix<>(vWidth, vHeight, Tree::new);

		// Nouveau labyrinthe vide
		Labyrinth labyrinth = new Labyrinth(width, height);

		// Labyrinthe plein
		for (int x = 0; x < labyrinth.getWidth(); x++) {
			for (int y = 0; y < labyrinth.getHeight(); y++) {
				if (x % 2 != 0 || y % 2 != 0)
					labyrinth.setMur(x, y);
			}
		}

		// Pile des edges
		Stack<Edge> edgesStack = new Stack<>();
		for (int x = 0; x < vWidth; x++) {
			for (int y = 0; y < vHeight; y++) {
				if (y < vHeight - 1)
					edgesStack.add(new Edge(x, y, Edge.Direction.up));
				if (x < vWidth - 1)
					edgesStack.add(new Edge(x, y, Edge.Direction.right));

			}
		}

		Random random = new Random(this.seed);
		Collections.shuffle(edgesStack, random);

		while (!edgesStack.isEmpty()) {
			Edge edge = edgesStack.pop();

			int vx = edge.getX();
			int vy = edge.getY();

			int vnx = vx + (edge.getDirection() == Edge.Direction.right ? 1 : 0);
			int vny = vy + (edge.getDirection() == Edge.Direction.up ? 1 : 0);

			Tree tree1 = matrix.get(vx, vy);
			Tree tree2 = matrix.get(vnx, vny);

			assert tree1 != null;
			assert tree2 != null;

			if (!tree1.isConnected(tree2)) {
				int x = vx * 2;
				int y = vy * 2;

				int nx = vnx * 2;
				int ny = vny * 2;

				if (x != nx) {
					labyrinth.setMur(x + 1, y);
				}

				if (y != ny) {
					labyrinth.setMur(x, y + 1);
				}

				tree1.connect(tree2);
			}
		}

		return labyrinth;
	}

	/**
	 * @param size une taille
	 * @return si la taille est valide ou non
	 */
	private boolean isSizeInvalid(int size) {
		return (size < 3) || (size % 2 != 1);
	}
}
