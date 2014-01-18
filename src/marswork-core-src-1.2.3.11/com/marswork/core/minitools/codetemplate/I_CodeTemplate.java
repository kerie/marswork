/**
 * 
 */
package com.marswork.core.minitools.codetemplate;

/**
 * <p>
 * <p>
 * 
 * @author MarsDJ
 * @since 2012-11-20
 * @version 1.0
 */
public interface I_CodeTemplate {

	public abstract String generateScript(String... params);
	
	public abstract String generateScript(Class<? extends I_CodeTemplate> class_, String... params);

	public abstract String generatePointedScript(Class<? extends I_CodeTemplate> class_,
			String fileName, String... params);
}
