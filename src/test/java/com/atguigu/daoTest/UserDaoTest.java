package com.atguigu.daoTest;

import com.atguigu.dao.impl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {

    @Test
    void getByUserName() {
        UserDaoImpl userDao = new UserDaoImpl();
        if(userDao.getByUserName("admin")==null){
            System.out.println("用户名可用");
        } else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    void getByUserNameAndPassword() {
        UserDaoImpl userDao = new UserDaoImpl();
        if(userDao.getByUserNameAndPassword("admin", "admin")==null){
            System.out.println("用户名或密码错误，登陆失败");
        } else {
            System.out.println("登陆成功");
        }
    }

    @Test
    void saveUser() {
        UserDaoImpl userDao = new UserDaoImpl();
        System.out.println(userDao.saveUser(new User(null, "Administrator", "123456", "administrator@atguigu.com")));

    }
}