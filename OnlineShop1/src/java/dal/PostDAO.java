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
import model.PostList;
import model.User;

/**
 *
 * @author CHANHSIRO
 */
public class PostDAO extends DBContext {
        private Connection connection = null;
        private PreparedStatement preparedStatement = null;
        private ResultSet results = null;

    public void insertPost(String postContent, InputStream thumbnail, String postTitle, int postAuthor) {
        
        String sql = "INSERT INTO `post` (`thumbnail`, `postTitle`, `postContent`, `postAuthor`) \n" +
"        VALUES (?, ?, ?, ?)";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBlob(1, thumbnail);
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
    
    public ArrayList<PostList> getPostList() throws Exception {
            ArrayList<PostList> postlist = new ArrayList<>();
        try {
        String sql = "select  p.thumbnail, p.postTitle, p.postContent, u.fullname from post as p\n" +
                        "Inner Join user as u ON u.uid = p.postAuthor";
        System.out.println(sql);
        String noImage = "";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();
            
            while (results.next()) {
                PostList post = new PostList();
                post.setPostContent(results.getString("postContent"));
                post.setPostTitle(results.getString("postTitle"));
                User user = new User();
                user.setFullname(results.getString("fullname"));
                post.setUser(user);
                Blob blob = results.getBlob("thumbnail");
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
                    post.setThumbnail(base64Image);
                } else {
                    post.setThumbnail(noImage);
                }
                postlist.add(post);
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
        return postlist;
    }
}