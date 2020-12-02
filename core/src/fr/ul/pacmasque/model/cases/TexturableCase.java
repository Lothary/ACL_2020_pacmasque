/*
 * TexturableCase.java
 * ACL-2020-pacmasque
 *
 * Created by ugocottin on 2/12/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.model.cases;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import fr.ul.pacmasque.exception.TextureException;
import fr.ul.pacmasque.util.TexturePack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class TexturableCase extends Case {

	public static final Color FALLBACK_COLOR = Color.CORAL;

	@NotNull private Texture texture;

	public TexturableCase(int x, int y, @Nullable TexturePack pack) {
		super(x, y);

		if (pack == null) {
			getFallbackTexture();
			return;
		}

		try {
			this.texture = pack.get(this.getTypeTexture());
		} catch (TextureException e) {
			this.getFallbackTexture();
		}
	}

	private void getFallbackTexture() {
		this.texture = TexturePack.getFallbackTexture(FALLBACK_COLOR);
	}

	@NotNull public Texture getTexture() {
		return this.texture;
	}

	protected abstract TexturePack.TypeTexture getTypeTexture();

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		batch.draw(this.getTexture(), this.getPosition().x, this.getPosition().y,1f,1f);
	}
}
