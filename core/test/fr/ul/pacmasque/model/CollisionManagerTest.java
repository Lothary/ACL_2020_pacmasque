/*
 * CollisionManagerTest.java
 * ACL_2020_pacmasque
 *
 * Created by nicol on 15/11/2020.
 * Copyright © 2020 nicol. All rights reserved.
 */

package fr.ul.pacmasque.model;


import fr.ul.pacmasque.PacmasqueTest;
import fr.ul.pacmasque.entity.BasicMonster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.badlogic.gdx.math.MathUtils.random;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CollisionManagerTest extends PacmasqueTest {

	private World world;
	private int width;
	private int height;
	private BasicMonster m1, m2;


	@BeforeEach
	void setUp() {
		this.width = random.nextInt(100);
		this.height = random.nextInt(100);

		Labyrinth labyrinth = new Labyrinth(width, height);
		this.world = new World(labyrinth, "test");

		m1 = new BasicMonster(4,4);
		this.world.addMonster(m1);

		m2 = new BasicMonster(2,2);
		this.world.addMonster(m2);

		world.getPlayer().setPositionX(2.0f);
		world.getPlayer().setPositionY(2.0f);
	}

	@Test
	void isCollision(){
		assertFalse(this.world.getCollisionManager().isCollision(m1));
		assertTrue(this.world.getCollisionManager().isCollision(m2));
	}


}
