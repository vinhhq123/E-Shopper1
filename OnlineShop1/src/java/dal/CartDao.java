/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import dbcontext.DBContext;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cart;

/**
 *
 * @author Edwars
 */
public class CartDao extends DBContext {
     private Connection connection = null;
     private PreparedStatement preparedStatement = null;
     private ResultSet results = null;
    public Cart getLastCartID() throws Exception {
        String sql = "select cartid from cart ORDER BY cartid DESC LIMIT 1;";
        System.out.println(sql);
        Cart cart = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                cart = new Cart();
                cart.setCartId(results.getInt("cartid"));
            }
        } catch (Exception ex) {
            System.out.println("Exception ==== " + ex);
        } finally {
            try {
                closeConnection(connection);
                closePrepareStatement(preparedStatement);
                //closeResultSet(results);

            } catch (SQLException | IOException ex) {
                System.out.println("Exception ==== " + ex);
            }
        }
        return cart;
    }
    
    public int addToCart(int cid, float totalPrice, int uid) throws Exception{
       String sql = "INSERT INTO `onlineshop1`.`cart` (`cartid`, `cart_total_price`, `uid`) "
                     + "VALUES (?, ?, ?)";
    int row = 0;
    try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cid);
            preparedStatement.setFloat(2, totalPrice);
            preparedStatement.setInt(3, uid);
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
    
    public int addToCartDetails(int cdid, int pid, int quan, float price, int cid)throws Exception{
         
       String sql = "INSERT INTO `onlineshop1`.`cartdetail` (`cartDetailId`, `pid`, `quantity`, `toalPriceOfProduct`, `cartid`)"
                     + "VALUES (?,?,?,?,?)";
    int row = 0;
    try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cdid);
            preparedStatement.setInt(2, pid);
            preparedStatement.setInt(3, quan);
            preparedStatement.setFloat(4, price);
            preparedStatement.setInt(5, cid);
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
