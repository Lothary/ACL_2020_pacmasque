/*
 * Case.java
 * ACL_2020_pacmasque
 *
 * Created by ValerieMarissens on 24/11/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.exception.TextureException;

interface Case {
    Vector2 getPosition();

    // todo: chercher treasure image et la mettre dans la factory
    Texture getTexture() throws TextureException;
}
