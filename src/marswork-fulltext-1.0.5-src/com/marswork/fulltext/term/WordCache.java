/**
 * 
 */
package com.marswork.fulltext.term;

import java.util.HashMap;

import com.marswork.fulltext.keywords.I_DocumentKeywords;
import com.marswork.fulltext.keywords.KeyHits;

/**
 * <p>
 * 关键字缓存
 * <p>
 * 将关键字及权重缓存起来<br>
 * 在分词时计算权值时<br>
 * 将关键字权值加入要{@link KeyHits}权值计算中
 * 
 * @author MarsDJ
 * @since 2012-1-4
 * @version 1.0
 * @see {@link KeyHits}
 * @see {@link I_DocumentKeywords}
 */
public class WordCache {

	private static HashMap<String, Float> wordCache = new HashMap<String, Float>();

	private WordCache() {

	}

	/**
	 * 缓存关键字<br>
	 * 当缓存的关键字不存在时，缓存改关键字<br>
	 * 如已经存在，则将关键字的权值更新为更高的权值
	 * 
	 * @param word
	 *            关键字
	 * @param weight
	 *            关键字权值
	 */
	public static void cacheWord(String word, float weight) {
		if (wordCache.get(word) == null || wordCache.get(word) < weight) {
			wordCache.put(word, weight);
		}
	}

	/**
	 * 获取关键字的权值
	 * 
	 * @param word
	 *            关键字
	 * @return 关键字权值
	 */
	public static float getWordWeight(String word) {
		Float temp = wordCache.get(word);
		if (temp == null) {
			return 1;
		}
		return temp;
	}
}
