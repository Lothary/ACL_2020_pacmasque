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
import java.util.List;

/**
 * Un labyrinthe en deux dimensions, ayant une largeur et une hauteur
 */
public class Labyrinth implements Drawable {
	private final int width;
	private final int height;
	private final List<Vector2> positionsMurs;

	private Texture texture = new Texture(Gdx.files.internal("packs/basepack/stone_1.png"));

	public Labyrinth(int width, int height) {
		this.width = width;
		this.height = height;
		this.positionsMurs = new ArrayList<>();
	}


	/**
	 * Ajoute un mur à la position `x` et `y`
	 * @param x la coordonnée `x` du mur
	 * @param y la coordonnée `y` du mur
	 */
	public void setMur(int x, int y){
		positionsMurs.add(new Vector2(x, y));
	}

	/**
	 * @return la largeur du labyrinthe
	 */
	public int getWidth() {
		return width;
	}


	/**
	 * @return la hauteur du labyrinthe
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Indique si la position donnée est un mur dans le labyrinthe
	 * @param vec un vecteur position
	 * @return si la position est un mur
	 */
	public boolean isWall(Vector2 vec){
		return positionsMurs.contains(vec);
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {

		this.positionsMurs.forEach(pos -> batch.draw(texture,pos.x,pos.y, 1,1));
	}
}
