/**
 * 
 */
package com.marswork.fulltext.keywords.invalidekeywords;

/**
 * <p>
 * <p>
 * @author MarsDJ
 * @since 2012-1-4
 * @version 1.0
 */
public class RepeatFeature extends InvalideKeywordFeature{

	/* (non-Javadoc)
	 * @see com.marswork.fulltext.keywords.invalidekeyword.InvalideKeywordFeature#meet(java.lang.String)
	 */
	@Override
	public boolean meet(String source) {
		return source.matches("^(.)(\\1{5,})$");
	}
}
