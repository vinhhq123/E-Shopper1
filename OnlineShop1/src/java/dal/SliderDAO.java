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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PostList;
import model.Slider;
import model.User;
/**
 *
 * @author CHANHSIRO
 */
public class SliderDAO extends DBContext {
    
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet result = null;
    
    public void insertSlider(String s_title, InputStream s_imgage, String s_backlink, String s_notes) {

        String sql = "INSERT INTO onlineshop1.slider (`s_title`, `s_imgage`, `s_backlink`, `s_notes`)\n" +
                     "VALUES (?, ?, ?, ?)";
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, s_title);
            ps.setBlob(2, s_imgage);
            ps.setString(3, s_backlink);
            ps.setString(4, s_notes);
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
    
    public ArrayList<Slider> getSliders(String search) throws Exception {
        ArrayList<Slider> sliders = new ArrayList<>();
        try {
            String sql = "select s.slider_id, s.s_title, s.s_imgage, s.s_backlink, s.s_notes \n" +
"from onlineshop1.slider as s WHERE s.s_title like ?";
            System.out.println(sql);
            String noImage = "";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + search + "%");
            result = ps.executeQuery();

            while (result.next()) {
                Slider slider = new Slider();
                slider.setS_id(result.getInt("slider_id"));
                slider.setS_title(result.getString("s_title"));
                slider.setBack_link(result.getString("s_backlink"));
                slider.setS_notes(result.getString("s_notes"));
                Blob blob = result.getBlob("s_imgage");
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
                    slider.setS_image(base64Image);
                } else {
                    slider.setS_image(noImage);
                }
                sliders.add(slider);
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
        return sliders;
    }
    
    public Slider getSliderById(int s_id) throws Exception {
        try {
            String sql = "select s.s_title, s.s_imgage, s.s_backlink, s.s_notes \n" +
                         "from onlineshop1.slider as s WHERE s.slider_id = ?";
            System.out.println(sql);
            String noImage = "";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, s_id);
            result = ps.executeQuery();

            while (result.next()) {
                Slider slider = new Slider();
                slider.setS_id(s_id);
                slider.setS_title(result.getString("s_title"));
                slider.setBack_link(result.getString("s_backlink"));
                slider.setS_notes(result.getString("s_notes"));
                Blob blob = result.getBlob("s_imgage");
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
                    slider.setS_image(base64Image);
                } else {
                    slider.setS_image(noImage);
                }
                return slider;
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
        return null;
    }
    
    public void update(int s_id, String s_title, InputStream s_image, String s_backlink, String s_notes) {
        try {
            String sql = "UPDATE `onlineshop1`.`slider` \n" +
                         "SET `s_title` = ?, `s_imgage` = ?, `s_backlink` = ?, `s_notes` = ? \n" +
                         "WHERE (`slider_id` = ?);";
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, s_title);
            ps.setBlob(2, s_image);
            ps.setString(3, s_backlink);
            ps.setString(4, s_notes);
            ps.setInt(5, s_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
