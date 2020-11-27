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
import fr.ul.pacmasque.exception.LabyrinthException;
import fr.ul.pacmasque.exception.TextureException;
import fr.ul.pacmasque.util.TexturePack;
import fr.ul.pacmasque.util.TexturePackFactory;
import org.jetbrains.annotations.Range;

import java.util.*;

/**
 * Un labyrinthe en deux dimensions, ayant une largeur et une hauteur
 */
public class Labyrinth implements Drawable {
	public enum typeCase {
		treasure,
		trap,
		magic,
		teleportation
	}
	private final int width;
	private final int height;

	private final List<Vector2> positionsMurs;
	/**
	 * Utilisée notamment par le draw.
	 */
	private final Map<typeCase, List<Case>> cases;
	/**
	 * Utilisée par le monde pour vérifier si le player est dans une des cases spéciales.
	 */
	private final Map<typeCase, List<Vector2>> casesCoordinates;
	private Texture textureWall;

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

		this.width = width;
		this.height = height;
		this.positionsMurs = new ArrayList<>();
		this.cases = new HashMap<>();
		this.casesCoordinates = new HashMap<>();

		try {
			this.textureWall = Objects.requireNonNull(TexturePackFactory.getInstance().getTexturePack("basepack")).get(TexturePack.typeTexture.stone);
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
			Vector2 vector2 = new Vector2(x, y);
			if (this.positionsMurs.contains(vector2))
				this.positionsMurs.remove(vector2);
			else
				positionsMurs.add(vector2);
		}
	}

	public void createCase(typeCase type, int x, int y){ //todo : initialize cases in generator ?
		if (this.inRange(x, y)){
			Case newCase;
			switch (type){
				case treasure:
					newCase = new TreasureCase(x, y);
					break;
				case magic:
					newCase = new MagicCase(x, y);
					break;
				case teleportation:
					newCase = new TeleportationCase(x, y);
					break;
				default:
					newCase = new TrapCase(x, y);
					break;
			}
			List<Case> casesList = cases.get(type);
			List<Vector2> coordsList = casesCoordinates.get(type);
			if ((casesList == null) || (coordsList == null)) {
				casesList = Collections.singletonList(newCase);
				coordsList = Collections.singletonList(new Vector2(x, y));
			}
			else{
				casesList.add(newCase);
				coordsList.add(new Vector2(x, y));
			}

			this.cases.put(type, casesList);
			this.casesCoordinates.put(type, coordsList);
		}
	}

	public Map<typeCase, List<Vector2>> getCasesCoordinates() {
		return this.casesCoordinates;
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

	public List<Vector2> getWalls() {
		return positionsMurs;
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
		return x < this.getWidth() && y < this.getHeight() && x >= 0 && y >= 0;

	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		// Dessin des murs
		this.positionsMurs.forEach(pos -> batch.draw(textureWall, pos.x, pos.y, 1,1));

		// Dessin des autres cases
		this.cases.forEach((type, listeCases) -> {
			try {
				for (Case c : listeCases) {
					batch.draw(c.getTexture(), c.getPosition().x, c.getPosition().y, 1, 1);
				}
			} catch (TextureException e) {
				e.printStackTrace();
			}
		});
	}

}
