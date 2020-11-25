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
import fr.ul.common.graphics.CGSize;
import fr.ul.pacmasque.model.Labyrinth;
import fr.ul.pacmasque.model.World;
import fr.ul.pacmasque.util.generator.KruskalGenerator;
import fr.ul.pacmasque.util.generator.LabyrinthGenerator;
import fr.ul.pacmasque.util.generator.LabyrinthGeneratorException;
import fr.ul.pacmasque.view.game.BuilderView;
import fr.ul.pacmasque.view.game.GameView;
import fr.ul.pacmasque.view.hierarchy.StageView;
import fr.ul.pacmasque.view.hierarchy.View;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NewWorldMenuView extends StageView {

	public static CGSize[] SIZES = new CGSize[]{new CGSize(7, 7), new CGSize(15, 15), new CGSize(31, 31), new CGSize(63, 63)};

	public NewWorldMenuView(float viewportWidth, float viewportHeight, @Nullable Color clearColor, @NotNull Skin skin) {
		super(viewportWidth, viewportHeight, clearColor, skin);
	}

	@Override
	public void build(@NotNull Stage stage, boolean debug) {

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
		SelectBox<CGSize> sizeSelectBox = new SelectBox<>(skin);
		sizeSelectBox.setDebug(debug);
		sizeSelectBox.setAlignment(Align.left);
		sizeSelectBox.setItems(SIZES);

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
				// Une taille doit être sélectionnée
				final CGSize selectedSize = sizeSelectBox.getSelected();

				if (selectedSize == null) {
					// TODO: Afficher un message d'erreur
					return;
				}

				final String worldtitle = titleTextArea.getText().trim();
				if (worldtitle.isEmpty()) {
					// TODO: Afficher un message d'erreur
					return;
				}

				final boolean creativeModeEnabled = checkBox.isChecked();

				View view = getNextView(creativeModeEnabled, worldtitle, selectedSize);
				if (view != null) {
					present(view);
				}
			}
		});

		table.add(createButton).width(400).fillX();

		stage.addActor(table);
	}

	@ApiStatus.Experimental
	@Nullable private View getNextView(boolean creativeMode, @NotNull String worldTitle, @NotNull CGSize worldSize) {

		if (creativeMode) {
			final Labyrinth labyrinth = new Labyrinth((int) worldSize.width, (int) worldSize.height);
			// TODO: - Utiliser le nom du world dans sa création
			return new BuilderView(labyrinth);
		}

		// TODO: - Sélectionne un algorithme de création
		// TODO: - Choisir un seed
		LabyrinthGenerator generator = new KruskalGenerator(0);

		try {
			// TODO: - Déléguer l'appel de l'algorithme dans une nouvelle vue, permettant l'affichage de la progression de celui-ci. Pour une grande taille, il peut bloquer le thread graphique
			final Labyrinth labyrinth = generator.generate((int) worldSize.width, (int) worldSize.height);

			// TODO: - Utiliser le nom du world dans sa création
			final World world = new World(labyrinth);
			return new GameView(world);

		} catch (LabyrinthGeneratorException e) {
			// TODO: Afficher un message d'erreur
			e.printStackTrace();
		}

		return null;
	}
}
