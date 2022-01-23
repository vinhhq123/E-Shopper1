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
import model.Account;

/**
 *
 * @author Edwars
 */
public class AccountDAO extends DBContext{
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;

    
   public void  addAccount(String Email, String Pass)throws SQLException{
        String sql = "INSERT INTO account (email, password, accountStatus, role) VALUES (?, ?, '7', '6');";
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
    
    public Account checkAccountExist(String Email) throws SQLException {
        String sql = "select * from account\n"
                + "where email = ?\n";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Email);
            results = preparedStatement.executeQuery();
            while ( results.next()) {
                return new Account( results.getInt(1),  results.getString(2),  results.getString(3),  results.getInt(4),  results.getInt(5));
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
}
