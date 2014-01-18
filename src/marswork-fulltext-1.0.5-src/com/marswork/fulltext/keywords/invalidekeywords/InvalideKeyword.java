/**
 * 
 */
package com.marswork.fulltext.keywords.invalidekeywords;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * <p>
 * 
 * @author MarsDJ
 * @since 2012-1-4
 * @version 1.0
 */
public class InvalideKeyword {

	private static List<InvalideKeywordFeature> features;

	public InvalideKeyword() {
		features = new ArrayList<InvalideKeywordFeature>();
		features.add(new RepeatFeature());
		features.add(new NotSeriousFeature());
	}

	public static void addFeature(InvalideKeywordFeature feature) {
		features.add(feature);
	}

	public static void removeFeature(InvalideKeywordFeature feature) {
		features.remove(feature);
	}

	public static void removeFeature(int index) {
		features.remove(index);
	}

	public static boolean validate(String source) {
		for (InvalideKeywordFeature feature : features) {
			if (feature.meet(source)) {
				return false;
			}
		}
		return true;
	}
}
