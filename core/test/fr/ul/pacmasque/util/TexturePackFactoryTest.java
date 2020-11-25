/*
 * TexturePackFactoryTest.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 13/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util;

import fr.ul.pacmasque.PacmasqueTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TexturePackFactoryTest extends PacmasqueTest {

	private TexturePackFactory factory;

	@BeforeEach
	void setUp() {
		this.factory = TexturePackFactory.getInstance();;
	}

	@Test
	void getInstance() {
		factory = TexturePackFactory.getInstance();
		assertNotNull(factory);
	}

	@Test
	void getExistantTexturePack() {
		TexturePack texturePack = factory.getTexturePack("basepack");
		assertNotNull(texturePack);
	}

	@Test
	void getNonExistantPack() {
		TexturePack texturePack = factory.getTexturePack("somethingRandom12235");
		assertNull(texturePack);
	}

	@Test
	void getExistantTexturePackMultipleCall() {
		TexturePack texturePack = factory.getTexturePack("basepack");
		assertNotNull(texturePack);

		texturePack = factory.getTexturePack("basepack");
		assertNotNull(texturePack);
	}

	@Test
	void getNonExistantTexturePackMultipleCall() {
		TexturePack texturePack = factory.getTexturePack("foo");
		assertNull(texturePack);

		texturePack = factory.getTexturePack("bar");
		assertNull(texturePack);
	}
}