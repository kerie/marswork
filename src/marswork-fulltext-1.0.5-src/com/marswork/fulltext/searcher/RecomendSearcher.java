package com.marswork.fulltext.searcher;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;

import com.marswork.fulltext.keywords.KeyHits;
import com.marswork.fulltext.keywords.KeywordsMaker;
import com.marswork.fulltext.keywords.StandardDocumentKeywords;

/**
 * 
 * <p>
 * 推荐对象搜索器
 * <p>
 * 用于两种对象进行匹配<br>
 * 例如：根据一个专利对象的关键信息<br>
 * 通过对关键字，标题及相关重要信息的分析<br>
 * 在索引中搜索与这个专利信息紧密相关的需求信息
 * <p>
 * 在对源信息的处理中<br>
 * 会根据词频和源的权重计算每个关键字的权值<br>
 * 例如："java"这个关键字在title中出现1次，在content中出现3次<br>
 * 而title和content的权值不相同<br>
 * 最终"java"的权值将会受到词频和权值的影响
 * 
 * @author MarsDJ
 * @since 2011-12-31
 * @version 1.0
 */
public class RecomendSearcher extends MarsSearcher {

	/**
	 * 构造函数
	 * 
	 * @param indexDir
	 *            索引所在文件夹
	 * @param keySet
	 *            keySet集合
	 * @throws IOException
	 *             文件读写例外
	 */
	public RecomendSearcher(File indexDir, String[] keySet) throws IOException {
		super(indexDir, keySet);
	}

	/**
	 * 快速查询，只需要将关键字字符串输入即可<br>
	 * 程序自动分析并给出推荐<br>
	 * 无法将关键精确的设置权重<br>
	 * 如果需要对不同类型的关键字设置不同的权重，请将 {@link StandardDocumentKeywords} 和
	 * {@link RecomendSearcher#search(List)}配合使用
	 * 
	 * @param keywords
	 *            需要查询的关键字，可以是多个，程序自动分词
	 * @return 查询结果集，默认最多9条记录
	 */
	public List<Document> search(String keywords) {
		StandardDocumentKeywords maker = new StandardDocumentKeywords();
		maker.appendContent(keywords);
		return search(maker.getKeyHits());
	}

	/**
	 * 根据查询字符串进行搜索<br>
	 * 关键字可以是多个关键字<br>
	 * 也可以是一句话，一段话，甚至是一篇文章<br>
	 * 该方法会自动分词识别关键字
	 * <p>
	 * 在对源信息的处理中<br>
	 * 会根据词频和源的权重计算每个关键字的权值<br>
	 * 例如："java"这个关键字在title中出现n次，在content中出现m次<br>
	 * 而title和content的权值分别为a,b<br>
	 * 最终"java"的权值将是n*a+m*b
	 * 
	 * @param keyHist
	 *            查询字符串词频分析<br>
	 *            通常使用{@link KeywordsMaker}生成
	 * @return 查询结果集，默认最多9条记录
	 * @see {@link KeywordsMaker}
	 */
	public List<Document> search(List<KeyHits> keyHist) {
		return search(keyHist, defaultMaxResult, 0);
	}

	/**
	 * 根据查询字符串进行搜索<br>
	 * 关键字可以是多个关键字<br>
	 * 也可以是一句话，一段话，甚至是一篇文章<br>
	 * 该方法会自动分词识别关键字
	 * <p>
	 * 在对源信息的处理中<br>
	 * 会根据词频和源的权重计算每个关键字的权值<br>
	 * 例如："java"这个关键字在title中出现n次，在content中出现m次<br>
	 * 而title和content的权值分别为a,b<br>
	 * 最终"java"的权值将是n*a+m*b
	 * 
	 * @param keyHist
	 *            查询字符串词频分析<br>
	 *            通常使用{@link KeywordsMaker}生成
	 * @param pageSize
	 *            页大小
	 * @param pageSize
	 *            页序号
	 * @return 查询结果集，默认最多9条记录
	 * @see {@link KeywordsMaker}
	 */
	public List<Document> search(List<KeyHits> keyHist, int pageSize, int pageIndex) {
		BooleanQuery tempQuery = new BooleanQuery();
		for (int j = 0; j < keySet.length; j++) {
			BooleanQuery subQuery = new BooleanQuery();
			for (int i = 0; i < keyHist.size(); i++) {
				TermQuery termQuery = new TermQuery(new Term(keySet[j], keyHist.get(i).getKey()));
				termQuery.setBoost(keyHist.get(i).getHits());
				subQuery.add(termQuery, Occur.SHOULD);
			}
			tempQuery.add(subQuery, Occur.SHOULD);
		}
		super.addQuery(tempQuery);
		return super.search(pageSize, pageIndex);
	}
}
