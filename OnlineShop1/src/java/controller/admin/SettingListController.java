/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.SettingDAO;
import dal.SettingTypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Setting;
import model.SettingType;
import model.User;

/**
 *
 * @author VINH
 */
public class SettingListController extends HttpServlet {

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

        String fullname = "";
        // Set current page when user access Setting List page is the first page
        int currentPage = 1;
        // Set total records per page is 5
        int recordsPerPage = 5;

        // Get the current page from request
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        
        SettingDAO settingDAO = new SettingDAO();
        SettingTypeDAO settingTypeDAO = new SettingTypeDAO();
        List<Setting> settingList = new ArrayList<>();
        List<String> settingType = new ArrayList<>();
        User  user = new User();

        // Read setting properties file
        ResourceBundle rb = ResourceBundle.getBundle("resources.setting");
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = rb.getString(key);
            settingType.add(value);
        }

        try {

            // Get session
            HttpSession session = request.getSession();
            // get account saved in session 
            user = (User) session.getAttribute("account");

            // Get all the setting in the selected page
            settingList = settingDAO.getSettingByPage(currentPage, recordsPerPage);
            fullname = user.getFullname();

            // Count total number of Setting in the Setting table
            int rows = settingDAO.getNumberOfRows();
            // Count total number of page
            int numOfPage = rows / recordsPerPage;
            if (rows % recordsPerPage > 0) {
                numOfPage++;
            }

            request.setAttribute("SettingList", settingList);
            session.setAttribute("fullname", fullname);
            request.setAttribute("types", settingType);
            request.setAttribute("numOfPage", numOfPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
            request.setAttribute("currentPage", currentPage);
            request.getRequestDispatcher("./admin/SettingList.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(SettingListController.class.getName()).log(Level.SEVERE, null, ex);
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
