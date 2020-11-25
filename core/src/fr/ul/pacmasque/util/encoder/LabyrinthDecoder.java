/*
 * LabyrinthDecoder.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 11/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.encoder;

import fr.ul.pacmasque.model.Labyrinth;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Décodeur d'un labyrinthe au format JSON
 */
public class LabyrinthDecoder implements Decoder<Labyrinth> {

	@Override
	public @NotNull Labyrinth decode(byte[] decodable) throws DecoderException {
		String content = new String(decodable, STANDARD_CHARSET);

		try {
			JSONObject rootObject = new JSONObject(content);

			int width = rootObject.getInt("width");
			int height = rootObject.getInt("height");

			Labyrinth labyrinth = new Labyrinth(width, height);

			JSONArray walls = rootObject.getJSONArray("walls");
			for (int indice = 0; indice < walls.length(); indice++) {
				final JSONObject wall = walls.getJSONObject(indice);
				int x = wall.getInt("x");
				int y = wall.getInt("y");

				labyrinth.setMur(x, y);
			}

			return labyrinth;

		} catch (JSONException e) {
			throw new DecoderException(decodable, -1, e.getMessage());
		}
	}
}
