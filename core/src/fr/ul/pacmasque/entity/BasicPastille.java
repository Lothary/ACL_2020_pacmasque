/*
 * BasicPastille.java
 * ACL_2020_pacmasque
 *
 * Created by Acer on 31/10/2020.
 * Copyright © 2020 Acer. All rights reserved.
 */

package fr.ul.pacmasque.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class BasicPastille  implements Pastille {
	private final Vector2 position ;
	public BasicPastille(){


		this.position = new Vector2((float)Math.random() * 6,(float)Math.random() * 6);


	}






	public Vector2 getPosition() {
		return null;
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		Texture texture = new Texture(Gdx.files.internal("monster_3.png"));
		batch.draw(texture,this.position.x,this.position.y,1,1);
	}
}
