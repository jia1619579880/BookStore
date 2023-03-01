package com.atguigu.daoTest;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BookDaoTest {

    BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        System.out.println(bookDao.addBook(new Book(1, "三体", "刘慈欣", new BigDecimal(56), 9999999, 0, null)));
    }

    @Test
    public void deleteBookById() {
        System.out.println(bookDao.deleteBookById(21));
    }

    @Test
    public void updateBook() {
        System.out.println(bookDao.updateBook(new Book(1,  "java从入门到放弃" , "国哥" , new BigDecimal(80) , 9999 , 9 , null)));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCountByPrice());
    }

    @Test
    public void queryForItems() {
        List<Book> books = bookDao.queryForItems(8, Page.PAGE_SIZE);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));
    }

    @Test
    public void queryForItemsByPrice() {
        List<Book> books = bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE,10,50);
        for (Book book : books) {
            System.out.println(book);
        }
    }

}