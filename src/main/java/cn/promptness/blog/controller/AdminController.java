package cn.promptness.blog.controller;

import cn.promptness.blog.common.constant.Constants;
import cn.promptness.blog.common.utils.AssertUtils;
import cn.promptness.blog.common.utils.QiniuUtils;
import cn.promptness.blog.common.utils.SnowflakeIdUtils;
import cn.promptness.blog.config.properties.QiniuProperties;
import cn.promptness.blog.exception.BizExceptionEnum;
import cn.promptness.blog.pojo.Posts;
import cn.promptness.blog.pojo.Terms;
import cn.promptness.blog.pojo.Users;
import cn.promptness.blog.support.service.PostsService;
import cn.promptness.blog.support.service.TermsService;
import cn.promptness.blog.support.service.UserService;
import cn.promptness.blog.vo.HttpResult;
import cn.promptness.blog.vo.PostsVO;
import cn.promptness.blog.vo.UploadVO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Lynn
 */
@Controller
@RequestMapping(value = "/admin")
@Slf4j
public class AdminController {

    @Resource
    private PostsService postsService;
    @Resource
    private TermsService termsService;
    @Resource
    private UserService userService;
    @Resource
    private QiniuProperties qiniuProperties;
    @Resource
    private QiniuUtils qiniuUtils;

    /**
     * 到后台页面
     */
    @GetMapping(value = "/article")
    public String toAdminPage(Model model) {
        return toArticlePage(model, 1);
    }

    /**
     * 到创建文章页面
     */
    @GetMapping(value = "/create")
    public String creatArticle() {
        return "admin/create";
    }

    /**
     * 到修改文章页面
     */
    @GetMapping(value = "/edit/{postsId:.+}")
    public String editArticle(Model model, @PathVariable Integer postsId) {

        Posts article = postsService.getArticleByPostsIdWithTermsAndContent(postsId);
        AssertUtils.notNull(article, BizExceptionEnum.ADMIN_PAGE);
        model.addAttribute(Constants.ARTICLE, article);

        //回显分类选项
        List<Long> checkTermIdList = article.getTerm().stream().map(Terms::getTermId).collect(Collectors.toList());
        //存在取出缓存的列表
        List<Terms> terms = termsService.getTerms();
        terms.forEach(x -> x.setChecked(checkTermIdList.contains(x.getTermId())));
        model.addAttribute(Constants.TERM_NAME, terms);

        return "admin/edit";
    }

    /**
     * 展示所有文章
     */
    @GetMapping(value = "/article/{pageNum:.+}")
    public String toArticlePage(Model model, @PathVariable Integer pageNum) {
        //不带文章内容的查询
        PageInfo<Posts> pageInfo = postsService.getArticlesWithTerms(null, pageNum, 20, 8);
        model.addAttribute(Constants.PAGE_INFO, pageInfo);
        return "admin/articles";
    }

    /**
     * 保存新建的文章
     */
    @PostMapping(value = "/create")
    public String saveArticle(PostsVO posts) {
        postsService.saveArticle(posts);
        return "redirect:/admin/article";
    }

    /**
     * 保存修改的文章
     */
    @PostMapping(value = "/edit")
    public String updateArticle(PostsVO posts) {
        postsService.updateArticle(posts);
        return "redirect:/admin/article";
    }

    /**
     * 批量删除文章
     */
    @PostMapping(value = "/delete")
    public String deleteArticles(PostsVO posts) {
        List<Integer> deleteIdList = posts.getDelete();
        if (!CollectionUtils.isEmpty(deleteIdList)) {
            postsService.batchDeleteArticles(deleteIdList);
        }
        if (StringUtils.isEmpty(posts.getPageNum())) {
            return "redirect:/admin/article";
        }
        return "redirect:/admin/article/" + posts.getPageNum();
    }

    /**
     * 到管理用户页面
     */
    @GetMapping(value = "/user")
    public String toManageUserPage(Model model) {
        List<Users> users = userService.getAllUsers();
        model.addAttribute(Constants.USER_LIST, users);
        return "admin/user";
    }

    /**
     * 修改用户信息
     */
    @PostMapping(value = "/user")
    public String updateUser(Users users) {
        userService.updateUser(users);
        return "redirect:/admin/user";
    }


    @ResponseBody
    @PostMapping(value = "/upload")
    public UploadVO upload(@RequestParam(value = "editormd-image-file") MultipartFile multipartFile) {
        try {
            String fileName = SnowflakeIdUtils.nextId() + Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            IOUtils.copy(multipartFile.getInputStream(), byteArrayOutputStream);
            boolean upload = qiniuUtils.upload(byteArrayOutputStream.toByteArray(), fileName);
            AssertUtils.isTrue(upload, BizExceptionEnum.SERVER_ERROR);
            return UploadVO.success(qiniuProperties.getImageHost() + fileName);
        } catch (Exception e) {
            log.error("上传文件失败,{}", e.getMessage());
        }
        return UploadVO.fail();
    }

    @RequestMapping(value = "/continue", method = RequestMethod.OPTIONS)
    @ResponseBody
    public HttpResult continueSession() {
        return HttpResult.SUCCESS;
    }
}
