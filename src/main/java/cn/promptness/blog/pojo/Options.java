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
public class Options implements Serializable {
    private static final long serialVersionUID = -3454119733597148161L;
    private Long optionId;
    private String optionName;
    private String optionValue;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}