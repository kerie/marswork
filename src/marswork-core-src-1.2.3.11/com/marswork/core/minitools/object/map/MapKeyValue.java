/**
 * 
 */
package com.marswork.core.minitools.object.map;

/**
 * <p>
 * Map的键值对
 * <p>
 * 一般用以对Map的遍历<br>
 * 记录下Map的键和值
 * 
 * @author MarsDJ
 * @since 2012-2-6
 * @version 1.0
 * @see {@link BaseMap#walkMap(java.util.Map)}
 */
public class MapKeyValue<T, K> {

	private T key;

	private K value;

	public MapKeyValue() {
	}

	public MapKeyValue(T key, K value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public T getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(T key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public K getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(K value) {
		this.value = value;
	}

}
