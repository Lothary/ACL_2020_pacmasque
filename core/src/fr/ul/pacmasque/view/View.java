/*
 * View.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Vue 2D comprenant un viewport, ainsi qu'un batch et une camera
 */
public abstract class View extends ScreenAdapter {

	public static final boolean DEBUG = false;
	public static final Color DEFAULT_CLEAR_COLOR = Color.BLACK;

	/**
	 * Le batch de la vue
	 */
	private final Batch batch;

	/**
	 * La camera de la vue
	 */
	private final Camera camera;

	/**
	 * Le viewport de la vue
	 */
	private final Viewport viewport;

	private final Color clearColor;

	/**
	 * Crée une vue de taille donnée, avec une couleur de fond noir
	 * @param viewportWidth la largeur du viewport
	 * @param viewportHeight la hauteur du viewport
	 * @param clearColor la couleur de fond
	 */
	public View(float viewportWidth, float viewportHeight, @Nullable Color clearColor) {
		this.batch = new SpriteBatch();

		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, viewportWidth, viewportHeight);
		this.camera = camera;

		this.viewport = new FitViewport(viewportWidth, viewportHeight, this.camera);

		if (clearColor != null) {
			this.clearColor = clearColor;
		} else {
			this.clearColor = View.DEFAULT_CLEAR_COLOR;
		}
	}

	/**
	 * @return le batch de la vue
	 */
	public Batch getBatch() {
		return this.batch;
	}

	/**
	 * @return la camera de la vue
	 */
	public Camera getCamera() {
		return this.camera;
	}

	/**
	 * @return le viewport de la vue
	 */
	public Viewport getViewport() {
		return this.viewport;
	}

	/**
	 * @return la couleur de fond
	 */
	public Color getClearColor() {
		return this.clearColor;
	}

	@Override
	public void show() {
		this.batch.setProjectionMatrix(this.camera.combined);
	}

	@Override
	public void resize(int width, int height) {
		this.viewport.update(width, height, false);
	}

	@Override
	public void render(float delta) {
		update(delta);

		Color color = this.getClearColor();
		Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void dispose() {
		this.batch.dispose();
	}

	/**
	 * Contient la logique de la vue, et pas le dessin.
	 * Exemple: step du world, mise a jour du model
	 * @param delta un delta
	 */
	public abstract void update(float delta);
}
