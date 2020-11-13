/*
 * PacmasqueTest.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 13/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class PacmasqueTest {

	static {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

		cfg.title = "Test";
		cfg.width = 2;
		cfg.height = 2;
		LwjglApplicationConfiguration.disableAudio = true;

		new LwjglApplication(new Pacmasque(), cfg);
	}

}
