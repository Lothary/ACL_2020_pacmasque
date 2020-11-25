/*
 * Codable.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 11/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.encoder;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Interface générique, implémentée par les classes capable de d'encodage et / ou de décodage.
 * Instaure un charset partagé dans l'application
 */
public interface Codable {

	@NotNull Charset STANDARD_CHARSET = StandardCharsets.UTF_8;
}
