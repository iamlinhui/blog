package cn.promptness.blog.vo;


import com.alibaba.fastjson.JSON;

/**
 * 返回前台数据类型
 *
 * @author linhuid
 */
public class HttpResultVO {

    public final static HttpResultVO DEFAULT = new HttpResultVO(0, "DEFAULT");

    public final static HttpResultVO ERROR = new HttpResultVO(500, "ERROR");

    public final static HttpResultVO SUCCESS = new HttpResultVO(200, "SUCCESS");

    public final static HttpResultVO ENTITY_EMPTY = new HttpResultVO(204, "ENTITY_EMPTY");

    private int code;
    private String message;

    public HttpResultVO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static HttpResultVO getHttpResult() {
        return new HttpResultVO(DEFAULT.code, DEFAULT.message);
    }

    public static HttpResultVO getErrorHttpResult(String message) {
        return new HttpResultVO(ERROR.code, message);
    }

    public static HttpResultVO getErrorHttpResult(int code) {
        return new HttpResultVO(code, ERROR.message);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public int getCode() {
        return code;
    }

    public HttpResultVO setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public HttpResultVO setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isSuccess() {
        return this.code == SUCCESS.code;
    }

    public boolean isFailed() {
        return !isSuccess();
    }

    public <T> T getContent(Class<T> clazz) {
        return JSON.parseObject(this.getMessage(), clazz);
    }


}
