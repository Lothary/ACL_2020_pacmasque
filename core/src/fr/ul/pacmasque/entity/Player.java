/*
 * Player.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.entity;

/**
 * Représente un personnage, jouable par le joueur
 */
public interface Player extends Entity {

	void setPositionX(float x);
	void setPositionY(float y);
}
