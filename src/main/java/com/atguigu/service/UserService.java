package com.atguigu.service;

import com.atguigu.pojo.User;

public interface UserService {
    public Integer getUserById(User user);
    /**
     * 用于注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 用于登陆用户
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 查看用户名存不存在
     * @param username
     * @return
     */
    public boolean existsUsername(String username);
}
