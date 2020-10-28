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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Vue 2D comprenant un viewport, ainsi qu'un batch et une camera
 */
public abstract class View extends ScreenAdapter {

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

	/**
	 * Crée une vue de taille donnée
	 * @param viewportWidth la largeur du viewport
	 * @param viewportHeight la hauteur du viewport
	 */
	public View(float viewportWidth, float viewportHeight) {
		this.batch = new SpriteBatch();

		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, viewportWidth, viewportHeight);
		this.camera = camera;

		this.viewport = new FitViewport(viewportWidth, viewportHeight, this.camera);
	}

	/**
	 * @return le viewport de la vue
	 */
	public Viewport getViewport() {
		return viewport;
	}

	/**
	 * @return le batch de la vue
	 */
	public Batch getBatch() {
		return batch;
	}

	/**
	 * @return la camera de la vue
	 */
	public Camera getCamera() {
		return camera;
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
		Gdx.gl.glClearColor(.1f, .12f, .18f, 1);
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
