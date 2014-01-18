/**
 * 
 */
package com.marswork.fulltext.searcher;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.marswork.fulltext.keywords.KeywordsMaker;

/**
 * <p>
 * 标准全文搜索器
 * <p>
 * 在索引中搜索与关键字相关的对象<br>
 * 查询用的源字符串可以是多个关键字<br>
 * 也可以是一句话，一段话，甚至是一篇文章<br>
 * 会自动分词识别关键字
 * 
 * @author MarsDJ
 * @since 2011-12-31
 * @version 1.0
 */
public class StandardSearcher extends MarsSearcher {

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
	public StandardSearcher(File indexDir, String[] keySet) throws IOException {
		super(indexDir, keySet);
	}

	/**
	 * 根据查询字符串进行搜索<br>
	 * 关键字可以是多个关键字<br>
	 * 也可以是一句话，一段话，甚至是一篇文章<br>
	 * 该方法会自动分词识别关键字
	 * 
	 * @param queryString
	 *            查询字符串
	 * @return 查询结果集，默认最多9条记录
	 */
	public List<Document> search(String queryString) {
		return search(queryString, defaultMaxResult, 0);
	}

	/**
	 * 根据查询字符串进行搜索<br>
	 * 关键字可以是多个关键字<br>
	 * 也可以是一句话，一段话，甚至是一篇文章<br>
	 * 该方法会自动分词识别关键字
	 * 
	 * @param queryString
	 *            查询字符串
	 * @param pageSize
	 *            页大小
	 * @param pageIndex
	 *            页序号
	 * @return 查询结果集，默认最多9条记录
	 */
	public List<Document> search(String queryString, int pageSize, int pageIndex) {
		BooleanQuery titleQuery = new BooleanQuery();
		try {
			List<String> temp = new KeywordsMaker(queryString).getKeywords();
			for (int j = 0; j < temp.size(); j++) {
				Query keywordQuery = null;
				BooleanClause.Occur[] flags = new BooleanClause.Occur[keySet.length];
				for (int i = 0; i < keySet.length; i++) {
					flags[i] = BooleanClause.Occur.SHOULD;
				}
				keywordQuery = MultiFieldQueryParser.parse(Version.LUCENE_33, temp.get(j), keySet,
						flags, new IKAnalyzer(false));
				titleQuery.add(keywordQuery, BooleanClause.Occur.SHOULD);
			}
			if (queryString.length() < 10) {
				for (String element : keySet) {
					titleQuery.add(new TermQuery(new Term(element, queryString)),
							BooleanClause.Occur.SHOULD);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		super.addQuery(titleQuery);
		return super.search(pageSize, pageIndex);
	}

}
