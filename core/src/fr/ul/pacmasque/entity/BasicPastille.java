/*
 * BasicPastille.java
 * ACL_2020_pacmasque
 *
 * Created by Acer on 31/10/2020.
 * Copyright © 2020 Acer. All rights reserved.
 */

package fr.ul.pacmasque.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.exception.TextureException;
import fr.ul.pacmasque.util.TexturePack;
import fr.ul.pacmasque.util.TexturePackFactory;

import java.util.Objects;

public class BasicPastille  implements Pastille {
	private final Vector2 position;
	private Texture texture;
	private boolean visible;

	public BasicPastille(int x, int y){
		this.position = new Vector2(x,y);

		try {
			this.texture = Objects.requireNonNull(TexturePackFactory.getInstance().getTexturePack("basepack")).get(TexturePack.typeTexture.pastille);
		} catch (TextureException e) {
			e.printStackTrace();
		}
		visible = true;
	}

	public Vector2 getPosition() {
		return this.position;
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		batch.draw(texture,this.position.x + 0.25f,this.position.y + 0.25f,0.4f,0.4f);
	}

	@Override
	public boolean isVisible() { return this.visible; }
	@Override
	public void setVisible(boolean visible){this.visible = visible;}
}
