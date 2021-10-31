package cn.promptness.blog.controller;


import cn.promptness.blog.common.constant.Constants;
import cn.promptness.blog.common.constant.enums.UserStateEnum;
import cn.promptness.blog.common.utils.AssertUtils;
import cn.promptness.blog.common.utils.BindUtils;
import cn.promptness.blog.common.utils.HashUtils;
import cn.promptness.blog.common.utils.HttpUtils;
import cn.promptness.blog.exception.BizExceptionEnum;
import cn.promptness.blog.pojo.Users;
import cn.promptness.blog.support.service.OptionsService;
import cn.promptness.blog.support.service.UserService;
import cn.promptness.blog.support.service.rpc.SendMailService;
import cn.promptness.blog.vo.AccountVO;
import cn.promptness.blog.vo.HttpResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

/**
 * @author Lynn
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private OptionsService optionsService;
    @Resource
    private SendMailService sendMailService;

    /**
     * 到登陆页面
     */
    @GetMapping(value = "/login")
    public String toLoginPage() {
        return "page/login";
    }

    /**
     * 到注册页面
     */
    @GetMapping(value = "/register")
    public String toRegisterPage() {
        return "page/register";
    }

    /**
     * 到找回密码页面
     */
    @GetMapping(value = "/forget")
    public String toForgetPage() {
        return "page/forget";
    }


    /**
     * 登陆账号
     */
    @PostMapping(value = "/login")
    public String login(@Validated(value = {AccountVO.Login.class}) AccountVO account) throws Exception {

        AssertUtils.isTrue(Objects.equals(HttpUtils.getRequest().getSession().getAttribute(Constants.LOGIN_CODE_KEY), account.getLoginCode()), BizExceptionEnum.LOGIN_CODE_ERROR);
        Users users = userService.doLogin(account);
        AssertUtils.notNull(users, BizExceptionEnum.LOGIN_ERROR);

        UserStateEnum userStateEnum = UserStateEnum.getInstance(users.getUserStatus());
        AssertUtils.isFalse(Objects.equals(userStateEnum, UserStateEnum.FROZEN), BizExceptionEnum.FROZEN_ACCOUNT);

        HttpSession session = HttpUtils.getRequest().getSession();
        session.setAttribute(Constants.USER, users);

        return userStateEnum.getPath();
    }


    @ResponseBody
    @PostMapping(value = "/email")
    public HttpResult email(@Validated(value = {AccountVO.EmailCode.class}) AccountVO account) throws Exception {

        UserStateEnum userStateEnum = UserStateEnum.getInstance(Integer.parseInt(optionsService.getOption(Constants.USER_STATUS)));
        AssertUtils.isFalse(UserStateEnum.FROZEN.equals(userStateEnum), BizExceptionEnum.FROZEN_REGISTER);

        // 生成邮箱验证码
        String emailCode = HashUtils.getRandomSalt(8);
        // 发送验证码到邮箱
        Boolean result = sendMailService.sendMail(account.getEmail(), "邮箱验证码", emailCode).get();
        AssertUtils.isTrue(result, BizExceptionEnum.SEND_EMAIL_CODE_ERROR);

        HttpUtils.getRequest().getSession().setAttribute(Constants.EMAIL_CODE_KEY, emailCode + account.getEmail());

        return HttpResult.SUCCESS;
    }

    /**
     * 注册用户
     */
    @PostMapping(value = "/register")
    public String register(@Validated(value = {AccountVO.Register.class}) AccountVO account) throws Exception {

        AssertUtils.isTrue(Objects.equals(HttpUtils.getRequest().getSession().getAttribute(Constants.EMAIL_CODE_KEY), account.getRegisterCode() + account.getEmail()), BizExceptionEnum.EMAIL_CODE_ERROR);

        UserStateEnum userStateEnum = UserStateEnum.getInstance(Integer.parseInt(optionsService.getOption(Constants.USER_STATUS)));
        AssertUtils.isFalse(UserStateEnum.FROZEN.equals(userStateEnum), BizExceptionEnum.FROZEN_REGISTER);

        boolean usernameIsExist = userService.usernameIsExist(account.getUsername());
        AssertUtils.isFalse(usernameIsExist, BizExceptionEnum.REGISTER_USER_NAME_EXIST);
        boolean emailIsExist = userService.emailIsExist(account.getEmail());
        AssertUtils.isFalse(emailIsExist, BizExceptionEnum.REGISTER_EMAIL_EXIST);

        // 保存账户信息
        Users users = new Users();
        users.setUserLogin(account.getUsername());
        users.setUserEmail(account.getEmail());
        users.setUserPass(HashUtils.md5(account.getPassword()));
        users.setUserNicename(account.getUsername());
        users.setUserRegistered(new Date());
        users.setUserStatus(Integer.parseInt(optionsService.getOption(Constants.USER_STATUS)));
        userService.register(users);

        return "redirect:/login";
    }

    /**
     * 找回密码
     */
    @PostMapping(value = "/forget")
    public String forget(@Validated(value = {AccountVO.Forget.class}) AccountVO account) throws Exception {

        AssertUtils.isTrue(Objects.equals(HttpUtils.getRequest().getSession().getAttribute(Constants.EMAIL_CODE_KEY), account.getRegisterCode() + account.getEmail()), BizExceptionEnum.EMAIL_CODE_ERROR);

        boolean emailIsExist = userService.emailIsExist(account.getEmail());
        AssertUtils.isTrue(emailIsExist, BizExceptionEnum.FORGET_EMAIL_NOT_EXIST);

        userService.updatePassword(HashUtils.md5(account.getPassword()), account.getEmail());

        return "redirect:/login";
    }

    /**
     * 退出登陆
     */
    @GetMapping(value = "/logout")
    public String logout() {
        HttpUtils.getSession().invalidate();
        return "redirect:/";
    }

    /**
     * 到用户修改信息页面
     */
    @GetMapping(value = "/user")
    public String toUserPage() {
        return "page/user";
    }

    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String editUser(AccountVO account) throws Exception {

        Users user = BindUtils.getUser();

        String userNiceName = account.getUserNiceName();
        String password = account.getPassword();

        //修改昵称
        if (!StringUtils.isEmpty(userNiceName)) {
            userService.updateUserNiceNameById(user.getId(), userNiceName);
            user.setUserNicename(userNiceName);
        }
        //修改密码
        if (!StringUtils.isEmpty(password)) {
            userService.updateUserPasswordById(user.getId(), HashUtils.md5(password));
            HttpUtils.getSession().invalidate();
            return "redirect:/";
        }

        return "redirect:/user";
    }

}
