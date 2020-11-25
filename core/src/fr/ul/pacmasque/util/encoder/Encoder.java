/*
 * Encoder.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 11/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.encoder;

import org.jetbrains.annotations.NotNull;

/**
 * Toute classe capable de d'encoder un objet en bytes
 * @param <T> un type encodé par le l'encoder
 */
public interface Encoder<T> extends Codable {

	/**
	 * Encode un objet en bytes
	 * @param encodable un objet encodé par l'encoder
	 * @return l'objet, encodé
	 * @throws EncoderException si une erreur s'est produite lors de l'encodage de l'objet
	 */
	byte[] encode(@NotNull T encodable) throws EncoderException;
}
