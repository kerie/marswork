/**
 * 
 */
package com.marswork.core.common.aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 * 抽象动态代理
 * <p>
 * 用于自定义AOP实现<br>
 * 要实现动态代理先要对目标对象需要织入的方法声明为 前织入，后织入或环绕织入<br>
 * 然后声明织入处理类<br>
 * 织入处理类必须实现{@link I_MarsAspect} <br>
 * 然后使用{@link AbstractDynamicAccess} 的实现类绑定目标对象<br>
 * 最后用绑定的对象执行被织入的方法即可
 * 
 * @author MarsDJ
 * @since 2012-2-3
 * @version 1.0
 */
public class AbstractDynamicProxy implements InvocationHandler {

	private Object proxyObj;

	/**
	 * 制作被动态代理的对象
	 * 
	 * @param obj
	 *            需要被动态代理的对象
	 * @return 被动态代理的对象
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public Object bind(Object obj) throws InstantiationException,
			IllegalAccessException {
		proxyObj = obj;
		Class<?> cls = obj.getClass();
		return Proxy.newProxyInstance(cls.getClassLoader(),
				cls.getInterfaces(), this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
	 * java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Exception {
		if (method == null)
			throw new NullPointerException("要执行的方法不可为空");

		Object result;

		WeaveAnalyzer analyzer = new WeaveAnalyzer(method);

		analyzer.doBeforeWeave(proxyObj);
		result = method.invoke(proxyObj, args);
		analyzer.doAfterWeave(proxyObj, result);

		return result;
	}
}
