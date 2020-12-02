/*
 * Case.java
 * ACL-2020-pacmasque
 *
 * Created by ugocottin on 2/12/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.model.cases;

import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.Drawable;
import org.jetbrains.annotations.NotNull;

/**
 * Interface des cases spéciales :
 * - MagicCase
 * - TeleportationCase
 * - TrapCase
 * - TreasureCase
 */
public abstract class Case implements Drawable {

    /**
     * Symbolise le type de case spéciale.
     */
    public enum TypeCase {
        treasure,
        trap,
        magic,
        teleportation
    }

    @NotNull private final Vector2 position;

    public Case(int x, int y) {
    	this.position = new Vector2(x, y);
    }

    @NotNull public Vector2 getPosition() {
    	return this.position;
    }

	@NotNull public abstract TypeCase getType();
}
