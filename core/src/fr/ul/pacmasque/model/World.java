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
import fr.ul.pacmasque.entity.Player;
import fr.ul.pacmasque.entity.BasicPlayer;

public class World implements Drawable {

	private final Labyrinth labyrinth;

	private final Player player;

	public World(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
		this.player = new BasicPlayer();
	}

	public int getWidth() {
		return this.labyrinth.getWidth();
	}

	public int getHeight() {
		return this.labyrinth.getHeight();
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


	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		this.labyrinth.draw(batch, x, y, width, height);
		this.player.draw(batch, x, y, width, height);


	}
}
