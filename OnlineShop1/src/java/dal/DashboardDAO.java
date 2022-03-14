/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import dbcontext.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Order;
import model.User;

/**
 *
 * @author CHANHSIRO
 */
public class DashboardDAO extends DBContext{
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet result = null;
    
    public int totalUser(){
        String query = "SELECT COUNT(uid) as 'laa'\n" +
                        "  FROM onlineshop1.user";
        int totalUser;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            
            while (result.next()) {
                totalUser = result.getInt("laa");
                return totalUser;
            }
        } catch (Exception e) {

        }
        return 0;
    }
    
    public ArrayList<Order> getTop3HightestUser(){
        ArrayList<Order> top3order = new ArrayList<>();
        String query = "with x as (SELECT o.customerId as 'i', SUM(o.totalCost) as 'r' FROM onlineshop1.orders as o\n" +
                        "GROUP BY o.customerId)\n" +
                        "SELECT u.fullname, u.email, u.phone, i, r FROM x\n" +
                        "INNER JOIN onlineshop1.user as u on u.uid = i\n" +
                        "order by r desc limit 3";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            
            while (result.next()) {
                Order order = new Order();
                User customer = new User();
                customer.setFullname(result.getString("fullname"));
                customer.setEmail(result.getString("email"));
                customer.setPhone(result.getString("phone"));
                order.setCustomerId(result.getInt("i"));
                order.setTotalCost(result.getInt("r"));
                order.setUser(customer);
                top3order.add(order);
            }
        } catch (Exception e) {

        }
        return top3order;
    }
    
    public int totalRevenue7days(){
        String query = "SELECT SUM(totalCost) as 'totalRevenue' FROM onlineshop1.orders\n" +
                        "WHERE orderDate between ? and ?";
        int totalRevenue;
        long toDay_raw =System.currentTimeMillis();//Today_raw
            long millisOf7days = 86400000 * 7;//7 days
            long _7daysAgo_raw = toDay_raw - millisOf7days; //7 days ago_raw
            java.sql.Date toDay =new java.sql.Date(toDay_raw);
            java.sql.Date _7daysAgo =new java.sql.Date(_7daysAgo_raw);
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setDate(1, _7daysAgo);
            ps.setDate(2, toDay);
            result = ps.executeQuery();
            
            while (result.next()) {
                totalRevenue = result.getInt("totalRevenue");
                return totalRevenue;
            }
        } catch (Exception e) {

        }
        return 0;
    }
    
    public int totalProduct(){
        String query = "SELECT COUNT(productId) as 'totalProduct' FROM onlineshop1.product";
        int totalProduct;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            
            while (result.next()) {
                totalProduct = result.getInt("totalProduct");
                return totalProduct;
            }
        } catch (Exception e) {

        }
        return 0;
    }
}
