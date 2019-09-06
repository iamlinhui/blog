package cn.promptness.blog.support.service.impl;

import cn.promptness.blog.mapper.PostsMapper;
import cn.promptness.blog.mapper.TermsMapper;
import cn.promptness.blog.pojo.Posts;
import cn.promptness.blog.pojo.Users;
import cn.promptness.blog.support.service.PostsService;
import cn.promptness.blog.support.utils.BindUtils;
import cn.promptness.blog.support.vo.PostsVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Lynn
 */
@Service
public class PostsServiceImpl implements PostsService {

    @Resource
    private PostsMapper postsMapper;

    @Resource
    private TermsMapper termsMapper;

    @Cacheable(value = "postsCache", key = "'getArticles' + #pageNum")
    @Override
    public PageInfo<Posts> getArticles(String postStatus, int pageNum, int pageSize, int navigationSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Posts> withBlows = postsMapper.listPostsByStatusWithoutTerms(postStatus);
        return new PageInfo<>(withBlows, navigationSize);
    }

    @Cacheable(value = "postsCache", key = "'getArticleByPostsId' + #postId + #postStatus")
    @Override
    public Posts getArticleByPostsId(long postId, String postStatus) {
        return postsMapper.getPostsWithoutTerms(postId, postStatus);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "postsCache", allEntries = true)
    @Override
    public void saveArticle(PostsVO postsVO) {

        Users user = BindUtils.getUser();

        Posts posts = buildPosts(postsVO, user);
        postsMapper.insertSelective(posts);

        List<Integer> termIdList = postsVO.getTermId();
        if (!CollectionUtils.isEmpty(termIdList)) {
            termsMapper.bathSaveTerms(termIdList, posts.getId());
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "postsCache", allEntries = true)
    @Override
    public void updateArticle(PostsVO postsVO) {

        //1).删除目录中间表的数据
        termsMapper.deleteRelationship(postsVO.getId());

        //2).更新文章
        Users user = BindUtils.getUser();
        Posts posts = buildPosts(postsVO, user);
        posts.setId(postsVO.getId());
        postsMapper.updateByPrimaryKeySelective(posts);

        //3).更新目录中间表的关系
        if (!CollectionUtils.isEmpty(postsVO.getTermId())) {
            termsMapper.bathSaveTerms(postsVO.getTermId(), postsVO.getId());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "postsCache", allEntries = true)
    @Override
    public void batchDeleteArticles(List<Integer> deleteId) {
        postsMapper.batchDeletePosts(deleteId);
    }

    @Cacheable(value = "postsCache", key = "'getArticlesWithTerms' + #postStatus + #pageNum")
    @Override
    public PageInfo<Posts> getArticlesWithTerms(String postStatus, int pageNum, int pageSize, int navigationSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Posts> withBlows = postsMapper.listPostsWithTermsWithoutContent(postStatus);
        return new PageInfo<>(withBlows, navigationSize);
    }

    @Cacheable(value = "postsCache", key = "'getArticleByPostsIdWithTermsAndContent' + #postId")
    @Override
    public Posts getArticleByPostsIdWithTermsAndContent(Integer postId) {
        return postsMapper.getPostsByPostsIdWithTermsAndContent(postId);
    }

    @Cacheable(value = "postsCache", key = "'getArticlesWithTermsBySlug' + #postStatus + #pageNum + #slug")
    @Override
    public PageInfo<Posts> getArticlesWithTermsBySlug(String postStatus, int pageNum, int pageSize, int navigationSize, String slug) {
        PageHelper.startPage(pageNum, pageSize);
        List<Posts> withBlows = postsMapper.getPostsWithTermsBySlug(slug, postStatus);
        return new PageInfo<>(withBlows, navigationSize);
    }

    @Override
    public PageInfo<Posts> getArticlesWithTermsForSearch(String postStatus, String key) {
        List<Posts> withTerms = postsMapper.getPostsWithTermsForSearchV2(postStatus, key);
        return new PageInfo<>(withTerms);
    }

    private Posts buildPosts(PostsVO postsVO, Users user) {
        Posts posts = new Posts();
        posts.setPostAuthor(user.getId());
        posts.setPostDate(new Date());
        posts.setPostStatus(StringUtils.isEmpty(postsVO.getPublish()) ? "draft" : "publish");
        posts.setCommentStatus(postsVO.getCommentStatus() ? "open" : "close");
        posts.setPostName(user.getUserNicename());
        posts.setPostContent(postsVO.getPostContent());
        posts.setPostTitle(postsVO.getPostTitle());
        posts.setPostExcerpt(postsVO.getPostExcerpt());
        return posts;
    }

}
