/*
 * CollisionManager.java
 * ACL_2020_pacmasque
 *
 * Created by nicol on 15/11/2020.
 * Copyright © 2020 nicol. All rights reserved.
 */

package fr.ul.pacmasque.model;

import fr.ul.pacmasque.entity.Entity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Experimental
public class CollisionManager {

	@NotNull private final World world;

	public CollisionManager(@NotNull World world){
		this.world = world;
	}

	public boolean isCollision(Entity entity){
		if(world.getPlayer().getPosition().x + 0.5f >= entity.getPosition().x && world.getPlayer().getPosition().x + 0.5f <= entity.getPosition().x + 1.0f){
			return world.getPlayer().getPosition().y + 0.5f >= entity.getPosition().y && world.getPlayer().getPosition().y + 0.5f <= entity.getPosition().y + 1.0f;
		}
		return false;
	}

	public boolean isInside(Case aCase){
		if(world.getPlayer().getPosition().x + 0.5f >= aCase.getPosition().x && world.getPlayer().getPosition().x + 0.5f <= aCase.getPosition().x + 1.0f){
			return world.getPlayer().getPosition().y + 0.5f >= aCase.getPosition().y && world.getPlayer().getPosition().y + 0.5f <= aCase.getPosition().y + 1.0f;
		}
		return false;
	}

}
