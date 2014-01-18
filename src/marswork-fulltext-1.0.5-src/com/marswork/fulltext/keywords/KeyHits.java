package com.marswork.fulltext.keywords;

import com.marswork.algorithm.sort.SortableSupport;

/**
 * 
 * <p>
 * 关键字词频
 * <p>
 * 在进行分词时<br>
 * 显示关键字被击中次数
 * 
 * @author MarsDJ
 * @since 2011-12-13
 * @version 1.0
 */
public class KeyHits extends SortableSupport{

	private String key;

	private float hits = 0;

	public KeyHits(String key, float hits) {
		this.key = key;
		this.hits = hits;
	}

	public String getKey() {
		return key;
	}

	public float getHits() {
		return hits;
	}

	public String toString() {
		return key + "--" + hits;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @param hits
	 *            the hits to set
	 */
	public void setHits(float hits) {
		this.hits = hits;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.algorithm.sort.I_Sortable#sortValue()
	 */
	@Override
	public float sortValue() {
		return hits;
	}
}
