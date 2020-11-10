/*
 * StaticSprite.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 10/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StaticSprite extends Sprite {

	public StaticSprite(Texture texture, int numberOfFrames, float cycleTime) {
		super(new TextureRegion(texture), 1, Float.MAX_VALUE);
	}
}
