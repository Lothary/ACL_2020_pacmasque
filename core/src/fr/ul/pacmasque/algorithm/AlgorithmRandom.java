/*
 * AlgorithmRandom.java
 * ACL_2020_pacmasque
 *
 * Created by nicol on 17/11/2020.
 * Copyright © 2020 nicol. All rights reserved.
 */

package fr.ul.pacmasque.algorithm;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.entity.Monster;
import fr.ul.pacmasque.model.World;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class AlgorithmRandom extends Algorithm {

	/**
	 * The world on which the algorithm will be based
	 */
	@NotNull private final World world;

	/**
	 * The monster driven by the algorithm
	 * @implNote factoriser l'algorithme à l'entité
	 */
	@NotNull private final Monster monster;

	/**
	 * Create an algorithm for a monster, moved in a world
	 * @param world the world containing the entity
	 * @param monster the entity moved by the algorithm
	 */
	public AlgorithmRandom(@NotNull World world, @NotNull Monster monster) {
		this.world = world;
		this.monster = monster;
	}

	/**
	 * {@inheritDoc}
	 */
	public void tick() {

		if(!this.monster.isMoving()) {

			Random r = new Random();
			//0 : LEFT, 1 : RIGHT, 2 : UP, 3 : DOWN
			int direction = r.nextInt(4);

			float moveAmount = 1.0f;
			Vector2 finalCase = new Vector2(this.monster.getNextPositionX(), this.monster.getNextPositionY());

			switch (direction) {
				case 0:
					finalCase.x = this.monster.getNextPositionX() - moveAmount;
					if (!this.world.getLabyrinth().isWall(finalCase) && finalCase.x >= 0.0) {
						this.monster.setNextPositionX(this.monster.getNextPositionX() - moveAmount);
						this.monster.addMouvement(Input.Keys.LEFT, 10);
					}
					break;
				case 1:
					finalCase.x = this.monster.getNextPositionX() + moveAmount;
					if (!this.world.getLabyrinth().isWall(finalCase) && finalCase.x < this.world.getLabyrinth().getWidth()) {
						this.monster.setNextPositionX(this.monster.getNextPositionX() + moveAmount);
						this.monster.addMouvement(Input.Keys.RIGHT, 10);
					}
					break;
				case 2:
					finalCase.y = this.monster.getNextPositionY() + moveAmount;
					if (!this.world.getLabyrinth().isWall(finalCase) && finalCase.y < this.world.getLabyrinth().getHeight()) {
						this.monster.setNextPositionY(this.monster.getNextPositionY() + moveAmount);
						this.monster.addMouvement(Input.Keys.UP, 10);
					}
					break;
				case 3:
					finalCase.y = this.monster.getNextPositionY() - moveAmount;
					if (!this.world.getLabyrinth().isWall(finalCase) && finalCase.y >= 0.0) {
						this.monster.setNextPositionY(this.monster.getNextPositionY() - moveAmount);
						this.monster.addMouvement(Input.Keys.DOWN, 10);
					}
					break;
			}
		}
	}
}
