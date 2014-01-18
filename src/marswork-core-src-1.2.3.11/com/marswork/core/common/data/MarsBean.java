package com.marswork.core.common.data;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import com.marswork.core.minitools.object.BasicUtils;

/**
 * 
 * <p>
 * MarsWork框架中的简单Bean对象
 * <p>
 * MarsBean有数据库逆向工程生成<br>
 * MarsWork体系中的所有成员均支持MarsBean<br>
 * MarsBean必有且只有一个主键
 * 
 * @author MarsDJ
 * @since 2011-9-17
 * @version 1.0
 */
public abstract class MarsBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4415306478851776925L;

	protected boolean modified = true;

	protected boolean isOnline = false;

	/**
	 * 对象的对比 <br>
	 * 如果两对象都为NULL，则返回true <br>
	 * 如果只有一个为NULL，则返回false <br>
	 * 如果都不为NULL，则返回 {@link Object#equals(Object)}方法的结果
	 * 
	 * @param source
	 *            源对象
	 * @param target
	 *            目标对象
	 * @return 是否相同
	 */
	protected boolean compareObj(Object source, Object target) {
		if (((source == null) && (target != null)) || ((source != null) && (target == null))) {
			return false;
		}
		if ((source == null) && (target == null)) {
			return true;
		}
		return source.equals(target);
	}

	/**
	 * 为主键复制
	 * 
	 * @param pkId
	 *            主键值
	 */
	public abstract void evaluatePkValue(String pkId);

	/**
	 * 获取MarsBean的主键列名
	 * 
	 * @return 主键列名
	 */
	public abstract String findPkColumn();

	/**
	 * 获取MarsBean的主键值
	 * 
	 * @return 主键值
	 */
	public abstract String findPkValue();

	/**
	 * 该Bean是否被修改过
	 * 
	 * @return 是否被修改过
	 */
	public boolean modified() {
		return this.modified;
	}

	/**
	 * 恢复出厂 <br>
	 * 恢复之后的MarsBean对象会被认为是没有经过修改的 <br>
	 */
	public void reset() {
		modified = false;
	}

	@Override
	public String toString() {
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(this.getClass());
			StringBuffer _temp = new StringBuffer("\n").append(this.getClass().getSimpleName())
					.append(" {");
			for (PropertyDescriptor prop : beanInfo.getPropertyDescriptors()) {
				try {
					Method getter = prop.getReadMethod();
					if (getter == null) {
						continue;
					}

					// 解析内部bean
					// if
					// (MarsBean.class.isAssignableFrom(prop.getPropertyType()))
					// {
					// Object value = getter.invoke(this);
					// _temp.append(value == null ? "null" : value.toString()
					// .replaceAll("\\n", "\n\t"));
					// } else {
					// _temp.append(getter.invoke(this));
					// }

					// 不解析内部bean
					if (!MarsBean.class.isAssignableFrom(prop.getPropertyType())
							&& !List.class.isAssignableFrom(prop.getPropertyType())) {
						_temp.append(", \n\t");
						_temp.append(prop.getName());
						_temp.append(" = ");
						_temp.append(getter.invoke(this));
					}
				} catch (Exception e) {
					_temp.append("<GETTER ERROR: " + e.getMessage() + ">");
				}
			}
			final String string = _temp.append("}").toString();
			return string.replaceAll("^([\\w\\s]+)\\{\\,", "$1{");
		} catch (IntrospectionException e) {
			return super.toString();
		}
	}

	/**
	 * @return the isOnline
	 */
	public boolean isOnline() {
		return isOnline;
	}

	/**
	 * @param isOnline the isOnline to set
	 */
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
}
