package cn.promptness.blog.support.service;

import cn.promptness.blog.pojo.Options;

import java.util.List;
import java.util.Map;

public interface OptionsService {

	String getOption(String key);

	void bathSaveOptions(Map<String, String> options);


    List<Options> listOptions();

}
