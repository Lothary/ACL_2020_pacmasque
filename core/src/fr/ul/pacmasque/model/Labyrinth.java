/*
 * Labyrinth.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.Drawable;

import java.util.ArrayList;

public class Labyrinth implements Drawable {
	private int x;
	private int y;
	private ArrayList<Vector2> positionsMurs;

	private Texture texture = new Texture(Gdx.files.internal("packs/basepack/stone_1.png"));

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

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {

		this.positionsMurs.forEach(pos -> batch.draw(texture,pos.x,pos.y, 1,1));
	}
}
