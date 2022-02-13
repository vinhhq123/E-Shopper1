/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import dal.ProductDAO;
import dal.SettingDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import model.Setting;
import model.User;

/**
 *
 * @author Edwars
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "ProductController", urlPatterns = {"/product/list", "/procuct/search",
    "/product/getProduct", "/product/update", "/product/add"})
public class ProductController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
            case "/user/search":
                proSearch(request, response);
                break;
            case "/user/list":
                proList(request, response);
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
    
    protected void proList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         int currentPage = 1;
        // Set total records per page is 5
        int recordsPerPage = 5;

        // Get the current page from request if there any
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        
        SettingDAO settingDAO = new SettingDAO();
        UserDAO userDAO = new UserDAO();
        ProductDAO proDAO = new ProductDAO();
        List<Setting> statusList = new ArrayList<>();
        List<Setting> categoryList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        List<Product> proList = new ArrayList<>();
        
        try {
            statusList = settingDAO.getAllProStatus();
            categoryList = settingDAO.getAllProCategory();
            userList = userDAO.getSaler();
            proList = proDAO.getProByPage(currentPage, recordsPerPage);
            
            int rows = proDAO.getNumberOfRows();
            // Count total number of page
            int numOfPage = rows / recordsPerPage;
            if (rows % recordsPerPage > 0) {
                numOfPage++;
            }
            request.setAttribute("SettingList", statusList);
            request.setAttribute("SettingList", categoryList);
            request.setAttribute("UserList", userList);
            request.setAttribute("ProList", proList);
            request.setAttribute("numOfPage", numOfPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
            request.setAttribute("currentPage", currentPage);
            request.getRequestDispatcher("/admin/ProductList.jsp").forward(request, response);
            
        } catch (Exception ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
        
    }
    protected void proSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchField = request.getParameter("table_search").trim();
        String category = request.getParameter("category");
        String status = request.getParameter("status");
        String saler = request.getParameter("saler");
        if (status == null) {
            status = "";
        }

        SettingDAO settingDAO = new SettingDAO();
        UserDAO userDAO = new UserDAO();
        ProductDAO proDAO = new ProductDAO();
        List<Setting> statusList = new ArrayList<>();
        List<Setting> categoryList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        List<Product> proList = new ArrayList<>();

        try {

            proList = proDAO.searchPro(searchField, category, status, saler);
            userList = userDAO.getSaler();
            categoryList = settingDAO.getAllSetting();
            statusList = settingDAO.getAllSetting();
            request.setAttribute("searchValue", searchField);
            request.setAttribute("valueCategory", category);
            request.setAttribute("valueStatus", status);
            request.setAttribute("valueSaler", saler);
            request.setAttribute("CategoryList", categoryList);
            request.setAttribute("StatusList", statusList);
            request.setAttribute("UserList", userList);
            request.setAttribute("searchNow", true);
            request.getRequestDispatcher("./admin/ProductList.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("./admin/Error.jsp").forward(request, response);
        }
    }

}
