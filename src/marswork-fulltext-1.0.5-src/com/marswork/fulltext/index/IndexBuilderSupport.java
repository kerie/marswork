package com.marswork.fulltext.index;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.marswork.core.exceptions.config.PropertiesNotFoundException;
import com.marswork.core.minitools.object.BasicUtils;
import com.marswork.fulltext.componentbase.config.FullTextConfig;

/**
 * 
 * <p>
 * 索引构造器支持
 * <p>
 * 实现索引构造器接口
 * 
 * @author MarsDJ
 * @since 2012-3-10
 * @version 1.0
 */
public abstract class IndexBuilderSupport<E> implements I_IndexBuilder<E> {

	public static String[] keySet = { DocumentWrapper.OBJECT_ID, DocumentWrapper.OBJECT_ABSTRACT,
			DocumentWrapper.OBJECT_CATEGORY, DocumentWrapper.OBJECT_CONTENT,
			DocumentWrapper.OBJECT_KEYWORDS, DocumentWrapper.OBJECT_PAGE_ID,
			DocumentWrapper.OBJECT_STATUS, DocumentWrapper.OBJECT_SUBMIT_TIME,
			DocumentWrapper.OBJECT_TITLE,DocumentWrapper.OBJECT_DYNAMIC_TAG };

	private Directory indexDir;

	private Analyzer analyzer = new IKAnalyzer(false);

	private IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_33, analyzer);

	/**
	 * 构造函数<br>
	 * 构建内存索引
	 * 
	 * @throws IOException
	 *             文件读写例外
	 */
	public IndexBuilderSupport() throws IOException {
		indexDir = new RAMDirectory();
	}

	/**
	 * 构造函数<br>
	 * 在指定目录构建索引
	 * 
	 * @param indexDir
	 *            指定的构建索引的目录
	 * @throws IOException
	 *             文件读写例外
	 */
	public IndexBuilderSupport(File indexDir) throws IOException {
		this.indexDir = FSDirectory.open(indexDir);
	}

	/**
	 * 从配置文件中获取指定的索引目录
	 * 
	 * @param propertyName
	 *            配置中指定的值
	 * @return 配置的索引目录
	 * @throws IOException
	 *             文件读写例外
	 */
	public static File getIndexDir() throws PropertiesNotFoundException {
		File dirFile = new File(FullTextConfig.getDefaultLuceneIndex());
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		return dirFile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marswork.fulltext.index.I_IndexBuilder#createIndex(java.util.List)
	 */
	@Override
	public void createIndex(List<E> source) {
		createIndex(OpenMode.CREATE, source);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marswork.fulltext.index.I_IndexBuilder#appendIndex(java.util.List)
	 */
	@Override
	public void appendIndex(List<E> source) {
		try {
			createIndex(OpenMode.APPEND, source);
		} catch (Exception e) {
			createIndex(OpenMode.CREATE, source);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marswork.fulltext.index.I_IndexBuilder#appendIndex(java.lang.Object)
	 */
	@Override
	public void appendIndex(E source) {
		ArrayList<E> temp = new ArrayList<E>();
		temp.add(source);
		try {
			createIndex(OpenMode.APPEND, temp);
		} catch (Exception e) {
			createIndex(OpenMode.CREATE, temp);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marswork.fulltext.index.I_IndexBuilder#deleteDocument(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public void deleteDocument(String filed, String value) {
		try {
			IndexWriter indexWriter = new IndexWriter(indexDir, config);
			indexWriter.deleteDocuments(new Term(filed, value));
			commitAndClose(indexWriter);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marswork.fulltext.index.I_IndexBuilder#updateDocument(java.lang.String
	 * , java.lang.String, org.apache.lucene.document.Document)
	 */
	@Override
	public void updateDocument(String filed, String value, Document doc) {
		try {
			IndexWriter indexWriter = new IndexWriter(indexDir, config);
			indexWriter.updateDocument(new Term(filed, value), doc);
			commitAndClose(indexWriter);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建索引
	 * 
	 * @param isNew
	 *            指定是新建还是追加
	 * @param source
	 *            构建索引的数据源
	 */
	public void createIndex(OpenMode isNew, List<E> source) {
		try {
			config.setOpenMode(OpenMode.CREATE_OR_APPEND);
			IndexWriter indexWriter = new IndexWriter(indexDir, config);
			List<Document> docs = createDocument(source);
			for (Document doc : docs) {
				indexWriter.addDocument(doc);
			}
			commitAndClose(indexWriter);
		}  catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 索引的关闭和提交操作
	 * 
	 * @param indexWriter
	 *            索引读写器
	 * @throws CorruptIndexException
	 *             无效的索引例外
	 * @throws IOException
	 *             文件读写例外
	 */
	private void commitAndClose(IndexWriter indexWriter) throws CorruptIndexException, IOException {
		indexWriter.commit();
		indexWriter.optimize();
		indexWriter.close();
	}

	/**
	 * 将指定数据源转换为文件集合的具体方法
	 * 
	 * @param source
	 *            数据源
	 * @return 转换后的文档集合
	 */
	public abstract List<Document> createDocument(List<E> source);
}
