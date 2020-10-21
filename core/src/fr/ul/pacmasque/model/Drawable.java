/*
 * Drawable.java
 * ACL-2020-pacmasque
 *
 * Created by ugocottin on 21/10/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.model;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface Drawable {

	void draw(Batch batch, float x, float y, float width, float height);
}
