/*
 * BatchView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 14/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.hierarchy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BatchView extends View implements Disposable {

	@NotNull private final Batch batch;

	public BatchView(float width, float height, @Nullable Color backgroundColor, @NotNull Batch batch) {
		super(width, height, backgroundColor);

		this.batch = batch;
	}

	public Batch getBatch() {
		return this.batch;
	}

	@Override
	public void dispose() {
		super.dispose();
		this.batch.dispose();
	}
}
