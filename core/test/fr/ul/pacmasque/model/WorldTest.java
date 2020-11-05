/*
 * WorldTest.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 4/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.model;

import fr.ul.pacmasque.PacmasqueTest;
import com.badlogic.gdx.Input;
import fr.ul.pacmasque.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest extends PacmasqueTest {

	private final Random random = new Random(System.currentTimeMillis());
	private int width;
	private int height;

	private World world;

	@BeforeEach
	void setUp() {
		this.width = random.nextInt(100);
		this.height = random.nextInt(100);

		Labyrinth labyrinth = new Labyrinth(width, height);
		this.world = new World(labyrinth);
	}

	@Test
	void getWidth() {
		assertEquals(this.width, this.world.getWidth());
	}

	@Test
	void getHeight() {
		assertEquals(this.height, this.world.getHeight());
	}

	@Test
	void movePlayer() {
		assertDoesNotThrow(() -> {
			//x = 2, y = 2 au début
			this.world.movePlayer(Input.Keys.LEFT);

			Player player = this.world.getPlayer();
			assertEquals(2, player.getPositionX());
			assertEquals(1, player.getPositionY());
		});
	}
}