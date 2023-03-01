package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

public interface BookDao {
    // 添加图书
    public int addBook(Book book);

    // 删除图书
    public int deleteBookById(Integer id);

    // 修改图书
    public int updateBook(Book book);

    // 利用ID来查询图书
    public Book queryBookById(Integer id);

    // 查询多个图书
    public List<Book> queryBooks();

    // 查询所有图书的数量
    Integer queryForPageTotalCountByPrice();

    // 查询所有图书数据
    List<Book> queryForItems(Integer pageNo, Integer pageSize);

    // 查询排序返回的页面总数
    public Integer queryForPageTotalCountByPrice(int min, int max);

    //
    public List<Book> queryForPageItemsByPrice(int pageNo, int pageSize, int min, int max);
}
