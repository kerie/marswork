/**
 * 
 */
package com.marswork.core.common.thread;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * <p>
 * <p>
 * 
 * @author MarsDJ
 * @since 2012-2-25
 * @version 1.0
 */
public abstract class MarsTask<V> implements Callable<V>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6728973959722908181L;

	public abstract V toDoTask();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public V call() throws Exception {
		return toDoTask();
	}

}
