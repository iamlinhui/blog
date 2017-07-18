package com.blog.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.mapper.OptionsMapper;
import com.blog.service.OptionsService;

@Service
public class OptionsServiceImpl implements OptionsService{
	
	@Autowired
	OptionsMapper optionsMapper;

	@Override
	public String getOption(String key) {
		String value = optionsMapper.getOption(key);
		return value;
	}

	@Override
	public void bathSaveOptions(Map<String, String> options) {
		optionsMapper.bathSaveOptions(options);
		
	}

}
