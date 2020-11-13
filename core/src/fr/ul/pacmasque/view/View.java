/*
 * View.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.ul.pacmasque.view.hierarchy.NavigationController;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Vue 2D comprenant un viewport, ainsi qu'un batch et une camera
 */
public abstract class View extends ScreenAdapter implements InputProcessor {

	public static final boolean DEBUG = true;
	@NotNull public static final Color DEFAULT_CLEAR_COLOR = Color.BLACK;

	/**
	 * Le batch de la vue
	 */
	@NotNull private final Batch batch;

	/**
	 * La camera de la vue
	 */
	@NotNull private final Camera camera;

	/**
	 * Le viewport de la vue
	 */
	@NotNull private final Viewport viewport;

	/**
	 * Couleur de fond
	 */
	@NotNull private final Color clearColor;

	/**
	 * Contrôleur de navigation
	 */
	@Nullable private final NavigationController<View> navigationController;

	/**
	 * Crée une vue de taille donnée, avec une couleur de fond noir
	 * @param viewportWidth la largeur du viewport
	 * @param viewportHeight la hauteur du viewport
	 * @param clearColor la couleur de fond
	 * @param navigationController le contrôleur de navigation
	 */
	public View(float viewportWidth, float viewportHeight, @Nullable Color clearColor, @Nullable NavigationController<View> navigationController) {
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

		this.navigationController = navigationController;
	}

	/**
	 * @return le batch de la vue
	 */
	public @NotNull Batch getBatch() {
		return this.batch;
	}

	/**
	 * @return la camera de la vue
	 */
	public @NotNull Camera getCamera() {
		return this.camera;
	}

	/**
	 * @return le viewport de la vue
	 */
	public @NotNull Viewport getViewport() {
		return this.viewport;
	}

	/**
	 * @return la couleur de fond
	 */
	public @NotNull Color getClearColor() {
		return this.clearColor;
	}

	/**
	 * @return le contrôleur de navigation, si présent
	 */
	public NavigationController<View> getNavigationController() {
		return this.navigationController;
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		this.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.batch.setProjectionMatrix(this.camera.combined);
	}

	@Override
	public void resize(int width, int height) {
		this.viewport.update(width, height, false);
	}

	@Override
	public void render(float delta) {
		update(delta);
		this.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.getViewport().apply(false);

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

	/**
	 * Indique si la vue dispose d'un input processor
	 * @return si elle dispose d'un input processor
	 */
	public boolean hasInputProcessor() {
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
