package cn.promptness.blog.support.service.impl;

import cn.promptness.blog.mapper.UsersMapper;
import cn.promptness.blog.pojo.Users;
import cn.promptness.blog.support.service.UserService;
import cn.promptness.blog.common.utils.HashUtils;
import cn.promptness.blog.vo.AccountVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Lynn
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UsersMapper usersMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(Users users) {
        usersMapper.insertSelective(users);
    }

    @Override
    public boolean usernameIsExist(String username) {
        return usersMapper.countUserName(username) != 0;
    }

    @Override
    public boolean emailIsExist(String email) {
        return usersMapper.countEmail(email) != 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePassword(String password, String email) {
        usersMapper.updatePassword(password, email);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersMapper.listUser();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(Users users) {
        usersMapper.updateByPrimaryKeySelective(users);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserNiceNameById(Long id, String userNiceName) {
        usersMapper.updateUserNiceNameById(id, userNiceName);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserPasswordById(Long id, String password) {
        usersMapper.updateUserPasswordById(id, password);
    }

    @Override
    public Users doLogin(AccountVO account) throws Exception {

        String loginName = account.getLoginName();
        String password = HashUtils.md5(account.getPassword());

        Users user = usersMapper.getUserByUserLogin(loginName, password);

        if (null == user) {
            user = usersMapper.getUserByUserEmail(loginName, password);
        }

        return user;
    }


}
