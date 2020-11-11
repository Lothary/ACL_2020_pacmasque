package fr.ul.pacmasque;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import fr.ul.pacmasque.exception.LabyrinthLoaderException;
import fr.ul.pacmasque.model.Labyrinth;
import fr.ul.pacmasque.model.World;
import fr.ul.pacmasque.util.LabyrinthLoader;
import fr.ul.pacmasque.util.encoder.Decoder;
import fr.ul.pacmasque.util.encoder.DecoderException;
import fr.ul.pacmasque.util.encoder.LabyrinthDecoder;
import fr.ul.pacmasque.util.encoder.LabyrinthEncoder;
import fr.ul.pacmasque.view.BuilderView;
import fr.ul.pacmasque.view.GameView;
import fr.ul.pacmasque.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pacmasque extends Game {

	public static final int V_WIDTH = 1080;
	public static final int V_HEIGHT = 720;

	private View renderedView;
	private final List<View> views = new ArrayList<>();

	public View getRenderedView() {
		return renderedView;
	}

	@Override
	public void create() {
		Gdx.graphics.setContinuousRendering(true);
		View view;
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

		/*LabyrinthLoader loader = LabyrinthLoader.shared();
		try {
			Labyrinth labyrinth = loader.loadFile("labys.txt");
			LabyrinthEncoder encoder = new LabyrinthEncoder();
			encoder.encode(labyrinth);
			view = new GameView(new World(labyrinth));
			this.setScreen(view);
		} catch (LabyrinthLoaderException e) {
			e.printStackTrace();
			Gdx.app.exit();
		}*/


		this.setScreen(builderView);
	}

	private void setScreen(View screen) {
		this.renderedView = screen;
		if (!this.views.contains(screen)) {
			this.views.add(screen);
		}
		super.setScreen(screen);
	}

	@Override
	public void dispose() {
		this.views.forEach(View::dispose);
	}
}
