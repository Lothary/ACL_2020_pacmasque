/*
 * AStar.java
 * ACL_2020_pacmasque
 *
 * Created by ValerieMarissens on 13/12/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.algorithm;

import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.entity.Monster;
import fr.ul.pacmasque.entity.Player;
import fr.ul.pacmasque.model.Labyrinth;
import fr.ul.pacmasque.model.World;

import java.util.*;

public class AlgorithmAStar extends Algorithm {
    private final Monster monster;
    private final Player player;
    private final Graph graph;

    public AlgorithmAStar(World world, Monster monster) {
        this.monster = monster;
        this.player = world.getPlayer();
        this.graph = this.toGraph(world.getLabyrinth());
    }

    /**
     * Le monstre bouge selon le chemin donné par l'algorithme A*.
     */
    @Override
    public void tick() {
        //todo: voir si getPosition ou getNextPosition
        Vector2 start = new Vector2(monster.getNextPositionX(), monster.getNextPositionX());
        Vector2 target = new Vector2(player.getNextPositionX(), player.getNextPositionY());
        List<Vector2> path = this.aStarPath(target, start);

        if (!monster.isMoving()){
            for (Vector2 nextCase : path){
                monster.setNextPositionX(nextCase.x);
                monster.setNextPositionY(nextCase.y);
            }
        }
    }

    private List<Vector2> aStarPath(Vector2 targetPos, Vector2 startPos){
        List<Vector2> path = new ArrayList<>();
        boolean reached = false;

        Vertex target = new Vertex(targetPos);
        Vertex start = new Vertex(startPos);
        start.setDistance(0);
        start.setHeuristique(0);

        Stack<Vertex> closed = new Stack();
        Stack<Vertex> open = new Stack();
        open.push(start);

        while(!open.empty() && !reached){
            Vertex actualCase = open.pop();
            if (actualCase.equals(target)){
                this.findPath(actualCase, start, path);
                reached = true;
            }
            else{
                List<Vertex> neighbors = this.graph.getAdjacency(actualCase.getPosition());
                for (Vertex neighbor : neighbors){
                    boolean closer = neighbor.getDistance() < actualCase.getDistance();
                    if (!(closed.contains(neighbor)) || (open.contains(neighbor) && closer)){
                        float newDistance = actualCase.getDistance() + 1;
                        neighbor.setDistance(newDistance);

                        float newHeuristique = neighbor.getDistance() + this.distanceAsTheCrowFlies(neighbor.getPosition(), targetPos);
                        neighbor.setHeuristique(newHeuristique);

                        open.push(neighbor);
                        neighbor.setPredecessor(actualCase);
                    }
                }
                closed.push(actualCase);
            }
        }
        if (open.empty()){
            System.out.println("Pas de chemin trouvé.");
        }
        return path;
    }

    /**
     * (x - targetX)^2 + (y - targetY)^2
     * @param actualC case où se trouve le monstre actuellement.
     * @return distance à vol d'oiseau entre la position actuelle
     * et la position du player.
     */
    private float distanceAsTheCrowFlies(Vector2 actualC, Vector2 target){
        float x = (float) Math.pow(actualC.x - target.x, 2);
        float y = (float) Math.pow(actualC.y - target.y, 2);
        return x + y;
    }

    private void findPath(Vertex c, Vertex start, List<Vector2> path){
        while (!c.equals(start)){
            path.add(c.getPosition());
            c = c.getPredecessor();
        }
    }

    private Graph toGraph(Labyrinth labyrinth){
        Graph graph = new Graph();
        int width = labyrinth.getWidth();
        int height = labyrinth.getHeight();

        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                if (!labyrinth.isWall(x, y)){
                    Vector2 pos = new Vector2(x, y);
                    graph.addVertex(pos);
                    for (Vector2 v : findNeighbors(x, y, labyrinth))
                        graph.addEdge(pos, v);
                }
            }
        }


        return graph;
    }

    private List<Vector2> findNeighbors(int x, int y, Labyrinth labyrinth){
        List<Vector2> neighbors = new ArrayList<>();
        int width = labyrinth.getWidth();
        int height = labyrinth.getHeight();

        if (x > 0){
            if (!labyrinth.isWall(x - 1, y))
                neighbors.add(new Vector2(x + 1, y));
        }
        if (x < width - 1){
            if (!labyrinth.isWall(x + 1, y))
                neighbors.add(new Vector2(x + 1, y));
        }
        if (y > 0){
            if (!labyrinth.isWall(x, y - 1))
                neighbors.add(new Vector2(x, y - 1));
        }
        if (y < height - 1){
            if (!labyrinth.isWall(x, y + 1))
                neighbors.add(new Vector2(x, y + 1));
        }

        //todo : add type neighbor ? (si Input.KEYS quand monster move)?
        return neighbors;
    }
}
