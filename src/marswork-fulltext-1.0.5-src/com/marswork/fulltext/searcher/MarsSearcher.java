/**
 * 
 */
package com.marswork.fulltext.searcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

import com.marswork.core.minitools.object.BasicUtils;

/**
 * <p>
 * 全文搜索器
 * <p>
 * 指定索引所在位置和索引的keyset后<br>
 * 对索引中的数据进行搜索<br>
 * 默认最大结果集为9
 * 
 * @author MarsDJ
 * @since 2011-12-31
 * @version 1.0
 */
public class MarsSearcher {

	protected Directory indexDirectory;

	protected String[] keySet;

	protected int defaultMaxResult = 9;

	private IndexSearcher searcher;

	private int totalCount;

	private static float lowerLimit = 0.0001f;

	private BooleanQuery query = new BooleanQuery();;

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
	public MarsSearcher(File indexDir, String[] keySet) throws IOException {
		if (indexDir == null) {
			indexDirectory = new RAMDirectory();
		} else {
			indexDirectory = FSDirectory.open(indexDir);
		}
		this.keySet = keySet;
	}

	/**
	 * 装配索引中的Document对象<br>
	 * 子类中可按需要重写装配方法
	 * 
	 * @param docs
	 *            从indexsaerch中得到结果集并进行装配
	 * @return
	 * @throws CorruptIndexException
	 * @throws IOException
	 */
	protected List<Document> wiredDocument(ScoreDoc[] docs) throws CorruptIndexException,
			IOException {
		List<Document> results = new ArrayList<Document>();
		for (ScoreDoc doc : docs) {
			Document d = searcher.doc(doc.doc);
			d.add(new Field("EXPLAN_SCORE", String.valueOf(doc.score), Field.Store.NO,
					Field.Index.NOT_ANALYZED));
			if (doc.score < lowerLimit) {
				break;
			}
			results.add(d);
		}
		return results;
	}

	public MarsSearcher addQuery(String term, String value) {
		addQuery(term, value, Occur.SHOULD);
		return this;
	}

	public MarsSearcher addQuery(String term, String value, Occur occur) {
		this.query.add(new TermQuery(new Term(term, value)), Occur.SHOULD);
		return this;
	}

	public MarsSearcher addQuery(Query query) {
		addQuery(query, Occur.SHOULD);
		return this;
	}

	public MarsSearcher addQuery(Query query, Occur occur) {
		this.query.add(query, occur);
		return this;
	}

	/**
	 * 在索引中进行搜索<br>
	 * 默认最多9条
	 * 
	 * @param query
	 *            查询对象
	 * @return 查询结果集
	 */
	public List<Document> search() {
		return search(defaultMaxResult);
	}

	/**
	 * 在索引中进行搜索<br>
	 * 取前几条数据
	 * 
	 * @param query
	 *            查询对象
	 * @param top
	 *            取前几条数据
	 * @return 查询结果集
	 */
	public List<Document> search(int top) {
		return search(top, 0);
	}

	/**
	 * 在索引中进行搜索<br>
	 * 取分页数据
	 * 
	 * @param query
	 *            查询对象
	 * @param pageSize
	 *            页大小
	 * @param pageIndex
	 *            页号
	 * @return 查询结果集
	 */
	public List<Document> search(int pageSize, int pageIndex) {
		List<Document> result = new ArrayList<Document>();
		try {
			searcher = new IndexSearcher(indexDirectory, true);
			int startIndex = pageSize * pageIndex;
			TopScoreDocCollector collector = TopScoreDocCollector.create(startIndex + pageSize,
					false);
			BooleanQuery topQuery = new BooleanQuery();
			BooleanQuery.setMaxClauseCount(1024);
			topQuery.add(query, Occur.MUST);

			BasicUtils.out("------" + query + "------");

			searcher.search(query, collector);
			TopDocs topDocs = collector.topDocs(pageSize * pageIndex, pageSize);
			ScoreDoc[] docs = topDocs.scoreDocs;
			totalCount = topDocs.totalHits;
			result = wiredDocument(docs);

			// for (ScoreDoc doc : docs) {
			// Document d = searcher.doc(doc.doc);
			// BasicUtils.out(doc.score + "--" + d);
			// BasicUtils.out(searcher.explain(query, doc.doc));
			// }

			searcher.close();
			query=new BooleanQuery();
			return result;
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @return the defaultMaxResult
	 */
	public int getDefaultMaxResult() {
		return defaultMaxResult;
	}

	/**
	 * @param defaultMaxResult
	 *            the defaultMaxResult to set
	 */
	public void setDefaultMaxResult(int defaultMaxResult) {
		this.defaultMaxResult = defaultMaxResult;
	}

	/**
	 * @return the lowerLimit
	 */
	public static float getLowerLimit() {
		return lowerLimit;
	}

	/**
	 * @param lowerLimit
	 *            the lowerLimit to set
	 */
	public static void setLowerLimit(float lowerLimit) {
		MarsSearcher.lowerLimit = lowerLimit;
	}

}
