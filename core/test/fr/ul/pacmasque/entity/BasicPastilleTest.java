/*
 * BasicPastilleTest.java
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

class BasicPastilleTest {

	private BasicPastille pastille;
	private Vector2 vector;
	private final Random random = new Random(System.currentTimeMillis());

	@BeforeEach
	void setUp() {
		int x = random.nextInt(100);
		int y = random.nextInt(100);

		this.pastille = new BasicPastille(x, y);
		this.vector = new Vector2(x, y);
	}

	@Test
	void getPosition() {
		assertEquals(this.vector, this.pastille.getPosition());
	}

	@Test
	void getPositionNonNull() {
		assertNotNull(this.pastille.getPosition());
	}
}