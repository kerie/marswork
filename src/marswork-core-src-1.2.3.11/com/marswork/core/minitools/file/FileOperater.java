package com.marswork.core.minitools.file;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.marswork.core.minitools.object.BasicUtils;

/**
 * <p>
 * 文件操作
 * 
 * @author MarsDJ
 * @since 2010-11-22
 * @version 1.0
 */
public class FileOperater {

	private FileOperater() {
	}

	/**
	 * 文件大小单位换算<br>
	 * 如果 小于1024 * 1024,以KB单位返回,反则以MB单位返回
	 * 
	 * @param size
	 *            文件字节数
	 * @return 带单位的文件大小
	 */
	public static String fileSizeConvert(long size) {

		DecimalFormat df = new DecimalFormat("###.##");
		float f;
		if (size < 1024 * 1024) {
			f = (float) ((float) size / (float) 1024);
			return (df.format(new Float(f).doubleValue()) + "KB");
		} else if (size < 1024 * 1024 * 1024) {
			f = (float) ((float) size / (float) (1024 * 1024));
			return (df.format(new Float(f).doubleValue()) + "MB");
		} else {
			f = (float) ((float) size / (float) (1024 * 1024 * 1024));
			return (df.format(new Float(f).doubleValue()) + "GB");
		}
	}

	/**
	 * 得到文件的扩展名.
	 * 
	 * @param fileName
	 *            需要处理的文件的名字.
	 * @return 文件的扩展名
	 */
	public static String getExtension(String fileName) {
		if (fileName != null) {
			int i = fileName.lastIndexOf('.');
			if (i > 0 && i < fileName.length() - 1) {
				return fileName.substring(i + 1).toLowerCase();
			}
		}
		return "";
	}

	public static String getSimpleName(String fileName) {
		if (fileName != null) {
			fileName = fileName.replace('\\', '/');
			int end = fileName.lastIndexOf(".");
			int start = 0;
			if (fileName.contains("/")) {
				start = fileName.lastIndexOf("/");
			}
			return fileName.substring(start + 1, end);
		}
		return "";
	}

	/**
	 * 得到文件的前缀名.
	 * 
	 * @param fileName
	 *            需要处理的文件的名字.
	 * @return 文件的前缀名
	 */
	public static String getPrefix(String fileName) {
		if (fileName != null) {
			fileName = fileName.replace('\\', '/');

			if (fileName.lastIndexOf("/") > 0) {
				fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
			}

			int i = fileName.lastIndexOf('.');
			if (i > 0 && i < fileName.length() - 1) {
				return fileName.substring(0, i);
			}
		}
		return "";
	}

	/**
	 * 得到文件的短路径, 不包括目录.
	 * 
	 * @param fileName
	 *            需要处理的文件的名字.
	 * @return 文件的短路径
	 */
	public static String getShortFileName(String fileName) {
		if (fileName != null) {
			String oldFileName = new String(fileName);

			fileName = fileName.replace('\\', '/');

			// Handle dir
			if (fileName.endsWith("/")) {
				int idx = fileName.indexOf('/');
				if (idx == -1 || idx == fileName.length() - 1) {
					return oldFileName;
				} else {
					return oldFileName.substring(idx + 1, fileName.length() - 1);
				}

			}
			if (fileName.lastIndexOf("/") > 0) {
				fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
			}

			return fileName;
		}
		return "";
	}

	/**
	 * 获取文件所在路径
	 * 
	 * @param fileName
	 *            文件长路径名，包含路径名和文件名
	 * @return 文件所在的文件路径
	 */
	public static String getFileDirName(String fileName) {
		String tempDir = fileName.replace("/", "\\");
		return tempDir.substring(0, tempDir.lastIndexOf("\\") + 1);
	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            目录路径
	 */
	public static void newFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
			System.out.println("新建目录操作 成功执行");
		} catch (Exception e) {
			System.out.println("新建目录操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 新建文件，并写入内容
	 * 
	 * @param filePathAndName
	 *            文件路径
	 * @param fileContent
	 *            文件内容
	 */
	public static void newFile(String filePathAndName, String fileContent) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();
			System.out.println("新建文件操作 成功执行");
		} catch (Exception e) {
			System.out.println("新建目录操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 向文件写入内容，文件不存在则抛出例外
	 * 
	 * @param fileName
	 *            文件路径
	 * @param content
	 *            文件内容
	 * @return 成功则返回true
	 * @throws IOException
	 *             读写例外
	 */
	public static boolean writeFileString(String fileName, String content) throws IOException {
//		FileWriter fout = new FileWriter(fileName);
		OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8");
		fout.write(content);
		fout.close();
		return true;
	}

	/**
	 * 向文件写入内容，文件不存在则抛出例外
	 * 
	 * @param fileName
	 *            文件路径
	 * @param content
	 *            文件内容
	 * @param autoCreate
	 *            如果文件不存在是否自动创建文件
	 * @return 成功则返回true
	 * @throws IOException
	 *             读写例外
	 */
	public static boolean writeFileString(String fileName, String content, boolean autoCreate)
			throws IOException {
		if (autoCreate) {
			File file = new File(fileName);
			if (!file.exists()) {
				File dir = new File(fileName.substring(0,
						fileName.replace("/", "\\").lastIndexOf("\\")));
				dir.mkdirs();
				file.createNewFile();
			}
			OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8");
			fout.write(content);
			fout.close();
			return true;
		} else {
			return writeFileString(fileName, content);
		}
	}

	/**
	 * 按编码类型向文件写入内容，文件不存在则抛出例外
	 * 
	 * @param fileName
	 *            文件路径
	 * @param content
	 *            文件内容
	 * @param encoding
	 *            编码类型
	 * @return 成功则返回true
	 * @throws IOException
	 *             读写例外
	 */
	public static boolean writeFileString(String fileName, String content, String encoding)
			throws IOException {
		OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(fileName), encoding);

		fout.write(content);
		fout.close();
		return true;
	}

	/**
	 * 按编码类型向文件追加内容，文件不存在则抛出例外
	 * 
	 * @param fileName
	 *            文件路径
	 * @param content
	 *            追加内容
	 * @param encoding
	 *            编码类型
	 * @return 成功则返回true
	 * @throws IOException
	 *             读写例外
	 */
	public static boolean appendFileString(String fileName, String content, String encoding)
			throws IOException {
		OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(fileName, true),
				encoding);

		fout.write(content);
		fout.close();
		return true;
	}

	/**
	 * 按编码类型向文件写入字节数组，文件不存在则抛出例外
	 * 
	 * @param fileName
	 *            文件路径
	 * @param content
	 *            字节数组
	 * @return 成功则返回true
	 * @throws IOException
	 *             读写例外
	 */
	public static boolean writeFileBinary(String fileName, byte[] content) throws IOException {
		FileOutputStream fout = new FileOutputStream(fileName);
		fout.write(content);
		fout.close();
		return true;
	}

	/**
	 * 删除文件
	 * 
	 * @param 文件完整路径
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myDelFile = new File(filePath);
			myDelFile.delete();
			System.out.println("删除文件操作 成功执行");
		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @param 文件夹
	 *            完整路径
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (myFilePath.delete()) { // 删除空文件夹
				System.out.println("删除文件夹" + folderPath + "操作 成功执行");
			} else {
				System.out.println("删除文件夹" + folderPath + "操作 执行失败");
			}
		} catch (Exception e) {
			System.out.println("删除文件夹操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            String 文件夹路径 如 c:/fqf
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				// delAllFile(path+"/"+ tempList[i]);//先删除文件夹里面的文件
				delFolder(path + File.separatorChar + tempList[i]);// 再删除空文件夹
			}
		}
		System.out.println("删除文件操作 成功执行");
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				// 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
			// System.out.println("复制单个文件操作 成功执行");
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}
				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/"
							+ (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {
					// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
			System.out.println("复制文件夹操作 成功执行");
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);
	}

	/**
	 * 移动文件夹到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf
	 * @param newPath
	 *            String 如：d:/fqf
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}

	/**
	 * 读取文件内容，返回字符串
	 * 
	 * @param fileName
	 *            文件路径
	 * @return 文件内容
	 * @throws Exception
	 *             任何例外
	 */
	public static String readFileAsString(String fileName) {
		try {
			return readFileAsString(fileName,"utf-8");
		} catch (Throwable e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 读取文件并返回为给定字符集的字符串.
	 * 
	 * @param fileName
	 *            文件路径
	 * @param encoding
	 *            编码类型
	 * @return 按编码类型编码的文件内容
	 * @throws Exception
	 *             任何例外
	 */
	public static String readFileAsString(String fileName, String encoding) throws Exception {
		String content = new String(readFileBinary(fileName), encoding);

		return content;
	}

	/**
	 * 读取文件并返回为给定字符集的字符串.
	 * 
	 * @param in
	 *            输入流
	 * @param encoding
	 *            编码类型
	 * @return 按编码类型编码的文件内容
	 * @throws Exception
	 *             任何例外
	 */
	public static String readFileAsString(InputStream in) throws Exception {
		String content = new String(readFileBinary(in));

		return content;
	}

	/**
	 * 读取文件并返回为给定字符集的字符串.
	 * 
	 * @param fileName
	 *            文件路径
	 * @return 按编码类型编码的文件内容
	 * @throws Exception
	 *             任何例外
	 */
	public static byte[] readFileBinary(String fileName) throws Exception {
		FileInputStream fin = new FileInputStream(fileName);
		return readFileBinary(fin);
	}

	/**
	 * 从输入流读取数据为二进制字节数组.
	 * 
	 * @param streamIn
	 *            输入流
	 * @return 二进制字节数组
	 * @throws IOException
	 *             读写例外
	 */
	public static byte[] readFileBinary(InputStream streamIn) throws IOException {
		byte buf[] = new byte[1024];
		BufferedInputStream in = new BufferedInputStream(streamIn);
		ByteArrayOutputStream out = new ByteArrayOutputStream(102400);
		int len;
		while ((len = in.read(buf)) >= 0)
			out.write(buf, 0, len);
		in.close();
		return out.toByteArray();
	}

	/**
	 * 检查文件名是否合法.文件名字不能包含字符\/:*?"<>|
	 * 
	 * @param fileName文件名
	 *            ,不包含路径
	 * @return 是否合法
	 */
	public static boolean isValidFileName(String fileName) {
		boolean isValid = true;
		String errChar = "\\/:*?\"<>|"; //
		if (fileName == null || fileName.length() == 0) {
			isValid = false;
		} else {
			for (int i = 0; i < errChar.length(); i++) {
				if (fileName.indexOf(errChar.charAt(i)) != -1) {
					isValid = false;
					break;
				}
			}
		}
		return isValid;
	}

	/**
	 * 把非法文件名转换为合法文件名.
	 * 
	 * @param fileName
	 *            文件名
	 * @return 合法文件名
	 */
	public static String replaceInvalidFileChars(String fileName) {
		StringBuffer out = new StringBuffer();

		for (int i = 0; i < fileName.length(); i++) {
			char ch = fileName.charAt(i);
			// Replace invlid chars: \\/:*?\"<>|
			switch (ch) {
			case '\\':
			case '/':
			case ':':
			case '*':
			case '?':
			case '\"':
			case '<':
			case '>':
			case '|':
				out.append('_');
				break;
			default:
				out.append(ch);
			}
		}

		return out.toString();
	}

	/**
	 * 将给定文件路径转换为URL路径
	 * 
	 * @param fileName
	 *            文件路径
	 * @return URL路径
	 */
	public static String filePathToURL(String fileName) {
		String fileUrl = new File(fileName).toURI().toString();
		return fileUrl;
	}

	/**
	 * 读取某个文件夹及其子文件夹下的所有文件
	 * 
	 * @param filepath
	 *            要读取的文件夹路径
	 * @return 文件夹及其子文件夹下的所有文件路径列表
	 * @throws FileNotFoundException
	 *             文件夹没有找到
	 * @throws IOException
	 *             读写操作失败
	 */
	public static List<String> readDir(String filepath) throws FileNotFoundException, IOException {
		filepath = filepath.replace("/", "\\");
		List<String> filaNames = new ArrayList<String>();
		File file = new File(filepath);
		if (!file.isDirectory()) {
			BasicUtils.out(0);
			filaNames.add(file.getAbsolutePath());
		} else if (file.isDirectory()) {
			readDir(filaNames, filepath);
		}
		BasicUtils.out(filaNames);
		return filaNames;
	}

	private static List<String> readDir(List<String> source, String filepath) {

		File file = new File(filepath);
		if (!file.isDirectory()) {
			source.add(file.getAbsolutePath());
		} else if (file.isDirectory()) {
			if (!filepath.endsWith("\\")) {
				filepath += "\\";
			}
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filepath + filelist[i]);
				if (!readfile.isDirectory()) {
					source.add(readfile.getAbsolutePath());
				} else if (readfile.isDirectory()) {
					readDir(source, filepath + filelist[i]);
				}
			}
		}

		return source;
	}

	public static void writeObject(String filePath, Object... objects) throws IOException {
		ObjectOutputStream oos = null;
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(filePath);
			oos = new ObjectOutputStream(fos);
			for (Object obj : objects) {
				oos.writeObject(obj);
			}
		} finally {
			if (oos != null) {
				oos.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T readSingleObject(String filePath, Class<T> class_) throws IOException {
		try {
			return (T) readObject(filePath).get(0);
		} catch (Exception e) {
			return null;
		}
	}

	public static List<Object> readObject(String filePath) throws IOException {
		List<Object> arrayList = new ArrayList<Object>();
		ObjectInputStream ois = null;
		try {
			FileInputStream fis = new FileInputStream(filePath);
			ois = new ObjectInputStream(fis);
			while (true) {
				try {
					arrayList.add(ois.readObject());
				} catch (Exception e) {
					break;
				}
			}
		} finally {
			if (ois != null) {
				ois.close();
			}
		}
		return arrayList;
	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Xcourse", "dfasdfasdfadf");

		try {
			writeObject("e:\\a.dd", map);
			BasicUtils.out(readSingleObject("e:\\a.dd", Map.class));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
