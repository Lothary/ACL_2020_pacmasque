/*
 * GameView.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameView extends View {

	private final OrthographicCamera camera;
	private final Batch batch;
	private final Texture texture = new Texture("badlogic.jpg");

	public GameView() {
		this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.camera.setToOrtho(true, 15, 10);
		this.camera.update();

		this.batch = new SpriteBatch();
		this.batch.setProjectionMatrix(this.camera.combined);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		this.camera.rotate(1);
		this.camera.update();
		this.batch.setProjectionMatrix(this.camera.combined);
		this.batch.begin();
		this.batch.draw(this.texture, 1, 1, 13, 8);
		this.batch.end();
	}

	@Override
	public void dispose() {
		super.dispose();
		this.batch.dispose();
		this.texture.dispose();
	}
}
