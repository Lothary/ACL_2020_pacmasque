/*
 * TrapCase.java
 * ACL-2020-pacmasque
 *
 * Created by ugocottin on 2/12/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.model.cases;

import fr.ul.pacmasque.util.TexturePack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TrapCase extends TexturableCase {

    public TrapCase(int x, int y, @Nullable TexturePack pack){
    	super(x, y, pack);
    }

    @Override
    public @NotNull TypeCase getType() {
        return TypeCase.trap;
    }

	@Override
	protected TexturePack.TypeTexture getTypeTexture() {
		return TexturePack.TypeTexture.trap;
	}
}
