package com.xyc.blog.dao;

import com.xyc.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    User getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    User getUserInfo();
}
