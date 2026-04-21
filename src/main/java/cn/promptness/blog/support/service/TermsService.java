package cn.promptness.blog.support.service;

import cn.promptness.blog.pojo.Terms;

import java.util.List;

public interface TermsService {

	List<Terms> getTerms();

	void saveTerms(Terms terms);

	void updateTerms(Terms terms);

	void deleteTermsById(long termId);

	void reorderTerms(List<Integer> termIds);

	String getNameBySlug(String slug);

}
