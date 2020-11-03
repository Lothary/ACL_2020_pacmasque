/*
 * LabyrinthBuilder.java
 * ACL-2020-pacmasque
 *
 * Created by ugocottin on 21/10/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util;

import fr.ul.pacmasque.exception.PacmasqueException;
import fr.ul.pacmasque.model.Labyrinth;
import org.jetbrains.annotations.NotNull;

/**
 * Constructeur de labyrinthe.
 * Construit un labyrinthe à partir d'une représentation sous chaîne de caractères
 */
public interface LabyrinthBuilder {

	/**
	 * Crée le labyrinthe correspondant à la chaîne de caractères.
	 * @param content le labyrinthe sous forme de chaîne de caractère
	 * @return un labyrinthe correspondant
	 * @throws PacmasqueException si il n'est pas possible de construire le labyrinthe
	 */
	@NotNull Labyrinth build(@NotNull String content) throws PacmasqueException;
}
