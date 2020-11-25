/*
 * NewWorldMenuView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 12/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import fr.ul.common.graphics.CGSize;
import fr.ul.pacmasque.model.Labyrinth;
import fr.ul.pacmasque.model.World;
import fr.ul.pacmasque.util.generator.Generators;
import fr.ul.pacmasque.util.generator.LabyrinthGenerator;
import fr.ul.pacmasque.util.generator.LabyrinthGeneratorException;
import fr.ul.pacmasque.view.game.BuilderView;
import fr.ul.pacmasque.view.game.GameView;
import fr.ul.pacmasque.view.hierarchy.StageView;
import fr.ul.pacmasque.view.hierarchy.View;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class NewWorldMenuView extends StageView {

	private static class _NewWorldSettings {
		boolean creativeMode = false;
		CGSize size = new CGSize(7,7);
		String title = "New World";
		String seed = "" + System.currentTimeMillis();
		String generator = "";
	}

	public static CGSize[] SIZES = new CGSize[]{new CGSize(7, 7), new CGSize(15, 15), new CGSize(31, 31), new CGSize(63, 63)};

	private final _NewWorldSettings settings = new _NewWorldSettings();

	@NotNull private final Generators generators;

	public NewWorldMenuView(float viewportWidth, float viewportHeight, @Nullable Color clearColor, @NotNull Skin skin) {
		super(viewportWidth, viewportHeight, clearColor, skin);

		generators = Generators.shared();
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

		Actor titleGroup = this.withTitle("World title", skin, () -> {
			TextArea titleTextArea = new TextArea(settings.title, skin);
			titleTextArea.setDebug(debug);
			titleTextArea.setAlignment(Align.left);

			titleTextArea.setTextFieldListener((textField, c) -> settings.title = textField.getText());

			return titleTextArea;
		});

		Actor sizeGroup = this.withTitle("World size", skin, () -> {
			SelectBox<CGSize> sizeSelectBox = new SelectBox<>(skin);
			sizeSelectBox.setDebug(debug);
			sizeSelectBox.setAlignment(Align.left);
			sizeSelectBox.setItems(SIZES);

			sizeSelectBox.addCaptureListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					settings.size = sizeSelectBox.getSelected();
				}
			});

			return sizeSelectBox;
		});

		table.add(titleGroup).width(400).fillX();
		table.add(sizeGroup).width(400).fillX();
		table.row();

		// Creative mode checkbox
		Actor creativeModeGroup = this.withTitle("Creative mode", skin, () -> {
			CheckBox checkBox = new CheckBox(settings.creativeMode ? "ON" : "OFF", skin);
			checkBox.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					checkBox.getText();
					settings.creativeMode = checkBox.isChecked();
					checkBox.setText((settings.creativeMode ? "ON" : "OFF"));
				}
			});

			return checkBox;
		});

		// Seed text area
		Actor seedGroup = this.withTitle("Seed", skin, () -> {
			TextArea seedTextArea = new TextArea(settings.seed, skin);
			seedTextArea.setDebug(debug);
			seedTextArea.setAlignment(Align.bottomLeft);

			seedTextArea.setTextFieldListener((textField, c) -> settings.seed = textField.getText());

			return seedTextArea;
		});

		Actor generatorGroup = this.withTitle("Generator", skin, () -> {
			SelectBox<String> generatorSelectBox = new SelectBox<>(skin);
			generatorSelectBox.setAlignment(Align.left);
			Array<String> array = new Array<>();
			array.add("");
			array.addAll(generators.getAvailableGenerators());
			generatorSelectBox.setItems(array);

			generatorSelectBox.addCaptureListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					settings.generator = generatorSelectBox.getSelected();
				}
			});

			return generatorSelectBox;
		});

		table.add(creativeModeGroup).width(400).fillX();
		table.add(seedGroup).width(400).fillX();
		table.row();
		table.add(generatorGroup).width(400).fillX();
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
				if (settings.size == null) {
					// TODO: Afficher un message d'erreur
					return;
				}

				final String worldtitle = settings.title.trim();
				if (worldtitle.isEmpty()) {
					// TODO: Afficher un message d'erreur
					return;
				}

				final boolean creativeModeEnabled = settings.creativeMode;

				long seed;
				try {
					seed = Long.parseLong(settings.seed);
				} catch (NumberFormatException e) {
					// TODO: Afficher un message d'erreur
					return;
				}

				final String generatorTitle = settings.generator.trim();
				if (generatorTitle.isEmpty()) {
					// TODO: Afficher un message d'erreur
					return;
				}

				View view = getNextView(creativeModeEnabled, worldtitle, settings.size, seed, generatorTitle);
				if (view != null) {
					present(view);
				}
			}
		});

		table.add(createButton).width(400).fillX();

		stage.addActor(table);
	}

	@ApiStatus.Experimental
	private Actor withTitle(String title, Skin skin, Supplier<Actor> callback) {

		// Label
		Label label = new Label(title, skin);
		label.setAlignment(Align.left);

		// Widget
		Actor widget = callback.get();

		// Group
		VerticalGroup group = new VerticalGroup();
		group.space(16f);
		group.align(Align.left);
		group.addActor(label);
		group.addActor(widget);
		group.expand().fill();

		return group;
	}

	@ApiStatus.Experimental
	@Nullable private View getNextView(boolean creativeMode, @NotNull String worldTitle, @NotNull CGSize worldSize, long seed, String generatorName) {

		if (creativeMode) {
			final Labyrinth labyrinth = new Labyrinth((int) worldSize.width, (int) worldSize.height);
			// TODO: - Utiliser le nom du world dans sa création
			return new BuilderView(labyrinth);
		}

		// TODO: - Sélectionne un algorithme de création
		Supplier<LabyrinthGenerator> generatorFactory = this.generators.getGeneratorFactory(generatorName);

		if (generatorFactory == null) {
			// TODO: Afficher un message d'erreur
			return null;
		}

		LabyrinthGenerator generator = generatorFactory.get();

		try {
			// TODO: - Déléguer l'appel de l'algorithme dans une nouvelle vue, permettant l'affichage de la progression de celui-ci. Pour une grande taille, il peut bloquer le thread graphique
			final Labyrinth labyrinth = generator.generate(seed, (int) worldSize.width, (int) worldSize.height);

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
