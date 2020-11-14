/*
 * View.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 14/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.hierarchy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import fr.ul.pacmasque.view.control.navigation.NavigationController;
import fr.ul.pacmasque.view.control.navigation.NavigationViewController;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class View extends Responder implements Screen {

	@NotNull public static final Color DEFAULT_BACKGROUND_COLOR = Color.BLACK;

	@NotNull public static final NavigationController<View> navigationController = new NavigationViewController(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

	private float width;
	private float height;

	/**
	 * Indique si la vue est chargée (construire)
	 */
	private boolean loaded;

	/**
	 * Si la vue est en pause. La vue est en pause lorsqu'elle n'est pas présentée.
	 */
	private boolean paused;

	@NotNull
	public Color backgroundColor;

	public View(float width, float height, @Nullable Color backgroundColor) {
		this.width = width;
		this.height = height;

		this.loaded = false;
		this.paused = true;

		if (backgroundColor != null) {
			this.backgroundColor = backgroundColor;
		} else {
			this.backgroundColor = DEFAULT_BACKGROUND_COLOR;
		}

	}

	public float getWidth() {
		return this.width;
	}

	public float getHeight() {
		return this.height;
	}

	public boolean isLoaded() {
		return this.loaded;
	}

	public @NotNull Color getBackgroundColor() {
		return this.backgroundColor;
	}

	public void create() {
		this.loaded = true;
	}

	/**
	 * Contient la logique de la vue, et pas le dessin.
	 * Exemple: step du world, mise a jour du model
	 * @param delta un delta
	 */
	public abstract void update(float delta);

	public void present(@NotNull View view) {
		View.navigationController.present(view);
	}

	public void dismiss() {
		View.navigationController.dismiss();
	}

	// Screen
	@Override
	public void show() {
		this.paused = false;
		this.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render(float delta) {
		if (!this.paused) {
			this.update(delta);
		}

		Color color = this.getBackgroundColor();
		Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void pause() {
		this.paused = true;
	}

	@Override
	public void resume() {
		this.paused = false;
	}

	@Override
	public void hide() {
		this.paused = true;
	}

	@Override
	public void dispose() {

	}
}
