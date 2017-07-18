package com.blog.handler;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.pojo.PostsWithBLOBs;
import com.blog.pojo.Terms;
import com.blog.pojo.Users;
import com.blog.service.OptionsService;
import com.blog.service.PostsService;
import com.blog.service.TermsService;
import com.blog.service.UserService;
import com.blog.utils.GlobalString;
import com.github.pagehelper.PageInfo;

@Controller
public class AdminHandler {
	
	@Autowired
	PostsService postsService;
	
	@Autowired
	TermsService termsService;	
	
	@Autowired
	OptionsService optionsService;
	
	@Autowired
	UserService userService;

	/**
	 * 到后台页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin")
	public String toAdminPage(Model model) {
 		// 需要从数据库中查出来
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		return "admin/index";
	}
	
	/**
	 * 到创建文章页面
	 */
	@RequestMapping(value="/admin/create",method=RequestMethod.GET)
	public String creatArticle(Model model) {
		
 		// 需要从数据库中查出来
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		// 文章导航栏
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		List<Terms> terms = termsService.getTerms();
		model.addAttribute(GlobalString.TERMS, terms);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		return "admin/create";
	}
	
	/**
	 * 到修改文章页面
	 */
	@RequestMapping(value="admin/edit/{postsId}",method=RequestMethod.GET)
	public String editArticle(Model model,@PathVariable("postsId")String postsId) {
		int parseInt = 1;
		
		try {
			parseInt = Integer.parseInt(postsId);
		} catch (NumberFormatException e) {
			return "redirect:/admin/article/1";
		}
		PostsWithBLOBs article = postsService.getArticleByPostsIdWithTerms(parseInt);
		
		if (article == null) {
			return "redirect:/admin/article/1";
		}
		model.addAttribute(GlobalString.ARTICLE, article);
		
 		// 需要从数据库中查出来
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		// 文章导航栏
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		List<Terms> terms = termsService.getTerms();
		model.addAttribute(GlobalString.TERMS, terms);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		//回显分类选项
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		List<Terms> termSelected = article.getTerm();
		for (Terms term : termSelected) {
			for (int i = 0; i < terms.size(); i++) {
				if (term.getTermId() == terms.get(i).getTermId()) {
					terms.get(i).setChecked(true);
				}
			}
		}
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		return "admin/edit";
	}
	
	/**
	 * 展示所有文章
	 * 
	 * @param model
	 * @param pageNum
	 * @return
	 */
	@RequestMapping(value = "admin/article/{pageNum}", method = RequestMethod.GET)
	public String toArticlePage(Model model,@PathVariable("pageNum")String pageNum) {
		
		int parseInt = 1;
		try {
			parseInt = Integer.parseInt(pageNum);
		} catch (NumberFormatException e) {
			return "redirect:/admin/article/1";
		}
		
 		// 需要从数据库中查出来
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		int pageSize = 20,navigationSize = 8;
		//不带文章内容的查询
		PageInfo<PostsWithBLOBs> pageInfo = postsService.getArticlesWithTerms(null, parseInt, pageSize, navigationSize);
		//(null,parseInt,pageSize,navigationSize);
		
		model.addAttribute(GlobalString.PAGE_INFO, pageInfo);
		
		return "admin/articles";
	}
	
	/**
	 * 保存新建的文章
	 * 
	 */
	@RequestMapping(value="admin/create",method=RequestMethod.POST)
	public String saveArticle(Model model,PostsWithBLOBs postsWithBLOBs,
			HttpServletRequest request,HttpSession session,
			@RequestParam(value="termId",required=false)List<Integer>termsId) {
		//1).获取全部的请求参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		if (parameterMap.containsKey("publish")) {
			postsWithBLOBs.setPostStatus("publish");
		}
		if (parameterMap.containsKey("draft")) {
			postsWithBLOBs.setPostStatus("draft");
		}
		Users user = (Users) session.getAttribute(GlobalString.USER);
		if (user == null) {
			return "redirect:/login";
		}
		//保存文章
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		postsWithBLOBs.setPostDate(new Date());
		postsWithBLOBs.setPostAuthor(user.getId());
		if(postsWithBLOBs.getCommentStatus() == null) {
			postsWithBLOBs.setCommentStatus("close");
		}else {
			postsWithBLOBs.setCommentStatus("open");
		}
		//判断是否有分类信息
		if (termsId == null) {
			postsService.saveArticle(postsWithBLOBs);
		}else {
			postsService.saveArticleWithTerms(postsWithBLOBs,termsId);
;		}
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		return "redirect:/admin/article/1";
	}

	/**
	 * 保存修改的文章
	 * 
	 */
	@RequestMapping(value="admin/edit",method=RequestMethod.POST)
	public String updateArticle(Model model,PostsWithBLOBs postsWithBLOBs,
			HttpServletRequest request,HttpSession session,
			@RequestParam(value="termId",required=false)List<Integer>termsId) {
		//1).获取全部的请求参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		if (parameterMap.containsKey("publish")) {
			postsWithBLOBs.setPostStatus("publish");
		}
		if (parameterMap.containsKey("draft")) {
			postsWithBLOBs.setPostStatus("draft");
		}
		Users user = (Users) session.getAttribute(GlobalString.USER);
		if (user == null) {
			return "redirect:/login";
		}
		//保存文章
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		//postsWithBLOBs.setPostDate(new Date());
		postsWithBLOBs.setPostAuthor(user.getId());
		if(postsWithBLOBs.getCommentStatus() == null) {
			postsWithBLOBs.setCommentStatus("close");
		}else {
			postsWithBLOBs.setCommentStatus("open");
		}
		postsService.updateArticle(postsWithBLOBs,termsId);
		
		
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		return "redirect:/admin/article/1";
	}
	
	/**
	 * 批量删除文章
	 */
	@RequestMapping(value="/admin/delete",method=RequestMethod.POST)
	public String deleteArticles(@RequestParam(value="delete",required=false)List<Integer> deleteId,
			@RequestParam("pageNum")Integer pageNum) {
		
		if (deleteId !=null) {
			postsService.batchDeleteArticles(deleteId);
		}
		
		
		return "redirect:/admin/article/"+pageNum;
	}
	
	/**
	 * 到管理用户页面
	 */
	@RequestMapping(value="/admin/user",method=RequestMethod.GET)
	public String toManageUserPage(Model model) {
		
		// 需要从数据库中查出来
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);
		
		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		List<Users> users = userService.getAllUsers();
		model.addAttribute(GlobalString.USER_LIST, users);
		
		return "admin/user";
	}
	
	/**
	 * 修改用户信息
	 */
	@RequestMapping(value="/admin/user",method=RequestMethod.POST)
	public String updateUser(Model model,Users users) {
		
		
		userService.updateUser(users);
		
		
		return "redirect:/admin/user";
		
	}

}
