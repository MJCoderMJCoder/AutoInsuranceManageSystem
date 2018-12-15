package com.lzf.util;

import java.text.SimpleDateFormat;

/**
 * 日期时间的格式化工具
 * 
 * @author MJCoder
 *
 */
public class DateTimeUtil {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 获取系统的当前时间
	 * 
	 * @return 格式化后的时间：yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentTime() {
		return simpleDateFormat.format(System.currentTimeMillis());
	}
}
