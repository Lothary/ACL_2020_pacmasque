/*
 * BlendingTransition.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 10/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.hierarchy.transition;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.jetbrains.annotations.NotNull;

public class BlendingTransition extends BatchTransition {


	public BlendingTransition(@NotNull Batch batch, float duration) {
		super(batch, duration);
	}

	@Override
	public void render(float delta, @NotNull TextureRegion lastScreen, @NotNull TextureRegion currScreen, float progress) {
		SpriteBatch batch = (SpriteBatch) this.getBatch();
		int width = this.getWidth();
		int height = this.getHeight();

		batch.begin();
		Color c = Color.CORAL;
		batch.setColor(c.r, c.g, c.b, 1);
		batch.setColor(c.r, c.g, c.b, progress);
		batch.draw(lastScreen, 0, 0, width, height);
		System.out.println(progress);
		batch.setColor(c.r, c.g, c.b, progress);
		batch.draw(currScreen, 0, 0, width, height);

		batch.end();
	}
}
