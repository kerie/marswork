package com.marswork.fulltext.index;

import java.util.List;

import org.apache.lucene.document.Document;

/**
 * 
 * <p>
 * 索引构造器接口
 * <p>
 * 用于创建，追加，删除，更新索引
 * 
 * @author MarsDJ
 * @since 2012-3-10
 * @version 1.0
 */
public interface I_IndexBuilder<E> {

	/**
	 * 创建索引
	 * 
	 * @param source
	 *            数据源集合
	 */
	public void createIndex(List<E> source);

	/**
	 * 追加索引
	 * 
	 * @param source
	 *            数据源
	 */
	public void appendIndex(E source);

	/**
	 * 追加索引
	 * 
	 * @param source
	 *            数据源集合
	 */
	public void appendIndex(List<E> source);

	/**
	 * 删除索引<br>
	 * 删除指定field中值为value的记录
	 * 
	 * @param filed
	 *            指定的filed
	 * @param value
	 *            指定的value
	 */
	public void deleteDocument(String filed, String value);

	/**
	 * 更新索引<br>
	 * 将指定field中值为value的记录更新为新的doc
	 * 
	 * @param filed
	 *            指定的filed
	 * @param value
	 *            指定的value
	 * @param value
	 *            更新后的新doc
	 */
	public void updateDocument(String filed, String value, Document doc);
}
