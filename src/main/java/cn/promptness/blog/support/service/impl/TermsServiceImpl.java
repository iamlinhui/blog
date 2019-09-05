package cn.promptness.blog.support.service.impl;

import cn.promptness.blog.mapper.TermsMapper;
import cn.promptness.blog.pojo.Terms;
import cn.promptness.blog.support.service.TermsService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TermsServiceImpl implements TermsService {

    @Resource
    private TermsMapper termsMapper;

    @Cacheable(value = "termsCache", key = "'getTerms'")
    @Override
    public List<Terms> getTerms() {
        return termsMapper.listTerms();
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "termsCache", allEntries = true)
    @Override
    public void saveTerms(Terms terms) {
        termsMapper.insertSelective(terms);
        termsMapper.updateTermsOrderByTermsId(terms.getTermId(), terms.getTermId());

    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "termsCache", allEntries = true)
    @Override
    public void updateTerms(Terms terms) {
        termsMapper.updateByPrimaryKeySelective(terms);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "termsCache", allEntries = true)
    @Override
    public void deleteTermsById(long parseInt) {
        termsMapper.deleteByPrimaryKey(parseInt);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "termsCache", allEntries = true)
    @Override
    public void upTerms(long thisTermsId) {
        //1).查找当前termsId的termsOrder值
        long thisTermsOrder = termsMapper.selectTermsOrderByTermsId(thisTermsId);
        //2).根据thisTermsOrder查找需要交换的目标的id
        long targetTermsId = termsMapper.selectUpTargetTermsId(thisTermsOrder);
        //3).根据targetTermsId查找targetTerms的termsOrder
        long targetTermsOrder = termsMapper.selectTermsOrderByTermsId(targetTermsId);
        //4).更新thisTerms的termsOrder
        termsMapper.updateTermsOrderByTermsId(thisTermsId, targetTermsOrder);
        //5).更新targetTerms的termsOrder
        termsMapper.updateTermsOrderByTermsId(targetTermsId, thisTermsOrder);

    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "termsCache", allEntries = true)
    @Override
    public void downTerms(long thisTermsId) {
        //1).查找当前termsId的termsOrder值
        long thisTermsOrder = termsMapper.selectTermsOrderByTermsId(thisTermsId);
        //2).根据thisTermsOrder查找需要交换的目标的id
        long targetTermsId = termsMapper.selectDownTargetTermsId(thisTermsOrder);
        //3).根据targetTermsId查找targetTerms的termsOrder
        long targetTermsOrder = termsMapper.selectTermsOrderByTermsId(targetTermsId);
        //4).更新thisTerms的termsOrder
        termsMapper.updateTermsOrderByTermsId(thisTermsId, targetTermsOrder);
        //5).更新targetTerms的termsOrder
        termsMapper.updateTermsOrderByTermsId(targetTermsId, thisTermsOrder);

    }

    @Cacheable(value = "termsCache", key = "'getNameBySlug' + #slug")
    @Override
    public String getNameBySlug(String slug) {
        return termsMapper.geNameBySlug(slug);
    }

}
