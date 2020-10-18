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
import fr.ul.pacmasque.model.World;

public class GameView implements View, InputProcessor {

	private World world;

	public GameView(World world){
		this.world = world;
	}



	@Override
	public boolean keyDown(int keycode) {

		if(keycode == Input.Keys.LEFT) // et si position.x > 0
			this.world.getPlayer().move(Input.Keys.LEFT);
		if(keycode == Input.Keys.RIGHT)  // et si position.x < tailleLabyrintheLargeur
			this.world.getPlayer().move(Input.Keys.RIGHT);
		if(keycode == Input.Keys.UP)  // et si position.y > 0
			this.world.getPlayer().move(Input.Keys.UP);
		if(keycode == Input.Keys.DOWN)  // et si position.y < tailleLabyrintheHauteur
			this.world.getPlayer().move(Input.Keys.DOWN);


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




}
