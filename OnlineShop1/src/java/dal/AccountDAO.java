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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author Edwars
 */
public class AccountDAO extends DBContext {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet results = null;

    public void addAccount(String Email, String Pass) throws SQLException {
        String sql = "INSERT INTO account (email, password, accountStatus, role) VALUES (?, ?, '7', '6');";
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

    public Account checkAccountExist(String Email) throws SQLException {
        String sql = "select * from account\n"
                + "where email = ?\n";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Email);
            results = preparedStatement.executeQuery();
            while (results.next()) {
                return new Account(results.getInt(1), results.getString(2), results.getString(3), results.getInt(4), results.getInt(5));
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

    public List<Account> getAllAccount() throws Exception {

        List<Account> accounts = new ArrayList<>();
        String sql = "select * from account";
        Account account = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                account = new Account();
                account.setAid(results.getInt("aid"));
                account.setEmail(results.getString("email"));
//                account.setPassword(results.getString("password"));
                account.setAccountStatus(results.getInt("accountStatus"));
                account.setRole(results.getInt("role"));
                accounts.add(account);
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

        return accounts;
    }

    public boolean addAccountWithoutPassword(String email, int accountStatus, int role) throws SQLException {
        boolean check = false;
        int results[] = null;
        // accountStatus = 6 : registered but not verified
        String sql = "INSERT INTO account (email, password, accountStatus, role) VALUES (?, '123', ?, ?);";
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setInt(2, accountStatus);
                preparedStatement.setInt(3, role);
                preparedStatement.addBatch();
            } catch (SQLException e) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
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

    public int getLastInsertedAccountId() throws Exception {
        String sql = "SELECT aid FROM account ORDER BY aid DESC LIMIT 1;";
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
        return value;
    }

    public Account getAccountByAccountId(int aid) throws Exception {

        String sql = "select * from account where aid = ?";
        Account account = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, aid);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                account = new Account();
                account.setAid(results.getInt("aid"));
                account.setEmail(results.getString("email"));
//                account.setPassword(results.getString("password"));
                account.setAccountStatus(results.getInt("accountStatus"));
                account.setRole(results.getInt("role"));
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

        return account;
    }

    public boolean getAccountStatusByAccountId(int aid) throws Exception {
        String sql = "select a.aid,a.email,s.settingStatus \n"
                + "from account as a join setting as s \n"
                + "on a.accountStatus = s.settingId\n"
                + "where a.aid = ?;";
        boolean value = false;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, aid);
            results = preparedStatement.executeQuery();

            while (results.next()) {
                value = results.getBoolean("s.settingStatus");
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
        return value;
    }

}
