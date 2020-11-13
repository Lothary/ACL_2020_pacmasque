/*
 * LabyrinthEncoder.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 11/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.encoder;

import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.model.Labyrinth;
import org.json.JSONArray;
import org.json.JSONObject;

public class LabyrinthEncoder implements Encoder<Labyrinth> {

	@Override
	public byte[] encode(Labyrinth labyrinth) {

		JSONObject rootObject = new JSONObject();

		JSONArray walls = new JSONArray();
		for (Vector2 wall : labyrinth.getWalls()) {
			JSONObject jsonWall = new JSONObject();
			jsonWall.put("x", wall.x);
			jsonWall.put("y", wall.y);
			walls.put(jsonWall);
		}

		rootObject.put("walls", walls);
		rootObject.put("width", labyrinth.getWidth());
		rootObject.put("height", labyrinth.getHeight());
		String encodedLabyrinth = rootObject.toString(4);
		return encodedLabyrinth.getBytes(STANDARD_CHARSET);
	}
}
