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
import model.SettingType;

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
                setting.setSettingOrder(results.getInt("settingOrder"));
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
            switch (type) {
                case "User Role":
                    sql += " and settingType = 1";
                    break;
                case "Account Status":
                    sql += " and settingType = 2";
                    break;
                case "Post Category":
                    sql += " and settingType = 3";
                    break;
                case "Post Status":
                    sql += " and settingType = 4";
                    break;
                case "Product Category":
                    sql += " and settingType = 5";
                    break;
                case "Product Status":
                    sql += " and settingType = 6";
                    break;
                case "Feedback Status":
                    sql += " and settingType = 7";
                    break;
                case "Order Status":
                    sql += " and settingType = 8";
                    break;

            }
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
                setting.setSettingOrder(results.getInt("settingOrder"));
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

    public List<Setting> getSettingByPage(int currentPage, int recordsPerPage) {

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
                setting.setSettingOrder(results.getInt("settingOrder"));
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

    public void insertSetting(Setting s) throws SQLException {
        String sql = "INSERT INTO setting (settingType, settingValue, settingOrder, settingStatus) VALUES (?, ?, ?, ?);";

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, s.getSettingType());
            preparedStatement.setString(2, s.getSettingValue());
            preparedStatement.setInt(3, s.getSettingOrder());
            preparedStatement.setBoolean(4, s.isSettingStatus());
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

    public Setting getAllSettingTypeName(int id) throws Exception {

        String sql = "select settingId,settingType,settingValue,settingOrder,settingStatus \n"
                + "from setting\n"
                + "where settingId=" + id;

        Setting setting = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();
            //preparedStatement.setInt(1, id);

            while (results.next()) {
                if (setting == null) {
                    setting = new Setting();
                    setting.setSettingId(results.getInt("settingId"));
                    setting.setSettingType(results.getInt("settingType"));
                    setting.setSettingValue(results.getString("settingValue"));
                    setting.setSettingOrder(results.getInt("settingOrder"));
                    setting.setSettingStatus(results.getBoolean("settingStatus"));
                }
            }
            return setting;
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
        System.out.println(setting.toString());
        return null;
    }

    public List<String> getValuebyType(int id) throws Exception {
        List<String> settingValuebyType = new ArrayList<>();
        String typename = "";
        String sql = "select settingValue \n"
                + "from setting\n"
                + "where settingType =" + id;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                typename = results.getString(1);
                settingValuebyType.add(typename);
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
        System.out.println("===============================================");
        return settingValuebyType;
    }

    public int getValuebySetting(int sid) throws Exception {
        String sql = "select settingType  \n"
                + "from setting\n"
                + "where settingId =" + sid;
        int value = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                value = results.getInt(1);

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
        System.out.println("===============================================");
        return value;
    }

    public void editsetting(Setting s) throws Exception {
        String sql = "UPDATE setting SET settingType = ?, settingValue= ?, \n"
                + "settingOrder = ?, settingStatus = ? \n"
                + "WHERE settingId = ?";
        try {
            connection = getConnection();
            //connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            //results = preparedStatement.executeQuery();
            preparedStatement.setInt(1, s.getSettingType());
            preparedStatement.setString(2, s.getSettingValue());
            preparedStatement.setInt(3, s.getSettingOrder());
            preparedStatement.setBoolean(4, s.isSettingStatus());
            preparedStatement.setInt(5, s.getSettingId());
            preparedStatement.executeUpdate();
            //connection.commit();
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
        System.out.println("===============================================");

    }
}
