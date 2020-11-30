/*
 * TrapCase.java
 * ACL_2020_pacmasque
 *
 * Created by ValerieMarissens on 24/11/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.exception.TextureException;
import fr.ul.pacmasque.util.TexturePack;
import fr.ul.pacmasque.util.TexturePackFactory;

import java.util.Objects;

public class TrapCase implements Case {
    private final Vector2 position;
    private Texture texture;

    public TrapCase(int x, int y){
        this.position = new Vector2(x, y);
        try {
            this.texture = TexturePackFactory.getInstance().getTexturePack("basepack").get(TexturePack.typeTexture.trap);
        } catch (TextureException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public typeCase getType() {
        return typeCase.trap;
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        batch.draw(texture,this.position.x,this.position.y,1f,1f);
    }
}
