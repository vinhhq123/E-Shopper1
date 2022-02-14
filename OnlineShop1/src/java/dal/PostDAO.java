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
import java.util.List;
import model.PostList;
import model.User;

/**
 *
 * @author CHANHSIRO
 */
public class PostDAO extends DBContext {
    
    
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet result = null;

    public List<PostList> getBlogSortByDate() {
        List<PostList> list = new ArrayList<>();
        String query = "select p.*, u.uid, u.fullname from post p inner join user u on p.postAuthor = u.uid order by p.postdDate desc";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();

            while (result.next()) {
                PostList p = new PostList();
                p.setPostId(result.getInt("postId"));
                p.setPostTitle(result.getString("postTitle"));
                p.setBreifInformation(result.getString("breifInformation"));
                p.setPostContent(result.getString("postContent"));
                User u = new User(result.getInt("uid"), result.getString("fullname"));
                p.setPostAuthor(u);
                p.setCategory(result.getString("postCategory"));
                p.setFeatured(result.getString("featured"));
                p.setSatatusPL(result.getInt("status"));
                p.setUpdateDate(result.getDate("postdDate"));
                Blob blob = result.getBlob("thumbnail");
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
                    p.setThumbnail(base64Image);
                } else {
                    p.setThumbnail("");
                }
                list.add(p);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public PostList getBlogById(int id) {
        String query = "select p.*, u.uid, u.fullname from post p inner join user u on p.postAuthor = u.uid where p.postId = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            result = ps.executeQuery();

            while (result.next()) {
                PostList p = new PostList();
                p.setPostId(result.getInt("postId"));
                p.setPostTitle(result.getString("postTitle"));
                p.setBreifInformation(result.getString("breifInformation"));
                p.setPostContent(result.getString("postContent"));
                User u = new User(result.getInt("uid"), result.getString("fullname"));
                p.setPostAuthor(u);
                p.setCategory(result.getString("postCategory"));
                p.setFeatured(result.getString("featured"));
                p.setSatatusPL(result.getInt("status"));
                p.setUpdateDate(result.getDate("postdDate"));
                Blob blob = result.getBlob("thumbnail");
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
                    p.setThumbnail(base64Image);
                } else {
                    p.setThumbnail("");
                }
                return p;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public List<PostList> getBlogCategory() {
        List<PostList> listPostCate = new ArrayList<>();
        String query = "SELECT postCategory FROM post";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();

            while (result.next()) {
                listPostCate.add(new PostList(result.getString(1)));
            }
        } catch (Exception e) {

        }
        return listPostCate;
    }

    public List<PostList> getBlogByPostCategory(String id) {
        List<PostList> list = new ArrayList<>();
        String query = "select p.* , u.uid, u.fullname from post p inner join user u on p.postAuthor = u.uid where p.postCategory = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            result = ps.executeQuery();

            while (result.next()) {
                PostList p = new PostList();
                p.setPostId(result.getInt("postId"));
                p.setThumbnail(result.getString("thumbnail"));
                p.setPostTitle(result.getString("postTitle"));
                p.setBreifInformation(result.getString("breifInformation"));
                p.setPostContent(result.getString("postContent"));
                User u = new User(result.getInt("uid"), result.getString("fullname"));
                p.setPostAuthor(u);
                p.setCategory(result.getString("postCategory"));
                p.setFeatured(result.getString("featured"));
                p.setSatatusPL(result.getInt("status"));
                p.setUpdateDate(result.getDate("postdDate"));
                list.add(p);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<PostList> searchBlogByTitle(String search) {
        List<PostList> list = new ArrayList<>();
        String query = "select p.* , u.uid, u.fullname from post p inner join user u on p.postAuthor = u.uid where p.postTitle like ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + search + "%");
            result = ps.executeQuery();

            while (result.next()) {
                PostList p = new PostList();
                p.setPostId(result.getInt("postId"));
                p.setPostTitle(result.getString("postTitle"));
                p.setBreifInformation(result.getString("breifInformation"));
                p.setPostContent(result.getString("postContent"));
                User u = new User(result.getInt("uid"), result.getString("fullname"));
                p.setPostAuthor(u);
                p.setCategory(result.getString("postCategory"));
                p.setFeatured(result.getString("featured"));
                p.setSatatusPL(result.getInt("status"));
                p.setUpdateDate(result.getDate("postdDate"));
                Blob blob = result.getBlob("thumbnail");
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
                    p.setThumbnail(base64Image);
                } else {
                    p.setThumbnail("");
                }
                list.add(p);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<PostList> getBlogByPage(List<PostList> list,
            int start, int end) {
        ArrayList<PostList> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
    
    public void insertPost(String postContent, InputStream thumbnail, String postTitle, int postAuthor) {
        
        String sql = "INSERT INTO `post` (`thumbnail`, `postTitle`, `postContent`, `postAuthor`) \n" +
"        VALUES (?, ?, ?, ?)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setBlob(1, thumbnail);
            ps.setString(2, postTitle);
            ps.setString(3, postContent);
            ps.setInt(4, postAuthor);
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception ==== " + ex);
        } finally {
            try {
                closeConnection(connection);
                closePrepareStatement(ps);
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
            ps = connection.prepareStatement(sql);
            result = ps.executeQuery();
            
            while (result.next()) {
                PostList post = new PostList();
                post.setPostContent(result.getString("postContent"));
                post.setPostTitle(result.getString("postTitle"));
                User user = new User();
                user.setFullname(result.getString("fullname"));
                post.setUser(user);
                Blob blob = result.getBlob("thumbnail");
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
                closePrepareStatement(ps);
                //closeResultSet(results);

            } catch (SQLException | IOException ex) {
                System.out.println("Exception ==== " + ex);
            }
        }
        return postlist;
    }
}
    

