package fr.ul.pacmasque;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import fr.ul.pacmasque.view.menu.MainMenuView;
import fr.ul.pacmasque.view.View;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Pacmasque extends Game {

	public static final int V_WIDTH = 1080;
	public static final int V_HEIGHT = 720;

	private final List<View> views = new ArrayList<>();

	@Override
	public void create() {
		Gdx.graphics.setContinuousRendering(true);
		View view;

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

		setScreen(new MainMenuView(V_WIDTH, V_HEIGHT, skin, null));
	}

	private void setScreen(@NotNull View screen) {
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
