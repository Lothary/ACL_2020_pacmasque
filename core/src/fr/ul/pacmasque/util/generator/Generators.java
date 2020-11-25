/*
 * Generators.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 25/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.util.generator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Generators {

	@NotNull public static Generators shared() {
		if (instance == null) {
			instance = new Generators();
		}

		return instance;
	}

	@Nullable private static Generators instance = null;

	@NotNull private final Map<String, Supplier<LabyrinthGenerator>> availableGenerators;

	public Generators() {
		this.availableGenerators = new HashMap<>();
		this.register("Kruskal", KruskalGenerator::new);
	}

	public void register(String generatorName, @NotNull Supplier<LabyrinthGenerator> factory) {
		this.availableGenerators.put(generatorName, factory);
	}

	@Nullable public Supplier<LabyrinthGenerator> getGeneratorFactory(String generatorName) {
		return this.availableGenerators.get(generatorName);
	}

	@NotNull public String[] getAvailableGenerators() {
		return this.availableGenerators.keySet().toArray(new String[0]);
	}
}
