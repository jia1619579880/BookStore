package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.*;

public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    /**
     * 添加商品到购物车
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());

        if (item == null){
            items.put(cartItem.getId(),cartItem);
        } else {
            item.setCount(item.getCount() + 1);     // 数量加1
            item.setTotalPrice(item.getTotalPrice().multiply(new BigDecimal(item.getCount())));     //总价格更新
        }
    }

    /**
     * 删除购物车商品
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id,Integer count){
        CartItem item = items.get(id);
        if (item != null){
            item.setCount(count);     // 数量更新
            item.setTotalPrice(item.getTotalPrice().multiply(new BigDecimal(item.getCount())));     //总价格更新
        }
    }


    public Integer getTotalCount() {
        Integer totalCount = 0;

        for (CartItem cartItems : items.values()){
            totalCount += cartItems.getCount();
        }

        return totalCount;
    }



    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = BigDecimal.valueOf(0);

        for (CartItem cartItems : items.values()){
            totalPrice = totalPrice.add(cartItems.getTotalPrice());
        }

        return totalPrice;
    }



    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public Cart() {
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
