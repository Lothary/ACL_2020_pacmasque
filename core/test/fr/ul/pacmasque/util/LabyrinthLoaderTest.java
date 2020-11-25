/*
 * LabyrinthLoaderTest.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 5/11/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.util;

import fr.ul.pacmasque.PacmasqueTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class LabyrinthLoaderTest extends PacmasqueTest {
	private LabyrinthLoader loader;

	@BeforeEach
	void setUp() {
		this.loader = LabyrinthLoader.shared();
	}

	@Test
	void loadFile() {
		// Ne marche pas car : Gdx.files est null et on fait fileHandle = Gdx.files... dans LabyrinthLoader
		assertDoesNotThrow(() -> loader.loadFile("labys.txt"));
	}


}