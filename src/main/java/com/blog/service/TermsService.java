package com.blog.service;

import java.util.List;

import com.blog.pojo.Terms;

public interface TermsService {

	public List<Terms> getTerms();

	public void saveTerms(Terms terms);

	public void updateTerms(Terms terms);

	public void deleteTermsById(long parse);

	public void upTerms(long parse);

	public void downTerms(long parse);

	public String getNameBySlug(String slug);

}
