package cn.promptness.blog.support.service;

import cn.promptness.blog.pojo.Terms;

import java.util.List;

public interface TermsService {

	List<Terms> getTerms();

	void saveTerms(Terms terms);

	void updateTerms(Terms terms);

	void deleteTermsById(long termId);

	void upTerms(long parse);

	void downTerms(long parse);

	String getNameBySlug(String slug);

}
