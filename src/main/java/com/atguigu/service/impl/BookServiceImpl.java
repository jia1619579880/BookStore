package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {

        return bookDao.queryBooks();
    }

    @Override
    public Page page(Integer pageNo, Integer pageSize) {
        Page<Book> page = new Page<>();


        page.setPageSize(pageSize);

        // 查询总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice();
        page.setPageTotalCount(pageTotalCount);

        // 求总页码
        Integer pageTotal = pageTotalCount % pageSize == 0? pageTotalCount / pageSize : pageTotalCount / pageSize + 1;
        if (pageNo > pageTotal){
            pageNo=pageTotal;
        }
        if (pageNo < 1){
            pageNo=1;
        }
        page.setPageNo(pageNo);

        page.setPageTotal(pageTotal);

        // 查询当前页记录
        Integer begin = (pageNo - 1) * pageSize;
        List<Book> bookList = bookDao.queryForItems(begin, pageSize);
        page.setItems(bookList);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(Integer pageNo, Integer pageSize,int min,int max) {
        Page<Book> page = new Page<>();

        // 获取总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);

        // 获取总页码
// 求总页码
        Integer pageTotal = pageTotalCount % pageSize == 0? pageTotalCount / pageSize : pageTotalCount / pageSize + 1;
        if (pageNo > pageTotal){
            pageNo=pageTotal;
        }
        if (pageNo < 1){
            pageNo=1;
        }
        // 获取当前页面数据
        List<Book> items = bookDao.queryForPageItemsByPrice(pageNo, pageSize, min, max);

        // 设置参数
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setPageTotal(pageTotal);
        page.setPageTotalCount(pageTotalCount);
        page.setItems(items);
        return page;
    }
}
