package com.blog.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.mapper.UsersMapper;
import com.blog.pojo.Users;
import com.blog.pojo.UsersExample;
import com.blog.pojo.UsersExample.Criteria;
import com.blog.service.UserService;
import com.blog.utils.StringUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UsersMapper usersMapper;

	@Override
	public Users login(Users login_user) {
		String password = StringUtil.encipher(login_user.getUserPass());

		UsersExample example = new UsersExample();
		Criteria criteria = example.createCriteria();
		// 判断登陆的是账号是用户名还是邮箱
		if (login_user.getUserLogin() != null && login_user.getUserLogin().length() > 0) {
			criteria.andUserLoginEqualTo(login_user.getUserLogin());
		} else {
			criteria.andUserEmailEqualTo(login_user.getUserEmail());
		}
		criteria.andUserPassEqualTo(password);
		List<Users> users_List = usersMapper.selectByExample(example);

		// 只能通过isEmpty()判断
		if (users_List.isEmpty()) {
			return null;
		}

		return users_List.get(0);
	}

	@Override
	public void regist(Users users) {
		usersMapper.insertSelective(users);
	}

	@Override
	public boolean usernameIsExist(String username) {
		UsersExample example = new UsersExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserLoginEqualTo(username);
		List<Users> users_list = usersMapper.selectByExample(example);

		return users_list.isEmpty() ? false : true;
	}

	@Override
	public boolean emailIsExist(String email) {
		UsersExample example = new UsersExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserEmailEqualTo(email);
		List<Users> users_list = usersMapper.selectByExample(example);

		return users_list.isEmpty() ? false : true;
	}

	@Override
	public void updatePassword(String new_password, String email) {
		usersMapper.updatePassword(new_password,email);	
	}

	@Override
	public List<Users> getAllUsers() {
		return usersMapper.selectByExample(null);
	}

	@Override
	public void updateUser(Users users) {
		usersMapper.updateByPrimaryKeySelective(users);
		
	}

	@Override
	public void updateUserNicenameById(Long id, String userNicename) {
		usersMapper.updateUserNicenameById( id, userNicename);
	}

	@Override
	public void updateUserPasswordById(Long id, String password) {
		usersMapper.updateUserPasswordById( id, password);
	}


}
