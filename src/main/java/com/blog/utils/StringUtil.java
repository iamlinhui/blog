package com.blog.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 加密字符串
	 * 
	 * @return
	 */
	public static String encipher(String password) {

		// 1).判断字符串
		if (password == null || password.length() == 0) {
			return null;
		}

		// 2).获取MessageDigest对象
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// 3).MessageDigest对象处理字符串,进行加密处理,得到16位的byte数组
		byte[] bs = digest.digest(password.getBytes());

		// 4).准备工作,准备需要将byte数字转化为字符的char数组
		StringBuilder builder = new StringBuilder();
		char[] c = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		for (int i = 0; i < bs.length; i++) {

			// 5).获取高四位的值
			int high = (bs[i] >> 4) & 15;
			// 6).获取低四位的值
			int low = (bs[i]) & 15;

			char highChar = c[high];
			char lowChar = c[low];
			// 7).拼串
			builder.append(highChar).append(lowChar);
		}
		// 8).返回
		return builder.toString();

	}
	
	/**
	 * 获取当前时间字符串
	 * @return 当前时间字符串
	 */
	public static String getStringTime(){
		Date date = new Date();//获取当前系统时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");//设置格式化格式
		return sdf.format(date);//返回格式化后的时间
	}
	
	public static String getStringTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置格式化格式
		return sdf.format(date);//返回格式化后的时间
	}
	
	
	/**
	 * 去除servletPath后面的参数问题
	 */
	public static String treatmentPath(String servletPath) {
		if (servletPath == null || servletPath.length() == 0) {
			return null;
		}
		// 1.按照“/”将servletPath拆分为数组
		String[] split = servletPath.split("/");

		// 2.拼字符串
		// split[0]是一个空字符串
		return "/" + split[1] + "/" + split[2] + "/" + split[3];
	}
	
	/**
	 * 校验邮箱地址是否正确
	 *
	 */
	public static boolean isEmail(String email) {
		String pattern = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";//登陆用户名是否为邮箱
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(email);
		return m.matches();
	}

}
