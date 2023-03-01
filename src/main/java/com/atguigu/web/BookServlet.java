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
import java.util.List;

public class BookServlet extends BaseServlet {


    private BookService bookService = new BookServiceImpl();

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo+=1;
        // 设置浏览器的默认字符集
        req.setCharacterEncoding("UTF-8");
        // 得到参数形成JavaBean对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 调用bookService的addBook方法添加图书
        bookService.addBook(book);
        // 使用重定向跳转到图书列表页面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        pageNo+=1;
        // 获取图书标号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 调用BookService的deleteBook方法删除图书
        bookService.deleteBookById(id);
        // 请求重定向到：/book/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        // 请求参数Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 调用BookService的updateBook方法更新图书
        bookService.updateBook(book);
        // 请求重定向到：/book/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2 把全部图书保存到request域中
        req.setAttribute("books", books);
        //2 请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    public void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取各个参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 调用BookService.queryById查询图书
        Book book = bookService.queryBookById(id);
        // 把book保存到request域中
        req.setAttribute("book", book);
        // 请求转发到pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取pageNo和pageSize参数
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 调用bookService.page方法分页查询图书
        Page<Book> page = bookService.page(pageNo, pageSize);

        // 赋值访问地址
        page.setUrl("manager/bookServlet?action=page&");

        // 将page保存到request域中
        req.setAttribute("page", page);

        // 请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
