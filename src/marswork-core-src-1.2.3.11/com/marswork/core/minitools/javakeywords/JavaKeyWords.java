/**
 * 
 */
package com.marswork.core.minitools.javakeywords;

import com.marswork.core.minitools.object.BaseCollection;

/**
 * <p>
 * Java关键字
 * <p>
 * 罗列Java关键字<br>
 * 判断一个字符串是否Java关键字
 * 
 * @author MarsDJ
 * @since 2011-10-22
 * @version 1.0
 */
public class JavaKeyWords {

	private JavaKeyWords() {
	}

	private final static String[] keywords = { "private", "protected",
			"public", "abstract", "class", "extends", "final", "interface",
			"@interface", "native", "new", "static", "strictfp",
			"synchronized", "transient", "volatile", "break", "continue",
			"return", "do", "while", "if", "else", "for", "instanceof",
			"switch", "case", "default", "catch", "finally", "throw", "throws",
			"try", "import", "package", "boolean", "byte", "char", "double",
			"float", "int", "long", "short", "null", "true", "false", "super",
			"this", "void", "finally" };

	/**
	 * 判断一个字符是否是java关键字
	 * 
	 * @param source
	 *            需要判断的源字符
	 * @return 是否是关键字
	 */
	public static boolean isKeyword(String source) {
		return BaseCollection.hasChild(keywords, source);
	}
	
	/**
	 * 判断一个字符是否是java关键字
	 * 
	 * @param source
	 *            需要判断的源字符
	 * @return 是否是关键字
	 */
	public static boolean isKeywordIgnoreCase(String source) {
		return BaseCollection.hasChildIgnoreCase(keywords, source);
	}

	/**
	 * @return the keywords
	 */
	public static String[] getKeywords() {
		return keywords;
	}

}
