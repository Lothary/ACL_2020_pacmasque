/*
 * State.java
 * ACL_2020_pacmasque
 *
 * Created by ugocottin on 2/12/2020.
 * Copyright © 2020 ugocottin. All rights reserved.
 */

package fr.ul.pacmasque;

public interface State<T extends Enum<T>> {

	void setState(T state);

	T getState();

}
