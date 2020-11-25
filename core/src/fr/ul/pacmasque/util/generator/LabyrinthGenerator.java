/*
 * LabyrinthGenerator.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 18/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.generator;

import fr.ul.pacmasque.model.Labyrinth;
import org.jetbrains.annotations.NotNull;

/**
 * Générateur de labyrinthe
 */
public interface LabyrinthGenerator {

	/**
	 * Génère un labyrinthe de taille donnée
	 * @param seed le seed
	 * @param width largeur du labyrinthe
	 * @param height hauteur du labyrinthe
	 * @return un labyrinthe
	 */
	@NotNull Labyrinth generate(long seed, int width, int height) throws LabyrinthGeneratorException;

}
