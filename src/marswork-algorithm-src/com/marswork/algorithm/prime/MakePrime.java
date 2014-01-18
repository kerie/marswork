/**
 * 
 */
package com.marswork.algorithm.prime;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 素数相关算法
 * <p>
 * 
 * @author Nordy.SU
 * @author MarsDJ
 * @since 2011-12-3
 * @version 1.0
 */
public class MakePrime {

	/**
	 * 求N以内的所有素数
	 * 
	 * @param num
	 *            取素数的范围
	 * @return N以内的所有素数列表
	 */
	public static List<Integer> makePrimeByHash(int num) {
		boolean[] flags = new boolean[num + 1];
		List<Integer> primes = new ArrayList<Integer>();
		for (int i = 2; i < num + 1; i++) {
			if (flags[i])
				continue;
			primes.add(i);
			for (int j = 2; i * j < flags.length; ++j) {
				flags[i * j] = true;
			}
		}
		return primes;
	}

	/**
	 * 求前N个素数
	 * 
	 * @param n
	 *            取素数的范围
	 * @return 前N个素数
	 */
	public static int[] makePrime(int n) {
		int[] primes = new int[n];
		int i, j, cnt;

		primes[0] = 2;
		primes[1] = 3;

		for (i = 5, cnt = 2; cnt < n; i += 2) {
			boolean flag = true;
			for (j = 1; primes[j] * primes[j] <= i; ++j) {
				if (i % primes[j] == 0) {
					flag = false;
					break;
				}
			}
			if (flag)
				primes[cnt++] = i;
		}
		return primes;
	}

}
