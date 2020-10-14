/*
 * MenuView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 14/10/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.ul.pacmasque.model.Labyrinth;

public class MenuView implements View {

	private final Batch batch;
	private final Stage stage;
	private final ExtendViewport viewport;
	private final OrthographicCamera camera;
	Labyrinth labyrinth = new Labyrinth(80, 80);
	Texture texture = new Texture("commodore64/raw/white.png");

	public MenuView() {
		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera();
		this.viewport = new ExtendViewport(80, 80, camera);
		this.viewport.setScreenPosition(40, 40);
		this.viewport.apply();

		this.camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		this.camera.update();
		this.batch.setProjectionMatrix(this.camera.combined);

		this.stage = new Stage(this.viewport, this.batch);

		Gdx.input.setInputProcessor(new InputProcessor() {
			@Override
			public boolean keyDown(int keycode) {
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				switch (keycode) {
					case 21:
						camera.translate(-1, 0);
						break;
					case 22:
						camera.translate(1, 0);
						break;
					case 19:
						camera.translate(0, 1);
						break;
					case 20:
						camera.translate(0, -1);
						break;

				}
				System.out.println(keycode);
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
				if (camera.zoom >= 0.01) {
					camera.zoom += (float) amount / 100;
					camera.update();
				}

				return false;
			}
		});
	}

	@Override
	public void show() {

		//Create Table
		Table mainTable = new Table();
		//Set table to fill stage
		mainTable.setFillParent(true);
		//Set alignment of contents in the table.
		mainTable.center();

		Skin skin = new Skin(Gdx.files.internal("commodore64/skin/uiskin.json"));
		TextButton playButton = new TextButton("Play", skin);
		TextButton exitButton = new TextButton("Exit", skin);

		mainTable.add(playButton);
		mainTable.row();
		mainTable.add(exitButton);

		//this.stage.addActor(mainTable);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.1f, .12f, .18f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.batch.setProjectionMatrix(this.camera.combined);

		this.batch.begin();
		this.labyrinth.getMursPosition().forEach(wall -> batch.draw(this.texture, wall.x, wall.y, 1, 1));
		this.batch.end();

		this.stage.act();
		this.stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		this.viewport.update(width, height, true);
		this.viewport.setScreenX(0);
		this.viewport.setScreenY(0);
		this.viewport.apply();
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
		this.batch.dispose();
		this.stage.dispose();
	}
}
