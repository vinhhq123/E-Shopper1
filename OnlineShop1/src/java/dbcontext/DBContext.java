/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbcontext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author HL2020
 */
public class DBContext {
     private final String condition = "allowPublicKeyRetrieval=true&verifyServerCertificate=false&useSSL=false&requireSSL=false";

    public Connection getConnection() {
        try {
            // Edit URL , username, password to authenticate with your MS SQL Server
            Class.forName("com.mysql.cj.jdbc.Driver");
            String serverName = "localhost";
            String portNumber = "3306";
            String dbName = "onlineshop1";
            String url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName + "?" + condition;
            String username = "root";
            String password = "12345678";

            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     *
     * @param args
     */
//    public static void main(String[] args) {
//        DBContext db = new DBContext();
//        System.out.println(db.getConnection());
//    }
        public void closeConnection(Connection connection) throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Releases this Statement object's database and JDBC resources immediately 
     * Instead of waiting for this to happen when it is automatically closed
     *
     * @param preparedStatement An object that represents a precompiled SQL statement. 
     * It's a <code>java.sql.PreparedStatement</code> object
     */
    public void closePrepareStatement(PreparedStatement preparedStatement) throws Exception {
        if (preparedStatement != null) {
            preparedStatement.close();

        }
    }

    
     /**
     * Releases this ResultSet object's database and JDBC resources immediately 
     * Instead of waiting for this to happen when it is automatically closed.
     *
     * @param resultSet  A table of data representing a database result set, 
     * Which is usually generated by executing a statement that queries the database 
     * It's a <code>java.sql.ResultSet</code> object
     */
    public void closeResultSet(ResultSet resultSet) throws Exception {
        if (resultSet != null) {
            resultSet.close();

        }
    }
}
