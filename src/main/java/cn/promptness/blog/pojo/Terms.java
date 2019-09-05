package cn.promptness.blog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lynn
 */
@Data
public class Terms implements Serializable {


    private static final long serialVersionUID = -6423037213245827779L;

    private Long termId;

    private String name;

    private String slug;

    private boolean checked;

}