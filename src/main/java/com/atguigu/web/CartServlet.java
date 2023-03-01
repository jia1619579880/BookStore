package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取参数 商品编号、商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart!=null){
            // 修改商品到数量方法
            cart.updateCount(id, count);

            // 重定向到原来的地址栏
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
     /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            // 清空购物车
            cart.clear();

            // 重定向到原来的地址栏
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 删除购物车中的商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取参数
        int id = WebUtils.parseInt(req.getParameter("id"),0);

        // 得到session域中的购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart!= null){
            // 删除购物车中的商品
            cart.deleteItem(id);

            // 重定向到原来的地址栏
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 添加商品到购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int id = WebUtils.parseInt(req.getParameter("id"), 0);
//        System.out.println("加入购物车id= "+req.getParameter("id"));
        // 获取请求的参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 调用bookService.queryBookById（ID）：book对象
        Book book = bookService.queryBookById(id);
        // 把图书信息转化成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(),book.getPrice());
        // 调用cart.addItem（商品项）：添加到购物车
//        Cart cart = null;
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
             cart = new Cart();
             req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart );
        req.getSession().setAttribute("lastName",cartItem.getName());

        // 重定向到原来的地址栏
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
