package com.atguigu.dao;

import com.atguigu.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);  //保存订单项

    public List<OrderItem> queryOrderItemsByOrderId(String orderId);  //根据订单id查询订单项
}
