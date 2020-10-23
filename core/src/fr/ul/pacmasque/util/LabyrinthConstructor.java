/*
 * LabyrinthConstructor.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.util;

import fr.ul.pacmasque.exception.LabyrinthConstructorException;
import fr.ul.pacmasque.exception.PacmasqueException;
import fr.ul.pacmasque.model.Labyrinth;

import java.util.HashMap;
import java.util.Map;

public class LabyrinthConstructor implements LabyrinthBuilder {
	/**
	 * Map qui contient tous les labyrinthes du fichier.
	 * La clé est le nom du labyrinthe, qui est sous forme
	 * binaire.
	 */
	private Map<Integer, String> labyrinths = new HashMap<>();

	public LabyrinthConstructor(){ }

	/**
	 * Récupère le laby de la map et transforme
	 * le string en Labyrinth.
	 * @param key du labyrinthe souhaité
	 * @return le Labyrinth
	 */
	public Labyrinth build(int key) throws PacmasqueException {
		if (!labyrinths.containsKey(key)){
			throw new LabyrinthConstructorException("Le labyrinthe n'existe pas");
		}

		String laby = labyrinths.get(key);
		String[] labyLines = laby.split("\n");
		int x = labyLines.length;
		int y = labyLines[0].length();

		Labyrinth labyrinth = new Labyrinth(x, y);

		for (int i=0; i<x; i++){
			for (int j=0; j<y; j++){
				if (labyLines[i].charAt(j) == '0'){
					labyrinth.setMur(i, j);
				}
			}
		}

		return labyrinth;
	}

	@Override
	public Labyrinth build(String content) throws PacmasqueException {
		String[] labys = content.split("#");

		for (int i = 0; i < labys.length; i++) {
			this.labyrinths.put(i, labys[i]);
		}

		return build(0);
	}
}
