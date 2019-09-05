package cn.promptness.blog.mapper;

import cn.promptness.blog.pojo.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OptionsMapper {

    int deleteByPrimaryKey(Long optionId);

    int insert(Options record);

    int insertSelective(Options record);

    Options selectByPrimaryKey(Long optionId);

    int updateByPrimaryKeySelective(Options record);

    int updateByPrimaryKey(Options record);

    String getOption(@Param("key") String key);

    void bathSaveOptions(@Param("options") Map<String, String> options);

    List<Options> list();

}