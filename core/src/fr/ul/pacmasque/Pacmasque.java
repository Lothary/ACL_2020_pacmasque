package fr.ul.pacmasque;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import fr.ul.pacmasque.view.GameView;
import fr.ul.pacmasque.view.View;

import java.util.ArrayList;
import java.util.List;

public class Pacmasque extends Game {

	private View renderedView;
	private List<View> views = new ArrayList<>();

	SpriteBatch batch;
	Texture img;

	public View getRenderedView() {
		return renderedView;
	}

	@Override
	public void create() {
		Gdx.graphics.setContinuousRendering(true);
		View view = new GameView();
		this.setScreen(view);
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	public void setScreen(View screen) {
		this.renderedView = screen;
		super.setScreen(screen);
	}

	@Override
	public void dispose() {
		if (this.getRenderedView() != null) {
			this.getRenderedView().dispose();
		}

		this.views.forEach(View::dispose);
	}
}
