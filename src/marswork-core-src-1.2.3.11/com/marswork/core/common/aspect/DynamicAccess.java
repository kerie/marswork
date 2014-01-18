package com.marswork.core.common.aspect;


/**
 * 
 * <p>
 * 抽象动态代理访问方法
 * <p>
 * 对抽象动态代理类进行单例管理
 * 
 * @author MarsDJ
 * @since 2010-12-9
 * @version 1.0
 */
public class DynamicAccess {

	private static AbstractDynamicProxy abstractDynamicProxy;

	/**
	 * 获取远程接口动态代理
	 * 
	 * @return 远程接口动态代理对象
	 */
	protected static AbstractDynamicProxy getDynamicProxy() {
		if (abstractDynamicProxy == null) {
			abstractDynamicProxy = new AbstractDynamicProxy();
		}
		return abstractDynamicProxy;
	}

	@SuppressWarnings("unchecked")
	public <T> T bind(T obj) {
		try {
			return (T) getDynamicProxy().bind(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
