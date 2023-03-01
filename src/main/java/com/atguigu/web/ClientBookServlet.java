package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BookServlet {
    BookService bookService = new BookServiceImpl();
    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取pageNo和pageSize参数
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 调用bookService.page方法分页查询图书
        Page<Book> page = bookService.page(pageNo, pageSize);

        // 赋值访问地址
        page.setUrl("client/bookServlet?action=page&");
        // 将page保存到request域中
        req.setAttribute("page", page);

        // 请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    public void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 获取请求参数pageNo 和 pageSize，min,max
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        // 调用bookService.pageByPrice方法分页查询图书
        Page<Book> pageByPrice = bookService.pageByPrice(pageNo, pageSize, min, max);

        // 创建拼接字符串
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        // 判断分页条里面的地址参数是否有min和max，如果有就拼接到字符串里面中
        if(req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }

        // 赋值访问地址
        pageByPrice.setUrl(sb.toString());

        // 保存分页对象到request域中
        req.setAttribute("page", pageByPrice);

        // 请求转发到/pages/client/index.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

}
