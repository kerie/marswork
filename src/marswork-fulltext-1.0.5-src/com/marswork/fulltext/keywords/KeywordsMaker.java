package com.marswork.fulltext.keywords;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKTokenizer;

import com.marswork.algorithm.sort.AbstractSort;
import com.marswork.algorithm.sort.SortOrder;
import com.marswork.core.datastructure.uniquelist.UniqueList;
import com.marswork.core.minitools.object.BasicUtils;
import com.marswork.fulltext.term.MarsTermAttribute;

/**
 * 
 * <p>
 * 关键字生成器
 * <p>
 * 对一个字符串进行分词，得到分次结果<br>
 * 分词结果可以带词频和权值<br>
 * 每个词的权值为源字符串权值×该词在源字符串中的词频<br>
 * 最后累加该词在不同源字符串中的权值<br>
 * 分词得到的结果集中的值具有唯一性
 * 
 * @author MarsDJ
 * @since 2011-12-30
 * @version 1.0
 */
public class KeywordsMaker {

	private List<KeyHits> result;

	private boolean isMaxWordLengthMode = true;

	/**
	 * 构造函数
	 * 
	 */
	public KeywordsMaker() {
		result = new UniqueList<KeyHits>();
	}

	/**
	 * 构造函数
	 * 
	 * @param source
	 *            要分词的源字符串
	 */
	public KeywordsMaker(String source) {
		this(source, false, 1);
	}

	/**
	 * 构造函数<br>
	 * 默认按照长词优先策略分词
	 * 
	 * @param source
	 *            要分词的源字符串
	 * @param boost
	 *            源字符串的权值<br>
	 *            每个词的权值为源字符串权值×该词在源字符串中的词频
	 */
	public KeywordsMaker(String source, float boost) {
		this(source, false, boost);
	}

	/**
	 * 构造函数<br>
	 * 默认按照长词优先策略分词
	 * 
	 * @param source
	 *            要分词的源字符串
	 * @param isMaxWordLengthMode
	 *            是否按照长词优先策略分词<br>
	 *            如果false则按照最细粒度策略分词
	 */
	public KeywordsMaker(String source, boolean isMaxWordLengthMode) {
		this(source, isMaxWordLengthMode, 1);
	}

	/**
	 * 构造函数
	 * 
	 * @param source
	 *            要分词的源字符串
	 * @param isMaxWordLengthMode
	 *            是否按照长词优先策略分词<br>
	 *            如果false则按照最细粒度策略分词
	 * @param boost
	 *            源字符串的权值<br>
	 *            每个词的权值为源字符串权值×该词在源字符串中的词频
	 */
	public KeywordsMaker(String source, boolean isMaxWordLengthMode, float boost) {
		this.isMaxWordLengthMode = isMaxWordLengthMode;
		result = makeKeyHits(source, boost);
	}

	/**
	 * 对源字符串进行分词
	 * 
	 * @param source
	 *            要分词的源字符串
	 */
	public List<KeyHits> makeKeyHits(String source) {
		return makeKeyHits(source, 1);
	}

	/**
	 * 对源字符串进行分词
	 * 
	 * @param source
	 *            要分词的源字符串
	 * @param boost
	 *            源字符串的权值<br>
	 *            每个词的权值为源字符串权值×该词在源字符串中的词频
	 * @param analyse
	 *            是否对源字符串进行分词分析<br>
	 *            true则进行分词<br>
	 *            false则不进行分词直接使用","分隔得到关键字
	 */
	public List<KeyHits> makeKeyHits(String source, float boost) {
		if (!BasicUtils.isTrimBlank(source)) {
			return doExec(source, boost);
		}
		return new UniqueList<KeyHits>();
	}

	/**
	 * 追加需要分析的字符串<br>
	 * 对该字符串进行分词<br>
	 * 并将结果归并到结果集中
	 * 
	 * @param targetStr
	 *            要追加并分析的源字符串
	 */
	public void mergeKeyHits(String targetStr) {
		mergeKeyHits(targetStr, 1);
	}

	/**
	 * 追加需要分析的字符串<br>
	 * 对该字符串进行分词<br>
	 * 并将结果归并到结果集中
	 * 
	 * @param targetStr
	 *            要追加并分析的源字符串
	 * @param boost
	 *            源字符串的权值<br>
	 *            每个词的权值为源字符串权值×该词在源字符串中的词频
	 * @param analyse
	 *            是否对源字符串进行分词分析<br>
	 *            true则进行分词<br>
	 *            false则不进行分词直接使用","分隔得到关键字
	 */
	public void mergeKeyHits(String targetStr, float boost) {
		List<KeyHits> target = makeKeyHits(targetStr, boost);
		int souceSize = result.size();
		for (int i = 0; i < target.size(); i++) {
			boolean isExsist = false;
			KeyHits targetH = target.get(i);

			for (int j = 0; j < souceSize; j++) {
				KeyHits sourceH = result.get(j);
				if (sourceH.getKey().equals(targetH.getKey())) {
					sourceH.setHits(sourceH.getHits() + targetH.getHits());
					isExsist = true;
					break;
				}
			}
			if (!isExsist) {
				result.add(targetH);
			}
		}
	}

	/**
	 * 执行分词 <br>
	 * 同时对源字符串中的词进行计数<br>
	 * 默认按最大词长切分<br>
	 * 如果需要最细粒度切分，需要设置isMaxLength参数
	 * 
	 * @param source
	 *            源字符串
	 * @param boost
	 *            该字符串的权值
	 * @return 带权值的关键词
	 */
	@SuppressWarnings("rawtypes")
	private List<KeyHits> doExec(String source, float boost) {

		HashMap<String, Float> keyAndHits = new HashMap<String, Float>();
		List<KeyHits> tempResult = new UniqueList<KeyHits>();

		IKTokenizer tokenizer = new IKTokenizer(new StringReader(source), isMaxWordLengthMode);

		try {
			while (tokenizer.incrementToken()) {
				MarsTermAttribute marsAtt = new MarsTermAttribute(
						tokenizer.getAttribute(CharTermAttribute.class));
				doCount(marsAtt, boost, keyAndHits);
			}

			Iterator it = keyAndHits.entrySet().iterator();
			KeyHits hits;
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String key = String.valueOf(entry.getKey());
				hits = new KeyHits(key, Float.parseFloat(String.valueOf(entry.getValue())));
				tempResult.add(hits);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempResult;
	}

	/**
	 * 对词频进行计数
	 * 
	 * @param term
	 *            词
	 * @param boost
	 *            当前源字符串的权值
	 * @param keyAndHits
	 *            计数器
	 */
	private void doCount(MarsTermAttribute term, float boost, HashMap<String, Float> keyAndHits) {
		float index = keyAndHits.get(term.toString()) == null ? 0 : keyAndHits.get(term.toString());
		keyAndHits.put(term.toString(), index += boost * term.getBoost());
	}

	/**
	 * 获取带权值的关键词
	 * 
	 * @return 带权值的关键词
	 */
	public List<KeyHits> getKeyHits() {
		return result;
	}

	/**
	 * 对KeyHits集合进行截取<br>
	 * 有可能KeyHits集合中含有大量的KeyHits对象<br>
	 * 而许多KeyHits由于权值和命中率都很低<br>
	 * 对推荐查询效果的影响可以忽略<br>
	 * 可以改方法截取一定比例KeyHits<br>
	 * 
	 * @param number
	 *            指定的个数
	 * @return 影响力在前指定的个数中的KeyHits
	 */
	public List<KeyHits> truncate(int number) {
		try {
			AbstractSort.getSortInstance().sort(result, SortOrder.DESC);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.subList(0, Math.min(number, result.size()));
	}

	/**
	 * 对KeyHits集合进行截取<br>
	 * 有可能KeyHits集合中含有大量的KeyHits对象<br>
	 * 而许多KeyHits由于权值和命中率都很低<br>
	 * 对推荐查询效果的影响可以忽略<br>
	 * 可以改方法截取一定比例KeyHits<br>
	 * 
	 * @param number
	 *            指定的百分比
	 * @return 影响力在前指定的百分比中的KeyHits
	 */
	public List<KeyHits> truncatePercent(int number) {
		return truncate(result.size() * number / 100);
	}

	/**
	 * 获取不带权值的关键词
	 * 
	 * @return 不带权值的关键词
	 */
	public List<String> getKeywords() {
		List<String> keywords = new ArrayList<String>();
		for (int i = 0; i < result.size(); i++) {
			keywords.add(result.get(i).getKey());
		}
		return keywords;
	}

	/**
	 * @param isMaxWordLengthMode
	 *            the isMaxWordLengthMode to set
	 */
	public void setMaxWordLengthMode(boolean isMaxWordLengthMode) {
		this.isMaxWordLengthMode = isMaxWordLengthMode;
	}
}
