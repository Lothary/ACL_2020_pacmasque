/*
 * DecoderException.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 11/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.encoder;

public class DecoderException extends Exception {

	public DecoderException(String message) {
		super("Une erreur est survenue lors du décodage: " + message);
	}
}
