/*
 * LabyrinthConstructor.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.util;

import fr.ul.pacmasque.exception.PacMasqueExceptions;
import fr.ul.pacmasque.model.Labyrinth;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LabyrinthConstructor {
	/**
	 * Map qui contient tous les labyrinthes du fichier.
	 * La clé est le nom du labyrinthe, qui est sous forme
	 * binaire.
	 */
	private Map<Integer, String> labyrinths;

	public LabyrinthConstructor(File fileBuilder){
		labyrinths = new HashMap<>();
		this.fillMap(fileBuilder);
	}

	/**
	 * A partir du fichier, remplie la map.
	 * @param fileBuilder fichier qui contient les labyrinthes
	 *                    en binaire, séparés par un #.
	 */
	private void fillMap(File fileBuilder){
		String labysTogether = "";
		System.out.println(fileBuilder.getAbsolutePath());
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileBuilder));
			StringBuilder stringBuilder = new StringBuilder();

			String line = bufferedReader.readLine();
			while (line != null) {
				stringBuilder.append(line);
				stringBuilder.append(System.lineSeparator());
				line = bufferedReader.readLine();
			}
			labysTogether = stringBuilder.toString();
			bufferedReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] labys = labysTogether.split("#");

		for (int i=0; i<labys.length; i++){
			labyrinths.put(i, labys[i]);
		}
	}

	/**
	 * Récupère le laby de la map et transforme
	 * le string en Labyrinth.
	 * @param key du labyrinthe souhaité
	 * @return le Labyrinth
	 */
	public Labyrinth build(int key) throws PacMasqueExceptions {
		if (!labyrinths.containsKey(key)){
			throw new PacMasqueExceptions("Le labyrinthe n'existe pas");
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
}
