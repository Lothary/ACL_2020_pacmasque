/*
 * Monster.java
 * ACL_2020_pacmasque
 *
 * Created by Acer on 27/10/2020.
 * Copyright © 2020 Acer. All rights reserved.
 */

package fr.ul.pacmasque.entity;

import fr.ul.pacmasque.algorithm.Algorithm;

public interface Monster extends Entity{

	/**
	 * @implNote considérer la factorisation de l'algorithme à l'entité, afin d'en faire profiter d'hypothétiques autres entités
	 */
	Algorithm getAlgorithm();

	void setAlgorithm(Algorithm algorithm);

	void setNextPositionX(float x);
	void setNextPositionY(float y);

	float getNextPositionX();
	float getNextPositionY();

	void addMouvement(int direction, int number);

	boolean isMoving();

	void setPlayerIsMagic(boolean b);
}
