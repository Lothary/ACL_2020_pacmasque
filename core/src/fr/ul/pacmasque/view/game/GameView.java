/*
 * GameView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 15/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.ul.pacmasque.model.World;
import fr.ul.pacmasque.view.hierarchy.PortedView;
import org.jetbrains.annotations.NotNull;

/**
 * Vue de jeu
 */
public class GameView extends PortedView {

	/**
	 * Zoom maximale de la vue
	 */
	private static final float MAX_ZOOM = .25f;

	/**
	 * Zoom minimal de la vue
	 */
	private static final float MIM_ZOOM = 3f;

	/**
	 * Facteur de vitesse auquel le zoom change
	 */
	private static final float ZOOM_FACTOR = 100f;

	/**
	 * le monde de la vue
	 */
	@NotNull private final World world;

	/**
	 * Crée une nouvelle vue pour un monde donné
	 * @param world un monde
	 */
	public GameView(@NotNull World world) {
		super(world.getWidth(),world.getHeight(), DEFAULT_BACKGROUND_COLOR);
		this.world = world;
	}

	/**
	 * @return le world de la vue
	 */
	public @NotNull World getWorld() {
		return this.world;
	}

	@Override
	public boolean keyDown(int keycode) {

		if(keycode == Input.Keys.LEFT)
			this.world.movePlayer(Input.Keys.LEFT);
		else if(keycode == Input.Keys.RIGHT)
			this.world.movePlayer(Input.Keys.RIGHT);
		else if(keycode == Input.Keys.UP)
			this.world.movePlayer(Input.Keys.UP);
		else if(keycode == Input.Keys.DOWN)
			this.world.movePlayer(Input.Keys.DOWN);


		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		Camera camera = getCamera();
		if (camera instanceof OrthographicCamera) {
			OrthographicCamera orthographicCamera = (OrthographicCamera) camera;

			orthographicCamera.zoom += amount / ZOOM_FACTOR;

			if (orthographicCamera.zoom < MAX_ZOOM) {
				orthographicCamera.zoom = MAX_ZOOM;
			}

			if (orthographicCamera.zoom > MIM_ZOOM) {
				orthographicCamera.zoom = MIM_ZOOM;
			}

			camera.update();
			getBatch().setProjectionMatrix(camera.combined);
		}

		return true;
	}

	@Override
	public boolean shouldCenterCameraOnResize() {
		return false;
	}

	@Override
	public void create() {
		super.create();
		Vector2 playerPosition = this.getWorld().getPlayer().getPosition();
		Camera camera = this.getCamera();
		camera.position.set(playerPosition, 0);
		camera.update();
		getBatch().setProjectionMatrix(camera.combined);
	}

	@Override
	public void update(float delta) {
		Vector2 playerPosition = this.getWorld().getPlayer().getPosition();
		Camera camera = this.getCamera();
		camera.position.set(playerPosition, 0);
		camera.update();
		getBatch().setProjectionMatrix(camera.combined);

		this.world.moveMonsters();
		this.world.updateCollision();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		Batch batch = getBatch();
		Viewport viewport = this.getViewport();

		batch.begin();
		Pixmap pixmap = new Pixmap(world.getWidth(), world.getHeight(), Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.BLACK);
		pixmap.fillRectangle(0,0, world.getWidth(), world.getHeight());
		Texture text = new Texture(pixmap);
		pixmap.dispose();

		batch.draw(text, 0, 0, world.getWidth(), world.getHeight());
		text.dispose();

		this.world.draw(batch, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
		batch.end();

	}
}
