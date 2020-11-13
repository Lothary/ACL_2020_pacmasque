package fr.ul.pacmasque;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import fr.ul.pacmasque.view.SplashView;
import fr.ul.pacmasque.view.View;
import fr.ul.pacmasque.view.hierarchy.NavigationController;
import fr.ul.pacmasque.view.menu.MainMenuView;
import fr.ul.pacmasque.view.menu.MenuView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Pacmasque extends Game {

	public static final int V_WIDTH = 1080;
	public static final int V_HEIGHT = 720;

	@Nullable private NavigationController<View> navigationController = null;

	@Override
	public void create() {
		Gdx.graphics.setContinuousRendering(true);

		// Set up of the spash view
		@NotNull View splashView = new SplashView(V_WIDTH, V_HEIGHT);
		this.navigationController = new NavigationController<>(splashView, V_WIDTH, V_HEIGHT);

		// Retrieve the default skin
		@Nullable FileHandle skinFileHandle = Gdx.files.internal("skin/craftacular/craftacular-ui.json");
		assert skinFileHandle != null;
		Skin skin = new Skin(skinFileHandle);

		// Set up main menu view
		@NotNull MenuView mainMenu = new MainMenuView(V_WIDTH, V_HEIGHT, skin, null, this.navigationController);

		// Push the menu view on the view stack
		this.navigationController.pushScreen(mainMenu, null);

		/*
		Labyrinth labyrinth = null;
		BuilderView builderView = null;

		FileHandle fileHandle = Gdx.files.external("export.json");
		if (fileHandle.exists() && !fileHandle.isDirectory()) {
			byte[] content = fileHandle.readBytes();

			Decoder<Labyrinth> decoder = new LabyrinthDecoder();
			try {
				labyrinth = decoder.decode(content);
			} catch (DecoderException e) {
				System.err.println(e.getMessage());
			}
		}

		if (labyrinth == null) {
			builderView = new BuilderView(25, 25);
		} else {
			builderView = new BuilderView(labyrinth);
		}

		//Transition transition = new BlendingTransition(new SpriteBatch(), 10);
		//this.navigationController.pushScreen(builderView, null);*/
	}

	@Override
	public void render() {
		assert this.navigationController != null;
		this.navigationController.render();
	}

	@Override
	public void dispose() {
		assert this.navigationController != null;
		this.navigationController.dispose();
	}
}
