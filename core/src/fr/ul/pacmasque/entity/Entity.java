/*
 * Entity.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import fr.ul.pacmasque.Drawable;


public interface Entity extends Drawable {

	/**
	 * La position de l'entité
	 * @return un vecteur position
	 */
	Vector2 getPosition();
}
