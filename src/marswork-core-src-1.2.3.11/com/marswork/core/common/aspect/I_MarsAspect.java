package com.marswork.core.common.aspect;

/**
 * <p>
 * 轻量级AOP处理
 * <p>
 * 使用动态代理模式完成AOP拦截器
 * 
 * @author MarsDJ
 * @since 2010-12-8
 * @version 1.0
 */
public interface I_MarsAspect {
	/**
	 * 前置增强
	 * 
	 * @param caller
	 *            调用该方法的对象
	 * @throws Exception
	 *             在运行过程中遇到的所有例外
	 */
	public void before(Object caller) throws Exception;

	/**
	 * 后置增强
	 * 
	 * @param caller
	 *            调用该方法的对象
	 * @param caller
	 *            调用方法后的结果对象
	 * @throws Exception
	 *            在运行过程中遇到的所有例外
	 */
	public void after(Object caller, Object result) throws Exception;
}
