package com.atguigu.dao;

import com.atguigu.dao.impl.BaseDao;
import com.atguigu.pojo.User;

public interface UserDao {
    public Integer getUserIdByUserName(String userName);
    /**
     * 用用户名查询用户
     * @param userName 用户名
     * @return
     */
    public User getByUserName(String userName);

    /**
     * 用用户名和密码查询用户
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    public User getByUserNameAndPassword(String userName, String password);

    /**
     * 储存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);

}
