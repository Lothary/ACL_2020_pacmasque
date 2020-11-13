/*
 * Transition.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 10/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.hierarchy.transition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import org.jetbrains.annotations.NotNull;

public abstract class Transition implements Disposable {

	private boolean initialized = false;

	public abstract boolean isDone();

	public abstract void resize(int width, int height);

	public abstract void render(float delta, @NotNull TextureRegion lastScreen, @NotNull TextureRegion currScreen);

	protected void create() {

	}

	@Override
	public void dispose() {

	}

	public void reset() {
		if (!this.initialized) {
			initialized = true;
			this.create();
			this.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}
}
