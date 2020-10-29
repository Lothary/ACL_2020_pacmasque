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

import static com.badlogic.gdx.math.MathUtils.random;

// Je pense que tu n'a pas ajouté la classe BasicMonster et l'interface Monster au projet git
// Il faut que tu fasse un clic-droit sur tes classes, git -> ajouter (ou add)
// c bon
// Ok, donc maintenant tu refait un commit et un push
public class BasicMonster implements Monster {

	public BasicMonster(){

		this.position.x=(float)Math.random() * 5;
		this.position.y=(float)Math.random() * 5;

	}
	public float getPositionX(){return this.position.x;}
	public float getPositionY(){return this.position.y;}

	public void setPositionX(int pos){this.position.x = pos;}
	public void setPositionY(int pos){this.position.y = pos;}

	public void draw(Batch batch, float x, float y, float width, float height) {
		Texture texture = new Texture(Gdx.files.internal("monster_3.png"));
		batch.draw(texture,this.position.x,this.position.y,1,1);
	}



}
