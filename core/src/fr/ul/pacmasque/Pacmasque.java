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

/**
 * Classe principale du jeu, en charge de lancer le jeu
 */
public class Pacmasque extends Game {

	/**
	 * Environnement dans lequel l'application se trouve.
	 * Son comportement peut varier en fonction de celui-ci.
	 * Par défaut, en production.
	 */
	public enum Environment {
		/**
		 * Coportement "normal" en production
		 */
		PRODUCTION,

		/**
		 * Comportement normal, accompagné d'outils de débogage comme des logs ou de l'affichage supplémentaire
		 */
		DEBUG,

		/**
		 * Environnement de test, aucun rendu ne sera effectué dans les vues.
		 * La méthode View::render est désactivée dans cette environnement.
		 */
		HEADLESS_TEST
	}

	/**
	 * Environnement dans lequel évolue l'application
	 */
	@NotNull public static Environment ENVIRONMENT = Environment.PRODUCTION;

	/**
	 * Largeur de la fenêtre de base
	 */
	public static final int V_WIDTH = 1080;

	/**
	 * Hauteur de la fenêtre de base
	 */
	public static final int V_HEIGHT = 720;

	/**
	 * Contrôleur de navigation de l'application
	 */
	@NotNull private final NavigationController<View> navigationViewController;

	/**
	 * Crée une instance de jeu dans l'environnement par défaut
	 */
	public Pacmasque() {
		this(null);
	}

	/**
	 * Crée une instance du jeu dans un environnement donné
	 * @param environment l'environnement de l'application
	 */
	public Pacmasque(@Nullable Environment environment) {

		if (environment != null) {
			Pacmasque.ENVIRONMENT = environment;
		}
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
