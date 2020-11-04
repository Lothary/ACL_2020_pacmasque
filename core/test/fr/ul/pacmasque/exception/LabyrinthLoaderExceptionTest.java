/*
 * LabyrinthLoaderExceptionTest.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 4/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabyrinthLoaderExceptionTest {

	LabyrinthLoaderException exception;

	@BeforeEach
	void setUp() {
		this.exception = new LabyrinthLoaderException("abc");
	}

	@Test
	void getMessage() {
		assertEquals("[Pacmasque] abc", this.exception.getMessage());
	}

	@Test
	void getMessageNull() {
		this.exception = new LabyrinthLoaderException(null);
		assertEquals("[Pacmasque] null", this.exception.getMessage());
	}

}