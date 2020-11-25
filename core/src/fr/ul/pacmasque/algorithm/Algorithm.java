/*
 * Algorithm.java
 * ACL_2020_pacmasque
 *
 * Created by nicol on 17/11/2020.
 * Copyright © 2020 nicol. All rights reserved.
 */

package fr.ul.pacmasque.algorithm;

public abstract class Algorithm {

	/**
	 * Signal the algorithm to compute the next step, and move the associated entity by one unit
	 */
	public abstract void tick();
}
