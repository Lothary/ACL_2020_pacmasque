/*
 * TexturePackTest.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 13/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util;

import com.badlogic.gdx.graphics.Texture;
import fr.ul.pacmasque.PacmasqueTest;
import fr.ul.pacmasque.exception.TextureException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TexturePackTest extends PacmasqueTest {

	private final TexturePackFactory instance = TexturePackFactory.getInstance();
	private TexturePack texturePack;
	private final String[] resources = new String[]{"pacman", "stone", "monster", "pastille"};

	/** NOTE:
	 * Un test est manquant. Il faut demander à récupérer une resources,
	 * non présente sur le disque mais qui devrait l'être.
	 * Deux solutions:
	 *  - Ajouter une resources nommée DEBUG dans la map de la classe TexturePack
	 *  - Rendre la map publique et l'ajouter lors du test
	 * La deuxième options me paraît être la meilleure,
	 * a voir si c'est raisonnable de rendre la map publique
	 */

	@Test
	void getOnExistingResources() {
		texturePack = instance.getTexturePack("basepack");
		assertNotNull(texturePack);

		assertDoesNotThrow(() -> {
			for (TexturePack.TypeTexture type : TexturePack.TypeTexture.values()) {
				Texture texture = texturePack.get(type);
				assertNotNull(texture);
			}
		});
	}

	@Test
	void getOnNonExistingResources() {
		texturePack = instance.getTexturePack("basepack");
		assertNotNull(texturePack);

		for (TexturePack.TypeTexture type : TexturePack.TypeTexture.values()) {
			assertThrows(TextureException.class, () -> {
				Texture texture = texturePack.get(TexturePack.TypeTexture.test_only_do_not_use);
				assertNotNull(texture);
			});
		}
	}


}