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
        String query = "select p.* , u.fullname from post p inner join user u on p.postAuthor = u.uid";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();

            while (result.next()) {
                PostList p = new PostList();
                p.setPostId(result.getInt(1));
                p.setThumbnail(result.getString(2));
                p.setPostTitle(result.getString(3));
                p.setBreifInformation(result.getString(4));
                p.setPostContent(result.getString(5));
                User u = new User(result.getString(6));
                p.setPostAuthor(u);
                p.setCategory(result.getString(7));
                p.setFeatured(result.getString(8));
                p.setSatatusPL(result.getInt(9));
                p.setUpdateDate(result.getDate(10));
                list.add(p);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public PostList getBlogById(String id) {
        // List<PostList> list = new ArrayList<>();
        String query = "select p.* , u.fullname from post p inner join user u on p.postAuthor = u.uid where p.postId = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            result = ps.executeQuery();

            while (result.next()) {
//                return new PostList(result.getInt(1), 
//                        result.getString(2), 
//                        result.getString(3), 
//                        result.getString(4), 
//                        result.getString(5), 
//                        result.getString(6), 
//                        result.getString(7), 
//                        result.getString(8), 
//                        result.getInt(9), 
//                        result.getDate(10));
                PostList p = new PostList();
                p.setPostId(result.getInt(1));
                p.setThumbnail(result.getString(2));
                p.setPostTitle(result.getString(3));
                p.setBreifInformation(result.getString(4));
                p.setPostContent(result.getString(5));
                User u = new User(result.getString(6));
                p.setPostAuthor(u);
                p.setCategory(result.getString(7));
                p.setFeatured(result.getString(8));
                p.setSatatusPL(result.getInt(9));
                p.setUpdateDate(result.getDate(10));
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
        String query = "select p.* , u.fullname from post p inner join user u on p.postAuthor = u.uid where p.postCategory = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, id);
            result = ps.executeQuery();

            while (result.next()) {
                PostList p = new PostList();
                p.setPostId(result.getInt(1));
                p.setThumbnail(result.getString(2));
                p.setPostTitle(result.getString(3));
                p.setBreifInformation(result.getString(4));
                p.setPostContent(result.getString(5));
                User u = new User(result.getString(6));
                p.setPostAuthor(u);
                p.setCategory(result.getString(7));
                p.setFeatured(result.getString(8));
                p.setSatatusPL(result.getInt(9));
                p.setUpdateDate(result.getDate(10));
                list.add(p);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<PostList> searchBlogByTitle(String search) {
        List<PostList> list = new ArrayList<>();
        String query = "select p.* , u.fullname from post p inner join user u on p.postAuthor = u.uid where p.postTitle like ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + search + "%");
            result = ps.executeQuery();

            while (result.next()) {
                PostList p = new PostList();
                p.setPostId(result.getInt(1));
                p.setThumbnail(result.getString(2));
                p.setPostTitle(result.getString(3));
                p.setBreifInformation(result.getString(4));
                p.setPostContent(result.getString(5));
                User u = new User(result.getString(6));
                p.setPostAuthor(u);
                p.setCategory(result.getString(7));
                p.setFeatured(result.getString(8));
                p.setSatatusPL(result.getInt(9));
                p.setUpdateDate(result.getDate(10));
                list.add(p);
            }
        } catch (Exception e) {

        }
        return list;
    }

    public static void main(String[] args) {
        PostDAO dao = new PostDAO();
        List<PostList> list = dao.getBlogCategory();
        for (PostList postList : list) {
            System.out.println(postList);
        }
    }
}
