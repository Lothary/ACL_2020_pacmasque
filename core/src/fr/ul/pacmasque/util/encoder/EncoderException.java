/*
 * EncoderException.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 25/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.encoder;

public class EncoderException extends Exception {

	public EncoderException(Object context, String message) {
		super("Une erreur est survenue lors de l'encodage de " + context + " : " + message);
	}
}
