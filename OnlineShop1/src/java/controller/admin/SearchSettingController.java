/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.SettingDAO;

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
import model.Setting;


/**
 *
 * @author VINH
 */
public class SearchSettingController extends HttpServlet {

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

        // Get parameter from request
        String searchField = request.getParameter("table_search").trim();
        String type = request.getParameter("type");
        String status = request.getParameter("status");

        // If user does not filter search by SettingStatus
        if (status == null) {
            status = "";
        }

        SettingDAO settingDAO = new SettingDAO();
        //SettingTypeDAO settingTypeDAO = new SettingTypeDAO();
        List<Setting> settingSearch = new ArrayList<>();
        List<String> settingTypes = new ArrayList<>();

        // Read setting properties file
        ResourceBundle rb = ResourceBundle.getBundle("resources.setting");
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = rb.getString(key);
            settingTypes.add(value);
        }

        try {
            // Search Setting by selected filter and return an arrayList result
            settingSearch = settingDAO.searchSeting(searchField, type, status);

            request.setAttribute("SettingList", settingSearch);
            request.setAttribute("types", settingTypes);
            request.setAttribute("searchValue", searchField);
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
