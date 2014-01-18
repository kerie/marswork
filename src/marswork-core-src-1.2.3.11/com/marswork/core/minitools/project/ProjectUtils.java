package com.marswork.core.minitools.project;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * <p>
 * 普通工程常用方法
 * <p>
 * 针对工程本身进行的操作
 * 
 * @author MarsDJ
 * @since 2010-11-22
 * @version 1.0
 */
public class ProjectUtils {

	/**
	 * 获取当前线程的相对路径，可用作工程 绝对路径
	 * 
	 * @return 工程相对路径
	 * @throws UnsupportedEncodingException
	 *             不可编码例外
	 */
	public static String getTreadRelativePath() {
		try {
			String path = URLDecoder
					.decode(Thread.currentThread().getContextClassLoader()
							.getResource("").getPath(), "utf-8");
			if (path.startsWith("/")) {
				path = path.substring(1);
			}
			return path.replace("/", "\\");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return Thread.currentThread().getContextClassLoader()
					.getResource("").getPath();
		}
	}

	/**
	 * 获取当前用户文件夹的相对路径<br>
	 * 可用作工程绝对路径<br>
	 * Web工程将会指向应用服务器所在目录
	 * 
	 * @return 工程相对路径
	 * @throws UnsupportedEncodingException
	 *             不可编码例外
	 */
	public static String getUserDirRelativePath() {
		String dir = "";
		try {
			dir = URLDecoder.decode(System.getProperty("user.dir"), "utf-8")
					.replace("/", "\\");
		} catch (UnsupportedEncodingException e) {
		}
		return appendSpretor(dir);
	}

	/**
	 * 获取当前工程名<br>
	 * Web工程获取用户名应当使用<br>
	 * WebProjectUtils#getProjectName方法
	 * 
	 * @return 工程名
	 * @throws UnsupportedEncodingException
	 *             不可编码例外
	 */
	public static String getProjectName() {
		String relativePath = getUserDirRelativePath();
		String temp = relativePath.substring(0, relativePath.length() - 1);
		String dir = temp.substring(temp.lastIndexOf("\\") + 1, temp.length());
		return dir;
	}

	/**
	 * 获取工程所在目录<br>
	 * Web工程将会指向应用服务器所在目录
	 * 
	 * @return 工程所在目录
	 * @throws UnsupportedEncodingException
	 *             不可编码例外
	 */
	public static String getProjectDir() {
		String relativePath = getUserDirRelativePath();
		String temp = relativePath.substring(0, relativePath.length() - 1);
		String dir = temp.substring(0, temp.lastIndexOf("\\"));
		return appendSpretor(dir);
	}

	/**
	 * 给路径追加分隔符
	 * 
	 * @param source
	 *            路径
	 * @return 追加分隔符后的路径
	 */
	private static String appendSpretor(String source) {
		return source.endsWith("\\") ? source : source + "\\";
	}
}
