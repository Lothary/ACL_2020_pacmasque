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
import fr.ul.pacmasque.entity.BasicPlayer;
import fr.ul.pacmasque.entity.Entity;
import fr.ul.pacmasque.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class World implements Drawable {

	private final Labyrinth labyrinth;

	private final Player player;
	private final List<Entity> entities;

	public World(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
		this.player = new BasicPlayer(2, 2);
		this.entities = new ArrayList<>();
		this.entities.add(this.player);
	}

	public int getWidth() {
		return this.labyrinth.getWidth();
	}

	public int getHeight() {
		return this.labyrinth.getHeight();
	}

	public void movePlayer(int direction) {
		float moveAmount = 1.0f;
		Vector2 finalCase = new Vector2(this.player.getPositionX(), this.player.getPositionY());

		switch(direction) {
			case Input.Keys.LEFT:
				finalCase.x = this.player.getPositionX() - moveAmount;
				if(!this.labyrinth.isWall(finalCase) && finalCase.x >= 0.0)
					this.player.setPositionX(this.player.getPositionX() - moveAmount);
				break;
			case Input.Keys.RIGHT:
				finalCase.x = this.player.getPositionX() + moveAmount;
				if(!this.labyrinth.isWall(finalCase) && finalCase.x < this.labyrinth.getWidth())
					this.player.setPositionX(this.player.getPositionX() + moveAmount);
				break;
			case Input.Keys.UP:
				finalCase.y = this.player.getPositionY() + moveAmount;
				if(!this.labyrinth.isWall(finalCase) && finalCase.y < this.labyrinth.getHeight())
					this.player.setPositionY(this.player.getPositionY() + moveAmount);
				break;
			case Input.Keys.DOWN:
				finalCase.y = this.player.getPositionY() - moveAmount;
				if(!this.labyrinth.isWall(finalCase) && finalCase.y >= 0.0)
					this.player.setPositionY(this.player.getPositionY() - moveAmount);
				break;
		}
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		this.labyrinth.draw(batch, x, y, width, height);
		this.entities.forEach(en -> en.draw(batch, x, y, width, height));
	}
}
