/*
 * BatchTransition.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 10/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.hierarchy.transition;

import com.badlogic.gdx.graphics.g2d.Batch;
import org.jetbrains.annotations.NotNull;

public abstract class BatchTransition extends TimedTransition {

	private final Batch batch;
	private int width;
	private int height;

	public BatchTransition(@NotNull Batch batch, float duration) {
		super(duration);
		this.batch = batch;
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	protected Batch getBatch() {
		return this.batch;
	}

	protected int getWidth() {
		return this.width;
	}

	protected int getHeight() {
		return this.height;
	}
}
