package fr.ul.pacmasque;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import fr.ul.pacmasque.model.World;
import fr.ul.pacmasque.view.GameView;
import fr.ul.pacmasque.view.View;

import java.util.ArrayList;
import java.util.List;

public class Pacmasque extends Game {

	private View renderedView;
	private final List<View> views = new ArrayList<>();

	public View getRenderedView() {
		return renderedView;
	}

	@Override
	public void create() {
		Gdx.graphics.setContinuousRendering(true);
		View view = new GameView(new World());
		this.setScreen(view);
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
