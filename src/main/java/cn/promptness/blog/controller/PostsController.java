package cn.promptness.blog.controller;

import cn.promptness.blog.common.constant.Constants;
import cn.promptness.blog.common.constant.enums.PostStatusEnum;
import cn.promptness.blog.exception.BizExceptionEnum;
import cn.promptness.blog.pojo.Posts;
import cn.promptness.blog.support.service.PostsService;
import cn.promptness.blog.support.service.TermsService;
import cn.promptness.blog.support.service.rpc.WeatherRpc;
import cn.promptness.blog.common.utils.AssertUtils;
import cn.promptness.blog.common.utils.HttpUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Lynn
 */
@Controller
public class PostsController {

    @Resource
    private PostsService postsService;
    @Resource
    private TermsService termsService;
    @Resource
    private WeatherRpc weatherRpc;

    /**
     * 获取天气
     */
    @ResponseBody
    @PostMapping(value = "/weather")
    public Map getWeather() throws Exception {
        return weatherRpc.getWeather(HttpUtils.getIp());
    }

    /**
     * 到首页去
     */
    @GetMapping(value = {"/index.html", "/"})
    public String toArticlePage(Model model) {
        return forPage(model, 1);
    }

    /**
     * 到站点首页
     */
    @GetMapping("/posts")
    public String forPage(Model model) {
        return forPage(model, 1);
    }

    /**
     * 到站点首页
     */
    @GetMapping("/posts/{pageNum:.+}")
    public String forPage(Model model, @PathVariable Integer pageNum) {
        PageInfo<Posts> recently = postsService.getArticlesWithTerms(PostStatusEnum.PUBLISH.getText(), 1, 10, 8);
        PageInfo<Posts> pageInfo = postsService.getArticles(PostStatusEnum.PUBLISH.getText(), pageNum, 1, 8);
        model.addAttribute(Constants.PAGE_INFO, pageInfo);
        model.addAttribute(Constants.RECENTLY_ARTICLE, recently);
        return "page/index";
    }

    /**
     * 到文章展示页面
     */
    @GetMapping("/article/{postId:.+}")
    public String toDetailPage(@PathVariable Long postId, Model model) {
        Posts posts = postsService.getArticleByPostsId(postId, PostStatusEnum.PUBLISH.getText());
        AssertUtils.notNull(posts, BizExceptionEnum.INDEX_PAGE);
        model.addAttribute(Constants.ARTICLE, posts);
        return "page/detail";
    }

    /**
     * 文章的归类显示
     */
    @GetMapping(value = "/classify/{slug:.+}")
    public String classifyArticle(@PathVariable String slug, Model model) {
        return classifyArticleForPage(slug, model, 1);
    }

    /**
     * 文章的归类显示
     */
    @GetMapping(value = "/classify/{slug:.+}/{pageNum:.+}")
    public String classifyArticleForPage(@PathVariable String slug, Model model, @PathVariable Integer pageNum) {

        PageInfo<Posts> pageInfo = postsService.getArticlesWithTermsBySlug(PostStatusEnum.PUBLISH.getText(), pageNum, 20, 8, slug);
        model.addAttribute(Constants.PAGE_INFO, pageInfo);

        String termName = termsService.getNameBySlug(slug);
        model.addAttribute(Constants.TERM_NAME, termName);

        return "page/classify";
    }

    /**
     * 模糊查询
     */
    @PostMapping(value = "/search")
    public String fuzzySearch(@RequestParam String key, Model model) {
        AssertUtils.isFalse(StringUtils.isEmpty(key), BizExceptionEnum.INDEX_PAGE);
        model.addAttribute(Constants.SEARCH_KEY, key);

        //模糊查询文章
        PageInfo<Posts> pageInfo = postsService.getArticlesWithTermsForSearch(PostStatusEnum.PUBLISH.getText(), key);
        model.addAttribute(Constants.PAGE_INFO, pageInfo);

        //最近文章
        PageInfo<Posts> recently = postsService.getArticlesWithTerms(PostStatusEnum.PUBLISH.getText(), 1, 10, 8);
        model.addAttribute(Constants.RECENTLY_ARTICLE, recently);

        return "page/index";
    }

}
