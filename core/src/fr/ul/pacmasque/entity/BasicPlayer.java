/*
 * BasicPlayer.java
 * ACL-2020-pacmasque
 *
 * Created by nicol on 18/10/2020.
 * Copyright © 2020 nicol. All rights reserved.
 */

package fr.ul.pacmasque.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.exception.TextureException;
import fr.ul.pacmasque.util.TexturePackFactory;

import java.util.ArrayList;
import java.util.List;

public class BasicPlayer implements Player{

	private final Vector2 position;

	/**
	 * Future feature, to be implemented!
	 * @apiNote unused
	 */
	private int lifePoints;
	private final Vector2 nextPosition;
	private Texture texture;

	private final List<Integer> movesList;

	public BasicPlayer(int x, int y){
		this.lifePoints = 3;
		this.position = new Vector2(x,y);
		this.nextPosition = new Vector2(x,y);
		movesList = new ArrayList<>();

		try {
			this.texture = TexturePackFactory.getInstance().getTexturePack("basepack").get("pacman");
		} catch (TextureException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return la position virtuelle en X du Player
	 */
	public float getNextPositionX(){return this.nextPosition.x;}
	/**
	 * @return la position virtuelle en Y du Player
	 */
	public float getNextPositionY(){return this.nextPosition.y;}

	public void setNextPositionX(float pos){ this.nextPosition.x = pos; }
	public void setNextPositionY(float pos){ this.nextPosition.y = pos; }

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		batch.draw(texture, this.position.x, this.position.y, 1, 1);
		updateMovement();
	}

	/**
	 * Applique un micro-mouvement à la position réelle du personnage
	 */
	private void updateMovement(){
		//Si la liste des mouvements n'est pas vide, ajoute ce mouvement à la position du personnage
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
	 * @return la position réelle du Player
	 */
	@Override
	public Vector2 getPosition() {
		return this.position;
	}

	public void setPositionX(float pos){ this.position.x = pos; }
	public void setPositionY(float pos){ this.position.y = pos; }

	/**
	 * Ajoute des micro-mouvement à la liste des prochains mouvements du Player
	 * @param dir la direction du mouvement
	 * @param x le nombre de micro-mouvement ajouté
	 */
	public void addMouvement(int dir, int x){
		for(int i = 1 ; i <= x ; i++)
			this.movesList.add(dir);
	}

	/**
	 * Supprime tous les micro-mouvements que devait effectuer le Player
	 */
	public void deleteMouvements(){
		this.movesList.clear();
	}

}
