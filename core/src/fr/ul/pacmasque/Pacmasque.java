package fr.ul.pacmasque;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import fr.ul.pacmasque.view.menu.MainMenuView;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.pacmasque.exception.LabyrinthLoaderException;
import fr.ul.pacmasque.model.Labyrinth;
import fr.ul.pacmasque.model.World;
import fr.ul.pacmasque.util.LabyrinthLoader;
import fr.ul.pacmasque.util.encoder.Decoder;
import fr.ul.pacmasque.util.encoder.DecoderException;
import fr.ul.pacmasque.util.encoder.LabyrinthDecoder;
import fr.ul.pacmasque.view.BuilderView;
import fr.ul.pacmasque.view.GameView;
import fr.ul.pacmasque.view.SplashView;
import fr.ul.pacmasque.view.View;
import fr.ul.pacmasque.view.menu.NewWorldMenuView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import fr.ul.pacmasque.view.hierarchy.NavigationController;
import fr.ul.pacmasque.view.hierarchy.transition.BlendingTransition;
import fr.ul.pacmasque.view.hierarchy.transition.Transition;

public class Pacmasque extends Game {

	public static final int V_WIDTH = 1080;
	public static final int V_HEIGHT = 720;

	private NavigationController<View> navigationController;

	@Override
	public void create() {
		Gdx.graphics.setContinuousRendering(true);
		Labyrinth labyrinth = null;
		BuilderView builderView = null;
		SplashView splashView = new SplashView(V_WIDTH, V_HEIGHT);

		this.navigationController = new NavigationController<>(splashView, V_WIDTH, V_HEIGHT);

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
		/*LabyrinthLoader loader = LabyrinthLoader.shared();
		try {
			Labyrinth labyrinth = loader.loadFile("labys.txt");
			view = new GameView(new World(labyrinth));
			this.setScreen(view);
		} catch (LabyrinthLoaderException e) {
			e.printStackTrace();
			Gdx.app.exit();
		}*/

		FileHandle skinFileHandle = Gdx.files.internal("skin/craftacular/craftacular-ui.json");
		Skin skin = new Skin(skinFileHandle);
		Color color = new Color(.1f, .12f, .18f, 1);
		setScreen(new NewWorldMenuView(V_WIDTH, V_HEIGHT, skin, color));

		if (labyrinth == null) {
			builderView = new BuilderView(25, 25);
		} else {
			builderView = new BuilderView(labyrinth);
		}

		Transition transition = new BlendingTransition(new SpriteBatch(), 10);
		this.navigationController.pushScreen(builderView, null);
	}

	@Override
	public void render() {
		this.navigationController.render();
	}

	@Override
	public void dispose() {

	}
}
