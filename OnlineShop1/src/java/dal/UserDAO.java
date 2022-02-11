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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Edwars
 */
public class UserDAO extends DBContext {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;

//    public void addUser(User user) throws SQLException {
//        String sql = "INSERT INTO user (fullname, title, gender, phone, address, aid, avatar) VALUES (?,?,?,?,?,?,?);";
//        try {
//            connection = getConnection();
//            preparedStatement.setString(1, user.getFullname());
//            preparedStatement.setString(2, user.getTitle());
//            preparedStatement.setBoolean(3, user.isGender());
//            preparedStatement.setString(4, user.getPhone());
//            preparedStatement.setString(5, user.getAddress());
//            preparedStatement.setString(6, user.getAvatar());
//            preparedStatement.setInt(7, user.getAid());
//            preparedStatement.executeUpdate();
//        } catch (Exception ex) {
//            System.out.println("Exception ==== " + ex);
//        } finally {
//            try {
//                closeConnection(connection);
//                closePrepareStatement(preparedStatement);
//                //closeResultSet(results);
//
//            } catch (Exception ex) {
//                System.out.println("Exception ==== " + ex);
//            }
//        }
//    }

    public User checkAccountExist(String Email) throws SQLException {
        String sql = "select * from user where email = ?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Email);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                return new User(results.getInt(1),
                        results.getString(2), results.getString(3), results.getString(4),
                        results.getString(5),results.getBoolean(6), results.getString(7),
                        results.getString(8),results.getString(9),
                        results.getInt(10), results.getInt(11));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    public void addAccount(String Email, String Pass) throws SQLException {
        String sql = "INSERT INTO user (email, password, accountStatus, role) VALUES (?, ?, '6', '5');";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Email);
            preparedStatement.setString(2, Pass);
            preparedStatement.executeUpdate();
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
    }

    public User getAccount(String email, String password) {
        try {
            String sql = "SELECT email, password ,fullname,uid FROM onlineshop2.user WHERE email = ? and password = ?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            results = preparedStatement.executeQuery();
            if (results.next()) {
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setFullname(results.getString("fullname"));
                user.setUid(results.getInt("uid"));
                return user;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
