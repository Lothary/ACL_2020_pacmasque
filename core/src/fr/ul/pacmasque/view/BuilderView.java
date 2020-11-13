/*
 * BuilderView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 10/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.model.Labyrinth;
import fr.ul.pacmasque.model.World;
import fr.ul.pacmasque.util.encoder.Encoder;
import fr.ul.pacmasque.util.encoder.LabyrinthEncoder;
import org.json.JSONArray;
import org.json.JSONObject;

public class BuilderView extends GameView {

	private boolean ctrlDown = false;

	/**
	 * Crée un constructeur de labyrinthe vide, de taille donnée
	 * @param width la largeur du labyrinthe
	 * @param height la hauteur du labyrinthe
	 */
	public BuilderView(int width, int height) {
		super(new World(new Labyrinth(width, height)));
	}

	/**
	 * Crée un constructeur de labyrinthe sur un labyrinthe déjà existant
	 * @param labyrinth un labyrinthe déjà existant
	 */
	public BuilderView(Labyrinth labyrinth) {
		super(new World(labyrinth));
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 screenCoordinates = new Vector2(screenX, screenY);
		Vector2 worldCoordinates = this.getViewport().unproject(screenCoordinates);
		Labyrinth labyrinth = this.getWorld().getLabyrinth();
		labyrinth.setMur((int) worldCoordinates.x, (int) worldCoordinates.y);
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.CONTROL_LEFT || keycode == Input.Keys.CONTROL_RIGHT) {
			ctrlDown = true;
		}

		if (keycode == Input.Keys.S && this.ctrlDown) {
			Encoder<Labyrinth> labyrinthEncoder = new LabyrinthEncoder();
			byte[] encodedLabyrinth = labyrinthEncoder.encode(this.getWorld().getLabyrinth());

			FileHandle fileHandle = Gdx.files.external("export.json");
			fileHandle.writeBytes(encodedLabyrinth, false);
			System.out.println(fileHandle.path());
		}

		return super.keyDown(keycode);
	}
}
