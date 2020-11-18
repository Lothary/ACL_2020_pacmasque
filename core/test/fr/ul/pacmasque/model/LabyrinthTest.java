/*
 * LabyrinthTest.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 4/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.model;

import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.PacmasqueTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthTest extends PacmasqueTest {

	private final Random random = new Random(System.currentTimeMillis());

	private Labyrinth labyrinth;
	private int width;
	private int height;

	@BeforeEach
	void setUp() {
		this.width = random.nextInt(100) + 3;
		this.height = random.nextInt(100) + 3;
		this.labyrinth = new Labyrinth(width, height);
	}

	@Test
	void setUpNegativeDimentions() {
		assertThrows(IllegalArgumentException.class, () -> new Labyrinth(-1, -2));
	}

	@Test
	void setUpTooSmallDimentions() {
		assertThrows(IllegalArgumentException.class, () -> new Labyrinth(1, 2));
	}

	@Test
	void setBigDimentions() {
		new Labyrinth(1000, 20000);
	}

	@Test
	void setMur() {
		int x = random.nextInt(this.width);
		int y = random.nextInt(this.height);

		this.labyrinth.setMur(x, y);
	}

	@Test
	void setMurRemoveMur() {
		int x = random.nextInt(this.width);
		int y = random.nextInt(this.height);

		assertTrue(this.labyrinth.getWalls().isEmpty());

		this.labyrinth.setMur(x, y);
		assertFalse(this.labyrinth.getWalls().isEmpty());

		this.labyrinth.setMur(x, y);
		assertTrue(this.labyrinth.getWalls().isEmpty());
	}

	@Test
	void setMurTwiceAndRemoveMur() {
		int x = random.nextInt(this.width);
		int y = random.nextInt(this.height);

		assertTrue(this.labyrinth.getWalls().isEmpty());

		this.labyrinth.setMur(x, y);
		assertFalse(this.labyrinth.getWalls().isEmpty());

		int dx = random.nextInt(this.width);
		int dy = random.nextInt(this.height);

		this.labyrinth.setMur(dx, dy);
		assertFalse(this.labyrinth.getWalls().isEmpty());


		this.labyrinth.setMur(x, y);
		assertFalse(this.labyrinth.getWalls().isEmpty());
		assertEquals(this.labyrinth.getWalls().size(), 1);
	}

	@Test
	void setMurOutOfBounds() {
		this.labyrinth.setMur(this.width + 5, this.height - 5);
	}

	@Test
	void getWidth() {
		assertEquals(this.width, this.labyrinth.getWidth());
	}

	@Test
	void getWidthNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.labyrinth = new Labyrinth(0, 5);
			assertEquals(0, this.labyrinth.getWidth());
		});
	}

	@Test
	void getHeight() {
		assertEquals(this.height, this.labyrinth.getHeight());
	}

	@Test
	void getHeightNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.labyrinth = new Labyrinth(5, 0);
			assertEquals(0, this.labyrinth.getHeight());
		});
	}

	@Test
	void isWall() {

		int x = random.nextInt(this.width);
		int y = random.nextInt(this.height);

		this.labyrinth.setMur(x, y);

		assertTrue(this.labyrinth.isWall(new Vector2(x, y)));
	}

	@Test
	void isWallOutOfBounds() {
		this.labyrinth.setMur(this.width + 5, this.height - 5);

		assertTrue(this.labyrinth.isWall(new Vector2(this.width + 5, this.height - 5)));
	}
}