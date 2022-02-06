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
import model.User;

/**
 *
 * @author Edwars
 */
public class UserDAO extends DBContext{
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;
    
    public void addUser(User user)throws SQLException{
        String sql = "INSERT INTO user (fullname, title, gender, phone, address, aid, avatar) VALUES (?,?,?,?,?,?,?);";
        try {
            connection = getConnection();
            preparedStatement.setString(1, user.getFullname());
            preparedStatement.setString(2, user.getTitle());
            preparedStatement.setBoolean(3, user.isGender());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getAvatar());
            preparedStatement.setInt(7, user.getAid());
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
        }}
    
}
