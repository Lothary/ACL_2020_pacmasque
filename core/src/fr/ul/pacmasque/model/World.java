/*
 * World.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.model;

import fr.ul.pacmasque.exception.LabyrinthLoaderException;
import fr.ul.pacmasque.exception.PacmasqueException;
import fr.ul.pacmasque.util.LabyrinthConstructor;

import java.io.File;

import fr.ul.pacmasque.entity.BasicPlayer;
import fr.ul.pacmasque.entity.Player;
import fr.ul.pacmasque.util.LabyrinthLoader;

public class World {
	private LabyrinthConstructor labyrinthConstructor;

	public World(File fileBuilder){
		try {
			LabyrinthLoader shared = LabyrinthLoader.shared();
			shared.loadFile(fileBuilder.getPath());
			System.out.println(shared.builderClass());
		} catch (LabyrinthLoaderException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		labyrinthConstructor = new LabyrinthConstructor(fileBuilder);
		try {
			labyrinthConstructor.build(0);
		} catch (PacmasqueException pacmasqueException) {
			pacmasqueException.printStackTrace();
		}
		this.player = new BasicPlayer();
	}

	private int width, height;
	private Player player;

	public Player getPlayer() { return player; }




}
