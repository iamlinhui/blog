package com.blog.handler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.blog.service.OptionsService;
import com.blog.utils.GlobalString;
import com.blog.utils.StringUtil;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class OptionsHandler {

	@Autowired
	OptionsService optionsService;

	/**
	 * 到站点设置页面
	 */
	@RequestMapping(value="/admin/website",method=RequestMethod.GET)
	public String toWebsitePage(Model model) {

		// 需要从数据库中查出来
		String title = optionsService.getOption(GlobalString.TITLE);
		String subtitle = optionsService.getOption(GlobalString.SUB_TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);
		String userStatus = optionsService.getOption(GlobalString.USER_STATUS);
		String commentId = optionsService.getOption(GlobalString.COMMENT_ID);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.SUB_TITLE, subtitle);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		model.addAttribute(GlobalString.USER_STATUS, userStatus);
		model.addAttribute(GlobalString.COMMENT_ID, commentId);

		return "admin/website";
	}

	/**
	 * 站点(信息保存)图标上传
	 * @throws IOException 
	 */
	@RequestMapping(value="/admin/website",method=RequestMethod.POST)
	public String saveOptions(@RequestParam("file") MultipartFile[] file, HttpSession session,
			@RequestParam(value="title",required=false)String title,
			@RequestParam(value="subtitle",required=false)String subtitle,
			@RequestParam(value="userStatus",required=false)String userStatus,
			@RequestParam(value="commentId",required=false)String commentId) throws IOException {
		//保存options的key-value
		Map<String, String> options = new HashMap<>();
		
		if (title!=null) {
			options.put(GlobalString.TITLE, title);
		}
		if (subtitle!=null) {
			options.put(GlobalString.SUB_TITLE, subtitle);
		}
		if (userStatus!=null) {
			options.put(GlobalString.USER_STATUS, userStatus);
		}
		if (commentId!=null) {
			options.put(GlobalString.COMMENT_ID, commentId);
		}
		
		
		for (MultipartFile multipartFile : file) {
			if (!multipartFile.isEmpty()) {
				// 1).获取文件名
				String filename = StringUtil.getStringTime()+".jpg";
				// 2).获取工程的真实路径
				String realPath = session.getServletContext().getRealPath("/static/img");
				// 3).保存文件
				InputStream inputStream = multipartFile.getInputStream();
				File toPic = new File(realPath+"/"+filename);
				//System.out.println(realPath+"/"+filename);
				Thumbnails.of(inputStream).forceSize(32,32).toFile(toPic);
				//4).保存信息
				options.put(GlobalString.LOGO_PATH, filename);
			}

		}
		//D:\mytomcat_temp\wtpwebapps\Blog\static\img/201707131727270589.jpg
		//{subtitle=还有诗和远方的田野, logoPath=201707131727270589.jpg, title=生活不止眼前的苟且}
//		System.out.println(options);
		if (!options.isEmpty()) {
			optionsService.bathSaveOptions(options);
		}

		return "redirect:/admin/website";
	}

}
