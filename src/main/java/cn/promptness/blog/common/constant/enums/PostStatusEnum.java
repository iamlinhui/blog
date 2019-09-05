package cn.promptness.blog.common.constant.enums;

import lombok.Getter;

/**
 * @author lynn
 */

public enum PostStatusEnum {

    /**
     * 文章状态
     */
    PUBLISH("publish"),
    DRAFT("draft");

    @Getter
    String text;

    PostStatusEnum(String text) {
        this.text = text;
    }
}
