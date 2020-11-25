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

/**
 * Image content plusieurs frames d'une animation
 */
public class Sprite implements Drawable {

	/**
	 * Collection des frames
	 */
	private final List<TextureRegion> frames;

	/**
	 * Temps maximal d'affichage d'une frame
	 */
	private final float maxFrameTime;

	/**
	 * Durée d'affichage de la frame actuelle
	 */
	private float currentFrameTime;

	/**
	 * Nombre de frames de la sprite
	 */
	private final int numberOfFrames;

	/**
	 * Index de la frame actuelle
	 */
	private int currentFrame;

	/**
	 * Crée une Sprite
	 * @param texture une texture contenant de multiples frames
	 * @param numberOfFrames nombre de frames dans la texture
	 * @param cycleTime temps pendant laquelle une frame doit être affichée
	 */
	public Sprite(Texture texture ,int numberOfFrames, float cycleTime) {
		this(new TextureRegion(texture), numberOfFrames, cycleTime);
	}

	/**
	 * Crée une Sprite
	 * @param textureRegion une textureRegion contenant de multiples frames
	 * @param numberOfFrames nombre de frames dans la texture
	 * @param cycleTime temps pendant laquelle une frame doit être affichée
	 */
	public Sprite(TextureRegion textureRegion, int numberOfFrames, float cycleTime) {
		this.numberOfFrames = numberOfFrames;
		this.frames = new ArrayList<>(this.numberOfFrames);

		// Largeur d'une frame
		int frameWidth = textureRegion.getRegionWidth() / this.numberOfFrames;

		// Séparation des différentes framers
		for (int i = 0; i < this.numberOfFrames; i++) {
			TextureRegion subRegion = new TextureRegion(textureRegion, i * frameWidth, 0, frameWidth, textureRegion.getRegionHeight());
			this.frames.add(subRegion);
		}

		this.maxFrameTime = cycleTime / this.numberOfFrames;
		this.currentFrame = 0;
	}

	/**
	 * @return la frame actuellement affichée
	 */
	public TextureRegion getFrame() {
		return this.frames.get(this.currentFrame);
	}

	/**
	 * Mets à jour la sprite
	 * @param delta le temps passé depuis la dernière mise à jour, en millisecondes
	 */
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
