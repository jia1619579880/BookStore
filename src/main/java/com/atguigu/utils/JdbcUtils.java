package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = new FileInputStream("/Users/oliverdai/JavaWeb/book/src/main/resources/jdbc.properties");
            properties.load(inputStream);
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection(){
        Connection connection = threadLocal.get();
        if (connection ==null){
            try {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return connection;
    }

    public static void commitAndClose(){
        Connection connection = threadLocal.get();
        if (connection!=null){
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        threadLocal.remove();
    }

    public static void rollbackAndClose(){
        Connection connection = threadLocal.get();
        if (connection!=null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        threadLocal.remove();

    }

    /**
     * 关闭连接
     * @param conn
     */
//    public static void close(Connection conn){
//        if (dataSource!=null){
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
