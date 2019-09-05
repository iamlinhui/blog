package cn.promptness.blog.exception;

import lombok.Getter;

/**
 * @author : Lynn
 * @date : 2019-04-18 22:50
 */
@Getter
public enum BizExceptionEnum {

    /**
     * 账户相关
     */
    LOGIN_ERROR              (100001, "用户名或密码错误",           "page/login"),
    REGISTER_EMAIL_ERROR     (100002, "邮箱格式不正确",             "page/register"),
    REGISTER_USER_NAME_EXIST (100002, "用户名已经存在",             "page/register"),
    REGISTER_EMAIL_EXIST     (100002, "邮箱已经存在",               "page/register"),
    FORGET_EMAIL_ERROR       (100002, "邮箱格式不正确",             "page/forget"),
    FORGET_EMAIL_EXIST       (100002, "邮箱已经存在",               "page/forget"),
    FORGET_CODE_ERROR        (100003, "验证码错误",                 "page/register"),
    USER_NOT_LOGIN           (100100, "请先登录账号!",              "page/login"),
    FROZEN_ACCOUNT           (100004, "账号已冻结!",                "page/login"),
    ACCESS_LIMIT             (100101, "后台重地,请管理员登录后访问!","page/login"),
    INDEX_PAGE               (100102, "文章不存在!",                "redirect:/"),
    ADMIN_PAGE               (100103, "文章不存在!",                "redirect:/admin/article"),
    SERVER_ERROR             (999999, "服务器异常");

    BizExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
        this.template = "error";
    }

    BizExceptionEnum(int code, String message, String template) {
        this.code = code;
        this.message = message;
        this.template = template;
    }

    private int code;

    private String message;

    private String template;

    public BusinessException pageException() {
        return new BusinessException(this);
    }


}
