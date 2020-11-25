/*
 * PauseMenuView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 25/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import fr.ul.pacmasque.model.Labyrinth;
import fr.ul.pacmasque.model.World;
import fr.ul.pacmasque.util.encoder.Encoder;
import fr.ul.pacmasque.util.encoder.EncoderException;
import fr.ul.pacmasque.util.encoder.LabyrinthEncoder;
import fr.ul.pacmasque.view.hierarchy.OverlayView;
import org.jetbrains.annotations.NotNull;

public class PauseMenuView extends OverlayView {

	@NotNull private final World world;

	/**
	 * Crée une vue munie d'une scène
	 *
	 * @param viewportWidth   la largeur du viewport
	 * @param viewportHeight  la hauteur du viewport
	 * @param skin            le skin utilisé par la scène
	 */
	public PauseMenuView(float viewportWidth, float viewportHeight, @NotNull Skin skin, @NotNull World world) {
		super(viewportWidth, viewportHeight, Color.valueOf("#111111EE"), skin);
		this.world = world;
	}

	@Override
	public void build(@NotNull Stage stage, boolean debug) {
		Skin skin = this.getSkin();

		Table table = new Table();
		table.defaults().pad(10f);
		table.setFillParent(true);
		table.setDebug(debug);
		table.center();

		Label titleLabel = new Label("Pause - Menu", skin);

		TextButton saveButton = new TextButton("Save", skin);
		saveButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Encoder<Labyrinth> labyrinthEncoder = new LabyrinthEncoder();

				try {
					byte[] encodedLabyrinth = labyrinthEncoder.encode(world.getLabyrinth());

					// TODO: - Changer le répertoire de sauvegarde
					String worldName = world.getWorldName().replace(' ', '_');
					FileHandle fileHandle = Gdx.files.external(worldName + ".json");
					fileHandle.writeBytes(encodedLabyrinth, false);
				} catch (EncoderException e) {
					e.printStackTrace();
					error("Impossible de sauvegarder le world");
				}
			}
		});

		TextButton backButton = new TextButton("Back", skin);
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				dismiss();
			}
		});

		TextButton exitButton = new TextButton("Exit", skin);
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				dismiss();dismiss();dismiss();
			}
		});

		table.add(titleLabel).width(400).fillX();
		table.row();
		table.add(saveButton).width(400).fillX();
		table.row();
		table.add(backButton).width(400).fillX();
		table.row();
		table.add(exitButton).width(400).fillX();
		table.row();

		stage.addActor(table);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.ESCAPE) {
			dismiss();
			return true;
		}

		return false;
	}
}
