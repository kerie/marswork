/**
 * 
 */
package com.marswork.core.common.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.marswork.core.common.aspect.annotations.AfterWeave;
import com.marswork.core.common.aspect.annotations.AroundWeave;
import com.marswork.core.common.aspect.annotations.BeforeWeave;

/**
 * <p>
 * <p>
 * 
 * @author MarsDJ
 * @since 2012-3-15
 * @version 1.0
 */
public class WeaveAnalyzer {

	protected List<I_MarsAspect> before = new ArrayList<I_MarsAspect>();

	protected List<I_MarsAspect> after = new ArrayList<I_MarsAspect>();

	public WeaveAnalyzer(Method targetMethod) {
		AroundWeave aroundWeave = targetMethod.getAnnotation(AroundWeave.class);
		BeforeWeave beforeWeave = targetMethod.getAnnotation(BeforeWeave.class);
		AfterWeave afterWeave = targetMethod.getAnnotation(AfterWeave.class);

		if (beforeWeave != null) {
			for (Class<? extends I_MarsAspect> temp : beforeWeave.value()) {
				try {
					appendBeforeWeave(temp.newInstance());
				} catch (Exception e) {
					continue;
				}
			}
		}
		if (aroundWeave != null) {
			for (Class<? extends I_MarsAspect> temp : aroundWeave.value()) {
				try {
					appendAroundWeave(temp.newInstance());
				} catch (Exception e) {
					continue;
				}
			}
		}
		if (afterWeave != null) {
			for (Class<? extends I_MarsAspect> temp : afterWeave.value()) {
				try {
					appendAfterWeave(temp.newInstance());
				} catch (Exception e) {
					continue;
				}
			}
		}
	}

	public void appendAroundWeave(I_MarsAspect aspect) {
		before.add(aspect);
		after.add(aspect);
	}

	public void appendBeforeWeave(I_MarsAspect aspect) {
		before.add(aspect);
	}

	public void appendAfterWeave(I_MarsAspect aspect) {
		after.add(aspect);
	}

	public void doBeforeWeave(Object param) throws Exception {
		for (I_MarsAspect temp : before) {
			temp.before(param);
		}
	}

	public void doAfterWeave(Object param, Object result) throws Exception {
		for (I_MarsAspect temp : after) {
			temp.after(param, result);
		}
	}
}
