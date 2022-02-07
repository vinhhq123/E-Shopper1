/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;
import dbcontext.DBContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.User;
/**
 *
 * @author HL2020
 */
public class CustomerDAO extends DBContext{
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;
     public List<User> getAllCustomer() throws Exception {

        List<User> users = new ArrayList<>();
        String sql = "SELECT s.uid,s.fullname,s.title,s.gender,s.phone,s.address,s.aid from user as s\n" +
                                        "inner join account as d \n" +
                                        "where s.aid=d.aid and d.role=5";
        User user = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                user = new User();
                user.setUid(results.getInt("uid"));
                user.setFullname(results.getString("fullname"));
                user.setTitle(results.getString("title"));
                user.setGender(results.getBoolean("gender"));
                user.setPhone(results.getString("phone"));
                user.setAddress(results.getString("address"));
                user.setAid(results.getInt("aid"));
                users.add(user);
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

        return users;
    }
     public User getUserByAccountId(int aid) throws Exception {

        String sql = "select * from user where aid = " + aid;
        System.out.println(sql);
        User user = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                user = new User();
                user.setUid(results.getInt("uid"));
                user.setFullname(results.getString("fullname"));
                user.setTitle(results.getString("title"));
                user.setGender(results.getBoolean("gender"));
                user.setPhone(results.getString("phone"));
                user.setAddress(results.getString("address"));
                user.setAvatar(results.getString("avatar"));
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

        return user;
    }
     public User getUserByUserId(int uid) throws Exception {

        String sql = "SELECT uid,fullname,title,gender,phone,address,aid \n" +
                        "from user where uid = " + uid;
        System.out.println(sql);
        User user = null;
  

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                user = new User();
                user.setUid(results.getInt("uid"));
                user.setFullname(results.getString("fullname"));
                user.setTitle(results.getString("title"));
                user.setGender(results.getBoolean("gender"));
                user.setPhone(results.getString("phone"));
                user.setAddress(results.getString("address"));
                user.setAid(results.getInt("aid"));
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
        return user;
    }
      public int updateUser(User s) throws SQLException {
        String sql = "update user\n" +
                    "SET fullname = ?, title = ?, gender = ?,phone =? , address= ?\n" +
                    "where uid = ?;";
        int row = 0;
   
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, s.getFullname());
            preparedStatement.setString(2, s.getTitle());
            preparedStatement.setBoolean(3, s.isGender());
            preparedStatement.setString(4, s.getPhone());
            preparedStatement.setString(5, s.getAddress());
            preparedStatement.setInt(6, s.getUid());
           
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
