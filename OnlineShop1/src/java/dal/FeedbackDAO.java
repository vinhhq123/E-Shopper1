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
import java.util.ArrayList;
import java.util.List;
import model.Feedback;
import model.Order;
import java.sql.SQLException;
import java.util.Base64;
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
            String sql = "select * from feedback order by updatedDate desc  LIMIT " + start + " ," + recordsPerPage;
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
        public List<Feedback> searchFeedback(String key, String ratestart, int status, int feedbackId) throws Exception {
        List<Feedback> feeds = new ArrayList<>();
        String sql = "SELECT a.feedbackId,a.customerId,a.ratedStar,a.productId,a.feedbackStatus,s.fullname\n" +
            "from feedback a join User s on s.uid=a.customerId where ";
        if (feedbackId != 0) {
            sql += "a.feedbackId = " + feedbackId;
        } else {
            sql += "s.fullname like '%" + key + "%' ";
        }
        if (ratestart != "") {
            sql += " and a.ratedStar = " + ratestart;
        }
        if (status != 0) {
            sql += " and a.feedbackStatus = " + status;
        }
       

        System.out.println(sql);
        Feedback feed = null;
        try {
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
                feeds.add(feed);
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
        return feeds;
    }
         public Feedback getFeedbackid(int fid) throws Exception {

        String sql = "SELECT f.*,s.settingStatus from feedback as f join setting as s\n" +
                        "on f.feedbackStatus=s.settingId where feedbackId =  " + fid;
        System.out.println(sql);
        Feedback user = null;
        String noImage = "";

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                user = new Feedback();
                user.setFeedbackId(results.getInt("feedbackId"));
                user.setCustomerId(results.getInt("customerId"));
                user.setRatedStart(results.getInt("ratedStar"));
                user.setTitle(results.getString("title"));
                user.setProductID(results.getInt("productId"));
                user.setFeedbackContent(results.getString("feedbackContent"));
                user.setNote(results.getString("note"));

                Blob blob = results.getBlob("image");
                if (blob != null) {

                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                    inputStream.close();
                    outputStream.close();
                    user.setImage(base64Image);
                } else {
                    user.setImage(noImage);
                }

                user.setFeedbackStatus(results.getInt("settingStatus"));
                user.setUpdatedDate(results.getDate("updatedDate"));
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
         public int updateFeedback(String note, int accountStatus,int feedid) throws SQLException {
        String sql = "Update feedback set feedbackStatus = ?,note = ? where feedbackId=?;";      
        int row = 0;
        
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, accountStatus);
            preparedStatement.setString(2, note);
            preparedStatement.setInt(3, feedid);
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


