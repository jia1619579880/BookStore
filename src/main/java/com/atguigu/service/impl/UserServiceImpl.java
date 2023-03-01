package com.atguigu.service.impl;

import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;

public class UserServiceImpl implements UserService {
    UserDaoImpl userDao = new UserDaoImpl();


    @Override
    public Integer getUserById(User user) {
        return userDao.getUserIdByUserName(user.getUsername());
    }

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.getByUserNameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        return userDao.getByUserName(username) != null;
    }
}
