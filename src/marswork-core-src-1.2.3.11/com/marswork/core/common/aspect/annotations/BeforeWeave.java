package com.marswork.core.common.aspect.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.marswork.core.common.aspect.I_MarsAspect;

/**
 * 
 * <p>
 * 前织入声明
 * <p>
 * 在被绑定的对象运行指定被织入的方法执行前<br>
 * 运行声明的类对象中的before方法<br>
 * 
 * @author MarsDJ
 * @since 2010-12-8
 * @version 1.0
 * @see {@link I_MarsAspect}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface BeforeWeave {

	/**
	 * 声明前织入的运行类<br>
	 * 在需要运行前织入的地方将生成该类的对象<br>
	 * 然后运行其before方法
	 * 
	 * @return 前织入的运行类
	 */
	public Class<? extends I_MarsAspect>[] value();

}
