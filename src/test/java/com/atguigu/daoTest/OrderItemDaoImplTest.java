package com.atguigu.daoTest;

import com.atguigu.dao.impl.OrderItemDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest {
    OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
    }

    @Test
    public void queryOrderItemsByOrderId() {
        System.out.println(orderItemDao.queryOrderItemsByOrderId("16768955946181"));
    }
}