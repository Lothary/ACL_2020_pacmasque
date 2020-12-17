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

/**
 * Représente un sommet du graphe Graph, ie une case du labyrinthe.
 */
public class Vertex implements Comparable<Vertex>{

    /**
     * Position de la case dans le labyrinthe.
     */
    private final Vector2 position;

    /**
     * Distance de la case avec la position du monstre.
     */
    private int distance;

    /**
     * Heuristique associée à cette case, ie distance à vol d'oiseau
     * à partir de la case où se trouve le monstre.
     */
    private int heuristique;

    /**
     * Dans le chemin construit par A*, prédécesseur de cette case.
     */
    private Vertex predecessor;

    /**
     * Définit le type de voisin que cette case est pour une autre:
     * peut être le voisin du haut, bas, gauche, droite ou ne pas
     * être un voisin.
     */
    public enum typeNeighbor {
        up,
        down,
        left,
        right,
        none
    }

    /**
     * Type de voisin.
     */
    private typeNeighbor type;

    /**
     * Initialise la distance et l'heuristique à 0 et
     * le type de voisin à none.
     *
     * @param position de la case.
     */
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

    /**
     * Deux vertex sont égaux s'ils représentent une même case,
     * c'est-à-dire s'ils sont à le même position.
     *
     * @param o objet avec qui on compare.
     * @return si les objets sont équivalents.
     */
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

    /**
     * Deux vertex sont comparables selon leur heuristique. Ceci est
     * important pour le trie de la file open dans l'algorithme A*.
     *
     * @param o objet vertex à comparer avec celui-ci.
     * @return comparaison.
     */
    @Override
    public int compareTo(@NotNull Vertex o) {
        return (int) (this.getHeuristique() - o.getHeuristique());
    }
}
