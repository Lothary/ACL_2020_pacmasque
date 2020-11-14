/*
 * PortedView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 14/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.hierarchy;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class PortedView extends BatchView {

	/**
	 * La camera de la vue
	 */
	@NotNull private final Camera camera;

	/**
	 * Le viewport de la vue
	 */
	@NotNull private final Viewport viewport;

	public PortedView(float viewportWidth, float viewportHeight, @Nullable Color backgroundColor) {
		super(viewportWidth, viewportHeight, backgroundColor, new SpriteBatch());

		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, viewportWidth, viewportHeight);
		this.camera = camera;

		this.viewport = new ExtendViewport(viewportWidth, viewportHeight, this.camera);
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
	 * @return si la caméra doit être centrée lors d'un redimensionnement
	 */
	public abstract boolean shouldCenterCameraOnResize();

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		this.getViewport().update(width, height, this.shouldCenterCameraOnResize());
		this.getViewport().apply();
		this.getBatch().setProjectionMatrix(this.getCamera().combined);
	}
}
