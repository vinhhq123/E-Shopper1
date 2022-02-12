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
import java.util.ArrayList;
import java.util.List;
import model.Post;

/**
 *
 * @author hungn
 */
public class BlogDAO extends DBContext{

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;

    public List<Post> getAllBlog() throws SQLException {

        List<Post> blog = new ArrayList<>();
        String query = "select * from post";
        //Setting setting = null;

        try {
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(query);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                blog.add(new Post(results.getInt(1),
                        results.getString(2),
                        results.getString(3),
                        results.getString(4),
                        results.getString(5),
                        results.getString(6),
                        results.getDate(7)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return blog;
    }
    
    public Post getBlogById(int postId) throws SQLException {

        String query = "select * from post where postID = ?";
        //Setting setting = null;

        try {
            connection = new DBContext().getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, postId);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                return new Post(results.getInt(1),
                        results.getString(2),
                        results.getString(3),
                        results.getString(4),
                        results.getString(5),
                        results.getString(6),
                        results.getDate(7));
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
