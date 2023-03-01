package com.atguigu.service;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

public interface BookService {
    // 添加图书
    public void addBook(Book book);

    // 删除图书
    public void deleteBookById(Integer id);

    // 修改图书
    public void updateBook(Book book);

    // 利用ID来查询图书
    public Book queryBookById(Integer id);

    // 查询多个图书
    public List<Book> queryBooks();

    Page<Book> page(Integer pageNo, Integer pageSize);

    public Page<Book> pageByPrice(Integer pageNo, Integer pageSize,int min,int max);
}
