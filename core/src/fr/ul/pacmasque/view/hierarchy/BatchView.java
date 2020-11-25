/*
 * BatchView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 14/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.hierarchy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import fr.ul.pacmasque.Pacmasque;
import fr.ul.pacmasque.util.TexturePack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.mockito.Mockito.mock;

/**
 * Vue contenant un batch. Elle gère elle-même le cycle de vue du batch
 */
public abstract class BatchView extends View implements Disposable {

	/**
	 * Le batch de la vue
	 */
	@NotNull private final Batch batch;

	/**
	 * Crée une vue munie d'un batch
	 * @param width sa largeur
	 * @param height sa hauteur
	 * @param backgroundColor sa couleur de fond
	 * @implNote en cas d'environnement de tests headless, le batch sera remplacé par un mock
	 */
	public BatchView(float width, float height, @Nullable Color backgroundColor) {
		super(width, height, backgroundColor);

		this.batch = Pacmasque.ENVIRONMENT == Pacmasque.Environment.HEADLESS_TEST ? mock(SpriteBatch.class) : new SpriteBatch();
		this.batch.enableBlending();
	}

	/**
	 * @return le batch de la vue
	 */
	public @NotNull Batch getBatch() {
		return this.batch;
	}

	@Override
	public void render(float delta) {
		super.render(delta);
	}

	@Override
	public void dispose() {
		super.dispose();
		this.batch.dispose();
	}
}
