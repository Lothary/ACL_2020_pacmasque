package fr.ul.pacmasque;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import fr.ul.pacmasque.view.SplashView;
import fr.ul.pacmasque.view.control.navigation.NavigationController;
import fr.ul.pacmasque.view.control.navigation.NavigationViewController;
import fr.ul.pacmasque.view.hierarchy.StageView;
import fr.ul.pacmasque.view.hierarchy.View;
import fr.ul.pacmasque.view.menu.MainMenuView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Pacmasque extends Game {

	public enum Environment {
		PRODUCTION,
		DEVELOPMENT,
		HEADLESS_TEST
	}

	public static final boolean DEBUG = false;
	@NotNull public static Environment ENVIRONMENT;

	public static final int V_WIDTH = 1080;
	public static final int V_HEIGHT = 720;

	@NotNull private final NavigationController<View> navigationViewController;

	public Pacmasque() {
		this(Environment.PRODUCTION);
	}
	public Pacmasque(@NotNull Environment environment) {
		Pacmasque.ENVIRONMENT = environment;
		this.navigationViewController = new NavigationViewController(V_WIDTH, V_HEIGHT);
	}

	@Override
	public void create() {
		Gdx.graphics.setContinuousRendering(true);

		// Set up of the splash view
		@NotNull StageView splashView = new SplashView(V_WIDTH, V_HEIGHT);
		splashView.navigationController = this.navigationViewController;
		this.navigationViewController.present(splashView);

		// Retrieve the default skin
		@Nullable FileHandle skinFileHandle = Gdx.files.internal("skin/craftacular/craftacular-ui.json");
		assert skinFileHandle != null;
		Skin skin = new Skin(skinFileHandle);

		// Set up main menu view
		@NotNull StageView mainMenu = new MainMenuView(V_WIDTH, V_HEIGHT, Color.valueOf("#111111"), skin);
		mainMenu.navigationController = this.navigationViewController;

		// Push the menu view on the view stack
		this.navigationViewController.present(mainMenu);
	}

	@Override
	public void resize(int width, int height) {
		this.navigationViewController.resize(width, height);
	}

	@Override
	public void render() {
		if (Pacmasque.ENVIRONMENT != Environment.HEADLESS_TEST) {
			this.navigationViewController.render(Gdx.graphics.getDeltaTime());
		}
	}

	@Override
	public void dispose() {
		this.navigationViewController.dispose();
	}
}
