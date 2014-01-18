package com.marswork.core.minitools.object;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * <p>
 * 对象操作类
 * <p>
 * 针对对象的一些基础操作
 * 
 * @author anonymous
 * @since 2011-9-4
 * @version 1.0
 */
public class ObjectUtils {
	/**
	 * 对象拷贝 - setter getter 方法级别上的拷贝
	 * 
	 * @param src
	 *            源对象
	 * @param dest
	 *            目标对象
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static void copyObject(Object src, Object dest)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {

		Method[] srcMethods = src.getClass().getMethods();
		Method[] destMethods = dest.getClass().getMethods();

		for (Method destMethod : destMethods) {
			// 找到目标对象的set方法
			if (destMethod.getName().startsWith("set")) {
				for (Method srcMethod : srcMethods) {
					// 找到源对象的get方法并比较 目标对象和源对象是否操作同名属性
					// 比较源方法的返回值 和 目标方法的参数个数和类型是否匹配
					if (srcMethod.getName().startsWith("get")
							&& destMethod
									.getName()
									.replace("set", "")
									.equals(srcMethod.getName().replace("get",
											""))
							&& destMethod.getParameterTypes().length == 1
							&& srcMethod.getReturnType().equals(
									destMethod.getParameterTypes()[0])) {
						// 调用方法进行属性值拷贝
						destMethod.invoke(dest, srcMethod.invoke(src));
					}
				}
			}
		}

	}

	/**
	 * 将对象持久化到文件系统
	 * 
	 * @param obj
	 *            目标对象
	 * @param path
	 *            要保存的文件路径
	 * @throws IOException
	 *             读写例外
	 */
	public static void object2File(Object[] objs, String path)
			throws IOException {
		File file = new File(path);
		file.createNewFile();
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				file));
		for (Object o : objs) {
			out.writeObject(o);
		}
		out.close();
	}

	/**
	 * 从文件恢复对象
	 * 
	 * @param path
	 *            要从中恢复对象的文件
	 * @return 恢复的对象数组
	 * @throws FileNotFoundException
	 *             文件找不到例外
	 * @throws IOException
	 *             文件读写例外
	 * @throws ClassNotFoundException
	 *             恢复的对象找不到定义例外
	 */
	public static Object[] getObjectFromFile(String path)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Object> objs = new ArrayList<Object>();
		File file = new File(path);
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		Object o = null;
		while ((o = in.readObject()) != null) {
			objs.add(o);
		}
		in.close();
		return objs.toArray();
	}
}
