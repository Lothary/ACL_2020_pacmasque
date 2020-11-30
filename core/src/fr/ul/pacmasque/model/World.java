/*
 * World.java
 * ACL-2020-pacmasque
 *
 * Created by ValerieMarissens on 13/10/2020.
 * Copyright © 2020 ValerieMarissens. All rights reserved.
 */

package fr.ul.pacmasque.model;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import fr.ul.pacmasque.Drawable;
import fr.ul.pacmasque.algorithm.AlgorithmRandom;
import fr.ul.pacmasque.entity.*;
import fr.ul.pacmasque.entity.BasicPlayer;
import fr.ul.pacmasque.exception.TextureException;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Monde dans lequel les entités vont évoluer
 */
public class World implements Drawable {


	/**
	 * Labyrinthe du monde
	 */
	@NotNull private final Labyrinth labyrinth;

	/**
	 * Le nom du monde
	 */
	@NotNull private final String worldName;

	/**
	 * Gestionnaire des collisions
	 */
	@NotNull private final CollisionManager collisionManager;

	/**
	 * Joueur
	 */
	@NotNull private final Player player;

	private boolean hasWin;

	/**
	 * Liste des pastilles présentes dans le monde
	 */
	@NotNull private final List<Pastille> pastilles;

	/**
	 * Liste des monstres présents dans le monde
	 */
	@NotNull private final List<Monster> monsters;

	@NotNull private final List<Case> specialCases;

	/**
	 * Crée un monde
	 * @param labyrinth le labyrinthe du monde
	 */
	public World(@NotNull Labyrinth labyrinth, @NotNull String worldName) {
		this.labyrinth = labyrinth;
		this.collisionManager = new CollisionManager(this);

		// TODO: - Définir, autrement, la position de départ du joueur...
		this.player = new BasicPlayer((int)labyrinth.getPositionDepart().x, (int)labyrinth.getPositionDepart().y);

		this.pastilles = new ArrayList<>();
		this.monsters = new ArrayList<>();
		this.specialCases = new ArrayList<>();

		this.worldName = worldName;

		//Créer 3 monstres à des positions aléatoires viables
		this.createMonster(3);
		//Créer 10 pastilles à des positions aléatoires viables
		this.createPastille(10);
		//Créer 10 cases spéciales dont le trésor, des cases de téléportation,
		//des cases de magie et des pièges.
		this.createSpecialCases();

		this.hasWin = false;
	}

	/**
	 * @return le nom du monde
	 */
	public @NotNull String getWorldName() {
		return this.worldName;
	}

	/**
	 * @return largeur du monde
	 */
	public int getWidth() {
		return this.labyrinth.getWidth();
	}

	/**
	 * @return hauteur du monde
	 */
	public int getHeight() {
		return this.labyrinth.getHeight();
	}

	/**
	 * @return gestionnaire de collisions du monde
	 */
	@NotNull public CollisionManager getCollisionManager() {
		return this.collisionManager;
	}

	/**
	 * @return le joueur
	 */
	@NotNull public Player getPlayer() {
		return this.player;
	}

	/**
	 * Ajoute un monstre au monde
	 * @param monster un nouveau monstre
	 */
	public void addMonster(Monster monster) {
		if (!this.monsters.contains(monster))
			this.monsters.add(monster);
	}

	/**
	 * Créer un monstre puis l'ajoute au monde
	 * @param nb nombre de monstres à créer
	 */
	private void createMonster(int nb) {
		Vector2 finalCase;

		for(int i = 0; i < nb ; i++){
			finalCase = this.findFreeCase();

			BasicMonster dummyMonster = new BasicMonster((int)finalCase.x, (int)finalCase.y);
			dummyMonster.setAlgorithm(new AlgorithmRandom(this, dummyMonster));
			this.addMonster(dummyMonster);

		}
	}

	/**
	 * @return Une case libre dans le labyrinthe
	 */
	private Vector2 findFreeCase(){
		Random random = new Random(System.currentTimeMillis());

		int x = random.nextInt(this.labyrinth.getWidth());
		int y = random.nextInt(this.labyrinth.getHeight());
		Vector2 finalCase = new Vector2(x, y);

		while((this.labyrinth.isWall(finalCase)) || (this.isSpecialCase(finalCase))) {
			x = random.nextInt(this.labyrinth.getWidth());
			y = random.nextInt(this.labyrinth.getHeight());
			finalCase = new Vector2(x, y);
		}
		return finalCase;
	}

	private boolean isSpecialCase(Vector2 coordinates){
		AtomicBoolean isTaken = new AtomicBoolean(false);
		this.specialCases.forEach(c -> {
			if (c.getPosition().equals(coordinates)){
				isTaken.set(true);
			}
		});
		return isTaken.get();
	}

	/**
	 * Créer une pastille puis l'ajoute au monde
	 * @param nb nombre de pastilles à créer
	 */
	private void createPastille(int nb) {
		Vector2 finalCase;

		for (int i = 0; i < nb; i++) {
			finalCase = this.findFreeCase();
			BasicPastille pastille = new BasicPastille((int) finalCase.x, (int) finalCase.y);
			this.addPastille(pastille);
		}
	}

	private void createSpecialCases(){
		Vector2 finalCase;

		for (int i = 0; i < 4; i++) {
			finalCase = this.findFreeCase();
			int x = (int) finalCase.x;
			int y = (int) finalCase.y;

			if (i == 0) { // un seul trésor
				Case c = new TreasureCase(x, y);
				specialCases.add(c);
			}
			if (i == 1) { // une case de téléportation
				Case c = new TeleportationCase(x, y);
				specialCases.add(c);
			}
			if (i == 2) { // un piège
				Case c = new TrapCase(x, y);
				specialCases.add(c);
			}
			if (i == 3) { // une case magique
				Case c = new MagicCase(x, y);
				specialCases.add(c);
			}
		}
	}

	/**
	 * @return le labyrinthe du monde
	 */
	public @NotNull Labyrinth getLabyrinth() {
		return labyrinth;
	}

	/**
	 * Ajoute une pastille au monde
	 * @param pastille une nouveau pastille
	 */
	public void addPastille(Pastille pastille) {
		if (!this.pastilles.contains(pastille))
			this.pastilles.add(pastille);
	}

	@ApiStatus.Experimental
	public void updateCollision() {
		boolean collision = false;

		//Collision avec les monstres
		for(Monster e : this.monsters){
			collision = this.collisionManager.isCollision(e);
			if(collision){
				this.player.setPositionX(this.labyrinth.getPositionDepart().x);
				this.player.setPositionY(this.labyrinth.getPositionDepart().y);
				this.player.setNextPositionX(this.labyrinth.getPositionDepart().x);
				this.player.setNextPositionY(this.labyrinth.getPositionDepart().y);
			}
		}

		//Collision avec les pastilles
		for(Pastille e : this.pastilles){
			collision = this.collisionManager.isCollision(e);
			if(collision){
				if(e.isVisible())
					e.setVisible(false);
			}
		}

		//"Collision" avec les cases spéciales
		for (Case c : this.specialCases) {
			collision = this.collisionManager.isInside(c);
			if (collision){
				switch (c.getType()) {
					case treasure:
						this.hasWin = true;
						System.out.println("YOU WIN");
						//todo : nouvelle view de gagnant?
						break;
					case trap:
						System.out.println("caíste");
						//todo : diminuer la vie du player
						break;
					case magic:
						System.out.println("magic");
						//todo : augmenter la vie du player ? Ou superpower ?
						break;
					case teleportation:
						// todo: search next teleportation door
						this.player.setPositionX(this.labyrinth.getPositionDepart().x);
						this.player.setPositionY(this.labyrinth.getPositionDepart().y);
						this.player.setNextPositionX(this.labyrinth.getPositionDepart().x);
						this.player.setNextPositionY(this.labyrinth.getPositionDepart().y);
						break;
				}
			}
		}
	}

	public void movePlayer(int direction) {

		float moveAmount = 1.0f;
		Vector2 finalCase = new Vector2(this.player.getNextPositionX(), this.player.getNextPositionY());

		switch (direction) {
			case Input.Keys.LEFT:
				finalCase.x = this.player.getNextPositionX() - moveAmount;
				if (!this.labyrinth.isWall(finalCase) && finalCase.x >= 0.0) {
					this.player.setNextPositionX(this.player.getNextPositionX() - moveAmount);
					this.player.addMouvement(Input.Keys.LEFT, 10);
				}
				break;
			case Input.Keys.RIGHT:
				finalCase.x = this.player.getNextPositionX() + moveAmount;
				if (!this.labyrinth.isWall(finalCase) && finalCase.x < this.labyrinth.getWidth()) {
					this.player.setNextPositionX(this.player.getNextPositionX() + moveAmount);
					this.player.addMouvement(Input.Keys.RIGHT, 10);
				}
				break;
			case Input.Keys.UP:
				finalCase.y = this.player.getNextPositionY() + moveAmount;
				if (!this.labyrinth.isWall(finalCase) && finalCase.y < this.labyrinth.getHeight()) {
					this.player.setNextPositionY(this.player.getNextPositionY() + moveAmount);
					this.player.addMouvement(Input.Keys.UP, 10);
				}
				break;
			case Input.Keys.DOWN:
				finalCase.y = this.player.getNextPositionY() - moveAmount;
				if (!this.labyrinth.isWall(finalCase) && finalCase.y >= 0.0) {
					this.player.setNextPositionY(this.player.getNextPositionY() - moveAmount);
					this.player.addMouvement(Input.Keys.DOWN, 10);
				}
				break;
		}
	}

	/**
	 * Indique aux algorithmes de se mettre à jour
	 */
	// TODO: - Centraliser la mise à jour du monde
	public void moveMonsters(){
		for(Monster m : this.monsters){
			m.getAlgorithm().tick();
		}
	}

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		this.labyrinth.draw(batch, x, y, width, height);
		this.player.draw(batch, x, y, width, height);

		this.monsters.forEach(en -> en.draw(batch, x, y, width, height));
		for(Pastille p : this.pastilles) {
			if(p.isVisible())
				p.draw(batch, x, y, width, height);
		}

		for (Case c : this.specialCases){
			c.draw(batch, x, y, width, height);
		}
	}
}
