/**
 * 
 */
package com.marswork.fulltext.extdict;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.wltea.analyzer.dic.DictSegment;

import com.marswork.core.minitools.object.BasicUtils;
import com.marswork.fulltext.term.WordCache;

/**
 * <p>
 * 字典值数据源
 * <p>
 * 字典的数据源有两种<br>
 * 一种是来自配置文件，这种数据源会被制作成输入流载入到字典库中<br>
 * 一种来来自程序接口，这种数据源以字符串集合形式被载入
 * 
 * @author MarsDJ
 * @since 2012-1-4
 * @version 1.0
 */
public class DictDataSource {

	private InputStream is;

	private List<String> ls;

	/**
	 * 构造函数，以输入流形式载入
	 * 
	 * @param is
	 *            包含字典值的输入流
	 */
	public DictDataSource(InputStream is) {
		this.is = is;
	}

	/**
	 * 构造函数，以字符串集合形式载入
	 * 
	 * @param ls
	 *            包含字典值的字符串集合
	 */
	public DictDataSource(List<String> ls) {
		this.ls = ls;
	}

	public void fillSegment(DictSegment dictSegment) {
		fillSegment(dictSegment, 1);
	}

	public void fillSegment(DictSegment dictSegment, float weight) {
		try {
			if (is != null) {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, "UTF-8"), 512);
				String theWord = null;
				do {
					theWord = br.readLine();
					if (!BasicUtils.isTrimBlank(theWord)) {
						dictSegment.fillSegment(theWord.trim().toCharArray());
						WordCache.cacheWord(theWord, weight);
					}
				} while (theWord != null);
			} else if (ls != null) {
				for (String theWord : ls) {
					if (!BasicUtils.isTrimBlank(theWord)) {
						dictSegment.fillSegment(theWord.trim().toCharArray());
						WordCache.cacheWord(theWord, weight);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
