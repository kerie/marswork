/**
 * 
 */
package com.marswork.core.minitools.cache;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

/**
 * 
 * <p>
 * Bean缓存类
 * <p>
 * 将Bean的信息进行缓存<br>
 * 如果未发现缓存则先对BeanInfo进行分析
 * 
 * @author Nordy.SU
 * @since 2011-05-29
 * @version 1.0
 */
public class BeanInfoCache {

	private static class BeanInfoBuilder implements
			ItemCache.ValueBuilder<Class<?>, BeanInfo> {

		@Override
		public BeanInfo build(Class<?> c) {
			try {
				return Introspector.getBeanInfo(c);
			} catch (IntrospectionException e) {
				throw new IllegalStateException("Bean introspection failed: "
						+ e.getMessage(), e);
			}
		}
	}

	private static final ItemCache<Class<?>, BeanInfo> beanInfoCache = new ItemCache<Class<?>, BeanInfo>(
			new BeanInfoBuilder());

	public static final BeanInfo getBeanInfo(Class<?> type) {
		return beanInfoCache.get(type);
	}
}
