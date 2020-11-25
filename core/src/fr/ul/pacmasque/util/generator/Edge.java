/*
 * Edge.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 18/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.generator;

import org.jetbrains.annotations.NotNull;

/**
 * Une arête entre deux points, représentée par une origine et une direction
 */
public class Edge implements Comparable<Edge> {

	/**
	 * Direction de l'arête
	 */
	enum Direction {
		up,
		right
	}

	/**
	 * Origine x de l'arête
	 */
	private final int x;

	/**
	 * Origine y de l'arête
	 */
	private final int y;

	/**
	 * Direction de l'arête
	 */
	@NotNull private final Direction direction;

	/**
	 * Crée une arête
	 * @param x une coordonnée x
	 * @param y une coordonnée y
	 * @param direction une direction
	 */
	public Edge(int x, int y, @NotNull Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	/**
	 * @return la coordonnée x de l'arête
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return la coordonnée y de l'arête
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return la direction de l'arête
	 */
	public @NotNull Direction getDirection() {
		return direction;
	}

	@Override
	public int compareTo(@NotNull Edge o) {
		return 0;
	}

	@Override
	public String toString() {
		return "Edge{" +
				"x=" + x +
				", y=" + y +
				", direction=" + direction +
				'}';
	}
}
