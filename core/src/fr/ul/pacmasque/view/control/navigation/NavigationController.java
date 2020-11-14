/*
 * NavigationController.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 15/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.control.navigation;

import com.badlogic.gdx.utils.Disposable;
import fr.ul.pacmasque.view.hierarchy.View;
import org.jetbrains.annotations.NotNull;

public interface NavigationController<S extends View> extends Disposable {

	/**
	 * Ajoute la vue a la pile
	 * @param view une vue
	 */
	void present(@NotNull S view);

	/**
	 * Retire la vue au sommet de la pile des vues
	 */
	void dismiss();

	/**
	 * Affiche la pile des vues
	 * @param delta le temps écoulé entre deux render
	 */
	void render(float delta);

	void resize(int width, int height);
}
