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
import fr.ul.pacmasque.util.TexturePackFactory;

import java.util.ArrayList;
import java.util.List;

public class BasicMonster implements Monster {

	private final Vector2 position;
	private Texture texture;
	private Algorithm algorithm;

	private final List<Integer> movesList;
	private final Vector2 nextPosition;

	public BasicMonster(int x, int y) {
		this.position = new Vector2(x,y);
		this.nextPosition = new Vector2(x,y);
		movesList = new ArrayList<>();

		try {
			// TODO: ajouter une "texture" de fallback en cas de problème de chargement de la texture choisie
			this.texture = TexturePackFactory.getInstance().getTexturePack("secondpack").get("monster");
		} catch (TextureException e) {
			e.printStackTrace();
		}
	}


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
	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		batch.draw(texture,this.position.x,this.position.y,1,1);
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

	@Override
	//Ajoute le mouvement dir, x fois
	public void addMouvement(int dir, int x){
		for(int i = 1 ; i <= x ; i++)
			this.movesList.add(dir);
	}

	@Override
	public boolean isMoving() {
		return !this.movesList.isEmpty();
	}
}
