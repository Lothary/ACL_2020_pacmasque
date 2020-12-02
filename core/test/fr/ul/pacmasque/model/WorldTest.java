/*
 * WorldTest.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 4/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.model;

import fr.ul.pacmasque.PacmasqueTest;
import fr.ul.pacmasque.util.generator.Generators;
import fr.ul.pacmasque.util.generator.LabyrinthGenerator;
import fr.ul.pacmasque.util.generator.LabyrinthGeneratorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.function.Supplier;

import static com.badlogic.gdx.math.MathUtils.random;
import static org.junit.jupiter.api.Assertions.*;


class WorldTest extends PacmasqueTest {

	private final Random random = new Random(System.currentTimeMillis());
	private int width;
	private int height;

	private World world;
	String seed = "" + System.currentTimeMillis();

	@BeforeEach
	void setUp() throws LabyrinthGeneratorException {
		this.width = random.nextInt(100);
		this.height = random.nextInt(100);

		if(this.width < 3)
			this.width += 5;
		if(this.height < 3)
			this.height += 5;
		if(this.width % 2 == 0)
			this.width -= 1;
		if(this.height % 2 == 0)
			this.height -= 1;

		Supplier<LabyrinthGenerator> generatorFactory = Generators.shared().getGeneratorFactory("Kruskal");
		LabyrinthGenerator generator = generatorFactory.get();
		Labyrinth labyrinth = generator.generate(System.currentTimeMillis(), (int) width, (int) height);

		this.world = new World(labyrinth, "New World");
	}

	@Test
	void getWidth() {
		assertEquals(this.width, this.world.getWidth());
	}

	@Test
	void getHeight() {
		assertEquals(this.height, this.world.getHeight());
	}

	//@Test
	//void movePlayer() {
	//	// TODO
	//}
}