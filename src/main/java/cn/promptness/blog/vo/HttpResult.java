package cn.promptness.blog.vo;


import com.alibaba.fastjson.JSON;

/**
 * 返回前台数据类型
 *
 * @author linhuid
 */
public class HttpResult {

    public final static HttpResult DEFAULT = new HttpResult(0, "DEFAULT");

    public final static HttpResult ERROR = new HttpResult(500, "ERROR");

    public final static HttpResult SUCCESS = new HttpResult(200, "SUCCESS");

    public final static HttpResult ENTITY_EMPTY = new HttpResult(204, "ENTITY_EMPTY");

    private int code;
    private String message;

    public HttpResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static HttpResult getHttpResult() {
        return new HttpResult(DEFAULT.code, DEFAULT.message);
    }

    public static HttpResult getErrorHttpResult(String message) {
        return new HttpResult(ERROR.code, message);
    }

    public static HttpResult getErrorHttpResult(int code) {
        return new HttpResult(code, ERROR.message);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public int getCode() {
        return code;
    }

    public HttpResult setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public HttpResult setMessage(String message) {
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
