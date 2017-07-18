package com.blog.service;

import java.util.List;

import com.blog.pojo.PostsWithBLOBs;
import com.github.pagehelper.PageInfo;

public interface PostsService {

	PageInfo<PostsWithBLOBs> getArticles(String postStatus, int pageNum, int pageSize, int navigationSize);

	PostsWithBLOBs getArticleByPostsId(long parseInt, String postStatus);

	void saveArticle(PostsWithBLOBs postsWithBLOBs);

	void updateArticle(PostsWithBLOBs postsWithBLOBs,List<Integer> termsId);

	void batchDeleteArticles(List<Integer> deleteId);

	void saveArticleWithTerms(PostsWithBLOBs postsWithBLOBs, List<Integer> termsId);

	PageInfo<PostsWithBLOBs> getArticlesWithTerms(String postStatus, int pageNum, int pageSize, int navigationSize);

	PostsWithBLOBs getArticleByPostsIdWithTerms(int postId);

	PageInfo<PostsWithBLOBs> getArticlesWithTermsBySlug(String postStatus, int pageNum, int pageSize, int navigationSize, String slug);

	PageInfo<PostsWithBLOBs> getArticlesWithTermsForSearch(String postStatus,String key);

}
