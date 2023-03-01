package com.atguigu.daoTest;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoImplTest {
    OrderDao orderDao = new OrderDaoImpl();
    Order order = new Order();
    @Test
    public void saveOrder() {
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = sdf.format(date);
//
//        System.out.println(date);
        orderDao.saveOrder(new Order("1234567890",new Timestamp(System.currentTimeMillis()),new BigDecimal(100),0,1));
        orderDao.saveOrder(new Order("1234567891",new Timestamp(System.currentTimeMillis()),new BigDecimal(100),0,1));
        orderDao.saveOrder(new Order("1234567892",new Timestamp(System.currentTimeMillis()),new BigDecimal(100),0, 1));
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDao.queryOrders();
        for (Order order:orders){
            System.out.println(order);
        }
    }

    @Test
    public void updateOrderStatus() {
        Integer i = orderDao.changeOrderStatus("16774759885951", 1);
        System.out.println(i);
    }

    @Test
    public void queryOrdersByUserId() {
        List<Order> orders = orderDao.queryOrdersByUserId(1);
        for (Order order:orders){
            System.out.println(order);
        }
    }

    @Test
    public void showMyOrders(){
        List<Order> orders = orderDao.queryOrdersByUserId(2);
        orders.forEach(System.out::println);
    }


}