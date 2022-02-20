/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import dal.FeedbackDAO;
import dal.OrderDAO;
import dal.SettingDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Feedback;
import model.Order;
import model.Product;
import model.Setting;
import model.User;

/**
 *
 * @author HL2020
 */
@WebServlet(name = "FeedbacksController", urlPatterns = {"/feedback/list","/feedback/detail","/feedback/getFeedback",
"/feedback/search"})
public class FeedbacksController extends HttpServlet {

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
//            out.println("<title>Servlet FeedbacksController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet FeedbacksController at " + request.getContextPath() + "</h1>");
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
            case "/feedback/list":
                FeedbacksList(request, response);
                break;
            case "/feedback/detail":
               // searchOrder(request, response);
                break;
            case "/feedback/search":
                //getOrderByOrderId(request, response);
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

     protected void FeedbacksList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          int currentPage = 1;
        // Set total records per page is 5
        int recordsPerPage = 2;

        // Get the current page from request if there any
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        UserDAO userDAO = new UserDAO();
        OrderDAO orderDAO = new OrderDAO();
        SettingDAO settingDAO = new SettingDAO();
        FeedbackDAO feedbackDAO = new FeedbackDAO();        
        List<User> customers = new ArrayList<>();
       // List<User> sales = new ArrayList<>();
        List<String> orderStatuses = new ArrayList<>();
        List<Feedback> feedback = new ArrayList<>();
        List<Setting> settingList = new ArrayList<>();
        List<Product> product = new ArrayList<>();
        List<Setting> statusList = new ArrayList<>();
        try {
            statusList = settingDAO.getAllFeedStatus();
            settingList = settingDAO.getAllSetting();
            customers = userDAO.getAllUserByRole(5);
            feedback = feedbackDAO.getFeedbackByPage(currentPage, recordsPerPage);
            product = feedbackDAO.getAllProduct();
            int rows = feedbackDAO.getNumberOfRows();
            // Count total number of page
            int numOfPage = rows / recordsPerPage;
            if (rows % recordsPerPage > 0) {
                numOfPage++;
            }    
            request.setAttribute("StatusList", statusList);
            request.setAttribute("Customers", customers);
            request.setAttribute("Product", product);
            request.setAttribute("Feedback", feedback);
            request.setAttribute("numOfPage", numOfPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("SettingList", settingList);
           
            request.getRequestDispatcher("/admin/FeedbackList.jsp").forward(request, response);

        } catch (Exception ex) {
            System.out.println("Exception  ===== " + ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
     }
    
}
