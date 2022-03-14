/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author VINH
 */
public class Order {

    private int orderId;
    private int customerId;
    private float totalCost;
    private int salesId;
    private int orderStatus;
    private Date orderDate;
    private String salesNote;
    private Date updatedDate;
    private User user; //Get the customers with the highest total cost
    
    public Order() {
    }

    public Order(int orderId, int customerId, float totalCost, int salesId, int orderStatus, Date orderDate, String salesNote) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.totalCost = totalCost;
        this.salesId = salesId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.salesNote = salesNote;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getSalesNote() {
        return salesNote;
    }

    public void setSalesNote(String salesNote) {
        this.salesNote = salesNote;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

}
