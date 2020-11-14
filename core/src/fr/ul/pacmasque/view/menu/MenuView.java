/*
 * MenuView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 12/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import fr.ul.pacmasque.view.View;
import fr.ul.pacmasque.view.hierarchy.NavigationController;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class MenuView extends View {

	/**
	 * The stage of the menu view
	 */
	private final Stage stage;

	/**
	 * The skin of the menu view
	 */
	private final Skin skin;

	public MenuView(float viewportWidth, float viewportHeight, @NotNull Skin skin, @Nullable Color clearColor, @Nullable NavigationController<View> navigationController) {
		super(viewportWidth, viewportHeight, clearColor, navigationController);

		this.stage = new Stage(this.getViewport(), this.getBatch());
		this.skin = skin;
	}

	public Stage getStage() {
		return this.stage;
	}

	public Skin getSkin() {
		return this.skin;
	}

	/**
	 * Construit le menu sur la scène `stage`
	 * @param stage la scène sur laquelle le menu doit être construit
	 */
	public abstract void build(Stage stage, boolean debug);

	@Override
	public boolean shouldCenterCameraOnResize() {
		return true;
	}

	@Override
	public void show() {
		super.show();

		Stage stage = this.getStage();
		Gdx.input.setInputProcessor(stage);
		this.build(stage, View.DEBUG);
	}

	@Override
	public void update(float delta) {
		this.stage.act();
	}

	@Override
	public void resume() {
		super.resume();
		Gdx.input.setInputProcessor(this.getStage());
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		this.stage.draw();
	}

	@Override
	public void dispose() {
		super.dispose();
		this.stage.dispose();
	}
}
