/**
 * 
 */
package com.marswork.core.minitools.object;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * <p>
 * 
 * @author MarsDJ
 * @since 2012-3-15
 * @version 1.0
 */
public class PropertiesUtils {

	private static final String formatSpecifier = "\\{\\d+\\}";
	
	private static Pattern fsPattern = Pattern.compile(formatSpecifier);

	public static String format(String s, String... params) {
		String[] p = params;
		Matcher m = fsPattern.matcher(s);
		int i = 0;
		int index=0;
		while (i < s.length()) {
			if (m.find(i)) {
				m.replaceAll(p[index]);
				index++;
				i = m.end();
			}else{
				i++;
			}
		}
		return "";
	}
}
