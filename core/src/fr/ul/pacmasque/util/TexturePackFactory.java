/*
 * TexturePackFactory.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.util;

public class TexturePackFactory {
	private final TexturePack basePack;
	private final TexturePack secondPack;
	private static final TexturePackFactory instance = new TexturePackFactory();

	private TexturePackFactory(){
		basePack = new TexturePack("basePack");
		secondPack = new TexturePack("secondPack");
	}

	public static TexturePackFactory getInstance(){
		return instance;
	}

	/**
	 * @param packName nom du pack de textures.
	 * @return le TexturePack qui correspond à ce nom.
	 * Si le packName n'existe pas, on retourne le
	 * TexturePack de base.
	 */
	public TexturePack getTexturePack(String packName){
		TexturePack texturePack;
		switch (packName){
			case "secondPack":
				texturePack = this.secondPack;
				break;
			default:
				texturePack = this.basePack;
				break;
		}
		return texturePack;
	}


}
