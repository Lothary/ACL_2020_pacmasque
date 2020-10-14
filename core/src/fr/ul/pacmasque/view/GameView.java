/*
 * GameView.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.model.Labyrinth;
import fr.ul.pacmasque.model.Object2D;
import fr.ul.pacmasque.model.World;

import java.util.List;

public class GameView extends View {

	private final Batch batch;
	private final Texture texture = new Texture("badlogic.jpg");
	private final World world;

	public GameView(World world) {
		this.world = world;
		Camera camera = this.cameraForDimensions(world);

		this.batch = new SpriteBatch();
		this.batch.setProjectionMatrix(camera.combined);
	}

	private Camera cameraForDimensions(Object2D object2D) {
		OrthographicCamera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true, object2D.getWidth(), object2D.getHeight());
		camera.update();

		return camera;
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		this.batch.begin();
		Labyrinth labyrinth = world.getLabyrinth();
		this.renderWalls(labyrinth.getMursPosition(), this.batch);
		this.batch.end();
	}

	private void renderWalls(List<Vector2> walls, Batch batch) {
		walls.forEach(wall -> {
			batch.draw(texture, wall.x, wall.y, 1, 1);
		});
	}

	@Override
	public void dispose() {
		super.dispose();
		this.batch.dispose();
		this.texture.dispose();
	}
}
