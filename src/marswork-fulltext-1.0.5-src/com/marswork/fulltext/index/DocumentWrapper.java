/**
 * 
 */
package com.marswork.fulltext.index;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import com.marswork.core.common.data.MarsBean;
import com.marswork.core.minitools.object.BaseString;

/**
 * <p>
 * 文档包装器
 * <p>
 * 在新建或更新索引的时候<br>
 * 可以用包装器快速将对象包装成文档<br>
 * 并制定常用属性
 * <p>
 * 包装器要将对象中每个需要索引的属性制作成Field<br>
 * 包装器认为常见的Field有5种：<br>
 * 1：TitleField通常是对象的名字<br>
 * 这种属性的权值非常高，需要分词<br>
 * 2：AbstractField通常是对象的摘要，简介等<br>
 * 这种属性权值高于一般属性，需要分词<br>
 * 3：AtomicField通常是对象中一些不可分词或不必分词的属性，如时间，积分等<br>
 * 这种属性权值一般，不需要分词<br>
 * 4：NormalField通常是对象中的详细内容，详细介绍等大文本属性<br>
 * 这种属性权值一般，需要分词<br>
 * 5：KeywordField通常是对象中的关键词，本身有人为分词的色彩<br>
 * 这种属性权值最高，需要分词<br>
 * <p>
 * 包装器默认一般属性权值为1，摘要属性权值5<br>
 * Title属性权值20，关键词属性权值30
 * 
 * @author MarsDJ
 * @since 2011-12-31
 * @version 1.0
 */
public abstract class DocumentWrapper<T extends MarsBean> {

	public static final String OBJECT_ID = "OBJECT_ID";

	public static final String OBJECT_TITLE = "OBJECT_TITLE";

	public static final String OBJECT_CATEGORY = "OBJECT_CATEGORY";

	public static final String OBJECT_ABSTRACT = "OBJECT_ABSTRACT";

	public static final String OBJECT_CONTENT = "OBJECT_CONTENT";

	public static final String OBJECT_ICON = "OBJECT_ICON";

	public static final String OBJECT_PAGE_ID = "OBJECT_PAGE_ID";

	public static final String OBJECT_KEYWORDS = "OBJECT_KEYWORDS";

	public static final String OBJECT_DYNAMIC_TAG = "OBJECT_DYNAMIC_TAG";

	public static final String OBJECT_SUBMIT_TIME = "OBJECT_SUBMIT_TIME";

	public static final String OBJECT_STATUS = "OBJECT_STATUS";
	
	private float titleWeight = 20;

	private float abstractWeight = 5;

	private float keywordWeight = 30;

	private Document document;

	public abstract Document doWrap(T bean);

	public abstract T unWrap(Document document);

	public List<T> unWrap(List<Document> documents) {
		List<T> result = new ArrayList<T>();
		for (Document document : documents) {
			result.add(this.unWrap(document));
		}
		return result;
	}

	/**
	 * 构造函数
	 */
	public DocumentWrapper() {
		this.document = new Document();
	}

	/**
	 * 将一个属性包装为Field<br>
	 * 可设置该属性的所有信息
	 * 
	 * @param key
	 *            属性名
	 * @param value
	 *            属性值
	 * @param weight
	 *            属性权值
	 * @param analyse
	 *            是否需要分词
	 */
	public void wrapField(String key, Object value, float weight, boolean analyse) {
		if (value != null) {
			Field newField = new Field(key, BaseString.cleanUp(value), Field.Store.YES,
					analyse ? Field.Index.ANALYZED : Field.Index.NOT_ANALYZED);
			newField.setBoost(weight);
			document.add(newField);
		}
	}

	/**
	 * 将一个属性作为Title属性包装为Field<br>
	 * Title属性默认权值20，需要分词
	 * 
	 * @param key
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public void wrapTitleField(String key, Object value) {
		wrapField(key, value, titleWeight, true);
	}

	/**
	 * 将一个属性作为摘要属性包装为Field<br>
	 * 摘要属性默认权值5，需要分词
	 * 
	 * @param key
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public void wrapAbstractField(String key, Object value) {
		wrapField(key, value, abstractWeight, true);
	}

	/**
	 * 将一个属性作为原子属性包装为Field<br>
	 * 原子属性默认权值1，不需要分词
	 * 
	 * @param key
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public void wrapAtomicField(String key, Object value) {
		wrapField(key, value, 1, false);
	}

	/**
	 * 将一个属性作为原子属性包装为Field<br>
	 * 并指定权值<br>
	 * 原子属性不需要分词
	 * 
	 * @param key
	 *            属性名
	 * @param value
	 *            属性值
	 * @param weight
	 *            指定的权值
	 */
	public void wrapAtomicField(String key, Object value, float weight) {
		wrapField(key, value, weight, false);
	}

	/**
	 * 将一个属性作为一般属性包装为Field<br>
	 * 一般属性默认权值1，需要分词
	 * 
	 * @param key
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public void wrapNormalField(String key, Object value) {
		wrapField(key, value, 1, true);
	}

	/**
	 * 将一个属性作为关键字属性包装为Field<br>
	 * 关键字属性默认权值30，需要分词
	 * 
	 * @param key
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public void wrapKeywordField(String key, Object value) {
		wrapField(key, value, keywordWeight, true);
	}

	/**
	 * @return the document
	 */
	public Document getDocument() {
		return document;
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
