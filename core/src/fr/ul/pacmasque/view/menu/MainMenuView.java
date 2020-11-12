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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MainMenuView extends MenuView {

	/**
	 * Crée une vue de taille donnée
	 *
	 * @param viewportWidth  la largeur du viewport
	 * @param viewportHeight la hauteur du viewport
	 */
	public MainMenuView(float viewportWidth, float viewportHeight, @NotNull Skin skin, @Nullable Color clearColor) {
		super(viewportWidth, viewportHeight, skin, clearColor);
	}

	@Override
	public void show() {
		super.show();

		Stage stage = this.getStage();
		Gdx.input.setInputProcessor(stage);

		Skin skin = this.getSkin();
		Table table = this.getMenuTable(false, skin);
		stage.addActor(table);
	}

	@Contract(pure = true)
	private Table getMenuTable(@SuppressWarnings("SameParameterValue") boolean debug, @NotNull Skin skin) {
		Table table = new Table();
		table.defaults().pad(10f);
		table.setFillParent(true);
		table.setDebug(debug);
		table.center();

		Label label = new Label("PACMASQUE!", skin);
		label.setAlignment(Align.center);

		TextButton newWorldButton = new TextButton("Nouveau monde", skin);
		TextButton loadWorldButton = new TextButton("Charger un monde", skin);

		TextButton optionsButton = new TextButton("Options...", skin);
		optionsButton.setDisabled(true);
		TextButton exitButton = new TextButton("Exit", skin);

		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});

		table.add(label).colspan(2).fillX();
		table.row();

		table.add(newWorldButton).colspan(2).fillX();
		table.row();

		table.add(loadWorldButton).colspan(2).fillX();
		table.row();

		HorizontalGroup group = new HorizontalGroup();
		group.setDebug(debug);
		group.space(10);
		group.addActor(optionsButton);
		group.addActor(exitButton);

		table.add(group).fillX();

		return table;
	}

}
