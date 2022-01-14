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
import model.Setting;

/**
 *
 * @author VINH
 */
public class SettingDAO extends DBContext{
    
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
}
