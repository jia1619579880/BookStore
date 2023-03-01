package com.atguigu.web;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.JdbcUtils;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();


    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取参数 购物车对象，userid
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            return;
        }
        // 获取userID参数
        Integer userid = loginUser.getId();

//        int i=12/0;

        // 保存订单项
        String orderId = orderService.createOrder(cart, userid);

        req.setAttribute("orderId", orderId);

        // 请求转发到订单页面
        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
    }

    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.showAllOrders();

        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            return;
        }

        req.getSession().setAttribute("orders", orders);

        resp.sendRedirect(req.getContextPath() + "/pages/manager/order_manager.jsp");
    }

    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            return;
        }

        String orderId = req.getParameter("orderId");

        orderService.sendOrder(orderId);

        resp.sendRedirect(req.getContextPath() + "/orderServlet?action=showAllOrders");

//        orderService.sendOrder();
    }

    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User loginUser = (User) req.getSession().getAttribute("user");

        // if not login
        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            return;
        }

        // if logined
        Integer userId = WebUtils.parseInt(req.getParameter("id"), 0);

        List<Order> orders = orderService.showMyOrders(userId);

        // 将通过userID查询出来的order集合项存到session中，然后在页面中显示
        req.getSession().setAttribute("orders", orders);

        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }


    protected void showOrderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("user");

        // if not login
        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            return;
        }

        // 获取orderID参数
        String orderId = req.getParameter("orderId");

        List<OrderItem> orderItems = orderService.showOrderDetails(orderId);

        Order order = orderService.showOrderByOrderId(orderId);

        req.getSession().setAttribute("order", order);

        req.getSession().setAttribute("orderItems", orderItems);

        req.getRequestDispatcher("/pages/order/order_details.jsp").forward(req, resp);
    }

    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
            return;
        }

        String orderId = req.getParameter("orderId");

        orderService.recieveOrder(orderId);

        resp.sendRedirect(getServletContext().getContextPath() + "/orderServlet?action=showMyOrders&id=" + loginUser.getId());
    }
}