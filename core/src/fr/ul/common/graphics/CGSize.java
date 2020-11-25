/*
 * CGSize.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 25/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.common.graphics;

/**
 * A structure that contains width and height values.
 */
public class CGSize {

	/**
	 * Width value
	 */
	public float width;

	/**
	 * Height value
	 */
	public float height;

	/**
	 * Create a size with zero width and height
	 */
	public CGSize() {
		this.width = 0;
		this.height = 0;
	}

	/**
	 * Create a size with given width and height
	 * @param width a width
	 * @param height a height
	 */
	public CGSize(float width, float height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return "(" + width + "," + height + ")";
	}
}
