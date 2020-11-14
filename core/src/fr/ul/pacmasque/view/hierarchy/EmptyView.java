/*
 * EmptyView.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 14/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.hierarchy;

import com.badlogic.gdx.graphics.Color;
import org.jetbrains.annotations.Nullable;

public class EmptyView extends View {

	public EmptyView(float width, float height, @Nullable Color backgroundColor) {
		super(width, height, backgroundColor);
	}

	@Override
	public void update(float delta) {

	}
}
