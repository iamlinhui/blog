package cn.promptness.blog.exception;

/**
 * @author : Lynn
 * @date : 2019-04-18 22:48
 */
public class BusinessException extends RuntimeException {

    /**
     * 友好提示的code码
     */
    private int code;

    /**
     * 友好提示
     */
    private String message;

    /**
     * 业务异常跳转的页面
     */
    private String template;

    public BusinessException() {
        super();
    }

    public BusinessException(BizExceptionEnum bizExceptionEnum) {
        this.code = bizExceptionEnum.getCode();
        this.message = bizExceptionEnum.getMessage();
        this.template = bizExceptionEnum.getTemplate();
    }

    public BusinessException(int code, String message, String template) {
        this.code = code;
        this.message = message;
        this.template = template;
    }

    public BusinessException(String message, String template) {
        this.code = BizExceptionEnum.SERVER_ERROR.getCode();
        this.message = message;
        this.template = template;
    }

    public int getCode() {
        return code;
    }

    public BusinessException setCode(int code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BusinessException setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getTemplate() {
        return template;
    }


}
