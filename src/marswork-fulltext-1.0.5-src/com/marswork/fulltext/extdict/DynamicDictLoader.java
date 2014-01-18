/**
 * 
 */
package com.marswork.fulltext.extdict;

import org.wltea.analyzer.dic.DictCategory;

/**
 * <p>
 * 动态字典装载器
 * <p>
 * 属于字典装载器的一种<br>
 * 可以从外部指定的字符串结合中装载字典值
 * 
 * @author MarsDJ
 * @since 2012-1-3
 * @version 1.0
 */
public class DynamicDictLoader implements I_DictLoader {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marswork.fulltext.extdict.I_DictLoader#loadDict(org.wltea.analyzer
	 * .dic.DictSegment, com.marswork.fulltext.extdict.DictDataSource)
	 */
	@Override
	public void loadDict(DictCategory dictCategory,
			DictDataSource dictDataSource) {
		loadDict(dictCategory, dictDataSource, 1);
	}

	/**
	 * 向指定类型的词典载入指定的字典值 <br>
	 * 同时可以控制这些动态字典值的权重
	 * 
	 * @param dictCategory
	 *            指定的词典类型<br>
	 *            包括主词典，姓氏词典，量词词典<br>
	 *            后缀词典，副词介词词典和停止词集合
	 * @param dictDataSource
	 *            指定的词典数据源
	 * @param weight
	 *            动态字典值的权重
	 */
	public void loadDict(DictCategory dictCategory,
			DictDataSource dictDataSource, float weight) {
		dictDataSource.fillSegment(dictCategory.getAccordingSegment(), weight);
	}
}
