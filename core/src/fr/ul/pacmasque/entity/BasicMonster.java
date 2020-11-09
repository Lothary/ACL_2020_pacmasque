/*
 * basicmonster.java
 * ACL_2020_pacmasque
 *
 * Created by Acer on 27/10/2020.
 * Copyright © 2020 Acer. All rights reserved.
 */

package fr.ul.pacmasque.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class BasicMonster implements Monster {
	private final Vector2 position;
	private Texture texture;

	public BasicMonster(int x, int y) {

		this.position = new Vector2(x,y);
		texture = new Texture(Gdx.files.internal("monster_3.png"));
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {

		batch.draw(texture,this.position.x,this.position.y,1,1);
	}

	@Override
	public Vector2 getPosition() {
		return position;
	}
}
