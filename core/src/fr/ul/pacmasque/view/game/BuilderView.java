/*
 * BuilderView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 15/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.model.Labyrinth;
import fr.ul.pacmasque.model.World;
import fr.ul.pacmasque.util.encoder.Encoder;
import fr.ul.pacmasque.util.encoder.EncoderException;
import fr.ul.pacmasque.util.encoder.LabyrinthEncoder;
import org.jetbrains.annotations.NotNull;

/**
 * Vue permettant la construction d'un labyrinthe
 */
public class BuilderView extends GameView {

	private boolean ctrlDown = false;

	/**
	 * Crée un constructeur de labyrinthe vide, de taille donnée
	 * @param width la largeur du labyrinthe
	 * @param height la hauteur du labyrinthe
	 */
	public BuilderView(int width, int height, @NotNull String worldName) {
		super(new World(new Labyrinth(width, height), worldName));
	}

	/**
	 * Crée un constructeur de labyrinthe sur un labyrinthe déjà existant
	 * @param labyrinth un labyrinthe déjà existant
	 */
	public BuilderView(Labyrinth labyrinth, @NotNull String worldName) {
		super(new World(labyrinth, worldName));
	}

	@Override
	public void update(float delta) {
		// TODO: Drag de la vue avec le pointeur
		this.getWorld().moveMonsters();
		this.getWorld().updateCollision();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 screenCoordinates = new Vector2(screenX, screenY);
		Vector2 worldCoordinates = this.getViewport().unproject(screenCoordinates);
		Labyrinth labyrinth = this.getWorld().getLabyrinth();
		labyrinth.setMur((int) worldCoordinates.x, (int) worldCoordinates.y);
		return true;
	}

	@Override
	public boolean keyDown(int keycode) {
		super.keyDown(keycode);

		if (keycode == Input.Keys.CONTROL_LEFT || keycode == Input.Keys.CONTROL_RIGHT) {
			ctrlDown = true;
			return true;
		}

		if (keycode == Input.Keys.S && this.ctrlDown) {
			// TODO: - Changer d'encoder ?
			Encoder<Labyrinth> labyrinthEncoder = new LabyrinthEncoder();

			try {
				World world = this.getWorld();
				byte[] encodedLabyrinth = labyrinthEncoder.encode(world.getLabyrinth());

				// TODO: - Changer le répertoire de sauvegarde
				String worldName = world.getWorldName().replace(' ', '_');
				FileHandle fileHandle = Gdx.files.external(worldName + ".json");
				fileHandle.writeBytes(encodedLabyrinth, false);
			} catch (EncoderException e) {
				e.printStackTrace();

			}


			return true;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		super.keyUp(keycode);

		if (keycode == Input.Keys.CONTROL_LEFT || keycode == Input.Keys.CONTROL_RIGHT) {
			ctrlDown = false;
			return true;
		}

		return false;
	}
}
