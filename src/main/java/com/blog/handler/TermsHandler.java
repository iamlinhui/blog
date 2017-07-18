package com.blog.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.pojo.Terms;
import com.blog.service.OptionsService;
import com.blog.service.TermsService;
import com.blog.utils.GlobalString;

@Controller
public class TermsHandler {
	
	@Autowired
	TermsService termsService;
	@Autowired
	OptionsService optionsService;
	
	/**
	 * 到导航栏设置页面
	 */
	@RequestMapping(value="/admin/navigation",method=RequestMethod.GET)
	public String toAdminNavigation(Model model) {
		
 		// 需要从数据库中查出来
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		//查询导航栏
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		List<Terms> terms = termsService.getTerms();
		model.addAttribute(GlobalString.TERMS, terms);
		
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		return "admin/navigation";
	}
	
	/**
	 * 保存或者修改导航栏
	 */
	@RequestMapping(value="/admin/navigation",method=RequestMethod.POST)
	public String addOrUpdateTerms(Terms terms) {
		
		if (terms.getTermId() == null) {
			termsService.saveTerms(terms);
		}else {
			termsService.updateTerms(terms);
		}
		
		return "redirect:/admin/navigation";
	}
	
	/**
	 * 删除导航项
	 */
	@RequestMapping(value="/admin/delete",method=RequestMethod.GET)
	public String deleteTerms(@RequestParam("termId")String termId) {
		long parse = 1;
		try {
			parse = Integer.parseInt(termId);
		} catch (NumberFormatException e) {
			return "redirect:/admin/navigation";
		}
		termsService.deleteTermsById(parse);
		
		return "redirect:/admin/navigation";
	}
	
	/**
	 * 上移导航项
	 */
	@RequestMapping(value="/admin/up/{termId}")
	public String upTerms(@PathVariable("termId")String termId) {
		long parse = 1;
		try {
			parse = Integer.parseInt(termId);
		} catch (NumberFormatException e) {
			return "redirect:/admin/navigation";
		}
		termsService.upTerms(parse);
		
		return "redirect:/admin/navigation";
	}
	
	/**
	 * 下移导航项
	 */
	@RequestMapping(value="/admin/down/{termId}")
	public String downTerms(@PathVariable("termId")String termId) {
		long parse = 1;
		try {
			parse = Integer.parseInt(termId);
		} catch (NumberFormatException e) {
			return "redirect:/admin/navigation";
		}
		termsService.downTerms(parse);
		
		return "redirect:/admin/navigation";
	}

}
