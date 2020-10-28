/*
 * GameView.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import fr.ul.pacmasque.model.World;

public class GameView extends View implements InputProcessor {

	private World world;

	public GameView(World world){
		super(7,7);
		this.world = world;
		Gdx.input.setInputProcessor(this);
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
		return false;
	}


	@Override
	public void update(float delta) {

	}

	@Override
	public void render(float delta) {
		super.render(delta);

		Batch batch = getBatch();
		batch.begin();
		this.world.draw(batch, 0, 0, 7, 7);
		batch.end();
	}
}
