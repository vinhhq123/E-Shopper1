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

/**
 *
 * @author CHANHSIRO
 */
public class DashboardDAO extends DBContext{
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet result = null;
    
    public int totalUser(){
        String query = "SELECT COUNT(uid) as 'laa'\n" +
                        "  FROM onlineshop1.user";
        int totalUser;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            
            while (result.next()) {
                totalUser = result.getInt("laa");
                return totalUser;
            }
        } catch (Exception e) {

        }
        return 0;
    }
}
