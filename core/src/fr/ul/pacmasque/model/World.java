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
import fr.ul.pacmasque.entity.*;

public class World implements Drawable {
	private final Labyrinth labyrinth;
	private final Monster monster;
	private int width, height;
	private final Player player;
	private final Monster monster2;
	private final Pastille pastille;

	public World(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
		this.player = new BasicPlayer();
		this.monster = new BasicMonster(1,1);
		this.monster2 = new BasicMonster(2, 2);
		this.pastille = new BasicPastille(3,3);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		this.labyrinth.draw(batch, x, y, width, height);
		this.player.draw(batch, x, y, width, height);
		this.monster.draw(batch, x, y, width, height);
		this.monster2.draw(batch, x, y, width, height);
		this.pastille.draw(batch, x, y, width, height);
	}
}
