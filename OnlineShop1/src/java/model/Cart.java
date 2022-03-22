/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hungn
 */
public class Cart {
    private int cartId;
    private List<Items> items;
    private int customerId;
    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<Items> items) {
        this.items = items;
    }

    public Cart(int cartId, List<Items> items, int customerId) {
        this.cartId = cartId;
        this.items = items;
        this.customerId = customerId;
    }
    
    public List<Items> getItems() {
        return items;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    public void setItems(List<Items> items) {
        this.items = items;
    }
    
    private Items getItemsById(int id) {
        for (Items i : items) {
            if (i.getProduct().getPid() == id) {
                return i;
            }
        }
        return null;
    }

    public int getQuantityById(int id) {
        return getItemsById(id).getQuantity();
    }
//add product to cart

    public void addItem(Items t) {
        //if product already exist -> get items and increased quantity
        if (getItemsById(t.getProduct().getPid()) != null) {
            Items m = getItemsById(t.getProduct().getPid());
            m.setQuantity(m.getQuantity() + t.getQuantity());
        } else {
            //if product is new -> add to cart
            items.add(t);
        }
    }

    //delete item
    public void reduceItem(Items t) {
        //if product already exist -> get items and increased quantity
        if (getItemsById(t.getProduct().getPid()) != null) {
            Items m = getItemsById(t.getProduct().getPid());
            if(m.getQuantity() > 1){
               m.setQuantity(m.getQuantity() - t.getQuantity());
            }
            else {
            //if product is new -> add to cart
            items.remove(t);
         }
      }
    }    
    //delete item
    public void removeItem(int id) {
        //if product already exist -> delete
        if (getItemsById(id) != null) {
            items.remove(getItemsById(id));
        }
    }
    
    public void removeAllItem() {
        //if product already exist -> delete
        for (Items i : items) {
            items.remove(i);
        }
    }
//total money
    public float getTotalMoney() {
        float t = 0;
        for (Items i : items) {
            t += (i.getQuantity() * i.getPrice());
        }
        return t;
    }

}
