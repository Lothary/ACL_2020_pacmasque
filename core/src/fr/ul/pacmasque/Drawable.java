/*
 * Drawable.java
 * ACL-2020-pacmasque
 *
 * Created by ugocottin on 21/10/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Définit un objet comme pouvant être dessiné, avec une position et une taille, le tout dans un batch
 */
public interface Drawable {

	/**
	 * Se dessine dans un batch, à une position donnée et avec une taille donnée
	 * @param batch le batch dans lequel se dessiner
	 * @param x la coordonnée x du dessin
	 * @param y la coordonnée y du dessin
	 * @param width la largeur du dessin
	 * @param height la hauteur du dessin
	 */
	void draw(Batch batch, float x, float y, float width, float height);
}
