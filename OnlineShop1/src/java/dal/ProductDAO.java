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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.Product;
import model.User;

/**
 *
 * @author Edwars
 */
public class ProductDAO extends DBContext {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;

    public int getNumberOfRows() throws Exception {

        int rows = 0;
        String sql = "select COUNT(productId) from product;";

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

    public List<Product> getProByPage(int currentPage, int recordsPerPage) {

        List<Product> products = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;
        Product product = null;

        try {
            String sql = "select * from product LIMIT " + start + " ," + recordsPerPage;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                product = new Product();
                product.setPid(results.getInt("productId"));
                product.setTitle(results.getString("title"));
                product.setLprice(results.getDouble("list_price"));
                product.setSprice(results.getDouble("sale_price"));
                product.setFeatured(results.getString("featured"));
                product.setProductStatus(results.getInt("productStatus"));
                product.setCategoryID(results.getInt("categoryId"));
                product.setSalesId(results.getInt("salesId"));
                product.setQuantity(results.getInt("quantity"));
                product.setUpdatedDate(results.getDate("updatedDate"));
//                user.setAvatar(results.getString("avatar"));
                products.add(product);
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
        return products;
    }

    public List<Product> searchPro(String key, String cat, String status, String sale) throws Exception {

        List<Product> products = new ArrayList<>();
        String sql = "SELECT productId,title,list_price,sale_price,featured,categoryId,u.uid as salesID, s.settingStatus as productStatus,quantity,updatedDate \n"
                + "FROM product as p join setting as s on p.productStatus = s.settingstatus join user as u on p.salesId = u.uid  "
                + "where (title like '%" + key + "%' "
                + "or feature like '%" + key + "%' ";
        Product product = null;
        if (cat != "") {
            sql += " and p.categoryId = " + cat;
        }
        if (status != "") {
            sql += " and s.settingStatus = " + status;
        }
        if (sale != "") {
            sql += " and u.uid = " + sale;
        }

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                product = new Product();
                product.setPid(results.getInt("productId"));
                product.setTitle(results.getString("title"));
                product.setLprice(results.getDouble("list_price"));
                product.setSprice(results.getDouble("sale_price"));
                product.setFeatured(results.getString("featured"));
                product.setProductStatus(results.getInt("productStatus"));
                product.setCategoryID(results.getInt("categoryId"));
                product.setSalesId(results.getInt("salesId"));
                product.setQuantity(results.getInt("quantity"));
                product.setUpdatedDate(results.getDate("updatedDate"));
                products.add(product);
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
        return products;
    }

    public Product getProductById(int pid) throws Exception {

        String sql = "SELECT productid, thumbnail, title, list_price, sale_price, featured, productStatus, categoryId, breif_information, quantity, updatedDate, salesId FROM product \n"
                + " where productid = " + pid;
        System.out.println(sql);
        Product product = null;
        String noImage = "";

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                product = new Product();
                product = new Product();
                product.setPid(results.getInt("productId"));
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
                    product.setThumbnail(base64Image);
                } else {
                    product.setThumbnail(noImage);
                }
                product.setTitle(results.getString("title"));
                product.setLprice(results.getDouble("list_price"));
                product.setSprice(results.getDouble("sale_price"));
                product.setFeatured(results.getString("featured"));
                product.setProductStatus(results.getInt("productStatus"));
                product.setCategoryID(results.getInt("categoryId"));
                product.setBreif(results.getString("breif_information"));
                product.setQuantity(results.getInt("quantity"));
                product.setUpdatedDate(results.getDate("updatedDate"));
                product.setSalesId(results.getInt("salesId"));
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
        return product;
    }

    public int updateProduct(String title, Double lprice, Double sprice, String feature, InputStream thumbnail, int category, int saleid, int status, int quantity, String breif, Date update, int pid) throws SQLException {
        String sql = "UPDATE product\n"
                + "SET title = ?,list_price = ?,sale_price = ?,featured = ?,categoryId =?, salesId = ?, productStatus = ?,quantity =?,breif_information=?,updatedDate= ?";
        int row = 0;
        if (thumbnail != null) {
            sql += " ,thumbnail = ? WHERE productId = ?;";
        } else {
            sql += " WHERE productId = ?;";
        }
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setDouble(2, lprice);
            preparedStatement.setDouble(3, sprice);
            preparedStatement.setString(4, feature);
            preparedStatement.setInt(5, category);
            preparedStatement.setInt(6, saleid);
            preparedStatement.setInt(7, status);
            preparedStatement.setInt(8, quantity);
            preparedStatement.setString(9, breif);
            preparedStatement.setDate(10, update);

            if (thumbnail != null) {
                preparedStatement.setBlob(11, thumbnail);
                preparedStatement.setInt(12, pid);
            } else {
                preparedStatement.setInt(11, pid);
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

    public int addProduct(String title, Double lprice, Double sprice, String feature, InputStream thumbnail, int category, int saleid, int status, int quantity, String breif, Date update) throws SQLException {
        String sql = "INSERT INTO product\n"
                + "(thumbnail,title,list_price,sale_price,featured,productStatus,categoryId,breif_information,quantity,updatedDate,salesId)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?,?,?,?);";
        int row = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (thumbnail != null) {
                preparedStatement.setBlob(1, thumbnail);
            }
            preparedStatement.setString(2, title);
            preparedStatement.setDouble(3, lprice);
            preparedStatement.setDouble(4, sprice);
            preparedStatement.setString(5, feature);
            preparedStatement.setInt(6, status);
            preparedStatement.setInt(7, category);
            preparedStatement.setString(8, breif);
            preparedStatement.setInt(9, quantity);
            preparedStatement.setDate(10, update);
            preparedStatement.setInt(11, saleid);
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

    public Product getLastInsertedProduct() throws Exception {

        String sql = "SELECT * FROM product ORDER BY productid DESC LIMIT 1; ";
        System.out.println(sql);
        Product pro = null;
        String noImage = "";

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                pro = new Product();
                pro.setPid(results.getInt("productid"));
                pro.setTitle(results.getString("title"));
                pro.setLprice(results.getDouble("list_price"));
                pro.setSprice(results.getDouble("sale_price"));
                pro.setFeatured(results.getString("featured"));
                pro.setProductStatus(results.getInt("productStatus"));
                pro.setCategoryID(results.getInt("categoryId"));
                pro.setBreif(results.getString("breif_information"));
                pro.setQuantity(results.getInt("quantity"));
                pro.setUpdatedDate(results.getDate("updatedDate"));
                pro.setSalesId(results.getInt("salesId"));

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
                    pro.setThumbnail(base64Image);
                } else {
                    pro.setThumbnail(noImage);
                }
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
        return pro;
    }

    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();
        Product product = null;

        try {
            String sql = "select * from product ";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                product = new Product();
                product.setPid(results.getInt("productId"));
                product.setTitle(results.getString("title"));
                product.setSprice(results.getDouble("sale_price"));
                product.setProductStatus(results.getInt("productStatus"));
                product.setCategoryID(results.getInt("categoryId"));
                product.setSalesId(results.getInt("salesId"));
                product.setQuantity(results.getInt("quantity"));
                product.setUpdatedDate(results.getDate("updatedDate"));
                products.add(product);
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
        return products;
    }

    public int updateProductQuantity(int quantity, int pid) throws SQLException {
        String sql = "UPDATE product SET quantity = ? WHERE productId = ?;";
        int row = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, pid);
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
