/*
 * Matrix.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 18/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.generator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Vector;
import java.util.function.Supplier;

/**
 * Matrice de taille donnée
 * @param <T> type de donnée de la matrice
 */
public class Matrix<T> {

	/**
	 * Nombre de colonnes
	 */
	private final int columns;

	/**
	 * Nombre de lignes
	 */
	private final int rows;

	/**
	 * Liste interne
	 */
	private final List<List<T>> grid;

	/**
	 * Crée une matrice
	 * @param columns nombre de colonnes
	 * @param rows nombre de lignes
	 * @param initialValue valeur initiale des cellules
	 * @implNote Dans le cas d'un objet, l'instance sera partagée par toutes les cellules. Pour des objets distincts, préférez l'utilisation de la factory
	 */
	public Matrix(int columns, int rows, @Nullable T initialValue) {
		this.columns = columns;
		this.rows = rows;

		this.grid = new Vector<>(columns);
		for (int x = 0; x < columns; x++) {
			List<T> column = new Vector<>(rows);
			for (int y = 0; y < rows; y++) {
				column.add(y, initialValue);
			}
			this.grid.add(x, column);
		}
	}

	/**
	 * Crée une matrice
	 * @param columns nombre de colonnes
	 * @param rows nombre de lignes
	 * @param initialValueFactory factory de la valeur initiale des cellules
	 */
	public Matrix(int columns, int rows, @NotNull Supplier<T> initialValueFactory) {
		this.columns = columns;
		this.rows = rows;

		this.grid = new Vector<>(columns);
		for (int x = 0; x < columns; x++) {
			List<T> column = new Vector<>(rows);
			for (int y = 0; y < rows; y++) {
				column.add(y, initialValueFactory.get());
			}
			this.grid.add(x, column);
		}
	}

	/**
	 * @return nombre de colonnes
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @return nombre de lignes
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param column une colonne
	 * @param row une ligne
	 * @return la valeur contenue dans la cellule (colonne, ligne)
	 */
	@Nullable public T get(int column, int row) {
		if (column >= this.columns) {
			return null;
		}

		if (row >= this.rows) {
			return null;
		}

		return this.grid.get(column).get(row);
	}

	/**
	 * Définit une valeur
	 * @param column une colonne
	 * @param row une ligne
	 * @param value la valeur qui sera contenue dans la cellule (colonne, ligne)
	 */
	public void set(int column, int row, @Nullable T value) {
		if ((column < this.columns) && (row < this.rows)) {
			this.grid.get(column).set(row, value);
		}
	}
}
