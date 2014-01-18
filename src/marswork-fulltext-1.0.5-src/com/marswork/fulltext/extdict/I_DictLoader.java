/**
 * 
 */
package com.marswork.fulltext.extdict;

import org.wltea.analyzer.dic.DictCategory;

/**
 * <p>
 * 字典值装载器接口
 * <p>
 * 负责装载指定类型的词典
 * 
 * @author MarsDJ
 * @since 2012-1-3
 * @version 1.0
 */
public interface I_DictLoader {

	/**
	 * 向指定类型的词典载入指定的字典值
	 * 
	 * @param dictCategory
	 *            指定的词典类型<br>
	 *            包括主词典，姓氏词典，量词词典<br>
	 *            后缀词典，副词介词词典和停止词集合
	 * @param dictDataSource
	 *            指定的词典数据源
	 */
	public void loadDict(DictCategory dictCategory,
			DictDataSource dictDataSource);
}
