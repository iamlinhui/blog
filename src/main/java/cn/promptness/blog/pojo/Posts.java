package cn.promptness.blog.pojo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Lynn
 */
@Data
@JsonInclude(content = JsonInclude.Include.NON_NULL)
public class Posts implements Serializable {

    private static final long serialVersionUID = -5453440001269279286L;
    private Long id;
    private Long postAuthor;
    private Date postDate;
    private String postStatus;
    private String commentStatus;
    private String postName;
    private Long commentCount;
    private String postContent;
    private String postTitle;
    private String postExcerpt;
    private List<Terms> term;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}