/*
 * MenuView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 14/10/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.ul.pacmasque.Pacmasque;


public class SplashView implements View {

	private Batch batch;

	private Camera camera;
	private Viewport viewport;
	private Stage stage;

	private Image splashImage;

	public SplashView() {
		this.batch = new SpriteBatch();

		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false, Pacmasque.V_WIDTH, Pacmasque.V_HEIGHT);
		this.camera = camera;

		this.viewport = new FitViewport(Pacmasque.V_WIDTH, Pacmasque.V_HEIGHT, this.camera);

		this.stage = new Stage(this.viewport);
		Gdx.input.setInputProcessor(this.stage);

		InputProcessor inputProcessor = new InputMultiplexer(new InputProcessor() {
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
				camera.zoom += amount / 10f;
				camera.update();
				return false;
			}
		}, this.stage);

		Gdx.input.setInputProcessor(inputProcessor);

		Texture texture = new Texture(Gdx.files.internal("badlogic.jpg"));
		this.splashImage = new Image(texture);
		this.splashImage.setPosition((Pacmasque.V_WIDTH - texture.getWidth()) / 2f,
				(Pacmasque.V_HEIGHT - texture.getHeight()) / 2f);

		this.stage.addActor(this.splashImage);
	}

	@Override
	public void show() {
		this.batch.setProjectionMatrix(this.camera.combined);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.1f, .12f, .18f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.stage.act(delta);

		this.stage.draw();

		this.batch.begin();
		// Drawing

		this.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		this.viewport.update(width, height, false);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}
