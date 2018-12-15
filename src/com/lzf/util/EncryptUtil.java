/**
 * 
 */
package com.lzf.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 * 
 * @author MJCoder
 *
 */
public class EncryptUtil {

	public static String encrypt(String raw) {
		return sha(md5(raw));
	}

	/**
	 * MD5加密
	 */
	private static String md5(String raw) {
		String result = raw;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(raw.getBytes());
			StringBuilder stringBuilder = new StringBuilder();
			for (byte b : messageDigest.digest()) {
				stringBuilder.append(Integer.toHexString((b >> 4) & 0xf));
				stringBuilder.append(Integer.toHexString(b & 0xf));
			}
			result = stringBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// System.out.println("MD5：" + result);
		return result;

	}

	/**
	 * SHA-256加密
	 */
	private static String sha(String raw) {
		String result = raw;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(raw.getBytes());
			byte[] engineDigest = messageDigest.digest();
			String tempStr = null;
			StringBuilder stb = new StringBuilder();
			for (int i = 0; i < engineDigest.length; i++) {
				tempStr = Integer.toHexString(engineDigest[i] & 0xff);
				if (tempStr.length() == 1) {
					stb.append("0");
				}
				stb.append(tempStr);
			}
			result = stb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// System.out.println("SHA：" + result);
		return result;
	}
}
