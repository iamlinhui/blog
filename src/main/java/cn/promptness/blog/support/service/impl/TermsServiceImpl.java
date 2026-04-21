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
    @CacheEvict(value = {"termsCache", "postsCache"}, allEntries = true)
    @Override
    public void updateTerms(Terms terms) {
        termsMapper.updateByPrimaryKeySelective(terms);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {"termsCache", "postsCache"}, allEntries = true)
    @Override
    public void deleteTermsById(long termId) {
        termsMapper.deleteByPrimaryKey(termId);
        termsMapper.deleteRelationshipByTermId(termId);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "termsCache", allEntries = true)
    @Override
    public void reorderTerms(List<Integer> termIds) {
        for (int i = 0; i < termIds.size(); i++) {
            termsMapper.updateTermsOrderByTermsId(Long.valueOf(termIds.get(i)), (long) (i + 1));
        }
    }

    @Cacheable(value = "termsCache", key = "'getNameBySlug' + #slug")
    @Override
    public String getNameBySlug(String slug) {
        return termsMapper.geNameBySlug(slug);
    }

}
