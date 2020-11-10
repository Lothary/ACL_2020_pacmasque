/*
 * BasicPlayerTest.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 4/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.entity;

import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BasicPlayerTest {

	private BasicPlayer player;
	private Vector2 vector;
	private final Random random = new Random(System.currentTimeMillis());

	@BeforeEach
	void setUp() {
		int x = random.nextInt(100);
		int y = random.nextInt(100);

		this.player = new BasicPlayer(x, y);
		this.vector = new Vector2(x, y);
	}

	@Test
	void getPosition() {
		assertEquals(this.vector, this.player.getPosition());
	}

	@Test
	void getPositionNonNull() {
		assertNotNull(this.player.getPosition());
	}


	@Test
	void getPositionX() {
		assertEquals(this.vector.x, this.player.getPosition().x);
	}

	@Test
	void getPositionY() {
		assertEquals(this.vector.y, this.player.getPosition().y);
	}

	@Test
	void setPositionX() {
		int x = random.nextInt(100);
		this.player.setNextPositionX(x);
		assertEquals(x, this.player.getPosition().x);
	}

	@Test
	void setPositionY() {
		int y = random.nextInt(100);
		this.player.setNextPositionY(y);
		assertEquals(y, this.player.getPosition().y);
	}
}