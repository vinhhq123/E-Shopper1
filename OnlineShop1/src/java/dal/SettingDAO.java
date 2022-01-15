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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Setting;

/**
 *
 * @author VINH
 */
public class SettingDAO extends DBContext {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;

    public List<Setting> getAllSetting() throws Exception {

        List<Setting> settings = new ArrayList<>();
        String sql = "select * from setting";
        Setting setting = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                setting = new Setting();
                setting.setSettingId(results.getInt("settingId"));
                setting.setSettingType(results.getInt("settingType"));
                setting.setSettingValue(results.getString("settingValue"));
                setting.setSettingOrder(results.getString("settingOrder"));
                setting.setSettingStatus(results.getBoolean("settingStatus"));
                settings.add(setting);
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

        return settings;
    }

    public List<Setting> searchSeting(String key, String type, String status) throws Exception {

        List<Setting> settings = new ArrayList<>();
        String sql = "select * from setting where settingValue like " + "'%" + key + "%'";
        Setting setting = null;

        if (type != "") {
            sql += " and settingType like " + "'%" + type + "%'";
        }
        if (status != "") {
            sql += " and settingStatus = " + status;
        }

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                setting = new Setting();
                setting.setSettingId(results.getInt("settingId"));
                setting.setSettingType(results.getInt("settingType"));
                setting.setSettingValue(results.getString("settingValue"));
                setting.setSettingOrder(results.getString("settingOrder"));
                setting.setSettingStatus(results.getBoolean("settingStatus"));
                settings.add(setting);
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
        return settings;
    }

    public boolean changeSettingStatus(int sid, int state) throws SQLException {

        boolean check = false;
        int results[] = null;
        String sql = "UPDATE setting SET settingStatus = ? where settingId = ?";

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);

            try {
                // PICTURE ??????
                preparedStatement.setInt(1, state);
                preparedStatement.setInt(2, sid);
                preparedStatement.addBatch();

            } catch (SQLException ex) {
                System.out.println("SQLException ==== " + ex);
            }

            results = preparedStatement.executeBatch();
            connection.commit();

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
        if (results.length > 0) {
            check = true;
        }
        return check;
    }

    public List<Setting> findSettings(int currentPage, int recordsPerPage) {

        List<Setting> settings = new ArrayList<>();
        int start = currentPage * recordsPerPage - recordsPerPage;
        Setting setting = null;

        try {
            String sql = "select * from setting LIMIT " + start + " ," + recordsPerPage;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                setting = new Setting();
                setting.setSettingId(results.getInt("settingId"));
                setting.setSettingType(results.getInt("settingType"));
                setting.setSettingValue(results.getString("settingValue"));
                setting.setSettingOrder(results.getString("settingOrder"));
                setting.setSettingStatus(results.getBoolean("settingStatus"));
                settings.add(setting);
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
        return settings;
    }

    public int getNumberOfRows() throws Exception {

        int rows = 0;
        String sql = "select COUNT(settingId) from Setting;";

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

}
