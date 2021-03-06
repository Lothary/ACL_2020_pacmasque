/*
 * MenuView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 12/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import fr.ul.pacmasque.view.hierarchy.StageView;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Vue principale du menu du jeu, permettant de sélectionner l'action à réaliser
 */
public class MainMenuView extends StageView {

	/**
	 * Stage de la vue de création d'un nouveau monde
	 */
	@Nullable private StageView newWorldMenu;

	/**
	 * Crée une vue de taille donnée
	 *
	 * @param viewportWidth  la largeur du viewport
	 * @param viewportHeight la hauteur du viewport
	 */
	public MainMenuView(float viewportWidth, float viewportHeight, @Nullable Color clearColor, @NotNull Skin skin) {
		super(viewportWidth, viewportHeight, clearColor, skin);

		this.newWorldMenu = null;
	}

	@Override
	public void build(@NotNull Stage stage, boolean debug) {
		Skin skin = this.getSkin();
		Table table = this.getMenuTable(debug, skin);
		stage.addActor(table);
	}

	@Contract(pure = true)
	private Table getMenuTable(boolean debug, @NotNull Skin skin) {
		Table table = new Table();
		table.defaults().pad(10f);
		table.setFillParent(true);
		table.setDebug(debug);
		table.center();

		// Main label
		Label label = new Label("PACMASQUE!", skin);
		label.setDebug(debug);
		label.setAlignment(Align.center);

		// New world button
		TextButton newWorldButton = new TextButton("Nouveau monde", skin);
		newWorldButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// Initialise au besoin
				if (newWorldMenu == null) {
					newWorldMenu = new NewWorldMenuView(getWidth(), getHeight(), getBackgroundColor(), skin);
				}

				present(newWorldMenu);
			}
		});

		// Load world button
		TextButton loadWorldButton = new TextButton("Charger un monde", skin);

		// Options button
		TextButton optionsButton = new TextButton("Options...", skin);
		optionsButton.setDisabled(true);

		// Exit button
		TextButton exitButton = new TextButton("Exit", skin);
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				dismiss();
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
