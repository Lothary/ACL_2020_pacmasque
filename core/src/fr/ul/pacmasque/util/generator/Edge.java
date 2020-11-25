/*
 * Edge.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 18/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.generator;

import org.jetbrains.annotations.NotNull;

public class Edge implements Comparable<Edge> {

	enum Direction {
		up,
		down,
		left,
		right
	}

	private int x;
	private int y;
	@NotNull private Direction direction;

	public Edge(int x, int y, @NotNull Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

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
