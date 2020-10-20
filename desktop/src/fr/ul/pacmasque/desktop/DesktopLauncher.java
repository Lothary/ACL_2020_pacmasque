package fr.ul.pacmasque.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.ul.pacmasque.Pacmasque;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.backgroundFPS = 200;
		config.foregroundFPS = 200;
		config.width = 1080;
		config.height = 720;
		new LwjglApplication(new Pacmasque(), config);
	}
}
