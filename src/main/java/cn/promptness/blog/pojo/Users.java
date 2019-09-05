package cn.promptness.blog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Lynn
 */
@Data
public class Users implements Serializable {

    private static final long serialVersionUID = 5960624572829924169L;

    private Long id;

    private String userLogin;

    private String userPass;

    private String userNicename;

    private String userEmail;

    private Date userRegistered;

    /**
     * 用户状态(0管理,1会员)
     */
    private Integer userStatus;

}