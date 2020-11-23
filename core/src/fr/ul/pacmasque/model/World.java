/*
 * World.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.model;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.Drawable;
import fr.ul.pacmasque.algorithm.AlgorithmRandom;
import fr.ul.pacmasque.entity.*;
import fr.ul.pacmasque.entity.BasicPlayer;

import java.util.ArrayList;
import java.util.List;

public class World implements Drawable {

	public Labyrinth getLabyrinth() {
		return labyrinth;
	}

	private final Labyrinth labyrinth;
	private CollisionManager collisionManager;

	private final Player player;
	private final List<Pastille> pastilles;
	private final List<Monster> monsters;

	public World(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
		this.collisionManager = new CollisionManager(this);

		this.player = new BasicPlayer(2, 2);
		this.pastilles = new ArrayList<>();
		this.monsters = new ArrayList<>();

		BasicMonster m1 = new BasicMonster(3,3);
		m1.setAlgorithm(new AlgorithmRandom(this, m1));
		this.addMonster(m1);


	}

	public void addMonster(Monster monster){
		monsters.add(monster);
	}
	public void addPastille(Pastille pastille){
		pastilles.add(pastille);
	}

	public int getWidth() {
		return this.labyrinth.getWidth();
	}

	public int getHeight() {
		return this.labyrinth.getHeight();
	}

	public CollisionManager getCollisionManager(){return this.collisionManager; }

	public Player getPlayer() {
		return player;
	}


	public void updateCollision(){
		boolean collision = false;

		//Collision avec les monstres
		for(Monster e : this.monsters){
			collision = this.collisionManager.isCollision(e);
			if(collision){
				// System.out.println("collision");
				// Ici mettre le résultat de la collision entre le player et l'entité
				// Mort si monstre, Points si pastille ?
			}
		}

		//Collision avec les pastilles
		for(Pastille e : this.pastilles){
			collision = this.collisionManager.isCollision(e);
			if(collision){
				if(e.isVisible())
					e.setVisible(false);
			}
		}

	}

	public void movePlayer(int direction) {

			float moveAmount = 1.0f;
			Vector2 finalCase = new Vector2(this.player.getNextPositionX(), this.player.getNextPositionY());

			switch (direction) {
				case Input.Keys.LEFT:
					finalCase.x = this.player.getNextPositionX() - moveAmount;
					if (!this.labyrinth.isWall(finalCase) && finalCase.x >= 0.0) {
						this.player.setNextPositionX(this.player.getNextPositionX() - moveAmount);
						this.player.addMouvement(Input.Keys.LEFT, 10);
					}
					break;
				case Input.Keys.RIGHT:
					finalCase.x = this.player.getNextPositionX() + moveAmount;
					if (!this.labyrinth.isWall(finalCase) && finalCase.x < this.labyrinth.getWidth()) {
						this.player.setNextPositionX(this.player.getNextPositionX() + moveAmount);
						this.player.addMouvement(Input.Keys.RIGHT, 10);
					}
					break;
				case Input.Keys.UP:
					finalCase.y = this.player.getNextPositionY() + moveAmount;
					if (!this.labyrinth.isWall(finalCase) && finalCase.y < this.labyrinth.getHeight()) {
						this.player.setNextPositionY(this.player.getNextPositionY() + moveAmount);
						this.player.addMouvement(Input.Keys.UP, 10);
					}
					break;
				case Input.Keys.DOWN:
					finalCase.y = this.player.getNextPositionY() - moveAmount;
					if (!this.labyrinth.isWall(finalCase) && finalCase.y >= 0.0) {
						this.player.setNextPositionY(this.player.getNextPositionY() - moveAmount);
						this.player.addMouvement(Input.Keys.DOWN, 10);
					}
					break;
			}
		}



	public void moveMonsters(){
		for(Monster m : this.monsters){
			m.getAlgorithm().tick();
		}
	}


	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		this.labyrinth.draw(batch, x, y, width, height);
		this.player.draw(batch, x, y, width, height);

		this.monsters.forEach(en -> en.draw(batch, x, y, width, height));
		for(Pastille p : this.pastilles) {
			if(p.isVisible())
				p.draw(batch, x, y, width, height);
		}
		//this.pastilles.forEach(en -> en.draw(batch, x, y, width, height));
	}
}
