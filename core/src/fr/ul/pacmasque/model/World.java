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
import fr.ul.pacmasque.entity.BasicMonster;
import fr.ul.pacmasque.entity.BasicPlayer;
import fr.ul.pacmasque.entity.Monster;
import fr.ul.pacmasque.entity.Player;

public class World implements Drawable {
	private final Labyrinth labyrinth;
	private Monster monster;
	private int width, height;
	private Player player;
	private Monster monster2;

	public World(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
		this.player = new BasicPlayer();
        this.monster=new BasicMonster();
        this.monster2=new BasicMonster();

	}

	public Player getPlayer() { return player; }
	public Monster getMonster(){ return monster;}
	public Monster getMonster2(){ return monster2;}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		this.labyrinth.draw(batch, x, y, width, height);
		this.player.draw(batch, x, y, width, height);
		this.monster.draw(batch, x, y, width, height);
		this.monster2.draw(batch, x, y, width, height);
	}
}
