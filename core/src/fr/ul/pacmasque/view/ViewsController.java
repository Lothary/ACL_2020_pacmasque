/*
 * ViewsController.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 10/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.function.Function;

public class ViewsController extends ScreenAdapter implements InputProcessor {

	private final ArrayDeque<View> stack = new ArrayDeque<>();

	public void push(View view) {
		if (this.stack.peek() != null) {
			this.stack.peek().hide();
		}
		this.stack.push(view);
		view.show();
	}

	public void pop() {
		this.stack.pop();
		if (this.stack.peek() != null) {
			this.stack.peek().show();
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.1f, .12f, .18f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Iterator<View> viewIterator = this.stack.descendingIterator();
		while (viewIterator.hasNext()) {
			viewIterator.next().render(delta);
		}
	}

	@Override
	public void resize(int width, int height) {
		this.stack.forEach(v -> v.resize(width, height));
	}

	@Override
	public void show() {
		//this.stack.forEach(View::show);
	}

	@Override
	public void hide() {
		//this.stack.forEach(View::hide);
	}

	@Override
	public void pause() {
		this.stack.forEach(View::pause);
	}

	@Override
	public void resume() {
		this.stack.forEach(View::resume);
	}

	@Override
	public void dispose() {
		this.stack.forEach(View::dispose);
	}

	private boolean respondTo(Function<View, Boolean> func) {
		View responder;
		boolean responded = false;
		Iterator<View> iterator = this.stack.iterator();
		while (!responded && iterator.hasNext()) {
			responder = iterator.next();
			responded = func.apply(responder);
		}

		return responded;
	}

	@Override
	public boolean keyDown(int keycode) {
		return this.respondTo(view -> view.keyDown(keycode));
	}

	@Override
	public boolean keyUp(int keycode) {
		return this.respondTo(view -> view.keyUp(keycode));
	}

	@Override
	public boolean keyTyped(char character) {
		return this.respondTo(view -> view.keyTyped(character));
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return this.respondTo(view -> view.touchDown(screenX, screenY, pointer, button));
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return this.respondTo(view -> view.touchUp(screenX, screenY, pointer, button));
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return this.respondTo(view -> view.touchDragged(screenX, screenY, pointer));
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return this.respondTo(view -> view.mouseMoved(screenX, screenY));
	}

	@Override
	public boolean scrolled(int amount) {
		return this.respondTo(view -> view.scrolled(amount));
	}
}
