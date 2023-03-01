package com.atguigu.dao.impl;

import com.atguigu.dao.UserDao;
import com.atguigu.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public Integer getUserIdByUserName(String userName) {
        String sql = "select id from t_user where username =?";
        Number id = (Number) queryForSingleValue(sql,userName);
        return id.intValue();
    }

    @Override
    public User getByUserName(String userName) {
        String sql = "select * from t_user where username =?";
        return queryForOne(User.class,sql,userName);
    }

    @Override
    public User getByUserNameAndPassword(String userName, String password) {
        String sql = "select * from t_user where username =? and password =?";
        return queryForOne(User.class,sql,userName,password);
    }



    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
