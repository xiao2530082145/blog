package com.xyc.blog.service.impl;

import com.xyc.blog.Utils.MD5Utils;
import com.xyc.blog.dao.UserDao;
import com.xyc.blog.pojo.User;
import com.xyc.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.getByUsernameAndPassword(username, MD5Utils.code(password));
    }

    @Override
    public User getUserInfo() {
        return userDao.getUserInfo();
    }
}
