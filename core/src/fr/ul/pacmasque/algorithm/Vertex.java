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
    private float distance;
    private float heuristique;
    private Vertex predecessor; // todo: à initialiser à (-1, -1) ?

    public Vertex(Vector2 position){
        this.position = position;
        this.distance = Integer.MAX_VALUE;
        this.heuristique = Integer.MAX_VALUE;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setHeuristique(float heuristique) {
        this.heuristique = heuristique;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getDistance() {
        return distance;
    }

    public float getHeuristique() {
        return heuristique;
    }

    public Vertex getPredecessor() {
        return predecessor;
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
