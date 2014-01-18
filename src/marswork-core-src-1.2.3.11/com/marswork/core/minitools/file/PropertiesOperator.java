package com.marswork.core.minitools.file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.marswork.core.exceptions.config.PropertiesNotFoundException;
import com.marswork.core.minitools.project.ProjectUtils;

/**
 * <p>
 * 元配置文件操作
 * <p>
 * 可进行读写操作
 * 
 * @author MarsDJ
 * @since 2010-11-22
 * @version 1.0
 */
public class PropertiesOperator {

	private Properties props;

	private String filePath;

	/**
	 * 构造函数，根据文件路径创建实例
	 * 
	 * @param filePath
	 *            文件路径
	 * @throws IOException
	 */
	public PropertiesOperator(String filePath) throws IOException {
		this.filePath = attachFileName(filePath);
		props = new Properties();
		InputStream in = new BufferedInputStream(new FileInputStream(filePath));
		props.load(in);
	}

	/**
	 * 根据key读取value
	 * 
	 * @param key
	 *            键值
	 * @return 键对应的值
	 */
	public String readValue(String key) {
		return props.getProperty(key);
	}

	/**
	 * 读取properties的全部信息
	 * 
	 * @return 以Map形式返回配置信息
	 */
	public Map<String, String> readProperties() {
		Enumeration<?> en = props.propertyNames();
		Map<String, String> map = new HashMap<String, String>();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			map.put(key, props.getProperty(key));
		}
		return map;
	}

	/**
	 * 将键值对写入properties信息
	 * 
	 * @param parameterName
	 *            键
	 * @param parameterValue
	 *            值
	 * @throws IOException
	 *             读写例外
	 */
	public void writeProperties(String parameterName, String parameterValue)
			throws IOException {

		OutputStream fos = new FileOutputStream(filePath);
		props.setProperty(parameterName, parameterValue);
		props.store(fos, "Update '" + parameterName + "' value");

	}

	/**
	 * 访问工程src根目录下的配置文件
	 * 
	 * @param parameterName
	 *            键
	 * @param parameterValue
	 *            值
	 * @return 配置文件中指定键的值
	 * @throws PropertiesNotFoundException
	 *             配置文件找不到或读取异常
	 */
	public static String getClassRootProperties(String fileName,
			String parameterName) throws PropertiesNotFoundException {
		return loadClassRootProperties(fileName).getProperty(parameterName);
	}

	/**
	 * 访问工程src根目录下的配置文件
	 * 
	 * @param fileName
	 *            配置文件名
	 * @return 配置类对象
	 * @throws PropertiesNotFoundException
	 * @throws IOException
	 *             文件读写例外
	 */
	public static Properties loadClassRootProperties(String fileName)
			throws PropertiesNotFoundException {
		try {
			Properties rootProps = new Properties();
			InputStream in = new BufferedInputStream(new FileInputStream(
					ProjectUtils.getTreadRelativePath()
							+ attachFileName(fileName)));
			rootProps.load(in);
			return rootProps;
		} catch (FileNotFoundException e) {
			throw new PropertiesNotFoundException(fileName);
		} catch (IOException e) {
			throw new PropertiesNotFoundException(fileName);
		}
	}

	public static String getJarProperties(String fileName, String parameterName)
			throws PropertiesNotFoundException {
		return loadJarProperties(fileName).getProperty(parameterName);
	}
	
	public static Properties loadJarProperties(String fileName)
			throws PropertiesNotFoundException {
		try {
			Properties rootProps = new Properties();
			InputStream in = PropertiesOperator.class
					.getResourceAsStream(attachFileName(fileName));
			rootProps.load(in);
			return rootProps;
		} catch (FileNotFoundException e) {
			throw new PropertiesNotFoundException(fileName);
		} catch (IOException e) {
			throw new PropertiesNotFoundException(fileName);
		}
	}

	/**
	 * 如果文件名不是以.properties结尾<br>
	 * 则追加.properties为扩展名
	 * 
	 * @param fileName
	 *            配置文件名
	 * @return 处理后的配置文件名
	 */
	public static String attachFileName(String fileName) {
		if (!fileName.endsWith(".properties")) {
			return fileName + ".properties";
		}
		return fileName;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
}