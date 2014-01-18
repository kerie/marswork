package com.marswork.core.minitools.system;

/**
 * <p>
 * java虚拟机，用户操作系统信息
 * <p>
 * 获取虚拟机和用户操作系统的基本信息<br>
 * 常用的有获取用户工作目录，获取系统分隔符等<br>
 * 获取javahome，操作系统名等也有可能用到
 * 
 * @author MarsDJ
 * @since 2011-5-5
 * @version 1.0
 */
public class SystemInfo {

	/**
	 * 获取 Java 运行时环境版本
	 * 
	 * @return Java 运行时环境版本
	 */
	public static String getJavaVersion() {
		return System.getProperty("java.version");
	}

	/**
	 * 获取 Java 运行时环境供应商
	 * 
	 * @return Java 运行时环境供应商
	 */
	public static String getJavaVendor() {
		return System.getProperty("java.vendor");
	}

	/**
	 * 获取 Java 供应商的 URL
	 * 
	 * @return Java 供应商的 URL
	 */
	public static String getJavaVendorUrl() {
		return System.getProperty("java.version.url");
	}

	/**
	 * 获取 Java 安装目录
	 * 
	 * @return Java 安装目录
	 */
	public static String getJavaHome() {
		return System.getProperty("java.home");
	}

	/**
	 * 获取 Java 虚拟机规范版本
	 * 
	 * @return Java 虚拟机规范版本
	 */
	public static String getJavaVmSpecificationVersion() {
		return System.getProperty("java.vm.specification.version");
	}

	/**
	 * 获取 Java 虚拟机规范供应商
	 * 
	 * @return Java 虚拟机规范供应商
	 */
	public static String getJavaVmSpecificationVendor() {
		return System.getProperty("java.vm.specification.vendor");
	}

	/**
	 * 获取 Java 虚拟机规范名称
	 * 
	 * @return Java 虚拟机规范名称
	 */
	public static String getJavaVmSpecificationName() {
		return System.getProperty("java.vm.specification.name");
	}

	/**
	 * 获取 Java 虚拟机实现版本
	 * 
	 * @return Java 虚拟机实现版本
	 */
	public static String getJavaVmVersion() {
		return System.getProperty("java.vm.version");
	}

	/**
	 * 获取 Java 虚拟机实现供应商
	 * 
	 * @return Java 虚拟机实现供应商
	 */
	public static String getJavaVmVendor() {
		return System.getProperty("java.vm.vendor");
	}

	/**
	 * 获取 Java 虚拟机实现名称
	 * 
	 * @return Java 虚拟机实现名称
	 */
	public static String getJavaVmName() {
		return System.getProperty("java.vm.name");
	}

	/**
	 * 获取 Java 运行时环境规范版本
	 * 
	 * @return Java 运行时环境规范版本
	 */
	public static String getJavaSpecificationVersion() {
		return System.getProperty("java.specification.version");
	}

	/**
	 * 获取 Java 运行时环境规范供应商
	 * 
	 * @return Java 运行时环境规范供应商
	 */
	public static String getJavaSpecificationVendor() {
		return System.getProperty("java.specification.vendor");
	}

	/**
	 * 获取 Java 运行时环境规范名称
	 * 
	 * @return Java 运行时环境规范名称
	 */
	public static String getJavaSpecificationName() {
		return System.getProperty("java.specification.name");
	}

	/**
	 * 获取 Java 类格式版本号
	 * 
	 * @return Java 类格式版本号
	 */
	public static String getJavaClassVersion() {
		return System.getProperty("java.class.version");
	}

	/**
	 * 获取 Java 类路径
	 * 
	 * @return Java 类路径
	 */
	public static String getJavaClassPath() {
		return System.getProperty("java.class.path");
	}

	/**
	 * 获取 加载库时搜索的路径列表
	 * 
	 * @return 加载库时搜索的路径列表
	 */
	public static String getJavaLibraryPath() {
		return System.getProperty("java.library.path");
	}

	/**
	 * 获取 默认的临时文件路径
	 * 
	 * @return 默认的临时文件路径
	 */
	public static String getJavaIoTmpdir() {
		return System.getProperty("java.io.tmpdir");
	}

	/**
	 * 获取 要使用的 JIT 编译器的名称
	 * 
	 * @return 要使用的 JIT 编译器的名称
	 */
	public static String getJavaCompiler() {
		return System.getProperty("java.compiler");
	}

	/**
	 * 获取 一个或多个扩展目录的路径
	 * 
	 * @return 一个或多个扩展目录的路径
	 */
	public static String getJavaExtDirs() {
		return System.getProperty("java.ext.dirs");
	}

	/**
	 * 获取 操作系统的名称
	 * 
	 * @return 操作系统的名称
	 */
	public static String getOsName() {
		return System.getProperty("os.name");
	}

	/**
	 * 获取 操作系统的架构
	 * 
	 * @return 操作系统的架构
	 */
	public static String getOsArch() {
		return System.getProperty("os.arch");
	}

	/**
	 * 获取 操作系统的版本
	 * 
	 * @return 操作系统的版本
	 */
	public static String getOsVersion() {
		return System.getProperty("os.version");
	}

	/**
	 * 获取 文件分隔符（在 UNIX 系统中是“/”）
	 * 
	 * @return 文件分隔符
	 */
	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}

	/**
	 * 获取 路径分隔符（在 UNIX 系统中是“:”）
	 * 
	 * @return 路径分隔符
	 */
	public static String getPathSeparator() {
		return System.getProperty("path.separator");
	}

	/**
	 * 获取 行分隔符（在 UNIX 系统中是“/n”）
	 * 
	 * @return 行径分隔符
	 */
	public static String getLineSeparator() {
		return System.getProperty("line.separator");
	}

	/**
	 * 获取 用户的账户名称
	 * 
	 * @return 用户的账户名称
	 */
	public static String getUserName() {
		return System.getProperty("user.name");
	}

	/**
	 * 获取 用户的主目录
	 * 
	 * @return 用户的主目录
	 */
	public static String getUserHome() {
		return System.getProperty("user.home");
	}

	/**
	 * 获取 用户的当前工作目录
	 * 
	 * @return 用户的当前工作目录
	 */
	public static String getUserDir() {
		return System.getProperty("user.dir");
	}
}
