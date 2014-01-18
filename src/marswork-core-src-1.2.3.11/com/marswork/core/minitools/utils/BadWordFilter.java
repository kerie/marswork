package com.marswork.core.minitools.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.marswork.core.minitools.object.BasicUtils;

/**
 * <p>敏感字过滤器
 * 
 * @author MarsDJ 
 * @since 2010-11-21
 * @version 1.0
 */
public class BadWordFilter {

	/**
	 * 敏感字列表
	 */
	private static List<String> wordList;

	static {
		wordList = readBadWordsListFromFile("");
	}

	/**
	 * 过滤敏感字.
	 * 
	 * @param input
	 *            源字符串
	 * @return 过滤后的字符串
	 */
	public static String filterBadWords(String input) {
		if (BasicUtils.isTrimBlank(input)) {
			return input;
		}

		for (String word : wordList) {
			if (word.matches("\\w+"))
				input = input.replaceAll("\\s+" + word + "\\s+", " ");
			else
				input = input.replace(word, "");
		}

		return input;
	}

	/**
	 * 检查输入字符串是否有非法词汇.
	 * 
	 * @param input
	 *            源字符串
	 * @return 是否含有非法词汇
	 */
	public static boolean hasBadWords(String input) {

		if (BasicUtils.isTrimBlank(input)) {
			return false;
		}

		for (String word : wordList) {
			if (input.indexOf(word) != -1) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 从配置文件载入非法词汇列表.
	 * 
	 * @return 敏感词汇列表
	 */
	private static List<String> readBadWordsListFromFile(String filePath) {
		// 从配置文件加载关键字信息, 存入到一个List中, 一行一个关键字
		InputStream in = BadWordFilter.class.getResourceAsStream(filePath);
		ArrayList<String> list = new ArrayList<String>();// 关键字
		// 转成 Reader, 一次读一行用 BufferedReader
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String line;

			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			in.close();
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 改变敏感字字典
	 * 
	 * @param filePath
	 *            字典文件路径
	 */
	public static void changeBadWordsListFromFile(String filePath) {
		wordList = readBadWordsListFromFile(filePath);
	}

	public static void setWordsList(List<String> keywords) {
		wordList = keywords;
	}
}
