/**
 * 
 */
package com.marswork.fulltext.extdict;

import java.io.InputStream;
import java.util.List;

import org.wltea.analyzer.dic.DictCategory;
import org.wltea.analyzer.dic.DictSegment;
import org.wltea.analyzer.dic.Dictionary;

import com.marswork.fulltext.exceptions.DictionaryNotFoundExeption;

/**
 * <p>
 * 配置文件字典装载器
 * <p>
 * 属于字典装载器的一种<br>
 * 负责从指定的配置文件中装载字典值
 * 
 * @author MarsDJ
 * @since 2012-1-3
 * @version 1.0
 */
public class ConfigDictLoader extends DynamicDictLoader {

	public void loadDict(DictSegment dictSegment, DictDataSource dictDataSource) {
		dictDataSource.fillSegment(dictSegment);
	}

	public void loadDict(DictSegment dictSegment, InputStream dictDataSource) {
		loadDict(dictSegment, new DictDataSource(dictDataSource));
	}

	public void loadDict(DictSegment dictSegment, DictCategory dictCategory)
			throws DictionaryNotFoundExeption {
		loadDict(dictSegment, dictCategory.getFilePath());
	}

	public void loadDict(DictSegment dictSegment, String fileName)
			throws DictionaryNotFoundExeption {
		loadDict(dictSegment, getConfigAsStream(fileName));
	}

	public void loadDict(DictSegment dictSegment, List<String> dictFiles)
			throws DictionaryNotFoundExeption {

		if (dictFiles != null) {
			for (String extDictName : dictFiles) {
				loadDict(dictSegment, extDictName);
			}
		}
	}

	private InputStream getConfigAsStream(String fileName)
			throws DictionaryNotFoundExeption {
		InputStream is = Dictionary.class.getResourceAsStream(fileName);
		if (is == null) {
			throw new DictionaryNotFoundExeption(fileName);
		}
		return is;
	}

}
