/*
 * ErrorView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 25/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import fr.ul.pacmasque.view.hierarchy.StageView;
import org.jetbrains.annotations.NotNull;

public class ErrorView extends StageView {

	private final String message;

	/**
	 * Crée une vue munie d'une scène
	 *
	 * @param viewportWidth   la largeur du viewport
	 * @param viewportHeight  la hauteur du viewport
	 * @param skin            le skin utilisé par la scène
	 */
	public ErrorView(float viewportWidth, float viewportHeight, @NotNull Skin skin, String message) {

		super(viewportWidth, viewportHeight, Color.valueOf("#111111EE"), skin);
		this.message = message;
	}

	@Override
	public void build(@NotNull Stage stage, boolean debug) {

		Skin skin = this.getSkin();

		Table table = new Table();
		table.defaults().pad(10f);
		table.setFillParent(true);
		table.setDebug(debug);
		table.center();

		Label titleLabel = new Label("Une erreur est survenue", skin);
		Label messageLabel = new Label(this.message, skin);

		TextButton okButton = new TextButton("Ok", skin);
		okButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				dismiss();
			}
		});

		table.add(titleLabel).width(400).fillX();
		table.row();
		table.add(messageLabel).width(400).fillX();
		table.row();
		table.add(okButton).width(400).fillX();
		table.row();

		stage.addActor(table);
	}
}
