package cn.promptness.blog.support.service.impl;

import cn.promptness.blog.mapper.OptionsMapper;
import cn.promptness.blog.pojo.Options;
import cn.promptness.blog.support.service.OptionsService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Lynn
 */
@Service
public class OptionsServiceImpl implements OptionsService {

    @Resource
    private OptionsMapper optionsMapper;

    @Cacheable(value = "optionsCache", key = "'getOption' + #key")
    @Override
    public String getOption(String key) {
        return optionsMapper.getOption(key);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "optionsCache", allEntries = true)
    @Override
    public void bathSaveOptions(Map<String, String> options) {
        optionsMapper.bathSaveOptions(options);

    }

    @Cacheable(value = "optionsCache", key = "'listOptions'")
    @Override
    public List<Options> listOptions() {
        return optionsMapper.list();
    }

}
