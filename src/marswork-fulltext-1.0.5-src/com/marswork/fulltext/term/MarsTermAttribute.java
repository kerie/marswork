/**
 * 
 */
package com.marswork.fulltext.term;

import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 * <p>
 * MarsTermAttribute
 * <p>
 * 对{@link CharTermAttribute}进行重新包转，使之支持权值<br>
 * 及源对象和目标文档击中的关键字也支持权值
 * 
 * @author MarsDJ
 * @since 2012-1-4
 * @version 1.0
 */
public class MarsTermAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4358568610392883461L;

	private float boost = 1.0f;

	private CharTermAttribute charTermAttribute;

	/**
	 * 构造函数
	 * 
	 * @param charTermAttribute
	 *            需要包装的CharTermAttribute对象
	 */
	public MarsTermAttribute(CharTermAttribute charTermAttribute) {
		this.charTermAttribute = charTermAttribute;
		boost = WordCache.getWordWeight(charTermAttribute.toString());
	}

	@Override
	public String toString() {
		return charTermAttribute.toString();
	}

	/**
	 * @return the boost
	 */
	public float getBoost() {
		return boost;
	}
}
