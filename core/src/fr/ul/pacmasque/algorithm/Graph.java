/*
 * Graph.java
 * ACL_2020_pacmasque
 *
 * Created by ValerieMarissens on 15/12/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.algorithm;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Graph {
    private Map<Vertex, List<Vertex>> adjacencyList;

    public Graph(){}

    public void addVertex(Vector2 position){
        adjacencyList.putIfAbsent(new Vertex(position), new ArrayList<>());
    }

    public void removeVertex(Vector2 position){
        Vertex vertex = new Vertex(position);
        adjacencyList.values()
                .stream()
                .map(e -> e.remove(vertex))
                .collect(Collectors.toList());
        adjacencyList.remove(new Vertex(position));
    }

    public void addEdge(Vector2 pos1, Vector2 pos2){
        Vertex v1 = new Vertex(pos1);
        Vertex v2 = new Vertex(pos2);
        adjacencyList.get(v1).add(v2);
        adjacencyList.get(v2).add(v1);
    }

    public void removeEdge(Vector2 pos1, Vector2 pos2){
        Vertex v1 = new Vertex(pos1);
        Vertex v2 = new Vertex(pos2);
        List<Vertex> l1 = adjacencyList.get(v1);
        List<Vertex> l2 = adjacencyList.get(v2);
        if (l1 != null) l1.remove(v2);
        if (l2 != null) l2.remove(v1);
    }

    public List<Vertex> getAdjacency(Vector2 pos) {
        return adjacencyList.get(new Vertex(pos));
    }
}
