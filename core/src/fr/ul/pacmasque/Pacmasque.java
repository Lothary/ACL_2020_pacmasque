package fr.ul.pacmasque;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.pacmasque.exception.LabyrinthLoaderException;
import fr.ul.pacmasque.model.Labyrinth;
import fr.ul.pacmasque.model.World;
import fr.ul.pacmasque.util.LabyrinthLoader;
import fr.ul.pacmasque.view.GameView;
import fr.ul.pacmasque.view.View;
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

		if (labyrinth == null) {
			builderView = new BuilderView(25, 25);
		} else {
			builderView = new BuilderView(labyrinth);
		}

		/*LabyrinthLoader loader = LabyrinthLoader.shared();
		try {
			Labyrinth labyrinth = loader.loadFile("labys.txt");
			View view = new GameView(new World(labyrinth));

			Transition transition = new BlendingTransition(new SpriteBatch(), 10);
			this.navigationController.pushScreen(view, transition);
		} catch (LabyrinthLoaderException e) {
			e.printStackTrace();
			Gdx.app.exit();
		}
	}

	@Override
	public void render() {
		this.navigationController.render();
	}

	@Override
	public void dispose() {

	}
}
