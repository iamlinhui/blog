package cn.promptness.blog.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.PrintWriter;

/**
 * 邮件模板工具类
 *
 * @author Lynn
 */
public class EmailUtils {

    private EmailUtils() {
    }

    /**
     * 渲染JSP邮件模板
     *
     * @param templatePath JSP模板路径, 如 /WEB-INF/templates/common/email.jsp
     * @param title        邮件标题
     * @param code         验证码
     * @return 渲染后的HTML字符串
     */
    public static String renderTemplate(String templatePath, String title, String code) {
        try {
            HttpServletRequest request = HttpUtils.getRequest();
            HttpServletResponse response = HttpUtils.getResponse();
            request.setAttribute("title", title);
            request.setAttribute("code", code);
            CharArrayWriter charWriter = new CharArrayWriter();
            PrintWriter writer = new PrintWriter(charWriter);
            HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response) {
                @Override
                public PrintWriter getWriter() {
                    return writer;
                }
            };
            request.getRequestDispatcher(templatePath).include(request, wrapper);
            writer.flush();
            return charWriter.toString();
        } catch (Exception e) {
            return "您的验证码为：" + code;
        }
    }
}

