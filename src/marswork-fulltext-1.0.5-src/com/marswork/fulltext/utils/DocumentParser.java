/**
 * 
 */
package com.marswork.fulltext.utils;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;

import com.marswork.core.common.data.MarsBean;
import com.marswork.core.minitools.cache.BeanInfoCache;
import com.marswork.core.minitools.object.BaseCollection;
import com.marswork.core.minitools.object.BaseString;
import com.marswork.core.minitools.object.time.AttachDateTime;

/**
 * <p>
 * lucene数据与外界数据类型转换器
 * <p>
 * lucene中的单元数据Document<br>
 * 与Map，{@link MarsBean}转换
 * 
 * @author MarsDJ
 * @since 2011-12-31
 * @version 1.0
 */
public class DocumentParser {

	private DocumentParser() {
	}

	/**
	 * Document转换为Map
	 * 
	 * @param source
	 *            需要转换的Document列表
	 * @param keySet
	 *            KeySet集合
	 * @return 转换后的Map列表
	 */
	public static List<Map<String, String>> parseMap(List<Document> source,
			String[] keySet) {
		List<Map<String, String>> temp = new ArrayList<Map<String, String>>();
		for (int i = 0; i < source.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (String element : keySet) {
				map.put(element, source.get(i).get(element));
			}
			temp.add(map);
		}
		return temp;
	}

	/**
	 * Document转换为MarsBean
	 * 
	 * @param source
	 *            需要转换的Document列表
	 * @param keySet
	 *            KeySet集合
	 * @param beanType
	 *            Bean的类型，该Bean是{@link MarsBean}的子类
	 * @return 转换后的MarsBean列表
	 */
	public static <T extends MarsBean> List<T> parseBean(List<Document> source,
			String[] keySet, Class<T> beanType) {
		List<T> temp = new ArrayList<T>();
		for (int i = 0; i < source.size(); i++) {
			try {
				temp.add(convertToBean(source.get(i), keySet, beanType));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	/**
	 * 转换Bean
	 * 
	 * @param <T>
	 *            要生成的Bean类型
	 * @param document
	 *            需要转换的Document
	 * @param keySet
	 *            KeySet集合
	 * @param beanType
	 *            Bean的类型，该Bean是{@link MarsBean}的子类
	 * @return 生成的Bean
	 * @throws Exception
	 *             任何例外
	 */
	private static <T extends MarsBean> T convertToBean(Document document,
			String[] keySet, Class<T> beanType) throws Exception {
		T temp = (T) beanType.newInstance();
		BeanInfo beanInfo = BeanInfoCache.getBeanInfo(beanType);
		for (PropertyDescriptor prop : beanInfo.getPropertyDescriptors()) {

			Method setter = prop.getWriteMethod();
			if (setter == null)
				continue;

			if (BaseCollection.hasChild(keySet, prop.getName())) {
				Object param = document.get(prop.getName());
				if (prop.getPropertyType().equals(Date.class)) {
					try {
						param = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.parse(AttachDateTime.attachDateTime(BaseString
										.cleanUp(param)));
					} catch (Exception e) {
					}
				} else if (prop.getPropertyType().equals(Integer.class)
						|| prop.getPropertyType().equals(Integer.TYPE)) {
					param = Integer.parseInt(BaseString.cleanUp(param));
				} else if (prop.getPropertyType().equals(Double.class)
						|| prop.getPropertyType().equals(Double.TYPE)) {
					param = Double.parseDouble(BaseString.cleanUp(param));
				} else if (prop.getPropertyType().equals(Float.class)
						|| prop.getPropertyType().equals(Float.TYPE)) {
					param = Float.parseFloat(BaseString.cleanUp(param));
				}
				setter.invoke(temp, param);
			}
		}
		return temp;
	}
}
