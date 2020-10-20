/*
 * World.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.model;

import fr.ul.pacmasque.exception.PacMasqueExceptions;
import fr.ul.pacmasque.util.LabyrinthConstructor;

import java.io.File;

import fr.ul.pacmasque.entity.BasicPlayer;
import fr.ul.pacmasque.entity.Player;

public class World {
	private LabyrinthConstructor labyrinthConstructor;

	public World(File fileBuilder){
		labyrinthConstructor = new LabyrinthConstructor(fileBuilder);
		try {
			labyrinthConstructor.build(0);
		} catch (PacMasqueExceptions pacMasqueExceptions) {
			pacMasqueExceptions.printStackTrace();
		}
		this.player = new BasicPlayer();
	}

	private int width, height;
	private Player player;

	public Player getPlayer() { return player; }




}
