/*
 * BasicPlayer.java
 * ACL-2020-pacmasque
 *
 * Created by nicol on 18/10/2020.
 * Copyright © 2020 nicol. All rights reserved.
 */

package fr.ul.pacmasque.entity;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import fr.ul.pacmasque.exception.TextureException;
import fr.ul.pacmasque.util.TexturePack;
import fr.ul.pacmasque.util.TexturePackFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BasicPlayer implements Player {

	/**
	 * Booléen qui définit si le player est sous l'effet de la
	 * magie ou pas.
	 */
	private boolean isMagic;

	/**
	 * Position du player.
	 */
	private final Vector2 position;

	/**
	 * Timer qui définit le temps d'activation de l'effet
	 * magique.
	 */
	private final Timer.Task timerMagicalEffect;

	/**
	 * Nombre de vies du player.
	 */
	private int numberLifes;

	/**
	 * Future feature, to be implemented!
	 * @apiNote unused
	 */
	private final Vector2 nextPosition;

	/**
	 * Texture du player.
	 */
	private Texture texture;

	/**
	 * Liste des mouvements du player.
	 */
	private final List<Integer> movesList;

	public BasicPlayer(int x, int y){
		this.position = new Vector2(x,y);
		this.nextPosition = new Vector2(x,y);
		this.movesList = new ArrayList<>();
		this.numberLifes = 3;

		try {
			this.texture = Objects.requireNonNull(TexturePackFactory.getInstance().getTexturePack("basepack")).get(TexturePack.typeTexture.pacman);
		} catch (TextureException e) {
			e.printStackTrace();
		}

		// Une fois que le timer est arrivé à sa fin, la magie est finie.
		timerMagicalEffect = new Timer.Task() {
			@Override
			public void run() {
				setMagic(false);
			}
		};
	}

	/**
	 * @return la position virtuelle en X du Player
	 */
	public float getNextPositionX(){return this.nextPosition.x;}
	/**
	 * @return la position virtuelle en Y du Player
	 */
	public float getNextPositionY(){return this.nextPosition.y;}

	public void setNextPositionX(float pos){ this.nextPosition.x = pos; }
	public void setNextPositionY(float pos){ this.nextPosition.y = pos; }

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		batch.draw(texture, this.position.x, this.position.y, 1, 1);
		updateMovement();
	}

	/**
	 * Applique un micro-mouvement à la position réelle du personnage
	 */
	private void updateMovement(){
		//Si la liste des mouvements n'est pas vide, ajoute ce mouvement à la position du personnage
		if(!this.movesList.isEmpty()){

			int dir = this.movesList.get(0);
			this.movesList.remove(0);
			switch (dir){
				case Input.Keys.UP:
					this.position.y += 0.1f;
					break;
				case Input.Keys.DOWN:
					this.position.y -= 0.1f;
					break;
				case Input.Keys.RIGHT:
					this.position.x += 0.1f;
					break;
				case Input.Keys.LEFT:
					this.position.x -= 0.1f;
					break;
			}
		}
	}

	/**
	 * @return la position réelle du Player
	 */
	@Override
	public Vector2 getPosition() {
		return this.position;
	}

	public void setPositionX(float pos){ this.position.x = pos; }
	public void setPositionY(float pos){ this.position.y = pos; }

	/**
	 * Ajoute des micro-mouvement à la liste des prochains mouvements du Player
	 * @param dir la direction du mouvement
	 * @param x le nombre de micro-mouvement ajouté
	 */
	public void addMouvement(int dir, int x){
		for(int i = 1 ; i <= x ; i++)
			this.movesList.add(dir);
	}

	/**
	 * Supprime tous les micro-mouvements que devait effectuer le Player
	 */
	public void deleteMouvements(){
		this.movesList.clear();
	}

	/**
	 * Active la magie et le timer qui enlèvera cette magie au
	 * bout de 10 secondes.
	 *
	 * @param magic vrai si le player est tombé sur une case magique.
	 */
	@Override
	public void setMagic(boolean magic) {
		this.isMagic = magic;
		if (!timerMagicalEffect.isScheduled()) {
			Timer.schedule(timerMagicalEffect, 10);
			Timer.instance().start();
		}
	}

	@Override
	public void takeALife() {
		this.numberLifes -= 1;
	}

	// TODO : afficher le nb de vies dans l'écran
	@Override
	public int getNumberLifes() {
		return this.numberLifes;
	}

	@Override
	public boolean isMagic() {
		return this.isMagic;
	}

	@Override
	public boolean isDead() {
		return this.numberLifes == 0;
	}


}
