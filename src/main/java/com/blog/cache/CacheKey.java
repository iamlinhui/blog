package com.blog.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

public class CacheKey implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
		// target：目标对象
		// method：目标方法
		// params：实参数组

		StringBuilder builder = new StringBuilder();

		// 1.获取目标对象的全类名
		String className = target.getClass().getName();

		builder.append(".").append(className);

		// 2.获取目标方法名
		String methodName = method.getName();

		builder.append(".").append(methodName);

		// 3.遍历params数组
		// ※注意：params数组有可能为null
		for (int i = 0; params != null && i < params.length; i++) {

			builder.append(".").append(params[i]);

		}

		String key = builder.substring(1).toString();
		//System.out.println(key);

		return key;
	}

}
