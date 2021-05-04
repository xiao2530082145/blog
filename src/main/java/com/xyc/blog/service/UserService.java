package com.xyc.blog.service;

import com.xyc.blog.pojo.User;

public interface UserService {
    User checkUser(String username, String password);

    User getUserInfo();
}
