/*
 * Graph.java
 * ACL_2020_pacmasque
 *
 * Created by ValerieMarissens on 15/12/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.algorithm;

import com.badlogic.gdx.math.Vector2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Représente le graphe qui correspond au labyrinthe, ici sous forme de
 * liste d'adjacence.
 */
public class Graph {
    /**
     * Collection qui associe à un sommet la liste des sommets qui
     * lui sont adjacents.
     */
    private final Map<Vertex, List<Vertex>> adjacencyList;

    /**
     * Initialise la liste d'adjacence.
     */
    public Graph(){
        adjacencyList = new HashMap<>();
    }

    /**
     * Ajoute un nouveau sommet au graphe.
     *
     * @param position du sommet.
     */
    public void addVertex(Vector2 position){
        adjacencyList.putIfAbsent(new Vertex(position), new ArrayList<>());
    }

    /**
     * Supprime un sommet du graphe.
     *
     * @param position du sommet.
     */
    public void removeVertex(Vector2 position){
        Vertex vertex = new Vertex(position);
        adjacencyList.values()
                .stream()
                .map(e -> e.remove(vertex))
                .collect(Collectors.toList());
        adjacencyList.remove(new Vertex(position));
    }

    /**
     * Ajoute une arête entre deux sommets. L'arête n'est pas
     * à sens unique, d'où on crée le lien dans les deux sens.
     *
     * @param pos1 position du premier sommet.
     * @param pos2 position du deuxième sommet.
     */
    public void addEdge(Vector2 pos1, Vector2 pos2){
        if (!pos1.equals(pos2)) {
            Vertex v1 = new Vertex(pos1);
            Vertex v2 = new Vertex(pos2);

            addOneSideEdge(v1, v2);
            addOneSideEdge(v2, v1);
        }
    }

    /**
     * Création d'une arête a sens unique de v1 vers v2.
     *
     * @param v1 sommet 1.
     * @param v2 sommet 2.
     */
    private void addOneSideEdge(Vertex v1, Vertex v2){
        List<Vertex> l1 = adjacencyList.get(v1);

        if (l1 == null){
            l1 = new ArrayList<>();
            l1.add(v2);
            adjacencyList.put(v1, l1);
        }
        else{
            if(!l1.contains(v2))
                l1.add(v2);
        }
    }

    /**
     * Supprime une arête entre deux sommets.
     *
     * @param pos1 position du 1er sommet.
     * @param pos2 position du 2eme sommet.
     */
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


    public boolean contains(Vertex v){
        return adjacencyList.containsKey(v);
    }

    public Map<Vertex, List<Vertex>> getAdjacencyList() {
        return adjacencyList;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Vertex v : adjacencyList.keySet()){
            s.append(v.getPosition());
            s.append(":\n");
            for (Vertex v2 : adjacencyList.get(v)){
                s.append("\t");
                s.append(v2.getPosition());
                s.append("\n");
            }
        }

        return s.toString();
    }
}
