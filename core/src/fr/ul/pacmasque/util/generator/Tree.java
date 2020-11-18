/*
 * Tree.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 18/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.generator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Tree implements Comparable<Tree> {

	@Nullable private Tree root;

	public Tree() {
		this.root = null;
	}

	public @Nullable Tree getRoot() {
		return root;
	}

	public void setRoot(@NotNull Tree root) {
		this.root = root;
	}

	public boolean isConnected(@NotNull Tree tree) {
		Tree root = this.getRoot();

		if (root == null) {
			return false;
		}

		return this.getRoot().compareTo(tree) > 0;
	}

	public void connect(@NotNull Tree tree) {
		if (this.getRoot() != null) {
			this.getRoot().setRoot(tree);
		}
	}

	@Override
	public int compareTo(@NotNull Tree o) {
		return hashCode() == o.hashCode() ? 1 : 0;
	}
}
