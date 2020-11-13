/*
 * TexturePack.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import fr.ul.pacmasque.exception.TextureException;

import java.util.HashMap;
import java.util.Map;

public class TexturePack {
	private final Map<String, String> resources;
	private final String packName;

	public TexturePack(String packName){
		resources = new HashMap<>();
		this.packName = packName;
		String path = "packs/" + packName + "/";
		resources.put("pacman", path+"pacman.png");
		resources.put("stone", path+"stone.png");
		resources.put("monster", path+"monster.png");
		resources.put("pastille", path+"pastille.png");
	}

	/**
	 * @param name nom de la texture souhaitée (pacman,
	 *             stone, monster, pastille).
	 * @return la Texture qui correspond à l'objet dont
	 * le nom est "name" et dont le pack est "packName".
	 * @throws TextureException si on ne trouve pas la
	 * texture -> peut être dû à une faute dans "name"
	 * ou la texture n'existe pas.
	 */
	public Texture get(String name) throws TextureException {
		Texture texture;
		if (resources.containsKey(name)){
			String path = resources.get(name);
			texture = new Texture(Gdx.files.internal(path));
		}
		else{
			throw new TextureException("La texture de "+name+" dans le pack "+packName+" n'a pas été trouvée.");
		}
		return texture;
	}
}
