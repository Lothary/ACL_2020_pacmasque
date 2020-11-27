/*
 * MagicCase.java
 * ACL_2020_pacmasque
 *
 * Created by ValerieMarissens on 24/11/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.exception.TextureException;
import fr.ul.pacmasque.util.TexturePack;
import fr.ul.pacmasque.util.TexturePackFactory;

import java.util.Objects;

public class MagicCase implements Case {
    private final Vector2 position;

    public MagicCase(int x, int y) {
        this.position = new Vector2(x, y);
    }

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public Texture getTexture() throws TextureException {
        return Objects.requireNonNull(TexturePackFactory.getInstance().getTexturePack("basepack")).get(TexturePack.typeTexture.magic);
    }
}
