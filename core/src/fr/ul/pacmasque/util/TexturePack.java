/*
 * TexturePack.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.util;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import fr.ul.pacmasque.exception.TextureException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class TexturePack {

	public enum TypeTexture {
		pacman,
		stone,
		monster,
		pastille,
		treasure,
		trap,
		magic,
		teleportation,
		/**
		 * Tests only
		 */
		test_only_do_not_use,
	}

	@NotNull private final Map<TypeTexture, String> resources;
	private final FileHandle handle;

	public TexturePack(FileHandle handle) {
		this.resources = new HashMap<>();
		this.handle = handle;

		this.resources.put(TypeTexture.pacman, "pacman.png");
		this.resources.put(TypeTexture.stone, "stone.png");
		this.resources.put(TypeTexture.monster, "monster.png");
		this.resources.put(TypeTexture.pastille, "pastille.png");
		this.resources.put(TypeTexture.treasure, "treasure.png");
		this.resources.put(TypeTexture.trap, "trap.png");
		this.resources.put(TypeTexture.magic, "magic.png");
		this.resources.put(TypeTexture.teleportation, "teleportation.png");
	}

	/**
	 * @param type nom de la texture souhaitée (pacman,
	 *             stone, monster, pastille).
	 * @return la Texture qui correspond à l'objet dont
	 * le nom est "name" et dont le pack est "packName".
	 * @throws TextureException si on ne trouve pas la
	 * texture -> peut être dû à une faute dans "name"
	 * ou la texture n'existe pas.
	 */
	@NotNull public Texture get(TypeTexture type) throws TextureException {

		if (!this.resources.containsKey(type)) {
			throw new TextureException("La texture \"" + type + "\" n'est pas une ressource disponible");
		}

		String textureName = this.resources.get(type);

		final FileHandle textureHandle = this.handle.child(textureName);

		if (!textureHandle.exists()) {
			throw new TextureException("La texture \"" + type + "\" n'a pas été trouvée dans le pack \"" + this.handle.name() + "\"");
		}

		return new Texture(textureHandle);
	}

	/**
	 * Crée une texture basique d'une couleur, en cas d'erreur de chargement de la texture de base
	 * @param color couleur de la texture
	 * @return un carré de couleur
	 */
	@NotNull public static Texture getFallbackTexture(@Nullable Color color) {
		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pixmap.setColor(color == null ? Color.PINK : color);
		pixmap.fillRectangle(0, 0, 1, 1);

		Texture texture = new Texture(pixmap);
		pixmap.dispose();

		return texture;
	}
}
