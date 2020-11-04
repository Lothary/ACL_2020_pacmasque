/*
 * PacMasqueExceptions.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 20/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.exception;

public abstract class PacmasqueException extends Exception {
	public PacmasqueException(String message){
		super("[Pacmasque] " + message);
	}
}
