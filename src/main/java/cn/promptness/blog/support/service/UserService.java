package cn.promptness.blog.support.service;

import cn.promptness.blog.pojo.Users;
import cn.promptness.blog.support.vo.AccountVO;

import java.util.List;

public interface UserService {

	void register(Users users);

	boolean usernameIsExist(String username);

	boolean emailIsExist(String email);

	void updatePassword(String newPassword, String email);

	List<Users> getAllUsers();

	void updateUser(Users users);

	void updateUserNiceNameById(Long id, String userNicename);

	void updateUserPasswordById(Long id, String encipher);

	Users doLogin(AccountVO account) throws Exception;
}
