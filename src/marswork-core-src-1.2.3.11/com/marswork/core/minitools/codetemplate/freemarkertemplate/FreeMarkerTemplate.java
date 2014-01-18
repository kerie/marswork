/**
 * 
 */
package com.marswork.core.minitools.codetemplate.freemarkertemplate;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.marswork.core.minitools.codetemplate.I_CodeTemplate;
import com.marswork.core.minitools.object.BasicUtils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * <p>
 * <p>
 * 
 * @author MarsDJ
 * @since 2012-11-20
 * @version 1.0
 */
public class FreeMarkerTemplate implements I_CodeTemplate {

	protected Configuration cfg;

	protected Map<String, Object> mapParams = new HashMap<String, Object>();

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
	@Override
	public String generateScript(Class<? extends I_CodeTemplate> class_, String... params) {
		return generatePointedScript(this.getClass(), "unit.ftl", params);
	}

	public String generatePointedScript(String fileName, String... params) {
		return generatePointedScript(this.getClass(), fileName, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.marswork.core.minitools.codetemplate.I_CodeTemplate#generatePointedScript
	 * (java.lang.Class, java.lang.String, java.lang.String[])
	 */
	@Override
	public String generatePointedScript(Class<? extends I_CodeTemplate> class_, String fileName,
			String... params) {
		try {
			cfg = new Configuration();
			cfg.setDefaultEncoding("UTF-8");
			cfg.setDirectoryForTemplateLoading(new File(class_.getResource("").getFile()));
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			Template temp = cfg.getTemplate(fileName);
			temp.setEncoding("UTF-8");

			StringWriter out = new StringWriter();
			temp.process(mapParams, out);

			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return "";
	}

	public void addParam(String key, Object value) {
		mapParams.put(key, value);
	}

	/**
	 * @return the mapParams
	 */
	public Map<String, Object> getMapParams() {
		return mapParams;
	}

}
