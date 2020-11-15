/*
 * CollisionManager.java
 * ACL_2020_pacmasque
 *
 * Created by nicol on 15/11/2020.
 * Copyright © 2020 nicol. All rights reserved.
 */

package fr.ul.pacmasque.model;

import fr.ul.pacmasque.entity.Entity;
import fr.ul.pacmasque.entity.Player;

public class CollisionManager {

	private World world;

	public CollisionManager(World world){
		this.world = world;
	}

	public boolean isCollision(Entity entity){
		if(world.getPlayer().getPosition().x + 0.5f >= entity.getPosition().x && world.getPlayer().getPosition().x + 0.5f <= entity.getPosition().x + 1.0f){
			if(world.getPlayer().getPosition().y + 0.5f >= entity.getPosition().y && world.getPlayer().getPosition().y + 0.5f <= entity.getPosition().y + 1.0f){
				return true;
			}
		}
		return false;
	}

}
