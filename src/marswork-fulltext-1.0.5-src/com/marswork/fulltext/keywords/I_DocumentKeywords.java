/**
 * 
 */
package com.marswork.fulltext.keywords;

import java.util.List;

import com.marswork.fulltext.searcher.RecomendSearcher;

/**
 * <p>
 * 文档分词器接口
 * <p>
 * 在进行查询前，对原对象进行分析<br>
 * 将其分为title属性，摘要属性，内容属性，关键字属性<br>
 * 每种属性分别进行分词和赋予权值 <br>
 * 最终生成用于在 {@link RecomendSearcher}<br>
 * 中可以直接进行查询的对象{@link KeyHits}集合
 * 
 * @author MarsDJ
 * @since 2011-12-31
 * @version 1.0
 * @see {@link RecomendSearcher}
 * @see {@link KeyHits}
 */
public interface I_DocumentKeywords {

	/**
	 * 追加Title属性
	 * 
	 * @param title
	 *            Title属性值
	 */
	public void appendTitle(String title);

	/**
	 * 追加关键字属性
	 * 
	 * @param keywords
	 *            关键字属性值
	 */
	public void appendKeywords(String keywords);

	/**
	 * 追加摘要属性
	 * 
	 * @param abstract_
	 *            摘要属性值
	 */
	public void appendAbstract(String abstract_);

	/**
	 * 追加内容属性
	 * 
	 * @param abstract_
	 *            内容属性值
	 */
	public void appendContent(String content);

	/**
	 * 生成KeyHits集合<br>
	 * KeyHits是{@link RecomendSearcher}指定的用于查询的参数
	 */
	public List<KeyHits> getKeyHits();

}
