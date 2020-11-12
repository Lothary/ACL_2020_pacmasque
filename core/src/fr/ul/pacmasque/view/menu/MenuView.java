/*
 * MenuView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 12/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import fr.ul.pacmasque.view.View;
import org.jetbrains.annotations.Contract;

public class MenuView extends View {

	private final Stage stage;
	private final Skin skin;

	/**
	 * Crée une vue de taille donnée
	 *
	 * @param viewportWidth  la largeur du viewport
	 * @param viewportHeight la hauteur du viewport
	 */
	public MenuView(float viewportWidth, float viewportHeight) {
		super(viewportWidth, viewportHeight);

		this.stage = new Stage(this.getViewport(), this.getBatch());

		FileHandle skinFileHandle = Gdx.files.internal("skin/craftacular/craftacular-ui.json");
		this.skin = new Skin(skinFileHandle);
	}

	@Override
	public void show() {
		super.show();

		Gdx.input.setInputProcessor(this.stage);

		Table table = this.getMenuTable(false, this.skin);
		this.stage.addActor(table);
	}

	@Override
	public void update(float delta) {
		stage.act();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		Gdx.gl.glClearColor(.1f, .12f, .16f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
	}

	@Contract(pure = true)
	private Table getMenuTable(boolean debug, Skin skin) {
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
