/*
 * Matrix.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 18/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.generator;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Vector;

public class Matrix<T> {

	private final int columns;
	private final int rows;

	private final List<List<T>> grid;

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

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}

	@Nullable public T get(int column, int row) {
		if (column >= this.columns) {
			return null;
		}

		if (row >= this.rows) {
			return null;
		}

		return this.grid.get(column).get(row);
	}
}
