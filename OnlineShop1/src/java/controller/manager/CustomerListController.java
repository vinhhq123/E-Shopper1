/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;
import controller.admin.SettingListController;
import dal.AccountDAO;
import dal.SettingDAO;
import dal.SettingTypeDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Setting;
import model.SettingType;
import model.User;
import dal.CustomerDAO;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author HL2020
 */
public class CustomerListController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        int aid =0;
        User user = new User();
        CustomerDAO cusDAO = new CustomerDAO();
        AccountDAO accountDAO = new AccountDAO();
        Account account = new Account();
        List<User> userList = new ArrayList<>();
        List<Account> accountList = new ArrayList<>();
                 try {

//            settingList = settingDAO.getAllSetting();
//            userList = userDAO.getUserByPage(currentPage, recordsPerPage);
            accountList = accountDAO.getAllAccount();
             userList = cusDAO.getAllCustomer();
//            HttpSession session = request.getSession();
//            account = (Account) session.getAttribute("account");
//            aid = account.getAid();
//            user = userDAO.getUserByAccountId(aid);
//            fullname = user.getFullname();

//            int rows = userDAO.getNumberOfRows();
//            // Count total number of page
//            int numOfPage = rows / recordsPerPage;
//            if (rows % recordsPerPage > 0) {
//                numOfPage++;
//            }

//            request.setAttribute("SettingList", settingList);
            request.setAttribute("AccountList", accountList);
            request.setAttribute("UserList", userList);
//            session.setAttribute("fullname", fullname);
//            request.setAttribute("numOfPage", numOfPage);
//            request.setAttribute("recordsPerPage", recordsPerPage);
//            request.setAttribute("currentPage", currentPage);
            request.getRequestDispatcher("./manager/CustomerList.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(CustomerListController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("./admin/Error.jsp").forward(request, response);
        }       
      
        }
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
