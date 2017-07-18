package com.blog.mapper;

import com.blog.pojo.Posts;
import com.blog.pojo.PostsExample;
import com.blog.pojo.PostsWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PostsMapper {
    int countByExample(PostsExample example);

    int deleteByExample(PostsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PostsWithBLOBs record);

    int insertSelective(PostsWithBLOBs record);

    List<PostsWithBLOBs> selectByExampleWithBLOBs(PostsExample example);

    List<Posts> selectByExample(PostsExample example);

    PostsWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PostsWithBLOBs record, @Param("example") PostsExample example);

    int updateByExampleWithBLOBs(@Param("record") PostsWithBLOBs record, @Param("example") PostsExample example);

    int updateByExample(@Param("record") Posts record, @Param("example") PostsExample example);

    int updateByPrimaryKeySelective(PostsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PostsWithBLOBs record);

    int updateByPrimaryKey(Posts record);

	void batchDeleteArticles(@Param("deleteId")List<Integer> deleteId);

	List<PostsWithBLOBs> selectArticlesWithTerms(@Param("postStatus")String postStatus);

	PostsWithBLOBs selectArticleByPostsIdWithTerms(int postId);

	List<PostsWithBLOBs> getArticlesWithTermsBySlug(@Param("slug")String slug,@Param("postStatus") String postStatus);

	List<PostsWithBLOBs> getArticlesWithTermsForSearch(@Param("postStatus")String postStatus, @Param("key")String key);
}