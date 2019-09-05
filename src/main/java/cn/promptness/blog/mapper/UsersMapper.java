package cn.promptness.blog.mapper;

import cn.promptness.blog.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {


    int deleteByPrimaryKey(Long id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    void updatePassword(@Param("newPassword") String newPassword, @Param("email") String email);

    void updateUserNiceNameById(@Param("id") Long id, @Param("userNiceName") String userNiceName);

    void updateUserPasswordById(@Param("id") Long id, @Param("password") String password);

    List<Users> listUser();

    Users getUserByUserLogin(@Param("loginName") String loginName, @Param("password") String password);

    Users getUserByUserEmail(@Param("loginName") String loginName, @Param("password") String password);

    int countUserName(String username);

    int countEmail(String email);
}