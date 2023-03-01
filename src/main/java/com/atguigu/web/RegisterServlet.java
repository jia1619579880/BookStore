package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取输入框的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(email);
//        System.out.println(code);

        // 判断验证码是否正确
        //正确则进行验证用户名
        if ("abcde".equalsIgnoreCase(code)) {
            if (userService.existsUsername(username)) {
                // 用户已存在
                // 注册失败 跳回注册页面
                System.out.println("用户名["+username+"]已存在");

                // 给request域中添加错误信息(用户名已存在)
                req.setAttribute("msg", "用户名已存在");
                // 回传username 和 邮箱

                req.setAttribute("username",username);
                req.setAttribute("email",email);

                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);

            } else {
                // 用户不存在
                // 注册成功 进行保存
                userService.registerUser(new User(null,username,password,email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            // 验证码错误
            // 返回注册页面
            System.out.println("验证码["+code+"]错误");

            // 给request域中添加错误信息(验证码错误)
            req.setAttribute("msg", "验证码错误");
            // 回传username 和 邮箱
            req.setAttribute("username",username);
            req.setAttribute("email",email);

            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }
}
