/**
 * 
 */
package org.wltea.analyzer.dic;

import java.util.Collection;
import java.util.List;

import org.wltea.analyzer.cfg.Configuration;

import com.marswork.fulltext.exceptions.DictionaryNotFoundExeption;
import com.marswork.fulltext.extdict.ConfigDictLoader;

/**
 * IK Analyzer v3.2 词典管理类,单子模式
 * 
 * @author 林良益
 * @author MarsDJ
 */
public class Dictionary {

	/*
	 * 词典单子实例
	 */
	protected static final Dictionary singleton;

	/*
	 * 词典初始化
	 */
	static {
		singleton = new Dictionary();
	}

	/*
	 * 主词典对象
	 */
	protected DictSegment _MainDict;
	/*
	 * 姓氏词典
	 */
	protected DictSegment _SurnameDict;
	/*
	 * 量词词典
	 */
	protected DictSegment _QuantifierDict;
	/*
	 * 后缀词典
	 */
	protected DictSegment _SuffixDict;
	/*
	 * 副词，介词词典
	 */
	protected DictSegment _PrepDict;
	/*
	 * 停止词集合
	 */
	protected DictSegment _StopWords;

	protected ConfigDictLoader dictLoader;

	protected Dictionary() {
		// 初始化系统词典
		try {
			dictLoader = new ConfigDictLoader();
			loadMainDict();
			loadSurnameDict();
			loadQuantifierDict();
			loadSuffixDict();
			loadPrepDict();
			loadStopWordDict();
		} catch (DictionaryNotFoundExeption e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载主词典及扩展词典
	 * 
	 * @throws DictionaryNotFoundExeption
	 */
	private void loadMainDict() throws DictionaryNotFoundExeption {
		// 建立一个主词典实例
		_MainDict = new DictSegment((char) 0);
		// 读取主词典文件
		dictLoader.loadDict(_MainDict, DictCategory.MAIN);
		// 加载扩展词典配置
		List<String> extDictFiles = Configuration.getExtDictionarys();
		dictLoader.loadDict(_MainDict, extDictFiles);
	}

	/**
	 * 加载姓氏词典
	 * 
	 * @throws DictionaryNotFoundExeption
	 */
	private void loadSurnameDict() throws DictionaryNotFoundExeption {
		// 建立一个姓氏词典实例
		_SurnameDict = new DictSegment((char) 0);
		// 读取姓氏词典文件
		dictLoader.loadDict(_SurnameDict, DictCategory.SURNAME);
	}

	/**
	 * 加载量词词典
	 * 
	 * @throws DictionaryNotFoundExeption
	 */
	private void loadQuantifierDict() throws DictionaryNotFoundExeption {
		// 建立一个量词典实例
		_QuantifierDict = new DictSegment((char) 0);
		// 读取量词词典文件
		dictLoader.loadDict(_QuantifierDict, DictCategory.QUANTIFIER);
	}

	/**
	 * 加载后缀词典
	 * 
	 * @throws DictionaryNotFoundExeption
	 */
	private void loadSuffixDict() throws DictionaryNotFoundExeption {
		// 建立一个后缀词典实例
		_SuffixDict = new DictSegment((char) 0);
		// 读取量词词典文件
		dictLoader.loadDict(_SuffixDict, DictCategory.SUFFIX);
	}

	/**
	 * 加载介词\副词词典
	 * 
	 * @throws DictionaryNotFoundExeption
	 */
	private void loadPrepDict() throws DictionaryNotFoundExeption {
		// 建立一个介词\副词词典实例
		_PrepDict = new DictSegment((char) 0);
		// 读取量词词典文件
		dictLoader.loadDict(_PrepDict, DictCategory.PREP);
	}

	/**
	 * 加载停止词词典
	 * 
	 * @throws DictionaryNotFoundExeption
	 */
	private void loadStopWordDict() throws DictionaryNotFoundExeption {
		// 建立一个停止词典实例
		_StopWords = new DictSegment((char) 0);
		// 读取量词词典文件
		dictLoader.loadDict(_StopWords, DictCategory.STOP);

		// 加载扩展停止词典
		List<String> extStopWordDictFiles = Configuration
				.getExtStopWordDictionarys();
		dictLoader.loadDict(_StopWords, extStopWordDictFiles);

	}

	/**
	 * 词典初始化 由于IK Analyzer的词典采用Dictionary类的静态方法进行词典初始化
	 * 只有当Dictionary类被实际调用时，才会开始载入词典， 这将延长首次分词操作的时间 该方法提供了一个在应用加载阶段就初始化字典的手段
	 * 用来缩短首次分词时的时延
	 * 
	 * @return Dictionary
	 */
	public static Dictionary getInstance() {
		return Dictionary.singleton;
	}

	/**
	 * 加载扩展的词条
	 * 
	 * @param extWords
	 *            Collection<String>词条列表
	 */
	public static void loadExtendWords(Collection<String> extWords) {
		if (extWords != null) {
			for (String extWord : extWords) {
				if (extWord != null) {
					// 加载扩展词条到主内存词典中
					singleton._MainDict.fillSegment(extWord.trim()
							.toCharArray());
				}
			}
		}
	}

	/**
	 * 加载扩展的停止词条
	 * 
	 * @param extStopWords
	 *            Collection<String>词条列表
	 */
	public static void loadExtendStopWords(Collection<String> extStopWords) {
		if (extStopWords != null) {
			for (String extStopWord : extStopWords) {
				if (extStopWord != null) {
					// 加载扩展的停止词条
					singleton._StopWords.fillSegment(extStopWord.trim()
							.toCharArray());
				}
			}
		}
	}

	/**
	 * 检索匹配主词典
	 * 
	 * @param charArray
	 * @return Hit 匹配结果描述
	 */
	public static Hit matchInMainDict(char[] charArray) {
		return singleton._MainDict.match(charArray);
	}

	/**
	 * 检索匹配主词典
	 * 
	 * @param charArray
	 * @param begin
	 * @param length
	 * @return Hit 匹配结果描述
	 */
	public static Hit matchInMainDict(char[] charArray, int begin, int length) {
		return singleton._MainDict.match(charArray, begin, length);
	}

	/**
	 * 检索匹配主词典, 从已匹配的Hit中直接取出DictSegment，继续向下匹配
	 * 
	 * @param charArray
	 * @param currentIndex
	 * @param matchedHit
	 * @return Hit
	 */
	public static Hit matchWithHit(char[] charArray, int currentIndex,
			Hit matchedHit) {
		DictSegment ds = matchedHit.getMatchedDictSegment();
		return ds.match(charArray, currentIndex, 1, matchedHit);
	}

	/**
	 * 检索匹配姓氏词典
	 * 
	 * @param charArray
	 * @param begin
	 * @param length
	 * @return Hit 匹配结果描述
	 */
	public static Hit matchInSurnameDict(char[] charArray, int begin, int length) {
		return singleton._SurnameDict.match(charArray, begin, length);
	}

	// /**
	// *
	// * 在姓氏词典中匹配指定位置的char数组
	// * （对传入的字串进行后缀匹配）
	// * @param charArray
	// * @param begin
	// * @param end
	// * @return
	// */
	// public static boolean endsWithSurnameDict(char[] charArray , int begin,
	// int length){
	// Hit hit = null;
	// for(int i = 1 ; i <= length ; i++){
	// hit = singleton._SurnameDict.match(charArray, begin + (length - i) , i);
	// if(hit.isMatch()){
	// return true;
	// }
	// }
	// return false;
	// }

	/**
	 * 检索匹配量词词典
	 * 
	 * @param charArray
	 * @param begin
	 * @param length
	 * @return Hit 匹配结果描述
	 */
	public static Hit matchInQuantifierDict(char[] charArray, int begin,
			int length) {
		return singleton._QuantifierDict.match(charArray, begin, length);
	}

	/**
	 * 检索匹配在后缀词典
	 * 
	 * @param charArray
	 * @param begin
	 * @param length
	 * @return Hit 匹配结果描述
	 */
	public static Hit matchInSuffixDict(char[] charArray, int begin, int length) {
		return singleton._SuffixDict.match(charArray, begin, length);
	}

	// /**
	// * 在后缀词典中匹配指定位置的char数组
	// * （对传入的字串进行前缀匹配）
	// * @param charArray
	// * @param begin
	// * @param end
	// * @return
	// */
	// public static boolean startsWithSuffixDict(char[] charArray , int begin,
	// int length){
	// Hit hit = null;
	// for(int i = 1 ; i <= length ; i++){
	// hit = singleton._SuffixDict.match(charArray, begin , i);
	// if(hit.isMatch()){
	// return true;
	// }else if(hit.isUnmatch()){
	// return false;
	// }
	// }
	// return false;
	// }

	/**
	 * 检索匹配介词、副词词典
	 * 
	 * @param charArray
	 * @param begin
	 * @param length
	 * @return Hit 匹配结果描述
	 */
	public static Hit matchInPrepDict(char[] charArray, int begin, int length) {
		return singleton._PrepDict.match(charArray, begin, length);
	}

	/**
	 * 判断是否是停止词
	 * 
	 * @param charArray
	 * @param begin
	 * @param length
	 * @return boolean
	 */
	public static boolean isStopWord(char[] charArray, int begin, int length) {
		return singleton._StopWords.match(charArray, begin, length).isMatch();
	}
	
	DictSegment getMainDict(){
		return _MainDict;
	}
	
	DictSegment getPrepDict(){
		return _PrepDict;
	}
	
	DictSegment getQuantifierDict(){
		return _QuantifierDict;
	}
	
	DictSegment getStopWords(){
		return _StopWords;
	}
	
	DictSegment getSuffixDict(){
		return _SuffixDict;
	}
	
	DictSegment getSurnameDict(){
		return _SurnameDict;
	}
}
