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
import model.User;

/**
 *
 * @author Edwars
 */
public class UserDAO extends DBContext {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO user (fullname, title, gender, phone, address, aid, avatar) VALUES (?,?,?,?,?,?,?);";
        try {
            connection = getConnection();
            preparedStatement.setString(1, user.getFullname());
            preparedStatement.setString(2, user.getTitle());
            preparedStatement.setBoolean(3, user.isGender());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getAvatar());
            preparedStatement.setInt(7, user.getAid());
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

    public User getUserByAccountId(int aid) throws Exception {

        String sql = "select * from user where aid = " + aid;
        System.out.println(sql);
        User user = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                user = new User();
                user.setUid(results.getInt("uid"));
                user.setFullname(results.getString("fullname"));
                user.setTitle(results.getString("title"));
                user.setGender(results.getBoolean("gender"));
                user.setPhone(results.getString("phone"));
                user.setAddress(results.getString("address"));
                user.setAvatar(results.getString("avatar"));
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

        return user;
    }

    public List<User> getAllUser() throws Exception {

        List<User> users = new ArrayList<>();
        String sql = "select * from user";
        User user = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                user = new User();
                user.setUid(results.getInt("uid"));
                user.setFullname(results.getString("fullname"));
                user.setTitle(results.getString("title"));
                user.setGender(results.getBoolean("gender"));
                user.setPhone(results.getString("phone"));
                user.setAddress(results.getString("address"));
                user.setAid(results.getInt("aid"));
//                user.setAvatar(results.getString("avatar"));
                users.add(user);
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

        return users;
    }

    public List<User> searchUser(String key, String role, String status) throws Exception {

        List<User> users = new ArrayList<>();
        String sql = "select u.uid,u.fullname,u.title,u.gender,u.phone,a.email,u.address,"
                + "u.aid,s.settingValue,a.role,s.settingStatus as accountStatus\n"
                + "from user as u join account as a on u.aid = a.aid\n"
                + "				join setting as s on a.accountStatus = s.settingId\n"
                + "where (u.fullname like '%" + key + "%' "
                + "or a.email like '%" + key + "%' "
                + "or u.phone like '%" + key + "%' )";
        User user = null;

        if (role != "") {
            sql += " and a.role = " + role;
        }
        if (status != "") {
            sql += " and s.settingStatus = " + status;
        }

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                user = new User();
                user.setUid(results.getInt("uid"));
                user.setFullname(results.getString("fullname"));
                user.setTitle(results.getString("title"));
                user.setGender(results.getBoolean("gender"));
                user.setPhone(results.getString("phone"));
                user.setAddress(results.getString("address"));
                user.setAid(results.getInt("aid"));
                users.add(user);
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
        return users;
    }

    public List<User> getUserByPage(int currentPage, int recordsPerPage) {

        List<User> users = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;
        User user = null;

        try {
            String sql = "select * from user LIMIT " + start + " ," + recordsPerPage;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                user = new User();
                user.setUid(results.getInt("uid"));
                user.setFullname(results.getString("fullname"));
                user.setTitle(results.getString("title"));
                user.setGender(results.getBoolean("gender"));
                user.setPhone(results.getString("phone"));
                user.setAddress(results.getString("address"));
                user.setAid(results.getInt("aid"));
//                user.setAvatar(results.getString("avatar"));
                users.add(user);
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
        return users;
    }

    public int getNumberOfRows() throws Exception {

        int rows = 0;
        String sql = "select COUNT(uid) from User;";

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

    public int addNewUserWithImage(String fullname, String title, boolean gender,
            String phone, String address, InputStream avatar, int aid) throws SQLException {
        String sql = "INSERT INTO user (fullname, title, gender, phone, address, avatar, aid) VALUES (?,?,?,?,?,?,?);";
        int row = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fullname);
            preparedStatement.setString(2, title);
            preparedStatement.setBoolean(3, gender);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, address);
            if (avatar != null) {
                preparedStatement.setBlob(6, avatar);
            }
            preparedStatement.setInt(7, aid);
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

    public User getUserByUserId(int uid) throws Exception {

        String sql = "select * from user where uid = " + uid;
        System.out.println(sql);
        User user = null;
        String noImage = "";

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                user = new User();
                user.setUid(results.getInt("uid"));
                user.setFullname(results.getString("fullname"));
                user.setTitle(results.getString("title"));
                user.setGender(results.getBoolean("gender"));
                user.setPhone(results.getString("phone"));
                user.setAddress(results.getString("address"));

                Blob blob = results.getBlob("avatar");
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
                    user.setAvatar(base64Image);
                } else {
                    user.setAvatar(noImage);
                }

                user.setAid(results.getInt("aid"));
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

    public int updateUser(String fullname, String title, boolean gender,
            String phone, String address, InputStream avatar, int uid) throws SQLException {
        String sql = "UPDATE onlineshop1.user\n"
                + "SET fullname = ?,title = ?,gender = ?,phone = ?,address = ? ";
        int row = 0;
        if (avatar != null) {
            sql += " ,avatar = ? WHERE uid = ?;";
        } else {
            sql += " WHERE uid = ?;";
        }
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fullname);
            preparedStatement.setString(2, title);
            preparedStatement.setBoolean(3, gender);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, address);
            if (avatar != null) {
                preparedStatement.setBlob(6, avatar);
                preparedStatement.setInt(7, uid);
            } else {
                preparedStatement.setInt(6, uid);
            }
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
