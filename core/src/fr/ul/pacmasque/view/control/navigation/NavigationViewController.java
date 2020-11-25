/*
 * NavigationViewController.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 15/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.control.navigation;

import com.badlogic.gdx.Gdx;
import fr.ul.pacmasque.view.control.controller.ViewController;
import fr.ul.pacmasque.view.hierarchy.EmptyView;
import fr.ul.pacmasque.view.hierarchy.View;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class NavigationViewController implements NavigationController<View> {

	/**
	 * File d'attente de présentation des vue.
	 * @implNote Plus utile si j'arrive à implémenter ces fichues transitions de m***e!
	 */
	@NotNull private final Queue<View> presentingQueue;

	/**
	 * File d'attente de disparition des vue.
	 * @implNote même remarque que le champ précédent
	 */
	@NotNull private final Queue<ViewController> dismissingQueue;
	@NotNull private final Stack<ViewController> viewStack;

	/**
	 * Crée un contrôleur de navigation, d'une taille donnée
	 * @param width sa largeur
	 * @param height sa hauteur
	 * @implNote les dimensions définies ici peuvent être modifiée via la méthode 'resize'. Elles seront passée aux vues gérées par le contrôleur.
	 */
	public NavigationViewController(float width, float height) {

		this.presentingQueue = new LinkedList<>();
		this.dismissingQueue = new LinkedList<>();
		this.viewStack = new Stack<>();

		// Crée une vue 'de base'. Première et dernière vue de la stack
		this.viewStack.add(new ViewController(new EmptyView(width, height, null)));
	}


	@Override
	public void present(@NotNull View view) {
		this.presentingQueue.add(view);
	}

	@Override
	public void dismiss() {
		// Sinon, on ajoute la vue au sommet de la pile a la queue de dismissing
		this.dismissingQueue.add(this.viewStack.pop());

		// Si on dismiss la seule vue de la pile, on quitte
		if (this.viewStack.empty()) {
			Gdx.app.exit();
		}
	}

	@Override
	public void render(float delta) {

		if (!this.presentingQueue.isEmpty()) {
			// Si une vue est en attente d'être présentée

			View view = this.presentingQueue.poll();
			ViewController futureView = new ViewController(view);

			ViewController currentView = this.viewStack.peek();
			currentView.viewWillDisappear();

			futureView.viewWillAppear();

			this.viewStack.push(futureView);
			this.render(delta);

			currentView.viewDidDisappear();
			futureView.viewDidAppear();
		} else if (!this.dismissingQueue.isEmpty()) {
			// Si une vue attend de disparaitre

			// On récupère la vue qui doit disparaitre
			ViewController dismissingViewController = this.dismissingQueue.poll();

			// On regarde la vue qui est maintenant au sommet de la pile, qui va se ré-afficher
			ViewController futureView = this.viewStack.peek();

			dismissingViewController.viewWillDisappear();
			futureView.viewWillAppear();

			this.render(delta);

			dismissingViewController.viewDidDisappear();
			futureView.viewDidAppear();
		} else {
			// Si aucune vue n'attend d'être présentée,
			// On render le sommet de la stack
			ViewController topViewController = this.viewStack.peek();
			topViewController.becomeFirstResponder();
			this.viewStack.forEach(vc -> vc.getView().render(delta));
			//topViewController.getView().render(delta);
		}

	}

	@Override
	public void resize(int width, int height) {
		for (ViewController viewController : this.viewStack) {
			viewController.getView().resize(width, height);
		}
	}

	@Override
	public void dispose() {
		for (ViewController viewController : this.viewStack) {
			viewController.getView().dispose();
		}
	}
}
