/*
 * ViewController.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 15/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.control.controller;

import fr.ul.pacmasque.view.hierarchy.Responder;
import fr.ul.pacmasque.view.hierarchy.View;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ApiStatus.Experimental
public class ViewController extends Responder {

	/**
	 * La vue gérée par le contrôleur.
	 */
	@NotNull private final View view;

	public ViewController(@NotNull View view) {
		this.view = view;
	}

	/**
	 * @return la vue gérée par le contrôleur.
	 */
	@NotNull public View getView() {
		return this.view;
	}

	/**
	 * Appelée quand la vue est chargée.
	 */
	public void viewDidLoad() { }

	/**
	 * Appelée quand la vue va apparaître à l'écran.
	 * Si la vue n'est pas chargée, elle est chargée avant son affichage.
	 */
	public void viewWillAppear() {
		if (!this.view.isLoaded()) {
			this.view.create();
			this.viewDidLoad();
		}

		this.view.show();
	}

	/**
	 * Appelée quand la vue est apparue de l'écran.
	 */
	public void viewDidAppear() {
		this.view.resume();
	}

	/**
	 * Appelée quand la vue va disparaitre de l'écran.
	 */
	public void viewWillDisappear() {
		this.view.pause();
	}

	/**
	 * Appelée quand la vue a disparue de l'écran.
	 */
	public void viewDidDisappear() {
		this.view.hide();
	}

	@Override
	public @Nullable Responder getNextResponder() {
		return this.view;
	}
}
