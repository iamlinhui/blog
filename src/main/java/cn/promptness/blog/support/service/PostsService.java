package cn.promptness.blog.support.service;

import cn.promptness.blog.pojo.Posts;
import cn.promptness.blog.support.vo.PostsVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PostsService {

    PageInfo<Posts> getArticles(String postStatus, int pageNum, int pageSize, int navigationSize);

    Posts getArticleByPostsId(long parseInt, String postStatus);

    void saveArticle(PostsVO postsVO);

    void updateArticle(PostsVO postsVO);

    void batchDeleteArticles(List<Integer> deleteId);

    PageInfo<Posts> getArticlesWithTerms(String postStatus, int pageNum, int pageSize, int navigationSize);

    PageInfo<Posts> getArticlesWithTermsBySlug(String postStatus, int pageNum, int pageSize, int navigationSize, String slug);

    PageInfo<Posts> getArticlesWithTermsForSearch(String postStatus, String key);

    Posts getArticleByPostsIdWithTermsAndContent(Integer postsId);
}
