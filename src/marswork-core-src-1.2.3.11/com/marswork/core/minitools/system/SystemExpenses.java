/**
 * 
 */
package com.marswork.core.minitools.system;

import java.util.HashMap;
import java.util.Map;

import com.marswork.core.minitools.object.BasicUtils;

/**
 * <p>
 * 检查时间和内存消耗量
 * <p>
 * 获取距离上一个检查点的时间和内存内存差值<br>
 * 分别以毫秒，字节为单位<br>
 * 内存差值负数表示消耗，正数表示释放
 * 
 * @author MarsDJ
 * @since 2011-12-13
 * @version 1.0
 */
public class SystemExpenses {

	private static long lastTime = -1L;
	private static long freeMemory = Long.MAX_VALUE;
	private static int index = 1;
	private static Map<String, Long> namedLastTime = new HashMap<String, Long>();

	/**
	 * 获取距离上一个检查点的时间差
	 * 
	 * @return 距离上一个检查点的时间差
	 */
	public static long getEclipse() {
		long curr = System.currentTimeMillis();
		long eclipse;
		if (lastTime != -1L) {
			eclipse = curr - lastTime;
		} else {
			eclipse = 0;
		}
		lastTime = curr;
		return eclipse;
	}

	/**
	 * 获取距离上一个同名检查点的时间差<br>
	 * 使用方法如下所示:
	 * 
	 * <pre>
	 * public myFunction(){
	 * 	getEclipse(&quot;checkPointName&quot;);
	 * 	myCode();
	 * 	myCode();
	 * 	myCode();
	 * 	getEclipse(&quot;checkPointName&quot;);
	 * }
	 * 
	 * <pre>
	 * 
	 * @param name 为检查点指定的名称
	 * @return 距离上一个同名检查点的时间差
	 */
	public static long getEclipse(String name) {
		long curr = System.currentTimeMillis();
		long eclipse;
		if (namedLastTime.get(name) != null) {
			eclipse = curr - namedLastTime.get(name);
		} else {
			eclipse = 0;
		}
		namedLastTime.put(name, curr);
		return eclipse;
	}

	/**
	 * 获取距离上一个检查点的内存差<br>
	 * 内存差值负数表示消耗，正数表示释放
	 * 
	 * @return 距离上一个检查点的内存差
	 */
	public static long getRuntimeMemory() {
		long curr = Runtime.getRuntime().freeMemory();
		long memory;
		if (freeMemory != Long.MAX_VALUE) {
			memory = curr - freeMemory;
		} else {
			memory = 0;
		}
		freeMemory = curr;
		return memory;
	}

	/**
	 * 
	 * 打印距离上一个同名检查点的时间，内存差值 <br>
	 * 内存差值负数表示消耗，正数表示释放
	 */
	public static void printChanges() {
		BasicUtils.out("检查点 " + index + "，距离上一个检查点：" + getEclipse() + "毫秒;" + getRuntimeMemory()
				+ "字节", 2);
		index++;
	}

	/**
	 * 打印距离上一个检查点的时间，内存差值 <br>
	 * 内存差值负数表示消耗，正数表示释放<br>
	 * 使用方法如下所示:
	 * 
	 * <pre>
	 * public myFunction() {
	 * 	printChanges(&quot;checkPointName&quot;);
	 * 	myCode();
	 * 	myCode();
	 * 	myCode();
	 * 	printChanges(&quot;checkPointName&quot;);
	 * }
	 * <pre>
	 * 
	 * @param name 为检查点指定的名称
	 */
	public static void printChanges(String name) {
		BasicUtils.out("检查点 " + name + "，距离上一个检查点：" + getEclipse(name) + "毫秒;" + getRuntimeMemory()
				+ "字节", 2);
	}

	/**
	 * 打印距离上一个检查点的时间，内存差值 <br>
	 * 内存差值负数表示消耗，正数表示释放
	 */
	public static void printChanges(boolean clear) {
		BasicUtils.out("检查点 " + index + "，距离上一个检查点：" + getEclipse() + "毫秒;" + getRuntimeMemory()
				+ "字节", 2);
		index++;
		if (clear) {
			clear();
		}
	}

	/**
	 * 清除检查点
	 */
	public static void clear() {
		lastTime = -1L;
		freeMemory = Long.MAX_VALUE;
		index = 1;
	}
}
