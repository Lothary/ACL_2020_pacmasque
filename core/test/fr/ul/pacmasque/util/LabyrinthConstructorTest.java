/*
 * LabyrinthConstructorTest.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 5/11/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.util;

import fr.ul.pacmasque.PacmasqueTest;
import fr.ul.pacmasque.exception.LabyrinthConstructorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthConstructorTest extends PacmasqueTest {
	private LabyrinthConstructor constructor;
	private String binaryLaby;

	@BeforeEach
	void setUp() {
		this.constructor = new LabyrinthConstructor();
		this.binaryLaby = "0111\n" +
				"0010\n" +
				"0110\n" +
				"0000\n" +
				"#" +
				"1001\n" +
				"1110\n" +
				"0100\n" +
				"0111\n";
	}

	@Test
	void build() {
		// Comme build crée un Labyrinth, il a besoin des Textures
		// Or pb de Gdx.files = null...
		assertDoesNotThrow(() -> constructor.build(binaryLaby));
	}

	@Test
	void buildWithNonexistentKey(){
		assertThrows(LabyrinthConstructorException.class, () -> constructor.build(550));
	}
}