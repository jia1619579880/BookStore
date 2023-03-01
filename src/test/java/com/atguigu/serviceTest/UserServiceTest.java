package com.atguigu.serviceTest;

import com.atguigu.pojo.User;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserServiceImpl userService = new UserServiceImpl();
    @Test
    void registerUser() {
        userService.registerUser(new User(null,"bbj168","666666","bbj168@168.com"));
        userService.registerUser(new User(null,"abc168","666666","abc168@168.com"));

    }

    @Test
    void login() {
        System.out.println(userService.login(new User(null, "bbj168", "666666",null)));
    }

    @Test
    void existsUsername() {
        System.out.println(userService.existsUsername("admin"));
        System.out.println(userService.existsUsername("123"));

    }
}