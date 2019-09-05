package cn.promptness.blog.support.utils;

import cn.promptness.blog.exception.BizExceptionEnum;

import java.util.Objects;

/**
 * @author : Lynn
 * @date : 2019-04-27 14:54
 */
public class AssertUtils {

    public static void isNull(Object validate, BizExceptionEnum bizExceptionEnum) {
        if (Objects.nonNull(validate)) {
            throw bizExceptionEnum.pageException();
        }
    }

    public static void notNull(Object validate, BizExceptionEnum bizExceptionEnum) {
        if (Objects.isNull(validate)) {
            throw bizExceptionEnum.pageException();
        }
    }

    public static void isFalse(boolean validate, BizExceptionEnum bizExceptionEnum) {
        if (validate) {
            throw bizExceptionEnum.pageException();
        }
    }

    public static void isTrue(boolean validate, BizExceptionEnum bizExceptionEnum) {
        if (!validate) {
            throw bizExceptionEnum.pageException();
        }
    }

}
