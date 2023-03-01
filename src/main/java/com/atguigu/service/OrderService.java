package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);

    public List<Order> showAllOrders();

    public void sendOrder(String orderId);

    public List<Order> showMyOrders(Integer userId);

    List<OrderItem> showOrderDetails(String orderId);

    Order showOrderByOrderId(String orderId);

    public void recieveOrder(String orderId);
}
