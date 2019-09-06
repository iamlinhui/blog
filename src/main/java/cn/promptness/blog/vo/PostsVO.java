package cn.promptness.blog.vo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.List;

/**
 * @author : Lynn
 * @date : 2019-06-02 00:14
 */
@Data
public class PostsVO {

    private Long id;
    private String postTitle;
    private String postExcerpt;
    private String postContent;
    private Boolean commentStatus;
    private String publish;
    private String draft;
    private List<Integer> termId;

    private Integer pageNum;
    private List<Integer> delete;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
