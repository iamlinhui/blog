package com.blog.service;

import java.util.List;

import com.blog.pojo.Users;

public interface UserService {

	/**
	 * 登陆用户
	 * 
	 * @param login_user
	 * @return
	 */
	Users login(Users login_user);

	void regist(Users users);

	boolean usernameIsExist(String username);

	boolean emailIsExist(String email);

	void updatePassword(String new_password, String email);

	List<Users> getAllUsers();

	void updateUser(Users users);

	void updateUserNicenameById(Long id, String userNicename);

	void updateUserPasswordById(Long id, String encipher);

	
	

}
