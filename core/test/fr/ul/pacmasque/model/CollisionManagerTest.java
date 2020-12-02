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
import fr.ul.pacmasque.util.generator.Generators;
import fr.ul.pacmasque.util.generator.LabyrinthGenerator;
import fr.ul.pacmasque.util.generator.LabyrinthGeneratorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static com.badlogic.gdx.math.MathUtils.random;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CollisionManagerTest extends PacmasqueTest {

	private World world;
	private int width;
	private int height;
	private BasicMonster m1, m2;


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
