/*
 * KruskalGeneratorTest.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 18/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.generator;

import org.junit.jupiter.api.Test;

class KruskalGeneratorTest {

	@Test
	void generateTest() {
		LabyrinthGenerator generator = new KruskalGenerator(3);

		try {
			generator.generate(3, 3);
		} catch (LabyrinthGeneratorException e) {
			e.printStackTrace();
		}
	}

}