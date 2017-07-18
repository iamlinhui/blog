package com.blog.mapper;

import com.blog.pojo.Users;
import com.blog.pojo.UsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper {
    int countByExample(UsersExample example);

    int deleteByExample(UsersExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Users record);

    int insertSelective(Users record);

    List<Users> selectByExample(UsersExample example);

    Users selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByExample(@Param("record") Users record, @Param("example") UsersExample example);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

	void updatePassword(@Param("new_password")String new_password, @Param("email")String email);

	void updateUserNicenameById(@Param("id")Long id, @Param("userNicename")String userNicename);

	void updateUserPasswordById(@Param("id")Long id, @Param("password")String password);
}