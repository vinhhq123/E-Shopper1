/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.AccountDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.User;

/**
 *
 * @author VINH
 */
public class EditUserController extends HttpServlet {

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
//            out.println("<title>Servlet EditUserController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet EditUserController at " + request.getContextPath() + "</h1>");
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

        String uid = request.getParameter("uid");
        int userId = Integer.parseInt(uid);
        String userGender = "1";
        String userStatus = "1";

        UserDAO userDAO = new UserDAO();
        AccountDAO accountDAO = new AccountDAO();
        User currentUser = new User();
        Account currentAccount = new Account();

        try {
            currentUser = userDAO.getUserByUserId(userId);
            if (!currentUser.isGender()) {
                userGender = "0";
            }

            int currentAccountId = currentUser.getAid();
            currentAccount = accountDAO.getAccountByAccountId(currentAccountId);
            String userEmail = currentAccount.getEmail();
            String userRole = currentAccount.getRole() +"";
            boolean accountStatus = accountDAO.getAccountStatusByAccountId(currentAccountId);
            if (!accountStatus) {
                userStatus = "0";
            }

            request.setAttribute("currentUser", currentUser);
            request.setAttribute("userGender", userGender);
            request.setAttribute("currentUserStatus", userStatus);
            request.setAttribute("currentUserEmail", userEmail);
            request.setAttribute("currentUserRole", userRole);
            request.getRequestDispatcher("./admin/UserDetail.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(EditUserController.class.getName()).log(Level.SEVERE, null, ex);
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
