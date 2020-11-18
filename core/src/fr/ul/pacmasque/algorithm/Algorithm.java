/*
 * Algorithm.java
 * ACL_2020_pacmasque
 *
 * Created by nicol on 17/11/2020.
 * Copyright © 2020 nicol. All rights reserved.
 */

package fr.ul.pacmasque.algorithm;

import fr.ul.pacmasque.entity.Monster;
import fr.ul.pacmasque.model.World;

public abstract class Algorithm {

	private World world;
	private Monster monster;

	public Algorithm(World world, Monster monster){}

	public void tick(){}
}