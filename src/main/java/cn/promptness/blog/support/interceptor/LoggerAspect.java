package cn.promptness.blog.support.interceptor;

import cn.promptness.blog.common.utils.HttpUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author : Lynn
 * @date : 2019-05-26 12:24
 */
@Slf4j
@Component
@Aspect
public class LoggerAspect implements Ordered {


    @Pointcut(value = "execution(* cn.promptness.blog.controller..*.*(..))")
    private void cut() {
    }

    @Around(value = "cut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        if (AnnotationUtils.findAnnotation(method, RequestMapping.class) == null) {
            return joinPoint.proceed();
        }
        HttpServletRequest request = HttpUtils.getRequest();
        log.info("[{}请求],请求路径={},请求IP={},执行方法={},接收参数={}",
                request.getMethod(),
                request.getRequestURI(),
                HttpUtils.getIp(),
                method.getName(),
                getArguments(joinPoint.getArgs())
        );
        return joinPoint.proceed();
    }


    @Override
    public int getOrder() {
        return -1;
    }

    private List<Object> getArguments(Object[] arguments) {
        List<Object> args = Lists.newArrayList();
        for (Object arg : arguments) {
            if (arg == null) {
                continue;
            }
            if (!isExclude(arg)) {
                args.add(arg);
            }
        }
        return args;
    }

    private boolean isExclude(Object arg) {
        if (HttpServletRequest.class.isAssignableFrom(arg.getClass())) {
            return true;
        }
        if (HttpServletResponse.class.isAssignableFrom(arg.getClass())) {
            return true;
        }
        if (MultipartFile.class.isAssignableFrom(arg.getClass())) {
            return true;
        }
        if (HttpSession.class.isAssignableFrom(arg.getClass())) {
            return true;
        }
        if (Model.class.isAssignableFrom(arg.getClass())) {
            return true;
        }
        return false;
    }
}
