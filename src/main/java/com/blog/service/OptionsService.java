package com.blog.service;

import java.util.Map;

public interface OptionsService {

	String getOption(String key);

	void bathSaveOptions(Map<String, String> options);


}
