package cn.promptness.blog.common.constant.enums;

import cn.promptness.blog.exception.BizExceptionEnum;
import lombok.Getter;

/**
 * @author : Lynn
 * @date : 2019-04-27 14:57
 */
@Getter
public enum UserStateEnum {


    /**
     * 状态：用户状态(0管理,1会员)
     */
    ADMIN(0, "管理员","redirect:/admin/article"),

    NORMAL(1, "正常", "redirect:/"),

    FROZEN(2, "冻结", "page/login");

    UserStateEnum(int state, String describe,String path) {
        this.state = state;
        this.describe = describe;
        this.path = path;
    }

    private int state;
    private String describe;
    private String path;

    public static UserStateEnum getInstance(int code) {
        for (UserStateEnum userStateEnum : UserStateEnum.values()) {
            if (userStateEnum.state == code) {
                return userStateEnum;
            }
        }
        throw BizExceptionEnum.SERVER_ERROR.pageException();
    }
}
