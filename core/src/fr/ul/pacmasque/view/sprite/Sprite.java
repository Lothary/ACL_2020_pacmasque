/*
 * Sprite.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 10/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import fr.ul.pacmasque.Drawable;

import java.util.ArrayList;
import java.util.List;

public class Sprite implements Drawable {
	private final List<TextureRegion> frames;
	private final float maxFrameTime;
	private float currentFrameTime;
	private final int numberOfFrames;
	private int currentFrame;

	public Sprite(Texture texture ,int numberOfFrames, float cycleTime) {
		this(new TextureRegion(texture), numberOfFrames, cycleTime);
	}

	public Sprite(TextureRegion textureRegion, int numberOfFrames, float cycleTime) {
		this.numberOfFrames = numberOfFrames;
		this.frames = new ArrayList<>(this.numberOfFrames);
		int frameWidth = textureRegion.getRegionWidth() / this.numberOfFrames;
		System.out.println(frameWidth);
		for (int i = 0; i < this.numberOfFrames; i++) {
			TextureRegion subRegion = new TextureRegion(textureRegion, i * frameWidth, 0, frameWidth, textureRegion.getRegionHeight());
			this.frames.add(subRegion);
		}

		this.maxFrameTime = cycleTime / this.numberOfFrames;
		this.currentFrame = 0;
	}

	public TextureRegion getFrame() {
		return this.frames.get(this.currentFrame);
	}

	public void update(float delta) {
		this.currentFrameTime += delta;
		if (this.currentFrameTime > this.maxFrameTime) {
			this.currentFrameTime = 0;
			this.currentFrame += 1;
		}

		if (this.currentFrame >= this.numberOfFrames) {
			this.currentFrame = 0;
		}
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		batch.draw(this.getFrame(), x, y, width, height);
	}
}
