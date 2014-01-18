package com.marswork.core.enums;

import com.marswork.core.minitools.object.enums.I_DataEnum;

/**
 * 
 * <p>
 * 类型枚举的接口
 * <p>
 * 
 * @author porter
 * @author MarsDJ
 * @since 2011-6-18
 * @version 1.0
 */
public interface I_Category<T> extends I_DataEnum {

	/**
	 * 获取显示名称
	 * 
	 * @return
	 */
	public String getCategoryName();

	/**
	 * 通过枚举项的显示名反射枚举项
	 * 
	 * @param categoryName 枚举项的显示名
	 * @return 对应的枚举项
	 */
	public T reflect(String categoryName);
}
