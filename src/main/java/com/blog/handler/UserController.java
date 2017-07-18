package com.blog.handler;


import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.exception.ForgetPageEmailIsExistExcepion;
import com.blog.exception.ForgetPageErrorEmailExcepion;
import com.blog.exception.NoUserLoginException;
import com.blog.exception.RegistPageEmailErrorExcepion;
import com.blog.exception.RegistPageEmailIsExistExcepion;
import com.blog.exception.RegistPageUserNameIsExistExcepion;
import com.blog.exception.UserLoginErrorExcepion;
import com.blog.pojo.Users;
import com.blog.service.OptionsService;
import com.blog.service.UserService;
import com.blog.utils.GlobalString;
import com.blog.utils.SendMailUtil;
import com.blog.utils.StringUtil;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	OptionsService optionsService;
	
	@Autowired
	SendMailUtil sendMailUtil;
	
	/**
	 * 到登陆页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String toLoginPage(Model model) {
		// 需要从数据库中查出来
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		return "page/login";
	}
	
	/**
	 * 到注册页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/regist",method=RequestMethod.GET)
	public String toRegistPage(Model model) {
		// 需要从数据库中查出来
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		return "page/regist";
	}
	
	/**
	 * 到找回密码页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/forget",method=RequestMethod.GET)
	public String toForgetPage(Model model) {
		// 需要从数据库中查出来
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		return "page/forget";
	}
	

	/**
	 * 登陆账号
	 * 
	 * @param loginName
	 * @param password
	 * @param remember
	 * @param model
	 * @param session
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("loginName")String loginName,
			@RequestParam("loginPassword")String password,
			@RequestParam(value = "remember", required = false) boolean remember,
			Model model,HttpSession session,
			HttpServletResponse response,HttpServletRequest request) {
		
//		System.out.println(remember);
		Users login_user = new Users();
		login_user.setUserPass(password);
		//登陆后的User对象
		Users users = null;
		//校验用户登陆信息
		if (StringUtil.isEmail(loginName)) {
			//匹配为邮箱登录
			login_user.setUserEmail(loginName);
			users = userService.login(login_user);
		} else {
			//匹配为用户名登陆
			login_user.setUserLogin(loginName);
			users = userService.login(login_user);	
		}
		
		if (users == null) {
			// 需要从数据库中查出来
			//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			String title = optionsService.getOption(GlobalString.TITLE);
			request.setAttribute(GlobalString.TITLE, title);
			//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			throw new UserLoginErrorExcepion(GlobalString.INVALID_USER_NAME);
		}
		model.addAttribute(GlobalString.USER, users);
		if (remember) {
			//保存登录信息的JSESSIONID到cookie中
			Cookie cookie = new Cookie("JSESSIONID", session.getId());
			cookie.setMaxAge(60*60*12);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		}
		session.setAttribute(GlobalString.USER, users);
		
		switch (users.getUserStatus()) {
		case 0:
			return "redirect:admin";
		default:
			return "redirect:index.html";
		}
		
	}
	
	/**
	 * 注册用户
	 * 
	 * @param username
	 * @param email
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public String regist(@RequestParam("username")String username,
			@RequestParam("email")String email,
			Model model,HttpServletRequest request) {
		boolean isEmail = StringUtil.isEmail(email);
		if (!isEmail) {
			// 需要从数据库中查出来
			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			String title = optionsService.getOption(GlobalString.TITLE);
			request.setAttribute(GlobalString.TITLE, title);
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			throw new RegistPageEmailErrorExcepion(GlobalString.ERROR_EMAIL);
		}
		
		//检测用户名和邮箱是否已经存在
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		boolean usernameIsExist = userService.usernameIsExist(username);
		if (usernameIsExist) {
			// 需要从数据库中查出来
			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			String title = optionsService.getOption(GlobalString.TITLE);
			request.setAttribute(GlobalString.TITLE, title);
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			throw new RegistPageUserNameIsExistExcepion(GlobalString.USER_NAME_IS_EXIST);
		}
		
		boolean emailIsExist = userService.emailIsExist(email);
		if (emailIsExist) {
			// 需要从数据库中查出来
			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			String title = optionsService.getOption(GlobalString.TITLE);
			request.setAttribute(GlobalString.TITLE, title);
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			throw new RegistPageEmailIsExistExcepion(GlobalString.EMAIL_IS_EXIST);
		}
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		
		
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		//1).生成密码
		String send_password = UUID.randomUUID().toString().split("-")[0];
		
		//2).发送密码到邮箱
		new Thread(new Runnable() {
			@Override
			public void run() {
				sendMailUtil.sendEmail(email, send_password);
			}
		}).start();
		
		
		
		//3).保存账户信息
		Users users = new Users();
		users.setUserLogin(username);
		users.setUserEmail(email);
		users.setUserPass(StringUtil.encipher(send_password));
		users.setUserNicename(username);
		users.setUserRegistered(new Date());
		//从数据库中查;当前注册用户的角色
		String userStatus = optionsService.getOption(GlobalString.USER_STATUS);
		if (userStatus == null) {
			users.setUserStatus(0);
		}else {
			users.setUserStatus(Integer.parseInt(userStatus));
		}
		userService.regist(users);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		return "redirect:/login";
	}
	
	/**
	 * 找回密码
	 * 
	 * @param email
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/forget",method=RequestMethod.POST)
	public String forget(@RequestParam("email")String email,HttpServletRequest request) {
		boolean isEmail = StringUtil.isEmail(email);
		if (!isEmail) {
			// 需要从数据库中查出来
			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			String title = optionsService.getOption(GlobalString.TITLE);
			request.setAttribute(GlobalString.TITLE, title);
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			throw new ForgetPageErrorEmailExcepion(GlobalString.ERROR_EMAIL);
		}
		//判断邮箱是否存在
		boolean emailIsExist = userService.emailIsExist(email);
		if (!emailIsExist) {//如果不存在
			// 需要从数据库中查出来
			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			String title = optionsService.getOption(GlobalString.TITLE);
			request.setAttribute(GlobalString.TITLE, title);
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			throw new ForgetPageEmailIsExistExcepion(GlobalString.EMAIL_IS_NOT_EXIST);
		}
		
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		//1).生成密码
		String send_password = UUID.randomUUID().toString().split("-")[0];
		
		//2).发送密码到邮箱
		new Thread(new Runnable() {
			@Override
			public void run() {
				sendMailUtil.sendEmail(email, send_password);
			}
		}).start();

		// 3).保存账户信息
		String new_password = StringUtil.encipher(send_password);
		userService.updatePassword(new_password, email);
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		
		return "redirect:/login";
		
		
	}
	
	/**
	 * 退出登陆
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.html";
	}
	
	/**
	 * 到用户修改信息页面
	 */
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public String toUserPage(Model model,HttpSession session,HttpServletRequest request) {
		
		Users users = (Users) session.getAttribute(GlobalString.USER);
		
		if (users == null) {
			
			// 需要从数据库中查出来
			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			String title = optionsService.getOption(GlobalString.TITLE);
			String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

			request.setAttribute(GlobalString.TITLE, title);
			request.setAttribute(GlobalString.LOGO_PATH, logoPath);
			
			throw new NoUserLoginException(GlobalString.NO_USER_LOGIN);
			
		}
		
		// 需要从数据库中查出来
		// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		String title = optionsService.getOption(GlobalString.TITLE);
		String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);

		model.addAttribute(GlobalString.TITLE, title);
		model.addAttribute(GlobalString.LOGO_PATH, logoPath);
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		
		return "page/user";
	}
	
	/**
	 * 修改用户信息
	 */
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public String editUser(Model model,HttpSession session,HttpServletRequest request,
			@RequestParam(value="password",required=false)String password,
			@RequestParam(value="userNicename",required=false)String userNicename) {
		
		Users usersession = (Users) session.getAttribute(GlobalString.USER);
		
		if (usersession == null) {
			
			// 需要从数据库中查出来
			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
			String title = optionsService.getOption(GlobalString.TITLE);
			String logoPath = optionsService.getOption(GlobalString.LOGO_PATH);
			

			request.setAttribute(GlobalString.TITLE, title);
			request.setAttribute(GlobalString.LOGO_PATH, logoPath);
			
			throw new NoUserLoginException(GlobalString.NO_USER_LOGIN);
			
		}
		
		//保存用户信息
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		//修改昵称
		if (userNicename != null && userNicename.trim().length() != 0) {
			userService.updateUserNicenameById(usersession.getId(),userNicename);
			usersession.setUserNicename(userNicename);
		}
		//修改密码
		if (password != null && password.trim().length() != 0) {
			userService.updateUserPasswordById(usersession.getId(),StringUtil.encipher(password));
		}
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		return "redirect:/user";
	}
	
}
