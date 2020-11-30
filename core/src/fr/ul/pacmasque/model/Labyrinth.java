/*
 * Labyrinth.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.Drawable;
import fr.ul.pacmasque.exception.TextureException;
import fr.ul.pacmasque.util.TexturePack;
import fr.ul.pacmasque.util.TexturePackFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Un labyrinthe en deux dimensions, ayant une largeur et une hauteur
 */
public class Labyrinth implements Drawable {

	/**
	 * Largeur du labyrinthe
	 */
	private final int width;

	/**
	 * Hauteur du labyrinthe
	 */
	private final int height;

	/**
	 * Position des murs du labyrinthe
	 */
	@NotNull private final List<Vector2> positionsMurs;

	/**
	 * Texture des murs du labyrinthe
	 */
	private Texture wallTexture;

	/**
	 * Position de départ du joueur
	 */
	private final Vector2 positionDepart;

	/**
	 * Nombre de monstre présent dans le labyrinthe
	 */
	private int nombreMonster;

	private Texture backgroundTexture;
	/**
	 * Crée un labyrinthe vide de dimensions données
	 * @param width la largeur du labyrinthe
	 * @param height la hauteur du labyrinthe
	 * @apiNote la taille du labyrinthe ne peut être inférieure à 3x3
	 */
	public Labyrinth(
			@Range(from = 3, to = Integer.MAX_VALUE) int width,
			@Range(from = 3, to = Integer.MAX_VALUE) int height) {


		//noinspection ConstantConditions
		if (width < 3 || height < 3) {
			throw new IllegalArgumentException("Width and height should be more or equal than 3");
		}

		this.positionDepart = new Vector2(0,0);
		this.width = width;
		this.height = height;
		this.positionsMurs = new ArrayList<>();
		this.nombreMonster = 3;


		// TODO: - Sélectionner un autre texturepack
		TexturePack texturePack = TexturePackFactory.getInstance().getTexturePack("basepack");
		this.loadTextures(texturePack);
	}



	/**
	 * @return La position de départ du Joueur
	 */
	public Vector2 getPositionDepart(){ return this.positionDepart; }


	/**
	 * Charge les textures utilisée par le labyrinthe
	 */
	private void loadTextures(@Nullable TexturePack texturePack) {
		if (texturePack == null) {
			loadFallbackTexture("all");
			return;
		}

		this.backgroundTexture = TexturePack.getFallbackTexture(Color.BLACK);

		try {
			this.wallTexture = texturePack.get(TexturePack.typeTexture.stone);
		} catch (TextureException e) {
			loadFallbackTexture("stone");
		}
	}

	/**
	 * Charge les textures de fallback en cas d'échec du chargement classique
	 * @param textureName la texture qui n'a pas été chargé
	 * @implNote dernier appel, ne doit pas échouer!
	 */
	private void loadFallbackTexture(String textureName) {

		switch (textureName) {
			case "stone":
				this.wallTexture = TexturePack.getFallbackTexture(null);
				break;
			case "background":
				this.backgroundTexture = TexturePack.getFallbackTexture(null);
				break;
			case "all":
				loadFallbackTexture("stone");
				loadFallbackTexture("background");
			default:
				break;
		}
	}

	/**
	 * @return les murs du labyrinthe
	 */
	@NotNull
	public List<Vector2> getWalls() {
		return positionsMurs;
	}

	/**
	 * Ajoute un mur à la position `x` et `y`.
	 * N'ajoute pas de mur si les coordonnées ne sont pas dans le labyrinthe
	 * @param x la coordonnée `x` du mur
	 * @param y la coordonnée `y` du mur
	 */
	public void setMur(int x, int y) {
		if (this.inRange(x, y)) {
			Vector2 vector2 = new Vector2(x, y);
			if (this.positionsMurs.contains(vector2))
				this.positionsMurs.remove(vector2);
			else
				positionsMurs.add(vector2);
		}
	}

	/**
	 * @return la largeur du labyrinthe
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * @return la hauteur du labyrinthe
	 */
	public int getHeight() {
		return this.height;
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

	public boolean isWall(int x, int y) {
		return isWall(new Vector2(x, y));
	}

	/**
	 * Permet de savoir si les coordonnées sont valides pour le labyrinthe
	 * @param x une coordonnée `x`
	 * @param y une coordonnée `y`
	 * @return si `x` et `y` sont dans le labyrinthe
	 */
	private boolean inRange(int x, int y) {
		return x < this.getWidth() && y < this.getHeight() && x >= 0 && y >= 0;

	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		batch.draw(this.backgroundTexture, 0, 0, getWidth(), getHeight());
		this.positionsMurs.forEach(pos -> batch.draw(wallTexture, pos.x, pos.y, 1,1));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < width; i++) {
			builder.append("-");
		}

		builder.append("\n");

		for (int x = 0; x < width; x++) {
			builder.append("|");
			for (int y = 0; y < height; y++) {
				builder.append(isWall(x, y) ? "x" : "o");
			}
			builder.append("|\n");
		}
		return builder.toString();
	}
}
