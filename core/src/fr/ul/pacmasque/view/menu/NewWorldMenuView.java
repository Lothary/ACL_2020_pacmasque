/*
 * NewWorldMenuView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 12/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import fr.ul.pacmasque.exception.LabyrinthLoaderException;
import fr.ul.pacmasque.model.Labyrinth;
import fr.ul.pacmasque.model.World;
import fr.ul.pacmasque.util.LabyrinthBuilder;
import fr.ul.pacmasque.util.LabyrinthLoader;
import fr.ul.pacmasque.util.encoder.Decoder;
import fr.ul.pacmasque.util.encoder.LabyrinthDecoder;
import fr.ul.pacmasque.view.game.BuilderView;
import fr.ul.pacmasque.view.game.GameView;
import fr.ul.pacmasque.view.hierarchy.StageView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NewWorldMenuView extends StageView {

	public NewWorldMenuView(float viewportWidth, float viewportHeight, @Nullable Color clearColor, @NotNull Skin skin) {
		super(viewportWidth, viewportHeight, clearColor, skin);
	}

	@Override
	public void build(Stage stage, boolean debug) {

		Skin skin = this.getSkin();

		// Table setup
		Table table = new Table();
		table.defaults().pad(10f);
		table.setFillParent(true);
		table.setDebug(debug);
		table.center();

		// Title label
		Label titleLabel = new Label("World name", skin);
		titleLabel.setDebug(debug);
		titleLabel.setAlignment(Align.left);

		// Title text area
		TextArea titleTextArea = new TextArea("New World", skin);
		titleTextArea.setDebug(debug);
		titleTextArea.setAlignment(Align.left);

		// Title group
		VerticalGroup titleGroup = new VerticalGroup();
		titleGroup.setDebug(debug);
		titleGroup.space(16f);
		titleGroup.align(Align.left);
		titleGroup.addActor(titleLabel);
		titleGroup.addActor(titleTextArea);
		titleGroup.expand().fill();

		// Size label
		Label sizeLabel = new Label("World size", skin);
		sizeLabel.setDebug(debug);
		sizeLabel.setAlignment(Align.left);

		// Size check box
		SelectBox<String> sizeSelectBox = new SelectBox<>(skin);
		sizeSelectBox.setDebug(debug);
		sizeSelectBox.setAlignment(Align.left);
		sizeSelectBox.setItems("16x16", "32x32", "64x64");

		// Size group
		VerticalGroup sizeGroup = new VerticalGroup();
		sizeGroup.setDebug(debug);
		sizeGroup.space(16f);
		sizeGroup.align(Align.left);
		sizeGroup.addActor(sizeLabel);
		sizeGroup.addActor(sizeSelectBox);
		sizeGroup.expand().fill();

		table.add(titleGroup).width(400).fillX();
		table.add(sizeGroup).width(400).fillX();
		table.row();

		// Creative mode checkbox
		CheckBox checkBox = new CheckBox("Creative mode: OFF", skin);
		checkBox.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				checkBox.getText();
				checkBox.setText("Creative mode: " + (checkBox.isChecked() ? "ON" : "OFF"));
			}
		});

		table.add(checkBox).colspan(2).fillX();
		table.row();

		// Back button
		TextButton backButton = new TextButton("Back", skin);
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				dismiss();
			}
		});

		table.add(backButton).width(400).fillX();

		TextButton createButton = new TextButton("Create", skin);
		createButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GameView gameView;
				Labyrinth labyrinth = null;
				if (checkBox.isChecked()) {
					gameView = new BuilderView(16, 16);
				} else {
					FileHandle fileHandle = Gdx.files.internal("labys.txt");
					if (fileHandle.exists()) {
						LabyrinthLoader loader = LabyrinthLoader.shared();
						try {
							labyrinth = loader.loadFile("labys.txt");
						} catch (LabyrinthLoaderException ignored) {

						}
					}

					if (labyrinth == null) {
						labyrinth = new Labyrinth(16,16);
					}

					World world = new World(labyrinth);
					gameView = new GameView(world);
				}

				present(gameView);
			}
		});

		table.add(createButton).width(400).fillX();

		stage.addActor(table);
	}
}
