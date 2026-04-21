package cn.promptness.blog.vo;

import lombok.Data;

/**
 * 文章筛选条件
 *
 * @author Lynn
 */
@Data
public class ArticleFilterVO {
    private String status;
    private String slug;
    private String dateFrom;
    private String dateTo;
}

