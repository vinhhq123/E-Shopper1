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
import model.Account;
/**
 *
 * @author CHANHSIRO
 */
public class LoginDAO extends DBContext {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;
    public Account getAccount(String email, String password) {
        try {
            String sql = "SELECT a.email, a.password FROM onlineshop1.account as a\n" +
                            "WHERE a.email = ? and a.password = ?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            results = preparedStatement.executeQuery();
            if(results.next())
            {
                Account account = new Account();
                account.setEmail(email);
                account.setPassword(password);
                return account;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
