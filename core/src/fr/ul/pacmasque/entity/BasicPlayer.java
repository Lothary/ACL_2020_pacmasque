/*
 * BasicPlayer.java
 * ACL-2020-pacmasque
 *
 * Created by nicol on 18/10/2020.
 * Copyright © 2020 nicol. All rights reserved.
 */

package fr.ul.pacmasque.entity;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class BasicPlayer implements Player{


	private int lifePoints;

	public BasicPlayer(){
		this.lifePoints = 3;
		this.position.x = 3;
		this.position.y = 3;
	}


	public void move(int direction){
		float moveAmount = 1.0f;
		switch(direction) {
			case Input.Keys.LEFT:
				this.position.x -= moveAmount;
				break;
			case Input.Keys.RIGHT:
				this.position.x += moveAmount;
				break;
			case Input.Keys.UP:
				this.position.y += moveAmount;
				break;
			case Input.Keys.DOWN:
				this.position.y -= moveAmount;
				break;
		}


	}

	public float getPositionX(){return this.position.x;}
	public float getPositionY(){return this.position.y;}

	public void setPositionX(int pos){this.position.x = pos;}
	public void setPositionY(int pos){this.position.y = pos;}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));
		batch.draw(texture,this.position.x,this.position.y, 1,1);
	}
}
