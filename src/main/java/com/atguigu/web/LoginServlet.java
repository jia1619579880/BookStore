package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (userService.login(new User(null,username,password,null))==null) {
            // 登陆失败 跳转回登陆页面
            System.out.println("用户名或密码错误");

            // 给request域中添加错误信息
            // 回传username信息
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        } else {
            // 登陆成功 跳到登陆成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
}
