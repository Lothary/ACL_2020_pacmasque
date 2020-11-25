/*
 * OverlayView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 25/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.hierarchy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Vue superposée à une autre
 */
public abstract class OverlayView extends StageView {
	/**
	 * Crée une vue munie d'une scène
	 *
	 * @param viewportWidth   la largeur du viewport
	 * @param viewportHeight  la hauteur du viewport
	 * @param backgroundColor sa couleur de fond
	 * @param skin            le skin utilisé par la scène
	 */
	public OverlayView(float viewportWidth, float viewportHeight, @Nullable Color backgroundColor, @NotNull Skin skin) {
		super(viewportWidth, viewportHeight, backgroundColor, skin);
	}

	@Override
	public boolean shouldClearScreen() {
		return false;
	}
}
