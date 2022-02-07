/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import controller.admin.SettingDetailEdit;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dal.AccountDAO;
import dal.CustomerDAO;
import dal.UserDAO;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Account;
import model.User;

/**
 *
 * @author HL2020
 */
public class CustomerEditController extends HttpServlet {

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
//            out.println("<title>Servlet CustomerEditController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CustomerEditController at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
         String uid = request.getParameter("uid");
        int userId = Integer.parseInt(uid);
        String userGender = "1";
        
        CustomerDAO cusDAO = new CustomerDAO();
        UserDAO userDAO = new UserDAO();
        AccountDAO accountDAO = new AccountDAO();
        User currentUser = new User();
        Account currentAccount = new Account();
          try {
            currentUser = cusDAO.getUserByUserId(userId);
//           if (!currentUser.isGender()) {
//                userGender = "0";
//            }

            int currentAccountId = currentUser.getAid();
            currentAccount = accountDAO.getAccountByAccountId(currentAccountId);
            String userEmail = currentAccount.getEmail();
            

            request.setAttribute("currentUser", currentUser);
           // request.setAttribute("userGender", userGender);
            request.setAttribute("currentUserEmail", userEmail);
            
            request.getRequestDispatcher("./manager/CustomerDetail.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CustomerEditController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("./manager/Error.jsp").forward(request, response);
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
    //processRequest(request, response);
     try { 
    User s = new User();
     s.setUid(Integer.parseInt(request.getParameter("uid")));
     s.setFullname(request.getParameter("name"));
     s.setTitle(request.getParameter("title"));
     s.setGender(request.getParameter("gender").equals("0"));
     s.setPhone(request.getParameter("phone"));
     s.setAddress(request.getParameter("address"));
     CustomerDAO db = new CustomerDAO();
     db.updateUser(s); 
     response.sendRedirect("listCustomer");
      } catch (Exception ex) {
            Logger.getLogger(CustomerEditController.class.getName()).log(Level.SEVERE, null, ex);
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
