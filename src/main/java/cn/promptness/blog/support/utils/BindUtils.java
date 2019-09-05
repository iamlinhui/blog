package cn.promptness.blog.support.utils;

import cn.promptness.blog.pojo.Users;

/**
 * @author : Lynn
 * @date : 2019-05-04 00:00
 */
public class BindUtils {

    private static final ThreadLocal<Users> THREAD_LOCAL = new ThreadLocal<>();

    public static void bind(Users user) {
        THREAD_LOCAL.set(user);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

    public static Users getUser() {
        return THREAD_LOCAL.get();
    }

}
