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
import model.SettingType;

/**
 *
 * @author VINH
 */
public class SettingTypeDAO extends DBContext{
    
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;

    public List<SettingType> getAllSettingTye() throws Exception {

        List<SettingType> settingTypes = new ArrayList<>();
        String sql = "select * from settingtype";
        SettingType settingType = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                settingType = new SettingType();
                settingType.setSettingTypeId(results.getInt("settingTypeId"));
                settingType.setTypeName(results.getString("typeName"));
                settingTypes.add(settingType);
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

        return settingTypes;
    }
    
}
