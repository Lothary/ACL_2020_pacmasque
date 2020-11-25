/*
 * Tree.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 18/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.generator;

import org.jetbrains.annotations.NotNull;

public class Tree implements Comparable<Tree> {

	@NotNull private Tree root;

	public Tree() {
		this.root = this;
	}

	public @NotNull Tree getRoot() {

		if (this == this.root) {
			return this;
		}

		return root.getRoot();
	}

	public void setRoot(@NotNull Tree root) {
		this.root = root;
	}

	public boolean isConnected(@NotNull Tree tree) {
		if (this.compareTo(tree) > 0) {
			return true;
		}

		if (this.getRoot() == this && tree.getRoot() == tree) {
			return this.compareTo(tree) > 0;
		}

		Tree thisRoot = this.getRoot();
		Tree thatRoot = tree.getRoot();

		return thisRoot.isConnected(thatRoot);
	}

	public void connect(@NotNull Tree tree) {
		this.getRoot().setRoot(tree.getRoot());
	}

	@Override
	public int compareTo(@NotNull Tree o) {
		return hashCode() == o.hashCode() ? 1 : 0;
	}
}
