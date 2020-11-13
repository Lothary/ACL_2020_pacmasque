/*
 * PacmasqueTest.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 13/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.graphics.GL20;

import static org.mockito.Mockito.mock;

public class PacmasqueTest {

	static {
		new HeadlessApplication(new Pacmasque());
		Gdx.gl = mock(GL20.class);
	}

}
