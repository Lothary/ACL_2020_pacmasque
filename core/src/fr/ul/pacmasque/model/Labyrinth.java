/*
 * Labyrinth.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.Drawable;
import fr.ul.pacmasque.exception.TextureException;
import fr.ul.pacmasque.util.TexturePackFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Un labyrinthe en deux dimensions, ayant une largeur et une hauteur
 */
public class Labyrinth implements Drawable {
	private final int width;
	private final int height;
	private final List<Vector2> positionsMurs;
	private Texture texture;

	public Labyrinth(int width, int height) {
		this.width = width;
		this.height = height;
		this.positionsMurs = new ArrayList<>();

		try {
			this.texture = TexturePackFactory.getInstance().getTexturePack("basePack").get("stone");
		} catch (TextureException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ajoute un mur à la position `x` et `y`.
	 * N'ajoute pas de mur si les coordonnées ne sont pas dans le labyrinthe
	 * @param x la coordonnée `x` du mur
	 * @param y la coordonnée `y` du mur
	 */
	public void setMur(int x, int y) {
		if (this.inRange(x, y)) {
			positionsMurs.add(new Vector2(x, y));
		}
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
	 * @return si la position est un mur, et vrai si la position n'est pas comprise dans le labyrinthe
	 */
	public boolean isWall(Vector2 vec) {
		if (this.inRange(((int) vec.x), ((int) vec.y))) {
			return positionsMurs.contains(vec);
		}

		return true;
	}

	/**
	 * Permet de savoir si les coordonnées sont valides pour le labyrinthe
	 * @param x une coordonnée `x`
	 * @param y une coordonnée `y`
	 * @return si `x` et `y` sont dans le labyrinthe
	 */
	private boolean inRange(int x, int y) {
		return x <= this.getWidth() && y <= this.getHeight() && x >= 0 && y >= 0;

	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		this.positionsMurs.forEach(pos -> batch.draw(texture, pos.x, pos.y, 1,1));
	}
}
