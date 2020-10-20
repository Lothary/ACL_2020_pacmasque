/*
 * Labyrinth.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.model;

import com.badlogic.gdx.math.Vector2;
import sun.java2d.loops.ProcessPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Labyrinth implements Object2D {

	private int width;
	private int height;
	private int[][] vertices;

	public Labyrinth(int width, int height) {
		this.width = width;
		this.height = height;
		build();
	}

	private void build() {
		vertices = new int[this.getWidth()][this.getHeight()];

		Random random = new Random(System.currentTimeMillis());
		double ratio = 0.4;
		for (int i = 0; i < (this.getHeight() * this.getWidth()) * ratio; i++) {
			vertices[random.nextInt(this.getWidth())][random.nextInt(this.getHeight())] = 1;
		}
	}

	public List<Vector2> getMursPosition() {
		List<Vector2> vectors = new ArrayList<>();

		for (int x = 0; x < this.getWidth(); x++) {
			for (int y = 0; y < this.getHeight(); y++) {
				if (vertices[x][y] == 1)
					vectors.add(new Vector2(x,y));
			}
		}

		for (int x = 0; x < this.getWidth(); x++) {
			vectors.add(new Vector2(x,0));
			vectors.add(new Vector2(x,this.getHeight() - 1));
		}

		for (int y = 0; y < this.getHeight(); y++) {
			vectors.add(new Vector2(0,y));
			vectors.add(new Vector2(this.getWidth() - 1, y));
		}

		return vectors;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}
}
