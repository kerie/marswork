package com.marswork.algorithm.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
 * <p>
 * 在数据集合中随机抽取样本
 * <p>
 * 例如要在100个对象中随机取出10个对象
 * 
 * @author MarsDJ
 * @since 2010-11-7
 * @version 1.0
 */
public class RandomListUtils {

	private RandomListUtils() {
	}

	/**
	 * 在数据集合中随机抽取样本
	 * 
	 * @param source
	 *            源数据集合
	 * @param targetSize
	 *            样本长度
	 * @return 随机抽取的数据集合
	 */
	public static <T> List<T> createRandomList(List<T> source, int targetSize) {
		List<T> target = new ArrayList<T>();
		int[] indexArray = getIndexArray(source.size(), targetSize);
		for (int i = 0; i < indexArray.length; i++) {
			target.add(source.get(indexArray[i]));
		}
		return target;
	}

	/**
	 * 在数据集合中随机抽取样本
	 * 
	 * @param source
	 *            源数据集合
	 * @param targetSize
	 *            样本长度
	 * @return 随机抽取的数据集合
	 */
	public static Object[] createRandomList(Object[] source, int targetSize) {
		Object[] target = new Object[targetSize];
		int[] indexArray = getIndexArray(source.length, targetSize);
		for (int i = 0; i < indexArray.length; i++) {
			target[i] = (source[(indexArray[i])]);
		}
		return target;
	}

	/**
	 * 生成随机样本index值.
	 * 
	 * @param indexRange
	 *            index值范围
	 * @param targetSize
	 *            样本长度
	 * @return 随机抽取的数据下标
	 */
	public static int[] getIndexArray(int indexRange, int targetSize) {
		if (indexRange < targetSize) {
			targetSize = indexRange;
		}
		int[] indexArray = new int[targetSize];
		for (int i = 0; i < targetSize; i++) {
			indexArray[i] = -1;
		}
		int index = 0;
		Random random = new Random();
		while (index < targetSize && indexArray[index] == -1) {
			boolean isExits = false;
			int k = Math.round(random.nextFloat() * indexRange);
			if (k != indexRange) {
				for (int j = 0; j < targetSize; j++) {
					if (k == indexArray[j]) {
						isExits = true;
						break;
					}
				}
			} else {
				isExits = true;
			}
			if (!isExits) {
				indexArray[index] = k;
				index++;
			}
		}
		return indexArray;
	}
}
