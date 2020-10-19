package fr.ul.pacmasque;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import fr.ul.pacmasque.view.SplashView;
import fr.ul.pacmasque.view.View;

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
		view = new SplashView();
		//view = new GameView(new World());
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
	public void render() {
		super.render();

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}

	}

	@Override
	public void dispose() {
		this.views.forEach(View::dispose);
	}
}
