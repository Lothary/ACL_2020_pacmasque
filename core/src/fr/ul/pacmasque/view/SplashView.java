/*
 * MenuView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 14/10/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import fr.ul.pacmasque.Pacmasque;

/**
 * Écran de chargement du jeu
 */
public class SplashView extends View {

	private final Stage stage;
	private final Texture texture;

	public SplashView(float viewPortWidth, float viewPortHeight) {
		super(viewPortWidth, viewPortHeight, null);

		this.stage = new Stage(this.getViewport());

		texture = new Texture(Gdx.files.internal("splashLogo.png"));
		Image splashImage = new Image(texture);
		//texture.dispose();
		splashImage.setPosition((Pacmasque.V_WIDTH - texture.getWidth()) / 2f,
				(Pacmasque.V_HEIGHT - texture.getHeight()) / 2f);

		this.stage.addActor(splashImage);
	}

	@Override
	public void update(float delta) {
		this.stage.act(delta);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		Batch batch = this.getBatch();

		batch.begin();
		batch.draw(this.texture, 0, 0);
		batch.end();
		//this.stage.draw();
	}

	@Override
	public void dispose() {
		super.dispose();
		this.texture.dispose();
	}
}
