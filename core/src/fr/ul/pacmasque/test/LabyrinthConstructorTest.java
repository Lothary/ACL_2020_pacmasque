/*
 * LabyrinthConstructorTest.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 20/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.test;

import fr.ul.pacmasque.util.LabyrinthConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthConstructorTest {
	private static LabyrinthConstructor labyrinthConstructor;
	private static File file;

	@BeforeAll
	static void start(){
		file = new File("assets/labys.txt");
		labyrinthConstructor = new LabyrinthConstructor(file);
	}

	@Test
	void buildTest(){
		assertDoesNotThrow(() -> labyrinthConstructor.build(0));
	}



}