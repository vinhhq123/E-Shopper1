/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import dbcontext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Order;

/**
 *
 * @author VINH
 */
public class OrderDAO extends DBContext {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;

    public int getNumberOfRows() throws Exception {

        int rows = 0;
        String sql = "select COUNT(orderId) from Orders;";

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                rows = results.getInt(1);
            }
        } catch (Exception ex) {
            System.out.println("Exception ==== " + ex);
        } finally {
            try {
                closeConnection(connection);
                closePrepareStatement(preparedStatement);
                //closeResultSet(results);

            } catch (Exception ex) {
                System.out.println("Exception ==== " + ex);
            }
        }

        return rows;
    }

    public List<Order> getOrderByPage(int currentPage, int recordsPerPage) {

        List<Order> orders = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;
        Order order = null;

        try {
            String sql = "select * from orders LIMIT " + start + " ," + recordsPerPage;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                order = new Order();
                order.setOrderId(results.getInt("orderId"));
                order.setCustomerId(results.getInt("customerId"));
                order.setTotalCost(results.getFloat("totalCost"));
                order.setSalesId(results.getInt("salesId"));
                order.setOrderStatus(results.getInt("orderStatus"));
                order.setOrderDate(results.getDate("orderDate"));
                orders.add(order);
            }
        } catch (Exception ex) {
            System.out.println("Exception ==== " + ex);
        } finally {
            try {
                closeConnection(connection);
                closePrepareStatement(preparedStatement);
                //closeResultSet(results);

            } catch (Exception ex) {
                System.out.println("Exception ==== " + ex);
            }
        }
        return orders;
    }

    public List<Order> searchOrder(String key, String from, String to, int salesId, int status, int orderId) throws Exception {
        List<Order> orders = new ArrayList<>();
        String sql = "select o.orderId,o.orderDate,o.customerId,o.totalCost,o.salesId,o.orderStatus ,u.fullname\n"
                + "from Orders o join User u on u.uid = o.customerId where ";
        if (orderId != 0) {
            sql += "o.orderId = " + orderId;
        } else {
            sql += "u.fullname like '%" + key + "%' ";
        }
        if (salesId != 0) {
            sql += " and o.salesId = " + salesId;
        }
        if (status != 0) {
            sql +=  " and o.orderStatus = " + status;
        }
        if (!from.isEmpty() && !to.isEmpty()) {
            sql += " and (o.orderDate between '" + from + "' and '" + to + "');";
        }
        
        System.out.println(sql);
        Order order = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                order = new Order();
                order.setOrderId(results.getInt("orderId"));
                order.setCustomerId(results.getInt("customerId"));
                order.setTotalCost(results.getFloat("totalCost"));
                order.setSalesId(results.getInt("salesId"));
                order.setOrderStatus(results.getInt("orderStatus"));
                order.setOrderDate(results.getDate("orderDate"));
                orders.add(order);
            }
        } catch (Exception ex) {
            System.out.println("Exception ==== " + ex);
        } finally {
            try {
                closeConnection(connection);
                closePrepareStatement(preparedStatement);
                //closeResultSet(results);

            } catch (Exception ex) {
                System.out.println("Exception ==== " + ex);
            }
        }
        return orders;
    }

}
