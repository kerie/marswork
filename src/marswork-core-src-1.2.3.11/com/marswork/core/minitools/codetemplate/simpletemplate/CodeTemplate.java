/**
 * 
 */
package com.marswork.core.minitools.codetemplate.simpletemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.marswork.core.minitools.codetemplate.I_CodeTemplate;
import com.marswork.core.minitools.object.BaseString;
import com.marswork.core.minitools.object.BasicUtils;

/**
 * <p>
 * <p>
 * 
 * @author MarsDJ
 * @since 2012-5-31
 * @version 1.0
 */
public class CodeTemplate implements I_CodeTemplate {

	protected String template;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marswork.core.minitools.codetemplate.I_CodeTemplate#generateScript
	 * (java.lang.String[])
	 */
	@Override
	public String generateScript(String... params) {
		return generateScript(this.getClass(), params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marswork.core.minitools.codetemplate.I_CodeTemplate#generateScript
	 * (java.lang.Class, java.lang.String[])
	 */
	public String generateScript(Class<? extends I_CodeTemplate> class_, String... params) {
		return generatePointedScript(class_, "unit.tpl", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marswork.core.minitools.codetemplate.I_CodeTemplate#generatePointedScript
	 * (java.lang.Class, java.lang.String, java.lang.String[])
	 */
	public String generatePointedScript(Class<? extends I_CodeTemplate> class_, String fileName,
			String... params) {
		try {
			if (BasicUtils.isTrimBlank(template)) {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						class_.getResourceAsStream(fileName), "utf-8"));
				StringBuffer sb = new StringBuffer();
				String oneLine = "";
				while ((oneLine = br.readLine()) != null) {
					if (!BasicUtils.isTrimBlank(oneLine)) {
						sb.append(oneLine);
					}
					sb.append("\n");
				}
				template = sb.toString();
			}
			return BaseString.unicodeToUtf8(String.format(template, (Object[]) params));
		} catch (IOException e) {
			return "";
		}
	}
}
