package com.atguigu.dao.impl;

import com.atguigu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update() 方法用来操作insert、update、delete语句
     * @param sql
     * @param args
     * @return 如果返回-1，说明执行失败
     */
    public int update(String sql,Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一个JavaBean的sql语句
     * @param type 返回的对象类型
     * @param sql sql语句
     * @param args 占位符
     * @param <T> 返回类型的泛性
     * @return
     */
    public <T> T queryForOne(Class<T> type,String sql,Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回多个JavaBean的sql语句
     * @param type 返回的对象类型
     * @param sql sql语句
     * @param args 占位符
     * @param <T> 返回类型的泛性
     * @return
     */
    public <T>List<T> queryForList(Class<T> type,String sql,Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new BeanListHandler<>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一个单行单列的的sql语句
     * @param sql
     * @param args
     * @return
     */
    public Object queryForSingleValue(String sql,Object... args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
