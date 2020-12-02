/*
 * basicmonster.java
 * ACL_2020_pacmasque
 *
 * Created by Acer on 27/10/2020.
 * Copyright © 2020 Acer. All rights reserved.
 */

package fr.ul.pacmasque.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.algorithm.Algorithm;
import fr.ul.pacmasque.exception.TextureException;
import fr.ul.pacmasque.util.TexturePack;
import fr.ul.pacmasque.util.TexturePackFactory;

import java.util.ArrayList;
import java.util.List;

public class BasicMonster implements Monster {

	private final Vector2 position;

	/**
	 * Texture sans que le player soit magique.
	 */
	private Texture normalTexture;

	/**
	 * Texture quand le player est magique.
	 */
	private Texture magicTexture;
	private Algorithm algorithm;
	private final List<Integer> movesList;
	private final Vector2 nextPosition;

	/**
	 * Booléen qui indique si le player est magique ou pas.
	 */
	private boolean playerIsMagic;

	private boolean visible;

	public BasicMonster(int x, int y) {
		this.position = new Vector2(x,y);
		this.nextPosition = new Vector2(x,y);
		this.movesList = new ArrayList<>();

		try {
			// TODO: ajouter une "texture" de fallback en cas de problème de chargement de la texture choisie
			TexturePack pack = TexturePackFactory.getInstance().getTexturePack("secondpack");
			this.normalTexture = pack.get(TexturePack.TypeTexture.monster);
			this.magicTexture = pack.get(TexturePack.TypeTexture.monster);
			this.playerIsMagic = false;
		} catch (TextureException e) {
			e.printStackTrace();
		}

		visible = true;
	}

	/**
	 * Applique un micro-mouvement à la position réelle du monstre
	 */
	private void updateMovement() {
		// TODO: factoriser! duplication de code
		//Si la liste des mouvements n'est pas vide, ajoute ce mouvement à la position du monstre
		if(!this.movesList.isEmpty()){

			int dir = this.movesList.get(0);
			this.movesList.remove(0);
			switch (dir){
				case Input.Keys.UP:
					this.position.y += 0.1f;
					break;
				case Input.Keys.DOWN:
					this.position.y -= 0.1f;
					break;
				case Input.Keys.RIGHT:
					this.position.x += 0.1f;
					break;
				case Input.Keys.LEFT:
					this.position.x -= 0.1f;
					break;
			}
		}
	}

	/**
	 * Dessine le monstre, la texture est différente selon si le player
	 * est sous l'effet magique ou pas.
	 *
	 * @param batch le batch dans lequel se dessiner
	 * @param x la coordonnée x du dessin
	 * @param y la coordonnée y du dessin
	 * @param width la largeur du dessin
	 * @param height la hauteur du dessin
	 */
	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		if (playerIsMagic) {
			batch.draw(magicTexture, this.position.x, this.position.y, 1, 1);
		}
		else{
			batch.draw(normalTexture, this.position.x, this.position.y, 1, 1);
		}
		updateMovement();
	}

	@Override
	public Vector2 getPosition() {
		return position;
	}

	@Override
	public Algorithm getAlgorithm() {
		return algorithm;
	}

	@Override
	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	public float getNextPositionX(){return this.nextPosition.x;}
	public float getNextPositionY(){return this.nextPosition.y;}

	public void setNextPositionX(float pos){ this.nextPosition.x = pos; }
	public void setNextPositionY(float pos){ this.nextPosition.y = pos; }

	/**
	 * Ajoute des micro-mouvement à la liste des prochains mouvements du Monstre
	 * @param dir la direction du mouvement
	 * @param x le nombre de micro-mouvement ajouté
	 */
	@Override
	//Ajoute le mouvement dir, x fois
	public void addMouvement(int dir, int x){
		for(int i = 1 ; i <= x ; i++)
			this.movesList.add(dir);
	}

	/**
	 * @return true si le Monstre est entrain de se déplacer
	 */
	@Override
	public boolean isMoving() {
		return !this.movesList.isEmpty();
	}

	@Override
	public boolean isVisible() { return this.visible; }
	@Override
	public void setVisible(boolean visible){this.visible = visible;}

	/**
	 * Renseigne si le player est actuellement sous un effet magique,
	 * afin de modifier la texture choisie.
	 *
	 * @param b vrai si le player est magique.
	 */
	@Override
	public void setPlayerIsMagic(boolean b) {
		this.playerIsMagic = b;
	}
}
