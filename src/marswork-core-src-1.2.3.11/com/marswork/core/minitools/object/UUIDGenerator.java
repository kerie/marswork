// Decompiled by DJ v2.9.9.61 Copyright 2000 Atanas Neshkov  Date: 2007-7-9 17:43:34
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UUIDGenerator.java

package com.marswork.core.minitools.object;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;

/**
 * 
 * <p>
 * ID生成器
 * <p>
 * 生成具有唯一性的32位ID<br>
 * 通常用于指定Item的ID
 * 
 * @author anonymous
 * @since 2011-9-4
 * @version 1.0
 */
public class UUIDGenerator {

	private static final UUIDGenerator gen = new UUIDGenerator();

	private static byte serverIP[] = null;

	private static final SecureRandom secureRand = new SecureRandom();

	static {
		try {
			serverIP = InetAddress.getLocalHost().getAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private UUIDGenerator() {
	}

	private int getInt(byte bytes[]) {

		int i = 0;
		int j = 24;
		for (int k = 0; j >= 0; k++) {
			int l = bytes[k] & 0xff;
			i += l << j;
			j -= 8;
		}
		return i;
	}

	public static synchronized final String getNextValue() {

		String hexServerIP = gen.hexFormat(gen.getInt(serverIP), 8);
		String hexThis = gen.hexFormat(System.identityHashCode(gen), 8);
		long timeNow = System.nanoTime();
		int timeLow = (int) timeNow & -1;
		String hexTime = gen.hexFormat(timeLow, 8);
		int rand = secureRand.nextInt();
		String hexRand = gen.hexFormat(rand, 8);

		StringBuffer guid = new StringBuffer(32);
		guid.append(hexTime);
		guid.append(hexServerIP);
		guid.append(hexRand);
		guid.append(hexThis);
		return guid.toString();
	}

	/*
     * 
     */

	public static final synchronized String getNextValue(String TBName) {

		String hexServerIP = gen.hexFormat(gen.getInt(serverIP), 8);
		String hexTBName = gen.hexFormat(System.identityHashCode(TBName), 8);
		long timeNow = System.currentTimeMillis();
		int timeLow = (int) timeNow & -1;
		String hexTime = gen.hexFormat(timeLow, 8);
		int rand = secureRand.nextInt();
		String hexRand = gen.hexFormat(rand, 8);

		StringBuffer guid = new StringBuffer(32);
		guid.append(hexTime);
		guid.append(hexServerIP);
		guid.append(hexRand);
		guid.append(hexTBName);
		return guid.toString();
	}

	private String hexFormat(int i, int j) {
		String s = Integer.toHexString(i);
		return this.padHex(s, j) + s;
	}

	private String padHex(String s, int i) {
		StringBuffer tmpBuffer = new StringBuffer();
		if (s.length() < i) {
			for (int j = 0; j < i - s.length(); j++) {
				tmpBuffer.append('0');
			}
		}
		return tmpBuffer.toString();
	}
}