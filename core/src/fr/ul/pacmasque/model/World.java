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
import fr.ul.pacmasque.State;
import fr.ul.pacmasque.WorldState;
import fr.ul.pacmasque.algorithm.AlgorithmRandom;
import fr.ul.pacmasque.entity.*;
import fr.ul.pacmasque.entity.BasicPlayer;
import fr.ul.pacmasque.model.cases.*;
import fr.ul.pacmasque.util.TexturePack;
import fr.ul.pacmasque.util.TexturePackFactory;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Monde dans lequel les entités vont évoluer
 */
public class World implements Drawable, State<WorldState> {


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

	/**
	 * Liste des pastilles présentes dans le monde
	 */
	@NotNull private final List<Pastille> pastilles;

	/**
	 * Liste des monstres présents dans le monde
	 */
	@NotNull private final List<Monster> monsters;

	/**
	 * Liste des cases spéciales présentes dans le monde.
	 */
	@NotNull private final List<Case> specialCases;

	/**
	 * Nombre de monstres.
	 */
	private final int numberOfMonsters;

	@NotNull private WorldState state;

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
		this.numberOfMonsters = 10;

		this.worldName = worldName;

		this.state = WorldState.Idle;

		//Créer 3 monstres à des positions aléatoires viables
		this.createMonster(3);
		//Créer 10 pastilles à des positions aléatoires viables
		this.createPastille(numberOfMonsters);
		//Créer des cases spéciales dont le trésor, des cases de téléportation,
		//des cases de magie et des pièges.
		this.createSpecialCases();

		this.state = WorldState.Playing;
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

	@Override
	public WorldState getState() {
		return this.state;
	}

	@Override
	public void setState(WorldState state) {
		this.state = state;
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

	/**
	 * Utilisé pour éviter que lors de la création des cases
	 * spéciales, 2 cases de différent type soient superposées.
	 *
	 * @param coordinates coordonnées de la case potentiellement vide
	 * @return vrai si la case est déjà occupée par une case spéciale.
	 */
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

	/**
	 * Créer les cases spéciales, 4 cases sont créées :
	 * - 1 trésor
	 * - 1 piège
	 * - 1 case téléportation
	 * - 1 case magique
	 */
	private void createSpecialCases() {
		Vector2 finalCase;

		TexturePack pack = TexturePackFactory.getInstance().getTexturePack("basepack");

		for (int i = 0; i < 4; i++) {
			finalCase = this.findFreeCase();
			int x = (int) finalCase.x;
			int y = (int) finalCase.y;

			if (i == 0) { // un seul trésor
				Case c = new TreasureCase(x, y, pack);
				specialCases.add(c);
			}
			if (i == 1) { // une case de téléportation
				Case c = new TeleportationCase(x, y, pack);
				specialCases.add(c);
			}
			if (i == 2) { // un piège
				Case c = new TrapCase(x, y, pack);
				specialCases.add(c);
			}
			if (i == 3) { // une case magique
				Case c = new MagicCase(x, y, pack);
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

	/**
	 * Se charge de bouger les monstres, update la collision et
	 * l'état actuel du niveau.
	 */
	public void update(){
		this.moveMonsters();
		this.updateCollision();
		this.updateLevelState();
	}

	/**
	 * Gère la collision du player avec :
	 * - les montres :
	 * 		- quand il est normal, il meurt
	 * 		- quand il est magique, c'est le monstre qui meurt
	 * - les pastilles : il les mange
	 * - les cases spéciales : pour chacune il y a un effet différent
	 */
	@ApiStatus.Experimental
	private void updateCollision() {
		boolean collision = false;

		//Collision avec les monstres
		Iterator<Monster> iterator = monsters.iterator();
		while(iterator.hasNext()){
			Monster monster = iterator.next();
			collision = this.collisionManager.isCollision(monster);
			if(collision){
				if (!this.player.isMagic()) { // en temps normal
					this.player.deleteMouvements();
					this.player.setPositionX(this.labyrinth.getPositionDepart().x);
					this.player.setPositionY(this.labyrinth.getPositionDepart().y);
					this.player.setNextPositionX(this.labyrinth.getPositionDepart().x);
					this.player.setNextPositionY(this.labyrinth.getPositionDepart().y);

					this.player.takeALife();
				}
				else { // le player est tombé sur une case magique donc il peut tuer le monstre
					iterator.remove();
				}
			}
		}

		//Collision avec les pastilles
		for(Pastille pastille : this.pastilles){
			collision = this.collisionManager.isCollision(pastille);
			if(collision){
				if(pastille.isVisible())
					pastille.setVisible(false);
			}
		}

		//"Collision" avec les cases spéciales
		for (Case c : this.specialCases) {
			collision = this.collisionManager.isInside(c);
			if (collision){
				switch (c.getType()) {
					// Le player gagne
					case treasure:
						// boolean hasWin ou enum state of the game ?
						//todo : nouvelle view de gagnant? ou nouveau laby?
						this.setState(WorldState.Win);
						break;

					// Le player perd une vie
					case trap:
						this.player.takeALife();
						break;

					// Le player devient magique, ie il peut
					// tuer les monstres en les touchant
					case magic:
						this.player.setMagic(true);
						break;

					// Le player est téléporté a une autre localisation
					case teleportation:
						// todo: implémenter comme couple<teleportation>
						this.player.setPositionX(this.labyrinth.getPositionDepart().x);
						this.player.setPositionY(this.labyrinth.getPositionDepart().y);
						this.player.setNextPositionX(this.labyrinth.getPositionDepart().x);
						this.player.setNextPositionY(this.labyrinth.getPositionDepart().y);
						break;
				}
			}
		}
	}

	/**
	 * Se charge de vérifier si le player a encore des vies
	 * ou pas. Si ce n'est pas le cas, le niveau est recommencé.
	 */
	private void updateLevelState(){
		if (this.player.isDead()){ // Le player a perdu toutes ses vies : restart
			setState(WorldState.Dead);
		}
	}

	/**
	 * Réinitialise le niveau :
	 * - toutes les pastilles réapparaîssent
	 * - tous les monstres sont ressuscités.
	 */
	public void restart() {
		setState(WorldState.Playing);
		this.player.setNumberLifes(3);

		for (Pastille p : this.pastilles) {
			p.setVisible(true);
		}

		int nbMissingMonsters = this.numberOfMonsters - this.monsters.size();
		if (nbMissingMonsters > 0) {
			this.createMonster(nbMissingMonsters);
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
	private void moveMonsters(){
		for(Monster m : this.monsters){
			m.getAlgorithm().tick();
		}
	}

	/**
	 * Dessine les murs du labyrinthe, le player, les monstres
	 * (que s'ils sont vivants), les pastilles et les cases
	 * spéciales.
	 *
	 * @param batch le batch dans lequel se dessiner
	 * @param x la coordonnée x du dessin
	 * @param y la coordonnée y du dessin
	 * @param width la largeur du dessin
	 * @param height la hauteur du dessin
	 */
	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		this.labyrinth.draw(batch, x, y, width, height);

		for(Pastille p : this.pastilles) {
			if(p.isVisible())
				p.draw(batch, x, y, width, height);
		}

		for (Case c : this.specialCases){
			c.draw(batch, x, y, width, height);
		}

		this.player.draw(batch, x, y, width, height);

		for (Monster m : this.monsters){
			m.setPlayerIsMagic(this.player.isMagic());
			m.draw(batch, x, y, width, height);
		}
	}
}
