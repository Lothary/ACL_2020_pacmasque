/*
 * NavigationController.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 10/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.hierarchy;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import fr.ul.pacmasque.util.Pair;
import fr.ul.pacmasque.view.View;
import fr.ul.pacmasque.view.hierarchy.transition.Transition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class NavigationController<S extends View> implements ApplicationListener {

	private final Queue<Pair<@NotNull S, @Nullable Transition>> transitionQueue = new LinkedList<>();
	private FrameBuffer currFBO;
	private FrameBuffer lastFBO;

	@NotNull private final Stack<S> viewStack;

	private int width;
	private int height;

	@Nullable private Transition ongoingTransition = null;
	@NotNull private S currentScreen;
	@Nullable private S previousScreen = null;

	public NavigationController(@NotNull S rootScreen, int width, int height) {
		this.currentScreen = rootScreen;
		this.width = width;
		this.height = height;

		this.viewStack = new Stack<S>();
		this.viewStack.add(this.currentScreen);

		initBuffers();
	}

	public void pushScreen(@NotNull S screen, @Nullable Transition transition) {
		this.transitionQueue.add(new Pair<>(screen, transition));
		//this.deque.add(screen);
	}

	public void popScreen() {
		this.viewStack.pop();
		S view = this.viewStack.peek();
		if (view != null) {
			this.currentScreen = view;
			this.currentScreen.resume();
		}
	}

	@Override
	public void create() {

	}

	private void initBuffers() {
		if (this.lastFBO != null) {
			this.lastFBO.dispose();
		}

		//lastFBO = new NestableFrameBuffer(Pixmap.Format.RGBA4444, HdpiUtils.toBackBufferX(this.width), HdpiUtils.toBackBufferY(this.height), false);
		if (this.currFBO != null) {
			this.currFBO.dispose();
		}

		//currFBO = new NestableFrameBuffer(Pixmap.Format.RGBA4444, HdpiUtils.toBackBufferX(this.width), HdpiUtils.toBackBufferY(this.height), false);

	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;

		initBuffers();
	}

	private void render(float delta) {


		if (this.ongoingTransition == null) {
			// Si aucune transition n'est en cours

			if (!this.transitionQueue.isEmpty()) {
				// Si une transition est en attente

				final Pair<S, Transition> nextTransition = this.transitionQueue.poll();

				this.previousScreen = this.currentScreen;
				this.currentScreen = nextTransition.first();

				this.previousScreen.pause();
				this.currentScreen.show();
				this.viewStack.add(this.currentScreen);
				this.ongoingTransition = nextTransition.second();

				if (this.ongoingTransition == null) {
					// Écran sans transition
					this.previousScreen.hide();
				} else {
					this.ongoingTransition.reset();
				}

				// On a une transition, donc on render encore
				this.render(delta);
			} else {
				// Si aucune transition n'est en attente, on refresh l'écran actuel
				// On clear l'écran
				Color color = this.currentScreen.getClearColor();
				Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
				Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

				// On render l'écran
				this.currentScreen.render(delta);
			}
		} else {
			// Si une transition est en cours

			// Si l'écran précédent est null
			if (this.previousScreen == null) {
				// Fin de la transition
				this.ongoingTransition = null;
				this.render(delta);
				return;
			}

			if (!this.ongoingTransition.isDone()) {
				// Si la transition n'est pas finie

				this.previousScreen.render(delta);
				this.currentScreen.render(delta);

				TextureRegion previousTextureRegion = screenToTexture(
						this.previousScreen, this.lastFBO, delta);
				TextureRegion currentTextureRegion = screenToTexture(
						this.currentScreen, this.currFBO, delta);

				this.ongoingTransition.render(delta, previousTextureRegion, currentTextureRegion);
			} else {
				// Si la transition est finie
				this.ongoingTransition = null;
				this.previousScreen.hide();

				// On render une fois de plus pour nettoyer la transition
				this.render(delta);
			}
		}
	}

	@Override
	public void render() {
		this.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	private TextureRegion screenToTexture(S screen, FrameBuffer fbo, float delta) {
		fbo.begin();

		Color color = screen.getClearColor();
		Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		screen.render(delta);
		fbo.end();

		Texture texture = fbo.getColorBufferTexture();

		// flip the texture
		TextureRegion textureRegion = new TextureRegion(texture);
		textureRegion.flip(false, true);

		return textureRegion;
	}
}
