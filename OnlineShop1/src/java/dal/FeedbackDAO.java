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
import java.util.ArrayList;
import java.util.List;
import model.Feedback;
import model.Order;
import java.sql.SQLException;
import model.Product;
import model.User;

/**
 *
 * @author HL2020
 */
public class FeedbackDAO extends DBContext{
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;
    
    public List<Feedback> getFeedbackByPage(int currentPage, int recordsPerPage) {

        List<Feedback> feedbacks = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;
        Feedback feed = null;

        try {
            String sql = "select * from feedback LIMIT " + start + " ," + recordsPerPage;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                feed = new Feedback();             
                 feed.setFeedbackId(results.getInt("feedbackId"));
                 feed.setCustomerId(results.getInt("customerId"));
                 feed.setRatedStart(results.getInt("ratedStar"));
                 feed.setProductID(results.getInt("productId"));
                 feed.setFeedbackStatus(results.getInt("feedbackStatus"));
                 feed.setFeedbackContent(results.getString("feedbackContent"));
                 feed.setUpdatedDate(results.getDate("updatedDate"));
                 feed.setFeedbackContent(results.getString("title"));
                 feed.setFeedbackContent(results.getString("note"));
                 feedbacks.add(feed);
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
        return feedbacks;
        
    }
    public int getNumberOfRows() throws Exception {

        int rows = 0;
        String sql = "select COUNT(feedbackId) from feedback;";

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                rows = results.getInt(1);
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

        return rows;
    }
       public List<Product> getAllProduct() {

        List<Product> product = new ArrayList<>();
        Product pro = null;

        try {
            String sql = "SELECT * FROM product; ";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
//                user = new User();
//                user.setUid(results.getInt("uid"));
//                user.setEmail(results.getString("email"));
//                user.setFullname(results.getString("fullname"));
//                user.setPhone(results.getString("phone"));
//                users.add(user);
                pro = new Product();
                pro.setPid(results.getInt("productId"));
                pro.setTitle(results.getString("title"));
                product.add(pro);
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
        return product;
    }
}


