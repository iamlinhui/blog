package cn.promptness.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lynn
 */
@Data
public class Options implements Serializable {

    private static final long serialVersionUID = -3454119733597148161L;

    private Long optionId;

    private String optionName;

    private String optionValue;


}