/*
 * DecoderException.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 11/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.encoder;

import java.util.Arrays;

public class DecoderException extends Exception {

	public DecoderException(byte[] context, int errorIndex, String message) {
		super("Une erreur est survenue lors du décodage: " + message + "\n"
		+ "ErrorIndex: " + errorIndex + ",\n"
		+ "Context: " + Arrays.toString(context));
	}
}
