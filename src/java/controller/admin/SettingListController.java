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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Setting;
import model.SettingType;

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
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet SettingListController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet SettingListController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
        
                String error = "";
        String fullname = "";
        int currentPage = 1;
        int recordsPerPage = 3;

        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
//
//        AccountDAO accountDAO = new AccountDAO();
//        ContactDAO contactDAO = new ContactDAO();
        SettingDAO settingDAO = new SettingDAO();
        SettingTypeDAO settingTypeDAO = new SettingTypeDAO();
        List<Setting> settingList = new ArrayList<>();
        List<SettingType> settingType = new ArrayList<>();

        try {
//            Account account = accountDAO.getAccountByEmail("vinhhqse05532@fpt.edu.vn");
//            int aid = account.getAid();
//            Contact contact = contactDAO.getContactByAccountID(aid);
            fullname = "Temp";

            settingList = settingDAO.getAllSetting();
            settingType = settingTypeDAO.getAllSettingTye();

//            int rows = settingDAO.getNumberOfRows();
//            int numOfPage = rows / recordsPerPage;
//            if (numOfPage % recordsPerPage > 0) {
//                numOfPage++;
//            }
            request.setAttribute("SettingList", settingList);
            request.setAttribute("fullname", fullname);
            request.setAttribute("types", settingType);
//            request.setAttribute("numOfPage", numOfPage);
//            request.setAttribute("recordsPerPage", recordsPerPage);
//            request.setAttribute("currentPage", currentPage);
            request.getRequestDispatcher("./admin/SettingList.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(SettingListController.class.getName()).log(Level.SEVERE, null, ex);
            error = "Đã xảy ra lỗi khi tải CSDL";
            request.setAttribute("message", error);
            request.getRequestDispatcher("Error.jsp").forward(request, response);

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
