package cn.promptness.blog.mapper;

import cn.promptness.blog.pojo.Posts;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostsMapper {

    int insertSelective(Posts record);

    int updateByPrimaryKeySelective(Posts record);

    int batchDeletePosts(@Param("deleteId") List<Integer> deleteId);

    List<Posts> getPostsWithTermsBySlug(@Param("slug") String slug, @Param("postStatus") String postStatus);

    List<Posts> getPostsWithTermsForSearch(@Param("postStatus") String postStatus, @Param("key") String key);

    List<Posts> listPostsByStatusWithoutTerms(@Param("postStatus") String postStatus);

    Posts getPostsWithoutTerms(@Param("postId") long postId, @Param("postStatus") String postStatus);

    Posts getPostsByPostsIdWithTermsAndContent(@Param("postId") Integer postId);

    List<Posts> listPostsWithTermsWithoutContent(@Param("postStatus") String postStatus);
}