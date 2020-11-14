package fr.ul.pacmasque;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import fr.ul.pacmasque.view.SplashView;
import fr.ul.pacmasque.view.hierarchy.StageView;
import fr.ul.pacmasque.view.hierarchy.View;
import fr.ul.pacmasque.view.menu.MainMenuView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Pacmasque extends Game {

	public static final boolean DEBUG = false;

	public static final int V_WIDTH = 1080;
	public static final int V_HEIGHT = 720;

	@Override
	public void create() {
		Gdx.graphics.setContinuousRendering(true);

		// Set up of the spash view
		@NotNull StageView splashView = new SplashView(V_WIDTH, V_HEIGHT);
		View.navigationController.present(splashView);

		// Retrieve the default skin
		@Nullable FileHandle skinFileHandle = Gdx.files.internal("skin/craftacular/craftacular-ui.json");
		assert skinFileHandle != null;
		Skin skin = new Skin(skinFileHandle);

		// Set up main menu view
		@NotNull StageView mainMenu = new MainMenuView(V_WIDTH, V_HEIGHT, Color.valueOf("#111111"), skin);

		// Push the menu view on the view stack
		View.navigationController.present(mainMenu);
	}

	@Override
	public void resize(int width, int height) {
		View.navigationController.resize(width, height);
	}

	@Override
	public void render() {
		View.navigationController.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		View.navigationController.dispose();
	}
}
