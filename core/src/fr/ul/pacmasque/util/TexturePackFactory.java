/*
 * TexturePackFactory.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class TexturePackFactory {

	public static final String DEFAULT_PATH = "packs";

	/**
	 * Instance partagée
	 */
	@Nullable private static TexturePackFactory instance = null;

	@NotNull public static TexturePackFactory getInstance() {
		if (instance == null) {
			instance = new TexturePackFactory(DEFAULT_PATH);
		}

		return instance;
	}

	@Nullable private final FileHandle path;

	@NotNull private final Map<String, TexturePack> cache;

	private TexturePackFactory(String path) {
		this.path = Gdx.files.internal(path);
		this.cache = new HashMap<>();
	}

	/**
	 * @param packName nom du pack de textures.
	 * @return le TexturePack qui correspond à ce nom.
	 * Si le packName n'existe pas, on retourne le
	 * TexturePack de base.
	 */
	@Nullable public TexturePack getTexturePack(String packName) {

		if (this.cache.containsKey(packName)) {
			// Cached pack
			return this.cache.get(packName);
		}

		if (this.path == null) {
			return null;
		}

		FileHandle handle = this.path.child(packName);

		// Est ce que la cible existe ?
		if (!handle.exists()) {
			return null;
		}

		TexturePack texturePack = new TexturePack(handle);
		this.cache.put(packName, texturePack);

		return texturePack;
	}


}
