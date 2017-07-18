package com.blog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.mapper.TermsMapper;
import com.blog.pojo.Terms;
import com.blog.pojo.TermsExample;
import com.blog.service.TermsService;

@Service
public class TermsServiceImpl implements TermsService {

	@Autowired
	TermsMapper termsMapper;

	@Override
	public List<Terms> getTerms() {
		TermsExample example = new TermsExample();
		example.setOrderByClause("term_order ASC");
		List<Terms> termsList = termsMapper.selectByExample(example);
		return termsList;
	}

	@Override
	public void saveTerms(Terms terms) {
		termsMapper.insertSelective(terms);
		termsMapper.updateTermsOrderByTermsId(terms.getTermId(),terms.getTermId());
		
	}

	@Override
	public void updateTerms(Terms terms) {
		termsMapper.updateByPrimaryKeySelective(terms);
		
	}

	@Override
	public void deleteTermsById(long parseInt) {
		termsMapper.deleteByPrimaryKey(parseInt);
	}

	@Override
	public void upTerms(long thisTermsId) {
		//1).查找当前termsId的termsOrder值
		long thisTermsOrder = termsMapper.selectTermsOrderByTermsId(thisTermsId);
		//2).根据thisTermsOrder查找需要交换的目标的id
		long targetTermsId = termsMapper.selectUpTargetTermsId(thisTermsOrder);
		//3).根据targetTermsId查找targetTerms的termsOrder
		long targetTermsOrder = termsMapper.selectTermsOrderByTermsId(targetTermsId);
		//4).更新thisTerms的termsOrder
		termsMapper.updateTermsOrderByTermsId(thisTermsId,targetTermsOrder);
		//5).更新targetTerms的termsOrder
		termsMapper.updateTermsOrderByTermsId(targetTermsId,thisTermsOrder);
		
	}

	@Override
	public void downTerms(long thisTermsId) {
		//1).查找当前termsId的termsOrder值
		long thisTermsOrder = termsMapper.selectTermsOrderByTermsId(thisTermsId);
		//2).根据thisTermsOrder查找需要交换的目标的id
		long targetTermsId = termsMapper.selectDownTargetTermsId(thisTermsOrder);
		//3).根据targetTermsId查找targetTerms的termsOrder
		long targetTermsOrder = termsMapper.selectTermsOrderByTermsId(targetTermsId);
		//4).更新thisTerms的termsOrder
		termsMapper.updateTermsOrderByTermsId(thisTermsId,targetTermsOrder);
		//5).更新targetTerms的termsOrder
		termsMapper.updateTermsOrderByTermsId(targetTermsId,thisTermsOrder);
		
	}

	@Override
	public String getNameBySlug(String slug) {
		String termName  = termsMapper.geNameBySlug(slug);
		return termName;
	}

}
