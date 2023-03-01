package com.atguigu.serviceTest;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {
    OrderService orderService = new OrderServiceImpl();
    Cart cart = new Cart();
    @Test
    public void createOrder() {
        cart.addItem(new CartItem(1,"three body",1,new BigDecimal(65),new BigDecimal(65)));
        cart.addItem(new CartItem(1,"three body",1,new BigDecimal(65),new BigDecimal(65)));
        cart.addItem(new CartItem(2,"data structure and Algorithm",1,new BigDecimal(100),new BigDecimal(100)));

        orderService.createOrder(cart,1);
    }

    @Test
    public void showAllOrders(){
        System.out.println(orderService.showAllOrders());
    }

    @Test
    public void showMyOrders(){
        System.out.println(orderService.showMyOrders(1));
    }


}