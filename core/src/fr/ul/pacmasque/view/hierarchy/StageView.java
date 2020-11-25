/*
 * StageView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 14/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.hierarchy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import fr.ul.pacmasque.Pacmasque;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class StageView extends PortedView {

	/**
	 * The stage of the menu view
	 */
	@NotNull private final Stage stage;

	/**
	 * The skin of the menu view
	 */
	@NotNull private final Skin skin;

	public StageView(float viewportWidth, float viewportHeight, @Nullable Color backgroundColor, @NotNull Skin skin) {
		super(viewportWidth, viewportHeight, backgroundColor);

		this.skin = skin;
		this.stage = new Stage(this.getViewport(), this.getBatch());
	}

	@NotNull public Stage getStage() {
		return this.stage;
	}

	@NotNull public Skin getSkin() {
		return this.skin;
	}

	/**
	 * Construit la scène
	 * @param stage la scène sur laquelle les éléments vont être placés
	 */
	public abstract void build(Stage stage, boolean debug);

	// Disposable
	@Override
	public void dispose() {
		super.dispose();
		this.stage.dispose();
	}

	// PortedView
	@Override
	public boolean shouldCenterCameraOnResize() {
		return true;
	}

	// View
	@Override
	public void create() {
		super.create();
		this.build(this.getStage(), Pacmasque.ENVIRONMENT == Pacmasque.Environment.DEBUG);
	}

	@Override
	public void update(float delta) {
		this.stage.act(delta);
	}

	// Screen
	@Override
	public void render(float delta) {
		super.render(delta);
		this.stage.draw();
	}

	// Responder

	@Override
	public @Nullable Responder getNextResponder() {
		return new Responder() {

			final Stage stage = getStage();

			@Override
			public boolean keyUp(int keycode) {
				return stage.keyUp(keycode);
			}

			@Override
			public boolean keyDown(int keycode) {
				return stage.keyDown(keycode);
			}

			@Override
			public boolean keyTyped(char character) {
				return stage.keyTyped(character);
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				return stage.touchDown(screenX, screenY, pointer, button);
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				return stage.touchUp(screenX, screenY, pointer, button);
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return stage.touchDragged(screenX, screenY, pointer);
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return stage.mouseMoved(screenX, screenY);
			}

			@Override
			public boolean scrolled(int amount) {
				return stage.scrolled(amount);
			}
		};
	}
}
