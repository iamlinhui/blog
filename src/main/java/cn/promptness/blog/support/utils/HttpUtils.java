package cn.promptness.blog.support.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author : Lynn
 * @date : 2019-05-04 01:13
 */
public class HttpUtils {

    /**
     * 获取 HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }

    /**
     * @return request
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 判断是否是  Ajax 请求
     * 如果是ajax请求响应头会有，x-requested-with
     */
    public static boolean isAjax() {
        return "XMLHttpRequest".equalsIgnoreCase(HttpUtils.getRequest().getHeader("X-Requested-With"));
    }

    /**
     * 获取ip
     */
    public static String getIp() {
        String ip = HttpUtils.getRequest().getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0) {
            ip = HttpUtils.getRequest().getRemoteAddr();
        }
        return ip;
    }

}
