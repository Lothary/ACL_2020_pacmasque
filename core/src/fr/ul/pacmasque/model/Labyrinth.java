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

public class Labyrinth {
	private int x;
	private int y;
	private ArrayList<Vector2> positionsMurs;

	public Labyrinth(int x, int y){
		this.x = x;
		this.y = y;
		this.positionsMurs = new ArrayList<>();
	}

	public void setMur(int x, int y){
		positionsMurs.add(new Vector2(x, y));
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
