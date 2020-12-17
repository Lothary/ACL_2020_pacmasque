/*
 * GraphTest.java
 * ACL_2020_pacmasque
 *
 * Created by ValerieMarissens on 17/12/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.algorithm;

import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private Graph graph;

    @BeforeEach
    void setUp(){
        graph = new Graph();
    }

    @Test
    void addVertexRepeated(){
        graph.addVertex(new Vector2(2, 2));
        graph.addVertex(new Vector2(2, 2));

        assertTrue(graph.contains(new Vertex(new Vector2(2, 2))));
    }

    @Test
    void addEdgeSame(){
        graph.addVertex(new Vector2(2, 2));
        graph.addEdge(new Vector2(2, 2), new Vector2(2, 2));

        List<Vertex> l = graph.getAdjacency(new Vector2(2, 2));
        assertFalse(l.contains(new Vertex(new Vector2(2, 2))));
    }

}