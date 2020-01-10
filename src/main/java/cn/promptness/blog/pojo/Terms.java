package cn.promptness.blog.pojo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Lynn
 */
@Data
@JsonInclude(content = JsonInclude.Include.NON_NULL)
public class Terms implements Serializable {

    private static final long serialVersionUID = -6423037213245827779L;
    private Long termId;
    private String name;
    private String slug;
    private boolean checked;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}