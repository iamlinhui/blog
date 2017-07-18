package com.blog.mapper;

import com.blog.pojo.Options;
import com.blog.pojo.OptionsExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface OptionsMapper {
    int countByExample(OptionsExample example);

    int deleteByExample(OptionsExample example);

    int deleteByPrimaryKey(Long optionId);

    int insert(Options record);

    int insertSelective(Options record);

    List<Options> selectByExample(OptionsExample example);

    Options selectByPrimaryKey(Long optionId);

    int updateByExampleSelective(@Param("record") Options record, @Param("example") OptionsExample example);

    int updateByExample(@Param("record") Options record, @Param("example") OptionsExample example);

    int updateByPrimaryKeySelective(Options record);

    int updateByPrimaryKey(Options record);

	String getOption(@Param("key")String key);

	void bathSaveOptions(@Param("options")Map<String, String> options);
}