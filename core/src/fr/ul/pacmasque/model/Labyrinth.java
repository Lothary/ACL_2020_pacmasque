/*
 * Labyrinth.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Labyrinth implements Object2D {

	public Labyrinth(int width, int height) {

	}

	public List<Vector2> getMursPosition() {
		List<Vector2> vectors = new ArrayList<>();

		vectors.add(new Vector2(1,1));
		vectors.add(new Vector2(2,2));
		vectors.add(new Vector2(3,3));
		vectors.add(new Vector2(4,4));
		vectors.add(new Vector2(5,5));

		return vectors;
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}
}
