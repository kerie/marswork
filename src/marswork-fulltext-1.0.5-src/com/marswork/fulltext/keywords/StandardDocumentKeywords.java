/**
 * 
 */
package com.marswork.fulltext.keywords;

import java.util.List;

/**
 * <p>
 * 标准文档分词器接口
 * <p>
 * 标准文档分词器默认内容属性权值为1，摘要属性权值5<br>
 * Title属性权值20，关键词属性权值30
 * 
 * @author MarsDJ
 * @since 2011-12-31
 * @version 1.0
 */
public class StandardDocumentKeywords implements I_DocumentKeywords {

	private KeywordsMaker maker;

	private float titleWeight = 20;

	private float abstractWeight = 5;

	private float keywordWeight = 30;

	/**
	 * 构造函数
	 */
	public StandardDocumentKeywords() {
		maker = new KeywordsMaker();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marswork.fulltext.keywords.I_DocumentKeywords#setTitle(java.lang.
	 * String)
	 */
	@Override
	public void appendTitle(String title) {
		maker.mergeKeyHits(title, titleWeight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marswork.fulltext.keywords.I_DocumentKeywords#setKeywords(java.lang
	 * .String)
	 */
	@Override
	public void appendKeywords(String keywords) {
		maker.mergeKeyHits(keywords, keywordWeight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marswork.fulltext.keywords.I_DocumentKeywords#setAbstract(java.lang
	 * .String)
	 */
	@Override
	public void appendAbstract(String abstract_) {
		maker.mergeKeyHits(abstract_, abstractWeight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marswork.fulltext.keywords.I_DocumentKeywords#appendContent(java.
	 * lang.String)
	 */
	@Override
	public void appendContent(String content) {
		maker.mergeKeyHits(content);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.marswork.fulltext.keywords.I_DocumentKeywords#getKeyHits()
	 */
	@Override
	public List<KeyHits> getKeyHits() {
		return maker.getKeyHits();
	}

	/**
	 * 对KeyHits集合进行截取<br>
	 * 有可能KeyHits集合中含有大量的KeyHits对象<br>
	 * 而许多KeyHits由于权值和命中率都很低<br>
	 * 对推荐查询效果的影响可以忽略<br>
	 * 可以改方法截取一定比例KeyHits<br>
	 * 默认截取比例为60%
	 * 
	 * @return 影响力在前60%的KeyHits
	 */
	public List<KeyHits> truncate() {
		return maker.truncatePercent(60);
	}

	/**
	 * @return the titleWeight
	 */
	public float getTitleWeight() {
		return titleWeight;
	}

	/**
	 * @param titleWeight
	 *            the titleWeight to set
	 */
	public void setTitleWeight(float titleWeight) {
		this.titleWeight = titleWeight;
	}

	/**
	 * @return the abstractWeight
	 */
	public float getAbstractWeight() {
		return abstractWeight;
	}

	/**
	 * @param abstractWeight
	 *            the abstractWeight to set
	 */
	public void setAbstractWeight(float abstractWeight) {
		this.abstractWeight = abstractWeight;
	}

	/**
	 * @return the keywordWeight
	 */
	public float getKeywordWeight() {
		return keywordWeight;
	}

	/**
	 * @param keywordWeight
	 *            the keywordWeight to set
	 */
	public void setKeywordWeight(float keywordWeight) {
		this.keywordWeight = keywordWeight;
	}
}
