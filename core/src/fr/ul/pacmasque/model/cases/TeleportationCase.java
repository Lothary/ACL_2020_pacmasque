/*
 * TeleportationCase.java
 * ACL-2020-pacmasque
 *
 * Created by ugocottin on 2/12/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.model.cases;

import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.util.TexturePack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TeleportationCase extends TexturableCase {

	@ApiStatus.Experimental
	private final Vector2 nextPosition; // todo: par couples ? Oui

    public TeleportationCase(int x, int y, @Nullable TexturePack pack) {
    	super(x, y, pack);
        this.nextPosition = this.getPosition();
    }

    @Override
    public @NotNull TypeCase getType() {
        return TypeCase.teleportation;
    }

	@Override
	protected TexturePack.TypeTexture getTypeTexture() {
		return TexturePack.TypeTexture.teleportation;
	}
}
