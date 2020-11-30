/*
 * Case.java
 * ACL_2020_pacmasque
 *
 * Created by ValerieMarissens on 24/11/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.model;

import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.Drawable;

/**
 * Interface des cases spéciales :
 * - MagicCase
 * - TeleportationCase
 * - TrapCase
 * - TreasureCase
 */
interface Case extends Drawable {
    /**
     * Symbolise le type de case spéciale.
     */
    enum typeCase {
        treasure,
        trap,
        magic,
        teleportation
    }

    Vector2 getPosition();

    typeCase getType();
}
