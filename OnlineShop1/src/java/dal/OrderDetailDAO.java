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
import java.sql.SQLException;
import model.OrderDetail;
import java.util.ArrayList;
import java.util.List;
import model.Order;

/**
 *
 * @author VINH
 */
public class OrderDetailDAO extends DBContext {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;

    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) throws Exception {

        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = null;

        try {
            String sql = "select * from orderdetails where orderId = " + orderId +" order by productId asc";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                orderDetail = new OrderDetail();
                orderDetail.setOrderDetailId(results.getInt("orderDetailId"));
                orderDetail.setOrderId(results.getInt("orderId"));
                orderDetail.setProductId(results.getInt("productId"));
                orderDetail.setQuantity(results.getInt("quantity"));
                orderDetail.setSubCost(results.getFloat("subCost"));
                orderDetails.add(orderDetail);
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
        return orderDetails;

    }

    public int updateProductOrderQuantity(int orderDetailId, int quantity, float subTotal)
            throws SQLException {
        String sql = "UPDATE `onlineshop1`.`orderdetails`\n"
                + "SET `quantity` = ? ,`subCost` = ?,`lastUpdated` = CURRENT_DATE() WHERE `orderDetailId` = ?;";
        int row = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setFloat(2, subTotal);
            preparedStatement.setInt(3, orderDetailId);
            row = preparedStatement.executeUpdate();
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
        return row;
    }

    public int deleteOrderDetail(int orderDetailId)
            throws SQLException {
        String sql = "DELETE FROM `onlineshop1`.`orderdetails` WHERE orderDetailId = ? ;;";
        int row = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderDetailId);
            row = preparedStatement.executeUpdate();
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
        return row;
    }

    public OrderDetail getOrderDetailsByOrderDetailId(int orderDetailId) throws Exception {

        OrderDetail orderDetail = null;

        try {
            String sql = "select * from orderdetails where orderDetailId = " + orderDetailId;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                orderDetail = new OrderDetail();
                orderDetail.setOrderDetailId(results.getInt("orderDetailId"));
                orderDetail.setOrderId(results.getInt("orderId"));
                orderDetail.setProductId(results.getInt("productId"));
                orderDetail.setQuantity(results.getInt("quantity"));
                orderDetail.setSubCost(results.getFloat("subCost"));
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
        return orderDetail;

    }

    public int addNewOrderDetail(int orderId, int productId, int quantity, float subTotal)
            throws SQLException {
        String sql = "INSERT INTO `onlineshop1`.`orderdetails`(`orderId`,`productId`,"
                + "`quantity`,`subCost`,`lastUpdated`) VALUES"
                + "(?,?,?,?,CURRENT_DATE());";
        int row = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setFloat(4, subTotal);
            row = preparedStatement.executeUpdate();
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
        return row;
    }
 public OrderDetail getOrderDetailsFeedback(int orderDetailId) throws Exception {

        OrderDetail orderDetail = null;
        Order order = null;
        try {
            String sql = "SELECT b.orderDetailId,a.customerId,b.productId FROM orders as a join orderdetails as b\n"
                    + "on a.orderId=b.orderDetailId where b.orderDetailId=" + orderDetailId;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                orderDetail = new OrderDetail();
                orderDetail.setOrderDetailId(results.getInt("orderDetailId"));
                orderDetail.setProductId(results.getInt("productId"));
                order =  new Order();
                order.setCustomerId(results.getInt("customerId"));
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
        return orderDetail;

    }
 
    
}
