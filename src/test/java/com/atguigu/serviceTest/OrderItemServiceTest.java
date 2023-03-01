package com.atguigu.serviceTest;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.OrderItem;
import org.junit.Test;

import java.util.List;

public class OrderItemServiceTest {
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Test
    public void showOrderDetails(){
        List<OrderItem> orderItems = orderItemDao.queryOrderItemsByOrderId("16768955946181");
        orderItems.forEach(System.out::println);
    }

}
