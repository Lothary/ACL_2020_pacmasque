/*
 * DeathView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 2/12/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.menu;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import fr.ul.pacmasque.Pacmasque;
import fr.ul.pacmasque.model.World;
import fr.ul.pacmasque.view.hierarchy.OverlayView;
import org.jetbrains.annotations.NotNull;

public class DeathView extends OverlayView {

	private final World world;

	/**
	 * Crée une vue munie d'une scène
	 *
	 * @param viewportWidth   la largeur du viewport
	 * @param viewportHeight  la hauteur du viewport
	 * @param skin            le skin utilisé par la scène
	 */
	public DeathView(float viewportWidth, float viewportHeight, @NotNull Skin skin, World world) {
		super(viewportWidth, viewportHeight, null, skin);
		this.world = world;
	}

	@Override
	public void show() {
		super.show();

		if (Pacmasque.ENVIRONMENT == Pacmasque.Environment.PRODUCTION) {
			FileHandle internal = Gdx.files.internal("sounds/lose.ogg");
			if (internal.exists()) {
				Sound sound = Gdx.audio.newSound(internal);
				sound.play();
			}
		}
	}

	@Override
	public void build(@NotNull Stage stage, boolean debug) {
		Skin skin = this.getSkin();

		Table table = new Table();
		table.defaults().pad(10f);
		table.setFillParent(true);
		table.setDebug(debug);
		table.center();

		Label titleLabel = new Label("Game Over", skin);

		TextButton restartButton = new TextButton("Restart", skin);
		restartButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				world.restart();
				dismiss();
			}
		});

		TextButton exitButton = new TextButton("Exit", skin);
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				dismiss();
				dismiss();
				dismiss();
			}
		});

		table.add(titleLabel).width(400).fillX();
		table.row();
		table.add(restartButton).width(400).fillX();
		table.row();
		table.add(exitButton).width(400).fillX();
		table.row();

		stage.addActor(table);
	}
}
