/*
 * Vertex.java
 * ACL_2020_pacmasque
 *
 * Created by ValerieMarissens on 15/12/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.algorithm;

import com.badlogic.gdx.math.Vector2;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Vertex implements Comparable<Vertex>{
    private final Vector2 position;
    private int distance;
    private int heuristique;
    private Vertex predecessor;
    public enum typeNeighbor {
        up,
        down,
        left,
        right,
        none
    }
    private typeNeighbor type;

    public Vertex(Vector2 position){
        this.position = position;
        this.distance = 0;
        this.heuristique = 0;
        this.type = typeNeighbor.none;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setHeuristique(int heuristique) {
        this.heuristique = heuristique;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }

    public void setType(typeNeighbor type) {
        this.type = type;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getDistance() {
        return distance;
    }

    public int getHeuristique() {
        return heuristique;
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public typeNeighbor getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(position, vertex.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public int compareTo(@NotNull Vertex o) {
        return (int) (this.getHeuristique() - o.getHeuristique());
    }
}
