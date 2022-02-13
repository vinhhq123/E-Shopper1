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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Setting;
import model.User;

/**
 *
 * @author VINH
 */
@WebServlet(name = "SettingController", urlPatterns = {"/setting/list","/setting/search"
,"/setting/activate"})
public class SettingController extends HttpServlet {

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
//            out.println("<title>Servlet SettingController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet SettingController at " + request.getContextPath() + "</h1>");
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
        String action = request.getServletPath();
        System.out.println(action);
        switch (action) {
            case "/setting/list":
                settingList(request, response);
                break;
            case "/setting/search":
                searchSetting(request, response);
                break;
            case "/setting/activate":
                changeSettingStatus(request, response);
                break;
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
        doGet(request, response);
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

    protected void settingList(HttpServletRequest request, HttpServletResponse response)
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
        //SettingTypeDAO settingTypeDAO = new SettingTypeDAO();
        List<Setting> settingList = new ArrayList<>();
        List<String> settingType = new ArrayList<>();
        User user = new User();

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
            request.getRequestDispatcher("/admin/SettingList.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(SettingController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);

        }
    }

    protected void searchSetting(HttpServletRequest request, HttpServletResponse response)
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
            request.getRequestDispatcher("/admin/SettingList.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(SettingController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);

        }
    }

    protected void changeSettingStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean check = false;
        // Setting status to save in the database
        int state = 0;
        // Get parameter from request
        int sid = Integer.parseInt(request.getParameter("sid"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        // If status is TRUE(Active) -- > status changes to FALSE (InActive) (state == 0)
        if (!status) {
            state = 1;
        } else {
            // If status is FALSE (InActive) -- > status changes to TRUE(Active) (state == 1)
            state = 0;
        }

        SettingDAO settingDAO = new SettingDAO();

        try {
            check = settingDAO.changeSettingStatus(sid, state);
            // If change setting status successfully
            if (check) {
                response.sendRedirect("list");
            } else {
                // Redirect user to error page
                request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(SettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
