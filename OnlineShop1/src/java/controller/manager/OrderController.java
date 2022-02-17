/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

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
import model.Order;
import model.User;

/**
 *
 * @author VINH
 */
@WebServlet(name = "OrderController", urlPatterns = {"/order/list"})
public class OrderController extends HttpServlet {

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
//            out.println("<title>Servlet OrderController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet OrderController at " + request.getContextPath() + "</h1>");
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
            case "/order/list":
                getListOrders(request, response);
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

    protected void getListOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set current page when user access User List page is the first page
        int currentPage = 1;
        // Set total records per page is 5
        int recordsPerPage = 5;

        // Get the current page from request if there any
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        UserDAO userDAO = new UserDAO();
        OrderDAO orderDAO = new OrderDAO();
        SettingDAO settingDAO = new SettingDAO();
        List<Order> orders = new ArrayList<>();
        List<User> customers = new ArrayList<>();
        List<User> sales = new ArrayList<>();
        List<String> orderStatuses = new ArrayList<>();
        try {

            
            customers = userDAO.getAllUserByRole(5);
            sales = userDAO.getAllUserByRole(3);
            orders = orderDAO.getOrderByPage(currentPage, recordsPerPage);
            orderStatuses = settingDAO.getSettingOrderValue();
            int rows = orderDAO.getNumberOfRows();
            // Count total number of page
            int numOfPage = rows / recordsPerPage;
            if (rows % recordsPerPage > 0) {
                numOfPage++;
            }

            request.setAttribute("Orders", orders);
            request.setAttribute("Customers", customers);
            request.setAttribute("Sales", sales);
            request.setAttribute("OrderStatuses", orderStatuses);
            request.setAttribute("numOfPage", numOfPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
            request.setAttribute("currentPage", currentPage);
            request.getRequestDispatcher("/admin/OrderList.jsp").forward(request, response);

        } catch (Exception ex) {
            System.out.println("Exception getListOrders ===== " + ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }

    }
}
