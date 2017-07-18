package com.blog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.mapper.PostsMapper;
import com.blog.mapper.TermsMapper;
import com.blog.pojo.PostsExample;
import com.blog.pojo.PostsExample.Criteria;
import com.blog.pojo.PostsWithBLOBs;
import com.blog.service.PostsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PostsServiceImpl implements PostsService{

	@Autowired
	PostsMapper postsMapper;
	
	@Autowired
	TermsMapper termsMapper;

	@Override
	public PageInfo<PostsWithBLOBs> getArticles(String postStatus, int pageNum, int pageSize, int navigationSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		PostsExample example = new PostsExample();
		example.setOrderByClause("`post_date` DESC");
		Criteria criteria = example.createCriteria();
		if (postStatus !=null) {
			criteria.andPostStatusEqualTo(postStatus);
		}
		
		List<PostsWithBLOBs> withBLOBs = postsMapper.selectByExampleWithBLOBs(example);
		PageInfo<PostsWithBLOBs> pageInfo = new PageInfo<>(withBLOBs, navigationSize);
		
		return pageInfo;
	}

	@Override
	public PostsWithBLOBs getArticleByPostsId(long parseInt, String postStatus) {
		PostsExample example = new PostsExample();
		Criteria criteria = example.createCriteria();
		if (postStatus !=null) {
			criteria.andPostStatusEqualTo(postStatus);
		}
		criteria.andIdEqualTo(parseInt);
		List<PostsWithBLOBs> withBLOBs = postsMapper.selectByExampleWithBLOBs(example);
		if (withBLOBs.isEmpty()) {
			return null;
		}
		return withBLOBs.get(0);
	}

	@Override
	public void saveArticle(PostsWithBLOBs postsWithBLOBs) {
		postsMapper.insertSelective(postsWithBLOBs);
	}

	@Override
	public void updateArticle(PostsWithBLOBs postsWithBLOBs,List<Integer> termsId) {
		
		//1).删除目录中间表的数据
		termsMapper.deleteRelationship(postsWithBLOBs.getId());
		
		//2).保存文章
		postsMapper.updateByPrimaryKeySelective(postsWithBLOBs);
		
		//3).更新目录中间表的关系
		if (termsId != null) {
			termsMapper.bathSaveTerms(termsId,postsWithBLOBs.getId());
		}
		
	}

	@Override
	public void batchDeleteArticles(List<Integer> deleteId) {
		postsMapper.batchDeleteArticles(deleteId);
		
	}

	@Override
	public void saveArticleWithTerms(PostsWithBLOBs postsWithBLOBs, List<Integer> termsId) {
		postsMapper.insertSelective(postsWithBLOBs);
		
		if (termsId != null) {
			termsMapper.bathSaveTerms(termsId,postsWithBLOBs.getId());
		}
		
	}

	@Override
	public PageInfo<PostsWithBLOBs> getArticlesWithTerms(String postStatus, int pageNum, int pageSize,int navigationSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<PostsWithBLOBs> withBLOBs = postsMapper.selectArticlesWithTerms(postStatus);
		
		PageInfo<PostsWithBLOBs> pageInfo = new PageInfo<>(withBLOBs, navigationSize);
		
		if (withBLOBs.isEmpty()) {
			return null;
		}
		
		return pageInfo;
	}

	@Override
	public PostsWithBLOBs getArticleByPostsIdWithTerms(int postId) {
		PostsWithBLOBs withBLOBs = postsMapper.selectArticleByPostsIdWithTerms(postId);
		return withBLOBs;
	}

	@Override
	public PageInfo<PostsWithBLOBs> getArticlesWithTermsBySlug(String postStatus, int pageNum, int pageSize, int navigationSize, String slug) {
		
		PageHelper.startPage(pageNum, pageSize);
		List<PostsWithBLOBs> withBLOBs  = postsMapper.getArticlesWithTermsBySlug(slug,postStatus);
		
		if (withBLOBs.isEmpty()) {
			return null;
		}
		
		PageInfo<PostsWithBLOBs> pageInfo = new PageInfo<>(withBLOBs, navigationSize);
		return pageInfo;
	}

	@Override
	public PageInfo<PostsWithBLOBs> getArticlesWithTermsForSearch(String postStatus,String key) {
		List<PostsWithBLOBs> withTerms = postsMapper.getArticlesWithTermsForSearch(postStatus,key);
		PageInfo<PostsWithBLOBs>pageInfo = new PageInfo<>(withTerms);
		return pageInfo;
	}
	
	
}
