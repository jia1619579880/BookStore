package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    /**
     * 创建Order表单项
     * @param cart
     * @param userId
     * @return
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 使用时间戳加userId作为订单编号
        String orderId = System.currentTimeMillis() + "" + userId;
        // 新建订单对象
        Order order = new Order(orderId,new Timestamp(System.currentTimeMillis()),cart.getTotalPrice(),0,userId);

        // 保存订单
        orderDao.saveOrder(order);


        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();

            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);

            orderItemDao.saveOrderItem(orderItem);

            Book book = bookDao.queryBookById(cartItem.getId());

            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }

        // 结算完清空购物车
        cart.clear();

        // 返货订单号
        return orderId;
    }

    /**
     * 查询所有订单（管理员权限）
     * @return
     */
    @Override
    public List<Order> showAllOrders() {
        List<Order> orders = orderDao.queryOrders();
        return orders;
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,1);
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    @Override
    public List<OrderItem> showOrderDetails(String orderId) {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }

    @Override
    public Order showOrderByOrderId(String orderId) {
        return orderDao.showOrderByOrderId(orderId);
    }

    @Override
    public void recieveOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,2);
    }
}
