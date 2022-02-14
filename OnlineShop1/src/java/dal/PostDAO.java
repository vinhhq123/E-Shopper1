/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import com.mysql.cj.xdevapi.PreparableStatement;
import dbcontext.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

   
    
    public static void main(String[] args) {
        PostDAO dao = new PostDAO();
        //List<PostList> list = dao.getBlogByPostCategory("1");
        PostList p = dao.getBlogById(1);
//        for (PostList postList : list) {
//            System.out.println(postList);
//        }
        System.out.println(p);
    }
}
