/*
 * AStar.java
 * ACL_2020_pacmasque
 *
 * Created by ValerieMarissens on 13/12/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.algorithm;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.entity.Monster;
import fr.ul.pacmasque.entity.Player;
import fr.ul.pacmasque.model.Labyrinth;
import fr.ul.pacmasque.model.World;

import java.util.*;

public class AlgorithmAStar extends Algorithm {
    /**
     * Monstre qui bougera selon cette algorithme. Le départ
     * de l'algorithme est donc sa position initiale.
     */
    private final Monster monster;

    /**
     * La position à atteindre dans l'algorithme est celle du
     * player.
     */
    private final Player player;

    /**
     * Graphe représentant le labyrinthe.
     */
    private final Graph graph;

    /**
     * Labyrinthe où se trouvent le monstre et le player.
     */
    private final Labyrinth labyrinth;

    /**
     * Récupère le monstre, le player, le labyrinthe et crée,
     * à partir de ce dernier, un graphe.
     *
     * @param world dans lequel est placé le monstre.
     * @param monster qu'on fait bouger.
     */
    public AlgorithmAStar(World world, Monster monster) {
        this.monster = monster;
        this.player = world.getPlayer();
        this.labyrinth = world.getLabyrinth();
        this.graph = this.createGraph();
    }

    /**
     * Le monstre bouge selon le chemin donné par l'algorithme A*.
     * Le tick() est appelé à chaque update du jeu.
     *
     * Il serait peut-être plus efficace de relancer cette algorithme
     * seulement quand la position du player ("target") change. Le
     * design pattern Observer pourrait par exemple être utilisé dans
     * une future version. Ou ne pas récupérer tout un chemin ("path")
     * avec A* mais seulement un pas.
     */
    @Override
    public void tick() {
        Vector2 start = new Vector2(monster.getNextPositionX(), monster.getNextPositionY());
        Vector2 target = new Vector2(player.getNextPositionX(), player.getNextPositionY());
        List<Vertex> path = this.aStarPath(target, start);
        if (!path.isEmpty()) System.out.println("monster: "+start+" has pathsize: "+path.size()+" when player is "+target);

        if (!monster.isMoving()){
            for (Vertex nextCase : path){
                System.out.println(nextCase.getPosition());
                monster.setNextPositionX(nextCase.getPosition().x);
                monster.setNextPositionY(nextCase.getPosition().y);
                switch (nextCase.getType()){
                    case up :
                        monster.addMouvement(Input.Keys.UP, 10);
                        break;
                    case down :
                        monster.addMouvement(Input.Keys.DOWN, 10);
                        break;
                    case left :
                        monster.addMouvement(Input.Keys.LEFT, 10);
                        break;
                    case right :
                        monster.addMouvement(Input.Keys.RIGHT, 10);
                        break;
                }
            }
        }
    }

    /**
     * Donne le chemin plus rapide pour arriver à targetPos depuis startPos en
     * appliquant l'algorithme de A étoile.
     * L'heuristique choisie est la distance à vol d'oiseau.
     *
     * @param targetPos position du player à ce moment donné.
     * @param startPos position initiale du monstre.
     * @return liste des cases à parcourir par le monstre, ie le chemin trouvé.
     */
    public List<Vertex> aStarPath(Vector2 targetPos, Vector2 startPos){
        List<Vertex> path = new ArrayList<>();
        boolean reached = false;

        Vertex target = new Vertex(targetPos);
        Vertex start = new Vertex(startPos);
        start.setDistance(0);
        start.setHeuristique(0);

        Stack<Vertex> closed = new Stack();
        Stack<Vertex> open = new Stack();
        open.push(start);


        while(!open.isEmpty() && !reached){
            open.sort(Vertex::compareTo);
            Vertex actualCase = open.pop();

            if (actualCase.equals(target)){
                this.findPath(actualCase, start, path);
                reached = true;
            }
            else{
                List<Vertex> neighbors = this.graph.getAdjacency(actualCase.getPosition());
                if (neighbors != null) {
                    for (Vertex neighbor : neighbors) {
                        boolean closer = neighbor.getDistance() < actualCase.getDistance();
                        if (!(closed.contains(neighbor)) || (open.contains(neighbor) && closer)) {
                            int newDistance = actualCase.getDistance() + 1;
                            neighbor.setDistance(newDistance);

                            int newHeuristic = neighbor.getDistance() + this.distanceAsTheCrowFlies(neighbor.getPosition(), targetPos);
                            neighbor.setHeuristique(newHeuristic);

                            open.push(neighbor);
                            neighbor.setPredecessor(actualCase);
                        }
                    }
                }
                closed.push(actualCase);
            }
        }

        return path;
    }

    /**
     * (x - targetX)^2 + (y - targetY)^2
     * @param actualC case où se trouve le monstre actuellement.
     * @return distance à vol d'oiseau entre la position actuelle
     * et la position du player.
     */
    private int distanceAsTheCrowFlies(Vector2 actualC, Vector2 target){
        double x = Math.pow(actualC.x - target.x, 2);
        double y = Math.pow(actualC.y - target.y, 2);
        return (int) (x + y);
    }

    /**
     * Utilisée pour récupérer le chemin créé par A* : on utilise le
     * prédécesseur de chaque case.
     *
     * @param c case à partir de laquelle on doit construire le chemin.
     * @param start case d'arrivée (la position du player).
     * @param path chemin créé par A*.
     */
    private void findPath(Vertex c, Vertex start, List<Vertex> path){
        while (!c.equals(start)){
            path.add(c);
            c = c.getPredecessor();
        }
    }

    /**
     * Transforme la labyrinthe en un graphe de la forme :
     * Map<Vertex, List<Vertex> où Vertex est un sommet (une
     * case du labyrinthe) et les arêtes existent quand les
     * deux cases sont adjacentes et aucune des deux est un mur.
     *
     * @return le graphe correspondant au labyrinthe
     */
    public Graph createGraph(){
        Graph graph = new Graph();
        int width = labyrinth.getWidth();
        int height = labyrinth.getHeight();

        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                if (!labyrinth.isWall(x, y)){
                    Vector2 pos = new Vector2(x, y);
                    graph.addVertex(pos);
                    List<Vertex> neighbors = findNeighbors(x, y);
                    for (Vertex v : neighbors)
                        if (!labyrinth.isWall(v.getPosition()))
                            graph.addEdge(pos, v.getPosition());
                }
            }
        }

        return graph;
    }

    /**
     * Récupère les voisins du haut, bas, droite et gauche que s'il
     * ne s'agit pas de murs, donc potentiellement vide.
     *
     * @param x abscisse de la case
     * @param y ordonnée de la case
     * @return liste des voisins de la case qui a pour abscisse x
     * et pour ordonnée y.
     */
    private List<Vertex> findNeighbors(int x, int y){
        List<Vertex> neighbors = new ArrayList<>();
        int width = labyrinth.getWidth();
        int height = labyrinth.getHeight();
        Vector2 pos = new Vector2();

        if (x > 0){
            pos.set(x - 1, y);
            if (!labyrinth.isWall(pos)){
                Vertex v = new Vertex(pos);
                v.setType(Vertex.typeNeighbor.left);
                neighbors.add(v);
            }
        }
        if (x < width - 1){
            pos.set(x + 1, y);
            if (!labyrinth.isWall(pos)) {
                Vertex v = new Vertex(pos);
                v.setType(Vertex.typeNeighbor.right);
                neighbors.add(v);
            }
        }
        if (y > 0){
            pos.set(x, y - 1);
            if (!labyrinth.isWall(pos)) {
                Vertex v = new Vertex(pos);
                v.setType(Vertex.typeNeighbor.down);
                neighbors.add(v);
            }
        }
        if (y < height - 1){
            pos.set(x, y + 1);
            if (!labyrinth.isWall(pos)) {
                Vertex v = new Vertex(pos);
                v.setType(Vertex.typeNeighbor.up);
                neighbors.add(v);
            }
        }

        return neighbors;
    }

    /**
     * Utilisée pour le test.
     *
     * @return le graphe créé.
     */
    public Graph getGraph() {
        return graph;
    }
}
