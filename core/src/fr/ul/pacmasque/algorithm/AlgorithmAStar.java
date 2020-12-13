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
import fr.ul.pacmasque.model.World;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class AlgorithmAStar extends Algorithm {
    private World world;
    private Monster monster;
    private Player player;
    private List<Vector2> path;
    private Vector2 start;
    private Vector2 target;
    private Map<Vector2, Float> distances;
    private Map<Vector2, Float> heuristiques;

    public AlgorithmAStar(World world, Monster monster) {
        this.world = world;
        this.monster = monster;
        this.player = this.world.getPlayer();
        this.path = new ArrayList<>();
    }

    @Override
    public void tick() {
        if (!this.monster.isMoving()){
            // bouge selon le chemin donné par astar
        }
    }

    private void astar(){
        this.start = new Vector2(this.monster.getNextPositionX(), this.monster.getNextPositionX());
        this.target = new Vector2(this.player.getNextPositionX(), this.player.getNextPositionY());
        boolean reached = false;

        this.distances = new HashMap<>();
        this.distances.put(start, 0f);

        this.heuristiques = new HashMap<>();

        Stack closed = new Stack();
        Stack open = new Stack();
        open.push(start);

        while(!open.empty() && !reached){
            Vector2 actualCase = (Vector2) open.pop();
            if (actualCase.equals(target)){
                reached = true;
            }
            else{
                List<Vector2> neighbors = this.getNeighbors(actualCase);
                for (Vector2 n : neighbors){
                    boolean closer = distances.get(n) < distances.get(actualCase);
                    if (!(closed.contains(n)) || (open.contains(n) && closer)){
                        float newDistance = distances.get(actualCase) + 1;
                        distances.put(n, newDistance);

                        float newHeuristique = distances.get(n) + this.distanceAsTheCrowFlies(n);
                        heuristiques.put(n, newHeuristique);

                        open.push(n);
                        path.add(n); // changer?
                    }
                }
                closed.push(actualCase);
            }
        }
        if (open.empty()){
            System.out.println("Pas de chemin trouvé.");
        }
    }

    private List<Vector2> getNeighbors(Vector2 c){
        List<Vector2> neighbors = new ArrayList<>();
        //todo
        return neighbors;
    }

    /**
     * (x - targetX)^2 + (y - targetY)^2
     * @param actualC case où se trouve le monstre actuellement.
     * @return distance à vol d'oiseau entre la position actuelle
     * et la position du player.
     */
    private float distanceAsTheCrowFlies(Vector2 actualC){
        float x = (float) Math.pow(actualC.x - target.x, 2);
        float y = (float) Math.pow(actualC.y - target.y, 2);
        return x + y;
    }
}
