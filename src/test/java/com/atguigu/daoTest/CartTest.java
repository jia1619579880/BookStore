package com.atguigu.daoTest;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {
    Cart cart = new Cart();
    @Test
    public void addItem() {
        cart.addItem(new CartItem(1,"three body",1,new BigDecimal(65),new BigDecimal(65)));
        cart.addItem(new CartItem(1,"three body",1,new BigDecimal(65),new BigDecimal(65)));
        cart.addItem(new CartItem(2,"data structure and Algorithm",1,new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        cart.addItem(new CartItem(1,"three body",1,new BigDecimal(65),new BigDecimal(65)));
        cart.addItem(new CartItem(1,"three body",1,new BigDecimal(65),new BigDecimal(65)));
        cart.addItem(new CartItem(2,"data structure and Algorithm",1,new BigDecimal(100),new BigDecimal(100)));

        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.addItem(new CartItem(1,"three body",1,new BigDecimal(65),new BigDecimal(65)));
        cart.addItem(new CartItem(1,"three body",1,new BigDecimal(65),new BigDecimal(65)));
        cart.addItem(new CartItem(2,"data structure and Algorithm",1,new BigDecimal(100),new BigDecimal(100)));

        System.out.println(cart);

        cart.clear();

        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        cart.addItem(new CartItem(1,"three body",1,new BigDecimal(65),new BigDecimal(65)));
        cart.addItem(new CartItem(1,"three body",1,new BigDecimal(65),new BigDecimal(65)));
        cart.addItem(new CartItem(2,"data structure and Algorithm",1,new BigDecimal(100),new BigDecimal(100)));


        cart.updateCount(1,1000);

        System.out.println(cart);


    }
}