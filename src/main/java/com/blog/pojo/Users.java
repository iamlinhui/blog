package com.blog.pojo;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;//主键

    private String userLogin;//用户名

    private String userPass;//密码

    private String userNicename;//昵称

    private String userEmail;//邮箱

    private Date userRegistered;//注册时间

    private Integer userStatus;//用户状态(0管理,1会员)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin == null ? null : userLogin.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    public String getUserNicename() {
        return userNicename;
    }

    public void setUserNicename(String userNicename) {
        this.userNicename = userNicename == null ? null : userNicename.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Date getUserRegistered() {
        return userRegistered;
    }

    public void setUserRegistered(Date userRegistered) {
        this.userRegistered = userRegistered;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
}