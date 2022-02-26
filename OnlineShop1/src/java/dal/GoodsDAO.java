/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import dbcontext.DBContext;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.Feedback;
import model.Product;
import model.Setting;
import model.User;

/**
 *
 * @author hungn
 */
public class GoodsDAO {

    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet result = null;

    public List<Product> getProductByPage(List<Product> list, int start, int end) {
        ArrayList<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Product> getGoodsSortByDate() {
        List<Product> listProduct = new ArrayList<>();
        String query = "select p.productId, p.thumbnail, p.title, p.list_price, p.sale_price,\n"
                + "p.categoryId, p.updatedDate, p.ratedStars, u.uid, u.fullname from product p inner join user u\n"
                + "on p.salesId = u.uid order by p.updatedDate desc";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();

            while (result.next()) {
                Product p = new Product();
                p.setPid(result.getInt("productId"));
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
                p.setTitle(result.getString("title"));
                p.setLprice(result.getDouble("list_price"));
                p.setSprice(result.getDouble("sale_price"));
                p.setCategoryID(result.getInt("categoryId"));
                p.setUpdatedDate(result.getDate("updatedDate"));
                User u = new User(result.getInt("uid"), result.getString("fullname"));
                p.setRatedStars(result.getDouble("ratedStars"));
                p.setAuthor(u);

                listProduct.add(p);
            }
        } catch (Exception e) {

        }
        return listProduct;
    }

    public List<Product> getGoodsByCategory(int id) {
        List<Product> listProduct = new ArrayList<>();
        String query = "select p.productId, p.thumbnail, p.title, p.list_price, p.sale_price,\n"
                + "p.categoryId, p.updatedDate, p.ratedStars, u.fullname, u.uid from product p inner join user u\n"
                + "on p.salesId = u.uid where p.categoryId = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            result = ps.executeQuery();

            while (result.next()) {
                Product p = new Product();
                p.setPid(result.getInt("productId"));
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
                p.setTitle(result.getString("title"));
                p.setLprice(result.getDouble("list_price"));
                p.setSprice(result.getDouble("sale_price"));
                p.setCategoryID(result.getInt("categoryId"));
                p.setUpdatedDate(result.getDate("updatedDate"));
                User u = new User(result.getInt("uid"), result.getString("fullname"));
                p.setRatedStars(result.getDouble("ratedStars"));
                p.setAuthor(u);

                listProduct.add(p);
            }
        } catch (Exception e) {

        }
        return listProduct;
    }

    public List<Product> searchGoodByTitle(String search) {
        List<Product> listProduct = new ArrayList<>();
        String query = "select p.productId, p.thumbnail, p.title, p.list_price, p.sale_price,\n"
                + "p.categoryId, p.updatedDate, p.ratedStars, u.fullname, u.uid from product p inner join user u\n"
                + "on p.salesId = u.uid where p.title like ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + search + "%");
            result = ps.executeQuery();

            while (result.next()) {
                Product p = new Product();
                p.setPid(result.getInt("productId"));
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
                p.setTitle(result.getString("title"));
                p.setLprice(result.getDouble("list_price"));
                p.setSprice(result.getDouble("sale_price"));
                p.setCategoryID(result.getInt("categoryId"));
                p.setUpdatedDate(result.getDate("updatedDate"));
                User u = new User(result.getInt("uid"), result.getString("fullname"));
                p.setRatedStars(result.getDouble("ratedStars"));
                p.setAuthor(u);

                listProduct.add(p);
            }
        } catch (Exception e) {

        }
        return listProduct;
    }

    public Product getGoodsById(int id) {
        String query = "select p.productId, p.thumbnail, p.title, p.list_price, p.sale_price,\n"
                + "p.categoryId, p.updatedDate, p.breif_information, p.ratedStars, u.fullname, u.uid, s.settingId, s.settingValue from product p\n"
                + "inner join user u on p.salesId = u.uid \n"
                + "inner join setting s on p.categoryId = s.settingId\n"
                + "where p.productId = ?";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            result = ps.executeQuery();

            while (result.next()) {
                Product p = new Product();
                p.setPid(result.getInt("productId"));
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
                p.setTitle(result.getString("title"));
                p.setBreif(result.getString("breif_information"));
                p.setLprice(result.getDouble("list_price"));
                p.setSprice(result.getDouble("sale_price"));
                p.setCategoryID(result.getInt("categoryId"));
                p.setUpdatedDate(result.getDate("updatedDate"));
                User u = new User(result.getInt("uid"), result.getString("fullname"));
                p.setAuthor(u);
                Setting s = new Setting(result.getInt("settingId"), result.getString("settingValue"));
                p.setRatedStars(result.getDouble("ratedStars"));
                p.setCate(s);
                return p;
            }
        } catch (Exception e) {

        }
        return null;
    }
    
    public List<Product> getFeaturedGood() {
        List<Product> listProduct = new ArrayList<>();
        String query = "select productId, thumbnail, title, list_price, sale_price,\n" +
"                categoryId, updatedDate, ratedStars from product order by ratedStars desc limit 5";
        try {
            connection = new DBContext().getConnection();
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();

            while (result.next()) {
                Product p = new Product();
                p.setPid(result.getInt("productId"));
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
                p.setTitle(result.getString("title"));
                p.setLprice(result.getDouble("list_price"));
                p.setSprice(result.getDouble("sale_price"));
                p.setCategoryID(result.getInt("categoryId"));
                p.setUpdatedDate(result.getDate("updatedDate"));
                User u = new User(result.getInt("uid"), result.getString("fullname"));
                p.setRatedStars(result.getDouble("ratedStars"));
                p.setAuthor(u);

                listProduct.add(p);
            }
        } catch (Exception e) {

        }
        return listProduct;
    }
//    
//    
//
//    public static void main(String[] args) {
//
//        GoodsDAO dao = new GoodsDAO();
//        Product postList = dao.getGoodsById(1);
//        System.out.println(postList);
//         List<Product> list1 = dao.getFeaturedGood();
//        for (Product list : list1) {
//            System.out.println(list);
//        }
//    }
}
