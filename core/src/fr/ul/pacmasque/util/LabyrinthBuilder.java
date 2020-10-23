/*
 * LabyrinthBuilder.java
 * ACL-2020-pacmasque
 *
 * Created by ugocottin on 21/10/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util;

import fr.ul.pacmasque.exception.PacmasqueException;
import fr.ul.pacmasque.model.Labyrinth;

public interface LabyrinthBuilder {

	Labyrinth build(String content) throws PacmasqueException;
}
