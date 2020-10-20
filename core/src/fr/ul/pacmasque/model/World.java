/*
 * World.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.model;

import fr.ul.pacmasque.entity.BasicPlayer;
import fr.ul.pacmasque.entity.Player;

public class World {

	private int width, height;
	private Player player;

	public World(){
		this.player = new BasicPlayer();
	}

	public Player getPlayer() { return player; }




}
