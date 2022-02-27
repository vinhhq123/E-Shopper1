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

    private List<Items> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<Items> items) {
        this.items = items;
    }

    public List<Items> getItems() {
        return items;
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
    public void removeItem(int id) {
        //if product already exist -> delete
        if (getItemsById(id) != null) {
            items.remove(getItemsById(id));
        }
    }
//total money
    public double getTotalMoney() {
        double t = 0;
        for (Items i : items) {
            t += (i.getQuantity() * i.getPrice());
        }
        return t;
    }

}
