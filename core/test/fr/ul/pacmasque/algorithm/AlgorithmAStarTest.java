/*
 * AlgorithmAStarTest.java
 * ACL_2020_pacmasque
 *
 * Created by ValerieMarissens on 13/12/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.algorithm;

import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.PacmasqueTest;
import fr.ul.pacmasque.entity.BasicMonster;
import fr.ul.pacmasque.model.Labyrinth;
import fr.ul.pacmasque.model.World;
import fr.ul.pacmasque.util.generator.Generators;
import fr.ul.pacmasque.util.generator.LabyrinthGenerator;
import fr.ul.pacmasque.util.generator.LabyrinthGeneratorException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import static com.badlogic.gdx.math.MathUtils.random;
import static org.junit.jupiter.api.Assertions.*;

class AlgorithmAStarTest extends PacmasqueTest {
    private static World world;
    private static Labyrinth labyrinth;
    private static AlgorithmAStar astar;
    private static BasicMonster m1;
    private static Graph graph;

    @BeforeAll
    static void setUp() throws LabyrinthGeneratorException {
        Supplier<LabyrinthGenerator> generatorFactory = Generators.shared().getGeneratorFactory("Kruskal");
        labyrinth = Objects.requireNonNull(generatorFactory).get().generate(System.currentTimeMillis(), 15, 15);

        world = new World(labyrinth, "New World");

        m1 = new BasicMonster(14,3);
        world.addMonster(m1);

        world.getPlayer().setPositionX(2.0f);
        world.getPlayer().setPositionY(2.0f);

        astar = (AlgorithmAStar) world.getAlgorithmAStar();
        graph = astar.getGraph();
    }

    @Test
    void testToGraphNotWall(){
        Map<Vertex, List<Vertex>> adjList = graph.getAdjacencyList();

        for (Vertex v : adjList.keySet()){
            if (labyrinth.isWall(v.getPosition())) System.out.println("WALL: "+v.getPosition());
            assertFalse(labyrinth.isWall(v.getPosition()));

            for (Vertex v2 : adjList.get(v)){
                if (labyrinth.isWall(v2.getPosition())) System.out.println("WALL: "+v2.getPosition());
                assertFalse(labyrinth.isWall(v2.getPosition()));
            }
        }
    }

    @Test
    void testAStarPath(){
        Vector2 playerPosition = world.getPlayer().getPosition();
        Vector2 monsterInitialPosition = m1.getPosition();

        System.out.println("start: "+monsterInitialPosition);
        System.out.println("target: "+playerPosition);

        System.out.println("start neighbors: "+graph.getAdjacency(monsterInitialPosition));

        List<Vertex> path = astar.aStarPath(playerPosition, monsterInitialPosition);

        // Possible car je place le monstre moi-même.
        if (!labyrinth.isWall(monsterInitialPosition)) {

            // Possible si le monstre est dans une case qui n'a pas de voisins.
            if (!graph.getAdjacency(monsterInitialPosition).isEmpty())
                assertFalse(path.isEmpty());
        }
    }
}