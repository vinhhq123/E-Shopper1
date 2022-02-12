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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.User;

/**
 *
 * @author Edwars
 */
public class UserDAO extends DBContext {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;

//    public void addUser(User user) throws SQLException {
//        String sql = "INSERT INTO user (fullname, title, gender, phone, address, aid, avatar) VALUES (?,?,?,?,?,?,?);";
//        try {
//            connection = getConnection();
//            preparedStatement.setString(1, user.getFullname());
//            preparedStatement.setString(2, user.getTitle());
//            preparedStatement.setBoolean(3, user.isGender());
//            preparedStatement.setString(4, user.getPhone());
//            preparedStatement.setString(5, user.getAddress());
//            preparedStatement.setString(6, user.getAvatar());
//            preparedStatement.setInt(7, user.getAid());
//            preparedStatement.executeUpdate();
//        } catch (Exception ex) {
//            System.out.println("Exception ==== " + ex);
//        } finally {
//            try {
//                closeConnection(connection);
//                closePrepareStatement(preparedStatement);
//                //closeResultSet(results);
//
//            } catch (Exception ex) {
//                System.out.println("Exception ==== " + ex);
//            }
//        }
//    }

 public User checkAccountExist(String Email) throws SQLException {
        String sql = "select * from user where email = ?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Email);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                return new User(results.getInt(1),
                        results.getString(2), results.getString(3), results.getString(4),
                        results.getString(5), results.getBoolean(6), results.getString(7),
                        results.getString(8), results.getString(9),
                        results.getInt(10), results.getInt(11));
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

    public void register(String Email, String Pass) throws SQLException {
        String sql = "INSERT INTO user (email, fullname, password, title, gender, phone, address, accountStatus, role) VALUES (?, ?, ?, ?, ?, ?, ?, 23, 5);";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Email);
            preparedStatement.setString(2, Pass);
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

    public User getAccount(String email, String password) {
        try {
            String sql = "SELECT email, password ,fullname,uid FROM onlineshop1.user WHERE email = ? and password = ?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            results = preparedStatement.executeQuery();
            if (results.next()) {
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setFullname(results.getString("fullname"));
                user.setUid(results.getInt("uid"));
                return user;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
                user.setEmail(results.getString("email"));
                user.setFullname(results.getString("fullname"));
                user.setTitle(results.getString("title"));
                user.setGender(results.getBoolean("gender"));
                user.setPhone(results.getString("phone"));
                user.setAddress(results.getString("address"));
                user.setAccountStatus(results.getInt("accountStatus"));
                user.setRole(results.getInt("role"));
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
        String sql = "select u.uid,u.fullname,u.title,u.gender,u.phone,u.email,\n"
                + "u.address,s.settingValue,u.role,s.settingStatus as accountStatus\n"
                + "from user as u join setting as s on u.accountStatus = s.settingId "
                + "where (u.fullname like '%" + key + "%' "
                + "or u.email like '%" + key + "%' "
                + "or u.phone like '%" + key + "%' )";
        User user = null;

        if (role != "") {
            sql += " and u.role = " + role;
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
                user.setEmail(results.getString("email"));
                user.setFullname(results.getString("fullname"));
                user.setTitle(results.getString("title"));
                user.setGender(results.getBoolean("gender"));
                user.setPhone(results.getString("phone"));
                user.setAddress(results.getString("address"));
                user.setAccountStatus(results.getInt("accountStatus"));
                user.setRole(results.getInt("role"));
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

    public User getUserByUserId(int uid) throws Exception {

        String sql = "select u.*,s.settingStatus from user as u join setting as s "
                + " on u.accountStatus = s.settingId where u.uid = " + uid;
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
                user.setEmail(results.getString("email"));
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

                user.setAccountStatus(results.getInt("settingStatus"));
                user.setRole(results.getInt("role"));
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
            String phone, String address, InputStream avatar, int accountStatus,
            int role, int uid) throws SQLException {
        String sql = "UPDATE onlineshop1.user\n"
                + "SET fullname = ?,title = ?,gender = ?,phone = ?,address = ? ,accountStatus = ?,role = ?";
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
            preparedStatement.setInt(6, accountStatus);
            preparedStatement.setInt(7, role);
            if (avatar != null) {
                preparedStatement.setBlob(8, avatar);
                preparedStatement.setInt(9, uid);
            } else {
                preparedStatement.setInt(8, uid);
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

    public int addNewUserWithImage(String email, String fullname, String title, boolean gender,
            String phone, String address, InputStream avatar, int role, int userStatus) throws SQLException {
        String sql = "INSERT INTO user (email,fullname, title, gender, phone, address, "
                + "avatar, role, accountStatus) VALUES (?,?,?,?,?,?,?,?,?);";
        int row = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, fullname);
            preparedStatement.setString(3, title);
            preparedStatement.setBoolean(4, gender);
            preparedStatement.setString(5, phone);
            preparedStatement.setString(6, address);
            if (avatar != null) {
                preparedStatement.setBlob(7, avatar);
            }
            preparedStatement.setInt(8, role);
            preparedStatement.setInt(9, userStatus);
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

    public boolean sendEmailActivation(String reciever, String fullname) {

        boolean check = false;
        final String username = "jb20225@gmail.com";
        final String password = "jb20225@gmail";

        // Setup mail server
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Get the default Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress("jb20225@gmail.com"));
            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(reciever));
            // Set Subject: header field
            message.setSubject("Password for your account !");
            // Send the actual HTML message, as big as you like
            String messageActivation = "Hi " + fullname + ", your username"
                    + " to login to our website is " + reciever + " and "
                    + "your password is 123 .";
            message.setText(messageActivation);

            Transport.send(message);

            System.out.println("Done");

            check = true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return check;
    }
    public List<User> getCusByPage(int currentPage, int recordsPerPage) {

        List<User> users = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;
        User user = null;

        try {
            String sql = "select * from user where role=5 LIMIT " + start + " ," + recordsPerPage;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                user = new User();
                user.setUid(results.getInt("uid"));
                user.setEmail(results.getString("email"));
                user.setFullname(results.getString("fullname"));
                user.setTitle(results.getString("title"));
                user.setGender(results.getBoolean("gender"));
                user.setPhone(results.getString("phone"));
                user.setAddress(results.getString("address"));
                user.setAccountStatus(results.getInt("accountStatus"));
                user.setRole(results.getInt("role"));
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
    public User getLastInsertedUser() throws Exception {

        String sql = "SELECT * FROM user ORDER BY uid DESC LIMIT 1; ";
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
                user.setEmail(results.getString("email"));
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

                user.setAccountStatus(results.getInt("settingStatus"));
                user.setRole(results.getInt("role"));
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

}
