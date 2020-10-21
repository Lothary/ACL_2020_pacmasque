package fr.ul.pacmasque;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import fr.ul.pacmasque.exception.PacMasqueExceptions;
import fr.ul.pacmasque.model.World;
import fr.ul.pacmasque.util.LabyrinthConstructor;
import fr.ul.pacmasque.view.GameView;
import fr.ul.pacmasque.view.View;

import java.io.File;
import java.util.ArrayList;
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
		//view = new SplashView(V_WIDTH, V_HEIGHT);
		File file = new File("core/assets/labys.txt");
		LabyrinthConstructor constructor = new LabyrinthConstructor(file);
		try {
			view = new GameView(new World(constructor.build(0)));
			this.setScreen(view);
		} catch (PacMasqueExceptions pacMasqueExceptions) {
			pacMasqueExceptions.printStackTrace();
		}
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
