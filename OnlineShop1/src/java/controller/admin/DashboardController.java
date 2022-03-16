/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.DashboardDAO;
import dal.UserDAO;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;
import model.Product;
import model.User;

/**
 *
 * @author CHANHSIRO
 */
@WebServlet(name = "DashboardController", urlPatterns = {"/dashboard/view"})
public class DashboardController extends HttpServlet {

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
        String action = request.getServletPath();
        System.out.println(action);

        switch (action) {
            case "/dashboard/view":
                postViewDashboard(request, response);
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
    
    protected void postViewDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DashboardDAO dashboardDAO = new DashboardDAO();
            UserDAO userDAO = new UserDAO();
            String daily_raw = request.getParameter("daily");
            String daily_raw2 = request.getParameter("daily2");
            if(daily_raw == null || daily_raw2 == null){
                long toDay_raw2 =System.currentTimeMillis();//Today_raw
                java.sql.Date toDay2 =new java.sql.Date(toDay_raw2);//Date of today
                System.out.println(toDay2);
                int[] dailyInformation = dashboardDAO.dailyInformation(toDay2, toDay2);
                int dailyCaneled = dashboardDAO.dailyCanceled(toDay2, toDay2);
                request.setAttribute("dailyInformation", dailyInformation);
                request.setAttribute("dailyCaneled", dailyCaneled);
                request.setAttribute("toDay2", toDay2);
                
            } else {
                Date toDay = Date.valueOf(daily_raw);
                Date toDay2 = Date.valueOf(daily_raw2);
                int[] dailyInformation = dashboardDAO.dailyInformation(toDay, toDay2);
                
                int dailyCaneled = dashboardDAO.dailyCanceled(toDay, toDay2);
                request.setAttribute("dailyInformation", dailyInformation);
                request.setAttribute("dailyCaneled", dailyCaneled);
                request.setAttribute("toDay", toDay);
                request.setAttribute("toDay2", toDay2);
            }
            ArrayList<Order> lastOrders = dashboardDAO.lastOrders();
            List<User> sellers = userDAO.getAllUserByRole(3);
            ArrayList<Order> top3Customer = dashboardDAO.getTop3HightestUser();
            ArrayList<Order> top3BestSeller = dashboardDAO.getTop3BestSeller();
            ArrayList<Product> bestSellingPro = dashboardDAO.bestSellingPro();
            int totalUser = dashboardDAO.totalUser();
            int totalRevenue7days = dashboardDAO.totalRevenue7days();
            int totalProduct = dashboardDAO.totalProduct();
            Double ratedStar = dashboardDAO.ratedStar();
            request.setAttribute("lastOrders", lastOrders);
            request.setAttribute("top3Customer", top3Customer);
            request.setAttribute("top3BestSeller", top3BestSeller);
            request.setAttribute("bestSellingPro", bestSellingPro);
            request.setAttribute("sellers", sellers);
            request.setAttribute("totalUser", totalUser);
            request.setAttribute("totalRevenue7days", totalRevenue7days);
            request.setAttribute("totalProduct", totalProduct);
            request.setAttribute("ratedStar", ratedStar);
            request.getRequestDispatcher("/Dashboard/dashboard.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
