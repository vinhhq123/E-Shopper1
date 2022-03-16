/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import dbcontext.DBContext;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.Product;
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
    
    public Double ratedStar(){
        String query = "SELECT ratedStar FROM onlineshop1.feedback";
        int star;
        int totalStar = 0;
        Double count = 0.0;
        List<Integer> listStar = new ArrayList<>();
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            
            while (result.next()) {
                star = result.getInt("ratedStar");
                listStar.add(star);
            }
            for (Integer integer : listStar) {
                totalStar = totalStar + integer;
                count++;
            }
            
            Double forMart = totalStar/count;
            DecimalFormat df = new DecimalFormat("#.##");
            String ratedStar_String = df.format(forMart);
            Double ratedStar = Double.parseDouble(ratedStar_String);
            return ratedStar;
        } catch (Exception e) {

        }
        return 0.0;
    }
    
    public ArrayList<Order> getTop3BestSeller(){
        ArrayList<Order> top3BestSeller = new ArrayList<>();
        String query = "with x as (SELECT o.salesId as 'saleId', COUNT(o.customerId) as 'amount' FROM onlineshop1.orders as o\n" +
                        "GROUP BY o.salesId)\n" +
                        "SELECT u.fullname, u.email, u.phone, saleId, amount FROM x\n" +
                        "INNER JOIN onlineshop1.user as u on u.uid = saleId\n" +
                        "order by amount desc limit 3";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            
            while (result.next()) {
                Order order = new Order();
                User seller = new User();
                seller.setFullname(result.getString("fullname"));
                seller.setEmail(result.getString("email"));
                seller.setPhone(result.getString("phone"));
                order.setCustomerId(result.getInt("saleId"));
                order.setCustomerId(result.getInt("amount"));
                order.setUser(seller);
                top3BestSeller.add(order);
            }
        } catch (Exception e) {

        }
        return top3BestSeller;
    }
    
    public int[] dailyInformation(Date daily, Date daily2){
        String query = "SELECT SUM(totalCost) as 'dailyRevenue', COUNT(customerId) as 'dailyCustomer' \n" +
                        "FROM onlineshop1.orders\n" +
                        "WHERE orderDate BETWEEN ? AND ?";
        int dailyRevenue;
        int dailyCustomer;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setDate(1, daily);
            ps.setDate(2, daily2);
            result = ps.executeQuery();
            
            while (result.next()) {
                dailyRevenue = result.getInt("dailyRevenue");
                dailyCustomer = result.getInt("dailyCustomer");
                int[] inf = {dailyRevenue, dailyCustomer};
                return inf;
            }
        } catch (Exception e) {

        }
        return null;
    }
    
    public int dailyCanceled(Date daily, Date daily2){
        String query = "SELECT COUNT(orderStatus) as 'canceled'\n" +
                        "FROM onlineshop1.orders\n" +
                        "WHERE orderStatus = 22 AND orderDate BETWEEN ? AND ?";
        int canceled;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            ps.setDate(1, daily);
            ps.setDate(2, daily2);
            result = ps.executeQuery();
            
            while (result.next()) {
                canceled = result.getInt("canceled");
                System.out.println(canceled);
                return canceled;
            }
        } catch (Exception e) {

        }
        return 0;
    }
    
    public ArrayList<Order> lastOrders(){
        ArrayList<Order> lastOrders = new ArrayList<>();
        String query = "select o.orderDate, u.fullname, u.email, u.phone, o.totalCost, o.salesId \n" +
                        "from onlineshop1.orders as o\n" +
                        "INNER JOIN onlineshop1.user as u ON u.uid = o.customerId ORDER BY orderDate DESC";
        System.out.println("last orders chua");
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            
            while (result.next()) {
                Order order = new Order();
                User customer = new User();
                order.setOrderDate(result.getDate("orderDate"));
                customer.setFullname(result.getString("fullname"));
                customer.setEmail(result.getString("email"));
                customer.setPhone(result.getString("phone"));
                order.setTotalCost(result.getInt("totalCost"));
                order.setSalesId(result.getInt("salesId"));
                order.setUser(customer);
                lastOrders.add(order);
            }
        } catch (Exception e) {

        }
        return lastOrders;
    }
    
    public ArrayList<Product> bestSellingPro(){
        ArrayList<Product> bestSellingPro = new ArrayList<>();
        String query = "with x as (SELECT o.productId as 'Id', SUM(o.quantity) as 'amount' FROM onlineshop1.orderdetails as o\n" +
                        "GROUP BY o.productId)\n" +
                        "SELECT p.title, amount FROM x\n" +
                        "INNER JOIN onlineshop1.product as p on p.productId = Id\n" +
                        "order by amount desc limit 3";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            
            while (result.next()) {
                Product product = new Product();
                product.setTitle(result.getString("title"));
                product.setQuantity(result.getInt("amount"));
                bestSellingPro.add(product);
            }
        } catch (Exception e) {

        }
        return bestSellingPro;
    }
    
}
