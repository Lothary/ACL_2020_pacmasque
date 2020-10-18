/*
 * BasicPlayer.java
 * ACL-2020-pacmasque
 *
 * Created by nicol on 18/10/2020.
 * Copyright © 2020 nicol. All rights reserved.
 */

package fr.ul.pacmasque.entity;


import com.badlogic.gdx.Input;

public class BasicPlayer implements Player{


	private int lifePoints;

	public BasicPlayer(){
		this.lifePoints = 3;
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
				this.position.y -= moveAmount;
				break;
			case Input.Keys.DOWN:
				this.position.y += moveAmount;
				break;
		}


	}

	public float getPositionX(){return this.position.x;}
	public float getPositionY(){return this.position.y;}

	public void setPositionX(int pos){this.position.x = pos;}
	public void setPositionY(int pos){this.position.y = pos;}

	@Override
	public void draw() {

	}



}
