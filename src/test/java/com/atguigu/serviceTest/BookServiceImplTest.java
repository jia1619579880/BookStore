package com.atguigu.serviceTest;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceImplTest {

    BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"社会我尧哥，人狠话不多","尧哥",new BigDecimal(9999),65655,644,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"社会我佳哥，人狠话不多","佳哥",new BigDecimal(100000),65655,644,null));

    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        bookService.queryBooks().forEach(System.out::println);
    }

    @Test
    public void page(){
        Page<Book> page = bookService.page(1, Page.PAGE_SIZE);
        System.out.println(page);
    }
}