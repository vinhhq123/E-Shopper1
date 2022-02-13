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

/**
 *
 * @author CHANHSIRO
 */
public class PostDAO extends DBContext {
        private Connection connection = null;
        private PreparedStatement preparedStatement = null;
        private ResultSet results = null;

    public void insertPost(String postContent, String thumbnail, String postTitle, int postAuthor) {
        
        String sql = "INSERT INTO `onlineshop3`.`post` (`thumbnail`, `postTitle`, `postContent`, `postAuthor`) \n" +
"        VALUES (?, ?, ?, ?)";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, thumbnail);
            preparedStatement.setString(2, postTitle);
            preparedStatement.setString(3, postContent);
            preparedStatement.setInt(4, postAuthor);
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
}