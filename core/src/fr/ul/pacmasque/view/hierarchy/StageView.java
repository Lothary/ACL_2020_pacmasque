/*
 * StageView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 14/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.hierarchy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import fr.ul.pacmasque.Pacmasque;
import fr.ul.pacmasque.util.TexturePack;
import fr.ul.pacmasque.view.menu.ErrorView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Vue disposant d'une scène `stage`
 */
public abstract class StageView extends PortedView {

	/**
	 * The stage of the menu view
	 */
	@NotNull private final Stage stage;

	/**
	 * The skin of the menu view
	 */
	@NotNull private final Skin skin;

	@NotNull private final Texture texture;

	/**
	 * Crée une vue munie d'une scène
	 * @param viewportWidth la largeur du viewport
	 * @param viewportHeight la hauteur du viewport
	 * @param backgroundColor sa couleur de fond
	 * @param skin le skin utilisé par la scène
	 */
	public StageView(float viewportWidth, float viewportHeight, @Nullable Color backgroundColor, @NotNull Skin skin) {
		super(viewportWidth, viewportHeight, backgroundColor);

		this.skin = skin;
		this.stage = new Stage(this.getViewport(), this.getBatch());
		this.texture = TexturePack.getFallbackTexture(this.getBackgroundColor());
	}

	/**
	 * @return la scène de la vue
	 */
	@NotNull public Stage getStage() {
		return this.stage;
	}

	/**
	 * @return le skin de la vue
	 */
	@NotNull public Skin getSkin() {
		return this.skin;
	}

	/**
	 * Construit la scène
	 * @param stage la scène sur laquelle les éléments vont être placés
	 */
	public abstract void build(@NotNull Stage stage, boolean debug);

	/**
	 * Affiche une vue d'erreur avec un bouton "OK"
	 * @param message le message de la vue
	 */
	protected void error(String message) {
		ErrorView errorView = new ErrorView(1080, 720, getSkin(), message);
		present(errorView);
	}

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

		Batch batch = this.getBatch();
		batch.begin();
		batch.draw(texture, 0, 0, this.getWidth(), this.getHeight());
		batch.end();

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
