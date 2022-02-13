/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import dal.UserDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.User;

/**
 *
 * @author HL2020
 */
@MultipartConfig(maxFileSize = 16177215)
public class CusEditController extends HttpServlet {

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
//            out.println("<title>Servlet CusEditController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CusEditController at " + request.getContextPath() + "</h1>");
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

        UserDAO userDAO = new UserDAO();
        User currentUser = new User();

        try {
            currentUser = userDAO.getUserByUserId(userId);
            if (!currentUser.isGender()) {
                userGender = "0";
            }
            
            String userRole = currentUser.getRole()+"";
            String accountStatus = currentUser.getAccountStatus() + "";
            
            request.setAttribute("currentUser", currentUser);
            request.setAttribute("userGender", userGender);
            request.setAttribute("currentUserRole", userRole);
            request.setAttribute("currentUserStatus",accountStatus );
            request.getRequestDispatcher("./admin/CusDetail.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CusEditController.class.getName()).log(Level.SEVERE, null, ex);
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
       String error = "";
        String phone = "";
        String name = "";
        String title = "";
        String address = "";
        String role = "";
        String successMessage = "";
        int uid = 0;
        boolean genderbit = true;
        int accountStaus = 0;
        int accountId = 0;

        phone = request.getParameter("phone");
        name = request.getParameter("name").trim();
        title = request.getParameter("title").trim();
        address = request.getParameter("address").trim();
        role = request.getParameter("role");
        uid = Integer.parseInt(request.getParameter("uid"));
        accountId = Integer.parseInt(request.getParameter("accountId"));

        // If female radio button is selected
        if (request.getParameter("gender").equals("0")) {
            genderbit = false;
        }
        // If active radio button is selected
        if (request.getParameter("status").equals("1")) {
            // In Setting table in the database 
            // user has accountStatus is Active and registered 
            //  has settingId = 24
            accountStaus = 24;
        } else {
            // In Setting table in the database 
            // user has accountStatus is Inactive
            // but verified has settingId = 6
            accountStaus = 6;
        }

        // Input stream of the upload file
        InputStream inputStream = null;
        // Obtains the upload file
        // part in this multipart request
        Part filePart = request.getPart("image");

        if (filePart.getSize() != 0) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            // Obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        } else {
            inputStream = null;
        }

        UserDAO userDAO = new UserDAO();
        // Get session
        HttpSession session = request.getSession();
        int accountRole = Integer.parseInt(role);
        try {
            int checkUpdateUser = userDAO.updateUser(name, title, genderbit,
                    phone, address, inputStream, accountStaus, accountRole, uid);
            if (checkUpdateUser > 0) {
                successMessage = "Update customer succesfully ";
                session.setAttribute("successEditMessage", successMessage);
                response.sendRedirect("cusEdit?uid=" + uid);
            } else {
                error = "Unexcepted error occured. Please try again later !!!";
                session.setAttribute("errorEditMessage", error);
                response.sendRedirect("cusEdit?uid=" + uid);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CusEditController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("./admin/Error.jsp").forward(request, response);
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
