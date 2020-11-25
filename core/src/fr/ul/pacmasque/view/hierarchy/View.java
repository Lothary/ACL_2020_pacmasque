/*
 * View.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 14/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.hierarchy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import fr.ul.pacmasque.util.TexturePack;
import fr.ul.pacmasque.view.control.navigation.NavigationController;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Classe de base d'une vue, permettant la réponse au évènement,
 * la gestion de son cycle de vie (via le viewcontroller).
 * Définie par sa taille et, optionnellement, une couleur de fond
 */
public abstract class View extends Responder implements Screen {

	/**
	 * Couleur de fond par défaut
	 */
	@NotNull public static final Color DEFAULT_BACKGROUND_COLOR = Color.valueOf("111111");

	/**
	 * Contrôle de navigation, permettant l'ajout et la suppression de vue dans la fenêtre
	 */
	@Nullable public NavigationController<View> navigationController;

	/**
	 * Une largeur
	 */
	private float width;

	/**
	 * Une hauteur
	 */
	private float height;

	/**
	 * Indique si la vue est chargée (construire)
	 */
	private boolean loaded;

	/**
	 * Si la vue est en pause. La vue est en pause lorsqu'elle n'est pas présentée.
	 */
	private boolean paused;

	/**
	 * Couleur de fond de la vue
	 */
	@NotNull
	private final Color backgroundColor;

	/**
	 * Crée une vue
	 * @param width sa largeur
	 * @param height sa hauteur
	 * @param backgroundColor sa couleur de fond, noir par défaut
	 */
	public View(float width, float height, @Nullable Color backgroundColor) {
		this.width = width;
		this.height = height;

		this.loaded = false;
		this.paused = true;

		if (backgroundColor != null) {
			this.backgroundColor = backgroundColor;
		} else {
			this.backgroundColor = DEFAULT_BACKGROUND_COLOR;
		}

	}

	/**
	 * @return largeur de la vue
	 */
	public float getWidth() {
		return this.width;
	}

	/**
	 * @return hauteur de la vue
	 */
	public float getHeight() {
		return this.height;
	}

	/**
	 * @return si la vue est chargée
	 */
	public boolean isLoaded() {
		return this.loaded;
	}

	/**
	 * @return sa couleur de fond
	 */
	public @NotNull Color getBackgroundColor() {
		return this.backgroundColor;
	}

	/**
	 * Crée la vue. Appelé une seule fois lors de son apparition
	 * @apiNote doit être implémentée. Les instruction de création de la vue doivent s'effectuer lors de cet appel
	 */
	public void create() {
		this.loaded = true;
	}

	/**
	 * Contient la logique de la vue, et pas le dessin.
	 * Exemple: step du world, mise a jour du model
	 * @param delta un delta
	 */
	public abstract void update(float delta);

	/**
	 * Présente une vue par dessus les autres, si un contrôleur de vue est définit
	 * @param view une vue à afficher
	 */
	public void present(@NotNull View view) {
		if (this.navigationController != null) {
			view.navigationController = this.navigationController;
			this.navigationController.present(view);
		}
	}

	/**
	 * Retire la vue de la pile d'affichage
	 */
	public void dismiss() {
		if (this.navigationController != null) {
			this.navigationController.dismiss();
		}
	}

	// Screen
	@Override
	public void show() {
		this.paused = false;
		this.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render(float delta) {
		if (!this.paused) {
			this.update(delta);
		}

		//Color color = this.getBackgroundColor();
		//Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void pause() {
		this.paused = true;
	}

	@Override
	public void resume() {
		this.paused = false;
	}

	@Override
	public void hide() {
		this.paused = true;
	}

	@Override
	public void dispose() {

	}
}
