/*
 * Responder.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 14/11/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque.view.hierarchy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

/**
 * Objet permettant de répondre aux différents évènements, selon une hiérarchie de priorité
 */
public abstract class Responder implements InputProcessor {

	private boolean _isFirstResponder = false;

	/**
	 * @return le prochain Responder dans la chaîne
	 */
	@Nullable public Responder getNextResponder() {
		return null;
	}

	/**
	 * @return si il est le premier Responder de la chaîne
	 */
	public boolean isFirstResponder() {
		return this._isFirstResponder;
	}

	/**
	 * Demande au moteur graphique d'être le premier Responder de la chaîne
	 */
	public void becomeFirstResponder() {
		Gdx.input.setInputProcessor(this);
		this._isFirstResponder = true;
	}

	/**
	 * Notifie l'objet quand il n'est plus le premier Responder de la chaîne
	 */
	public void resignFirstResponder() {
		this._isFirstResponder = false;
	}

	/**
	 * Transfert l'évènement au prochain Responder, s'il existe
	 * @param callback l'évènement à transférer
	 * @return si l'évènement à été pris en compte
	 */
	 private boolean dispatchEvent(Function<Responder, Boolean> callback) {
		Responder nextResponder = this.getNextResponder();
		if (nextResponder != null) {
			return callback.apply(nextResponder);
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return this.dispatchEvent(responder -> responder.keyUp(keycode));
	}

	@Override
	public boolean keyDown(int keycode) {
		return this.dispatchEvent(responder -> responder.keyDown(keycode));
	}

	@Override
	public boolean keyTyped(char character) {
		return this.dispatchEvent(responder -> responder.keyTyped(character));
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return this.dispatchEvent(responder -> responder.touchDown(screenX, screenY, pointer, button));
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return this.dispatchEvent(responder -> responder.touchUp(screenX, screenY, pointer, button));
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return this.dispatchEvent(responder -> responder.touchDragged(screenX, screenY, pointer));
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return this.dispatchEvent(responder -> responder.mouseMoved(screenX, screenY));
	}

	@Override
	public boolean scrolled(int amount) {
		return this.dispatchEvent(responder -> responder.scrolled(amount));
	}
}
