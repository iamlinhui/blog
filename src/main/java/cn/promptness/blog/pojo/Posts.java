package cn.promptness.blog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Lynn
 */
@Data
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


}