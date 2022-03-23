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
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import model.Cart;
import model.Items;

/**
 *
 * @author Edwars
 */
public class CartDao extends DBContext {
     private Connection connection = null;
     private PreparedStatement preparedStatement = null;
     private ResultSet results = null;
    
    public int addToCartDetails( int pid, int quan, float price, int uid)throws Exception{
         
       String sql = "INSERT INTO `onlineshop1`.`cartdetail` (`pid`, `quantity`, `toalPriceOfProduct`, `uid`,`sta`)"
                     + "VALUES (?,?,?,?,1)";
    int row = 0;
    try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, pid);
            preparedStatement.setInt(2, quan);
            preparedStatement.setFloat(3, price);
            preparedStatement.setInt(4, uid);
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
    
    public int changeSta( int uid)throws Exception{
        String sql = "UPDATE cartdetail\n"
                + "SET sta = 0 WHERE sta = 1 and `uid` = ?;";
       int row = 0;
       try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, uid);
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
    
    public int changeQuan(int quan,float price, int cid)throws Exception{
        String sql = "UPDATE cartdetail\n"
                + "SET quantity = ?, toalPriceOfProduct=? WHERE `cartDetailId` = ?;";
       int row = 0;
       try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, quan);
            preparedStatement.setFloat(2, price);
            preparedStatement.setInt(3, cid);
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
    
    public List<Items> getCartDetail(int uid){
        
        List<Items> item = new ArrayList<>();
        try {
            String sql = "select * from cartdetail where sta = 1 uid =  " + uid;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                Items i = new Items();
                i.setCid(results.getInt("cartDetailId"));
                i.getProduct().setPid(results.getInt("pid"));
                i.setQuantity(results.getInt("quantity"));
                i.setTotalPrice(results.getFloat("toalPriceOfProduct"));
                i.setUid(results.getInt("uid"));
                i.setSta(results.getInt("sta"));
//                user.setAvatar(results.getString("avatar"));
                item.add(i);
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
        return item;
    }
}
