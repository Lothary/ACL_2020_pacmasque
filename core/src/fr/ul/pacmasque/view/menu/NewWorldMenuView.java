/*
 * NewWorldMenuView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 12/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NewWorldMenuView extends MenuView {

	public NewWorldMenuView(float viewportWidth, float viewportHeight, @NotNull Skin skin, @Nullable Color clearColor) {
		super(viewportWidth, viewportHeight, skin, clearColor);
	}

	@Override
	public void build(Stage stage, boolean debug) {

		Skin skin = this.getSkin();

		Table table = new Table();
		table.defaults().pad(10f);
		table.setFillParent(true);
		table.setDebug(debug);
		table.center();

		Label label = new Label("Nom du monde", skin);
		label.setDebug(debug);

		TextArea textArea = new TextArea("New World", skin);
		textArea.setDebug(debug);
		textArea.setAlignment(Align.left);

		VerticalGroup group = new VerticalGroup();
		group.setDebug(debug);
		group.space(10f);
		group.align(Align.left);
		group.addActor(label);
		group.addActor(textArea);
		group.fill().expand();

		table.add(group).expandX().width(300f);
		table.row();

		SelectBox<String> selectBox = new SelectBox<>(skin);
		selectBox.setDebug(debug);
		selectBox.setItems("16x16", "32x32", "64x64");
		table.add(selectBox).expandX().width(300f);

		stage.addActor(table);
	}
}
