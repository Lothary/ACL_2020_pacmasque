/*
 * World.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import fr.ul.pacmasque.Drawable;
import fr.ul.pacmasque.entity.BasicPlayer;
import fr.ul.pacmasque.entity.Player;

public class World implements Drawable {
	private final Labyrinth labyrinth;

	public World(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
		this.player = new BasicPlayer();
	}

	private int width, height;
	private Player player;

	public Player getPlayer() { return player; }

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		this.labyrinth.draw(batch, x, y, width, height);
		this.player.draw(batch, x, y, width, height);
	}
}
