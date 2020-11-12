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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import fr.ul.pacmasque.view.View;
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

	public MenuView(float viewportWidth, float viewportHeight, @NotNull Skin skin, @Nullable Color clearColor) {
		super(viewportWidth, viewportHeight, clearColor);

		this.stage = new Stage(this.getViewport(), this.getBatch());
		this.skin = skin;
	}

	public Stage getStage() {
		return this.stage;
	}

	public Skin getSkin() {
		return this.skin;
	}

	@Override
	public void update(float delta) {
		this.stage.act();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		this.stage.draw();
	}
}
