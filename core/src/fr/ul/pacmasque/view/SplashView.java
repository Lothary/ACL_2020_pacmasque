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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import fr.ul.pacmasque.view.hierarchy.StageView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Écran de chargement du jeu
 */
public class SplashView extends StageView {

	@Nullable  private Texture splashTexture;

	public SplashView(float viewPortWidth, float viewPortHeight) {
		//noinspection ConstantConditions
		super(viewPortWidth, viewPortHeight, null, null);
		this.splashTexture = null;
	}

	@Override
	public void build(@NotNull Stage stage, boolean debug) {
		splashTexture = new Texture(Gdx.files.internal("splashLogo.png"));
		Image splashImage = new Image(splashTexture);

		splashImage.setAlign(Align.center);
		splashImage.setFillParent(true);

		stage.addActor(splashImage);
	}

	@Override
	public void dispose() {
		super.dispose();
		if (this.splashTexture != null) {
			this.splashTexture.dispose();
		}
	}
}
