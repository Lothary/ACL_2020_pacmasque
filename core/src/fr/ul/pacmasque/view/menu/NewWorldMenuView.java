/*
 * NewWorldMenuView.java
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
import fr.ul.pacmasque.view.View;
import fr.ul.pacmasque.view.hierarchy.NavigationController;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NewWorldMenuView extends MenuView {

	public NewWorldMenuView(float viewportWidth, float viewportHeight, @NotNull Skin skin, @Nullable Color clearColor, @Nullable NavigationController<View> navigationController) {
		super(viewportWidth, viewportHeight, skin, clearColor, navigationController);
	}

	@Override
	public void build(Stage stage, boolean debug) {

		Skin skin = this.getSkin();

		Table table = new Table();
		table.defaults().pad(10f);
		table.setFillParent(true);
		table.setDebug(debug);
		table.center();

		TextButton button = new TextButton("Back", skin);
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				NavigationController<View> navigationController = getNavigationController();
				if (navigationController != null) {
					navigationController.popScreen();
				}
			}
		});

		Label label = new Label("Nom du monde", skin);
		label.setDebug(debug);

		TextArea textArea = new TextArea("New World", skin);
		textArea.setDebug(debug);
		textArea.setAlignment(Align.left);

		VerticalGroup group = new VerticalGroup();
		group.setDebug(debug);
		group.space(10f);
		group.align(Align.left);
		group.addActor(button);
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
