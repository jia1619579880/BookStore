package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取谷歌的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 马上删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        // 获取输入框的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(email);
//        System.out.println(code);

        // 利用已经封装好了的BeanUtils.jar包进行注入
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        // 判断验证码是否正确
        //正确则进行验证用户名
        if (token!=null && token.equalsIgnoreCase(code)) {
            if (userService.existsUsername(username)) {
                // 用户已存在
                // 注册失败 跳回注册页面
                System.out.println("用户名[" + username + "]已存在");

                // 给request域中添加错误信息(用户名已存在)
                req.setAttribute("msg", "用户名已存在");
                // 回传username 和 邮箱

                req.setAttribute("username", username);
                req.setAttribute("email", email);

                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);

            } else {
                // 用户不存在
                // 注册成功 进行保存
                userService.registerUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            // 验证码错误
            // 返回注册页面
            System.out.println("验证码[" + code + "]错误");

            // 给request域中添加错误信息(验证码错误)
            req.setAttribute("msg", "验证码错误");
            // 回传username 和 邮箱
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 立马让session失效
        req.getSession().invalidate();

        // 重定向到首页
        resp.sendRedirect(req.getContextPath() );
    }
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        if (userService.login(user) == null) {
            // 登陆失败 跳转回登陆页面
            System.out.println("用户名或密码错误");

            // 给request域中添加错误信息
            // 回传username信息
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 登陆成功后 在session域中存储用户信息
            req.getSession().setAttribute("user",user);
//            session中用户id为空可以在保存用户信息后加句
//            resp.addCookie(new Cookie("JSESSIONID",req.getSession().getId()));
            Integer userById = userService.getUserById(user);
            user.setId(userById);
            // 登陆成功 跳到登陆成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }


}