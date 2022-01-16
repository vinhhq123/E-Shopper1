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
    public void insertSetting(Setting s )throws SQLException{
        String sql = "INSERT INTO setting (settingType, settingValue, settingOrder, settingStatus) VALUES (?, ?, ?, ?);";
        

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,s.getSettingType());
            preparedStatement.setString(2,s.getSettingValue());
            preparedStatement.setString(3,s.getSettingOrder());
            preparedStatement.setBoolean(4,s.isSettingStatus());
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
        }}
         public Setting getAllSettingTypeName(int id) throws Exception {

        
        String sql = "select settingId,settingType,settingValue,settingOrder,settingStatus \n" +
                            "from setting\n" +
                            "where settingId="+id;
                           
        Setting setting = null;
        

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();
            //preparedStatement.setInt(1, id);
            
            while (results.next()) {
                if(setting==null){
                setting = new Setting();
                setting.setSettingId(results.getInt("settingId"));
                setting.setSettingType(results.getInt("settingType"));
                setting.setSettingValue(results.getString("settingValue"));
                setting.setSettingOrder(results.getString("settingOrder"));
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
        return null;}
   public List<String> getValuebyType(int id) throws Exception{
        List<String> settingValuebyType = new ArrayList<>();
        String typename="";
        String sql = "select settingValue \n" +
                    "from setting\n" +
                   "where settingType ="+id;
        
         try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();
            
            while (results.next()) {
                typename=results.getString(1);
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
        return settingValuebyType ;
    }
   public int getValuebySetting (int sid) throws Exception{
       String sql = "select settingType  \n" +
                "from setting\n" +
                   "where settingId ="+sid;
       int value=0;
       try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();
            
            while (results.next()) {
                value=results.getInt(1);
            
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
        return value ;
    }
   public void editsetting(Setting s) throws Exception{
   String sql ="UPDATE setting SET settingType = ?, settingValue= ?, \n" +
        "settingOrder = ?, settingStatus = ? \n" +
            "WHERE settingId = ?";
    try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();
            preparedStatement.setInt(1, s.getSettingType());
            preparedStatement.setString(2, s.getSettingValue());
            preparedStatement.setString(3, s.getSettingOrder());
            preparedStatement.setBoolean(4,s.isSettingStatus());
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
        System.out.println("===============================================");
        
  }
   }
          

        
    
