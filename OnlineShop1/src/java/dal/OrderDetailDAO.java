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
            String sql = "select * from orderdetails where orderId = " + orderId;
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

}
