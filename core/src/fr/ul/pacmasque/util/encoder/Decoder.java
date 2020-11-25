/*
 * Decoder.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 11/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.encoder;

import org.jetbrains.annotations.NotNull;

/**
 * Toute classe capable de décoder un objet à partir de bytes
 * @param <T> un type décodé par le décoder
 */
public interface Decoder<T> extends Codable {

	/**
	 * Décode un objet depuis une collection de bytes
	 * @param decodable un objet décodable sous forme de bytes
	 * @return l'objet, décodé
	 * @throws DecoderException si une erreur survient lors du décodage
	 */
	@NotNull T decode(byte[] decodable) throws DecoderException;
}
