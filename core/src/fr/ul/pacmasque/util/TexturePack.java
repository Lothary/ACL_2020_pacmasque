/*
 * TexturePack.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.util;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import fr.ul.pacmasque.exception.TextureException;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class TexturePack {
	public enum typeTexture{
		pacman,
		stone,
		monster,
		pastille,
		treasure,
		trap,
		magic,
		teleportation
	}
	@NotNull private final Map<typeTexture, String> resources;
	private final FileHandle handle;

	public TexturePack(FileHandle handle) {
		this.resources = new HashMap<>();
		this.handle = handle;

		this.resources.put(typeTexture.pacman, "pacman.png");
		this.resources.put(typeTexture.stone, "stone.png");
		this.resources.put(typeTexture.monster, "monster.png");
		this.resources.put(typeTexture.pastille, "pastille.png");
		this.resources.put(typeTexture.treasure, "treasure.png");
		this.resources.put(typeTexture.trap, "trap.png");
		this.resources.put(typeTexture.magic, "magic.png");
		this.resources.put(typeTexture.teleportation, "teleportation.png");
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
	@NotNull public Texture get(typeTexture type) throws TextureException {

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
}
