/*
 * GameView.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.ul.pacmasque.model.World;

/**
 * Vue de jeu, affichant un monde
 */
public class GameView extends View implements InputProcessor {

	public World getWorld() {
		return world;
	}

	/**
	 * le monde de la vue
	 */
	private final World world;

	/**
	 * Crée une nouvelle vue pour un monde donné
	 * @param world un monde
	 */
	public GameView(World world) {
		super(world.getWidth(),world.getHeight(), null, null);
		this.world = world;
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
		Camera camera = getCamera();
		if (camera instanceof OrthographicCamera) {
			((OrthographicCamera) camera).zoom += amount / 100f;
			camera.update();
			getBatch().setProjectionMatrix(camera.combined);
		}

		return true;
	}


	@Override
	public void update(float delta) {

	}

	@Override
	public void render(float delta) {
		super.render(delta);

		Batch batch = getBatch();
		Viewport viewport = this.getViewport();

		batch.begin();
		this.world.draw(batch, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
		batch.end();
	}
}
