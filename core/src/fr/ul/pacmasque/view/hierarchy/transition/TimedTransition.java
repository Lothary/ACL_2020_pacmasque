/*
 * TimedTransition.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 10/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.hierarchy.transition;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.jetbrains.annotations.NotNull;

public abstract class TimedTransition extends Transition {

	private final float duration;
	private float elapsedTime;

	public TimedTransition(float duration) {
		this.duration = duration;
		this.elapsedTime = 0f;
	}

	public float progress() {
		return this.elapsedTime / this.duration;
	}

	@Override
	public void reset() {
		this.elapsedTime = 0;
	}

	@Override
	public void render(float delta, @NotNull TextureRegion lastScreen, @NotNull TextureRegion currScreen) {
		this.elapsedTime += delta;

		render(delta, lastScreen, currScreen, this.progress());
	}

	public abstract void render(float delta, @NotNull TextureRegion lastScreen, @NotNull TextureRegion currScreen, float progress);

	@Override
	public boolean isDone() {
		return this.elapsedTime >= this.duration;
	}
}
