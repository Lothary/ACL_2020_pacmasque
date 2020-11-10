/*
 * Pair.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 10/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util;

public class Pair<F, S> {

	private final F el1;
	private final S el2;

	public Pair(F el1, S el2) {
		this.el1 = el1;
		this.el2 = el2;
	}

	public F first() {
		return this.el1;
	}

	public S second() {
		return this.el2;
	}
}
