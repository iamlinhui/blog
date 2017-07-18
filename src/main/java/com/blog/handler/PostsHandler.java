package com.blog.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.pojo.PostsWithBLOBs;
import com.blog.pojo.Terms;
import com.blog.service.OptionsService;
import com.blog.service.PostsService;
import com.blog.service.TermsService;
import com.blog.utils.GlobalString;
import com.github.pagehelper.PageInfo;

@Controller
public class PostsHandler {
	
	@Autowired
	PostsService postsService;
	
	@Autowired
	TermsService termsService;
	
	@Autowired
	OptionsService optionsService;
	
	/**
	 * 到首页去
	 * 
	 * @return
	 */
	@RequestMapping("/index.html")
	public String toArticlePage(Model model) {
		
 		// 需要从数据库中查出来
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String subtitle = optionsService.getOption(GlobalString.SUB_TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.SUB_TITLE, subtitle);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		int pageNum = 1,pageSize = 10,navigationSize = 8;
		PageInfo<PostsWithBLOBs> recently = postsService.getArticlesWithTerms("publish",pageNum,pageSize,navigationSize);
		
		PageInfo<PostsWithBLOBs> pageInfo = postsService.getArticles("publish", pageNum, 1, navigationSize);
		
		model.addAttribute(GlobalString.PAGE_INFO, pageInfo);
		model.addAttribute(GlobalString.RECENTLY_ARTICLE, recently);
		
		// 文章导航栏
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		List<Terms> terms = termsService.getTerms();
		model.addAttribute(GlobalString.TERMS, terms);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		
		return "page/index";
	}
	
	/**
	 * 到站点首页
	 */
	@RequestMapping("/posts/{pageNum}")
	public String forPage(Model model,@PathVariable("pageNum")String pageNum) {
		
		int parseInt = 1;
		try {
			parseInt = Integer.parseInt(pageNum);
		} catch (NumberFormatException e) {
			return "redirect:/index.html";
		}
		
		int page = 1, pageSize = 10,navigationSize = 8;
		PageInfo<PostsWithBLOBs> recently = postsService.getArticlesWithTerms("publish",page,pageSize,navigationSize);

		PageInfo<PostsWithBLOBs> pageInfo = postsService.getArticles("publish", parseInt, 1, navigationSize);
		
		model.addAttribute(GlobalString.PAGE_INFO, pageInfo);
		
		model.addAttribute(GlobalString.RECENTLY_ARTICLE, recently);
		
 		// 需要从数据库中查出来
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String subtitle = optionsService.getOption(GlobalString.SUB_TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.SUB_TITLE, subtitle);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		
		//文章导航栏
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		List<Terms> terms = termsService.getTerms();
		model.addAttribute(GlobalString.TERMS, terms);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		return "page/index";
	}
	
	/**
	 * 到文章展示页面
	 * 
	 * @param postId
	 * @return
	 */
	@RequestMapping("/article/{postId}")
	public String toDetailPage(@PathVariable("postId")String postId,Model model) {
		long parseLong = 1;
		
		
		try {
			parseLong = Long.parseLong(postId);
		} catch (NumberFormatException e) {
			return "redirect:/index.html";
		}
		
		PostsWithBLOBs postsWithBLOBs = postsService.getArticleByPostsId(parseLong,"publish");
		if (postsWithBLOBs == null) {
			return "redirect:/index.html";
		}
		model.addAttribute(GlobalString.ARTICLE, postsWithBLOBs);
		
 		// 需要从数据库中查出来
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String subtitle = optionsService.getOption(GlobalString.SUB_TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);
		String commentId = optionsService.getOption(GlobalString.COMMENT_ID);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.SUB_TITLE, subtitle);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		model.addAttribute(GlobalString.COMMENT_ID, commentId);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		// 文章导航栏
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		List<Terms> terms = termsService.getTerms();
		model.addAttribute(GlobalString.TERMS, terms);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		return "page/detail";
	}
	
	/**
	 * 文章的归类显示
	 */
	@RequestMapping(value="/classify/{slug}",method=RequestMethod.GET)
	public String classifyArticle(@PathVariable("slug")String slug,Model model) {
		
		PageInfo<PostsWithBLOBs> pageInfo = postsService.getArticlesWithTermsBySlug("publish", 1, 20, 8,slug);
//		if (pageInfo == null) {
//			return "redirect:/index.html";
//		}
		
		model.addAttribute(GlobalString.PAGE_INFO, pageInfo);
		
		
 		// 需要从数据库中查出来
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String subtitle = optionsService.getOption(GlobalString.SUB_TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.SUB_TITLE, subtitle);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		// 文章导航栏
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		List<Terms> terms = termsService.getTerms();
		model.addAttribute(GlobalString.TERMS, terms);
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		String termName = termsService.getNameBySlug(slug);
		model.addAttribute(GlobalString.TERM_NAME, termName);
		
		
		return "page/classify";
	}
	
	/**
	 * 文章的归类显示
	 */
	@RequestMapping(value="/classify/{slug}/{pageNum}",method=RequestMethod.GET)
	public String classifyArticleForPage(@PathVariable("slug")String slug,Model model,
			@PathVariable("pageNum")String pageNum) {
		
		int parseInt = 1;
		try {
			parseInt = Integer.parseInt(pageNum);
		} catch (NumberFormatException e) {
			return "redirect:/classify"+slug;
		}
		
		PageInfo<PostsWithBLOBs> pageInfo = postsService.getArticlesWithTermsBySlug("publish", parseInt, 20, 8,slug);
//		if (pageInfo == null) {
//			return "redirect:/index.html";
//		}
		
		model.addAttribute(GlobalString.PAGE_INFO, pageInfo);
		
		
 		// 需要从数据库中查出来
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String subtitle = optionsService.getOption(GlobalString.SUB_TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.SUB_TITLE, subtitle);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		// 文章导航栏
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		List<Terms> terms = termsService.getTerms();
		model.addAttribute(GlobalString.TERMS, terms);
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		String termName = termsService.getNameBySlug(slug);
		model.addAttribute(GlobalString.TERM_NAME, termName);
		
		
		return "page/classify";
	}
	
	/**
	 * 模糊查询
	 */
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public String fuzzySearch(@RequestParam(value="key",required=false)String key,Model model) {
		
		
		if (key == null) {
			return "redirect:/index.html";
		}
		//System.out.println(key);
		model.addAttribute(GlobalString.SEARCH_KEY, key);
		
		
		//模糊查询文章
		PageInfo<PostsWithBLOBs>pageInfo = postsService.getArticlesWithTermsForSearch("publish","%"+key+"%");
		model.addAttribute(GlobalString.PAGE_INFO, pageInfo);
		
		//最近文章
		int pageNum = 1,pageSize = 10,navigationSize = 8;
		PageInfo<PostsWithBLOBs> recently = postsService.getArticlesWithTerms("publish",pageNum,pageSize,navigationSize);
		model.addAttribute(GlobalString.RECENTLY_ARTICLE, recently);
		
		// 需要从数据库中查出来
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String subtitle = optionsService.getOption(GlobalString.SUB_TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.SUB_TITLE, subtitle);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		// 文章导航栏
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		List<Terms> terms = termsService.getTerms();
		model.addAttribute(GlobalString.TERMS, terms);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		return "page/index";
		
	}

}
