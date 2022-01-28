/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * SOURCE : https://www.geeksforgeeks.org/how-to-add-image-to-mysql-database-using-servlet-and-jdbc/
 */
package controller.admin;

import dal.AccountDAO;
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
import model.Account;
import model.User;

/**
 *
 * @author VINH
 */
// This annotation defines the maximum
// file size which can be taken.
@MultipartConfig(maxFileSize = 16177215)
public class AddNewUserController extends HttpServlet {

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
//            out.println("<title>Servlet AddNewUserController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet AddNewUserController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("./admin/AddNewUser.jsp").forward(request, response);
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

        String email = "";
        String error = "";
        String phone = "";
        String name = "";
        String title = "";
        String address = "";
        String role = "";
        String successMessage = "";
        boolean genderbit = true;
        int accountStaus = 0;

        email = request.getParameter("email").trim();
        phone = request.getParameter("phone");
        name = request.getParameter("fullname").trim();
        title = request.getParameter("title").trim();
        address = request.getParameter("address").trim();
        role = request.getParameter("role");

        // If female radio button is selected
        if (request.getParameter("gender").equals("0")) {
            genderbit = false;
        }

        // If active radio button is selected
        if (request.getParameter("status").equals("1")) {
            // In Setting table in the database 
            // user has accountStatus is Active and registered 
            // but not verified has settingId = 26
            accountStaus = 26;
        } else {
            // In Setting table in the database 
            // user has accountStatus is Inactive and registered 
            // but not verified has settingId = 6
            accountStaus = 6;
        }

        // Input stream of the upload file
        InputStream inputStream = null;
        // Obtains the upload file
        // part in this multipart request
        Part filePart = request.getPart("image");

        if (filePart != null) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            // Obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }

        UserDAO userDAO = new UserDAO();
        AccountDAO accountDAO = new AccountDAO();
        User user = null;
        Account account = null;

        try {
            Account checkAccountEmail = accountDAO.checkAccountExist(email);
            request.setAttribute("emailValue", email);
            request.setAttribute("phoneValue", phone);
            request.setAttribute("nameValue", name);
            request.setAttribute("titleValue", title);
            request.setAttribute("addressValue", address);
            request.setAttribute("roleValue", role);
            if (checkAccountEmail != null) {
                error = "This email has already been registered !!!";
                request.setAttribute("error", error);
                request.getRequestDispatcher("./admin/AddNewUser.jsp").forward(request, response);
            } else if (filePart.getSize() == 0) {
                error = "Please choose an image !!!";
                request.setAttribute("errorImage", error);
                request.getRequestDispatcher("./admin/AddNewUser.jsp").forward(request, response);
            } else {
                // Insert into Account table with user entered email and default password is 123
                int convertedRole = Integer.parseInt(role);
                boolean checkAddAccount = accountDAO.addAccountWithoutPassword(email, accountStaus,convertedRole);
                // if add new account succesfully
                if (checkAddAccount) {
                    try {
                        // Get accountID user has just inserted into Account table
                        int justInsertedAid = accountDAO.getLastInsertedAccountId();
                        // Inser into User table 
                        // with the accountID
                        //  is the aid you have just inserted 
                        // in Account table
                        int checkAddUser = userDAO.addNewUserWithImage(name, title, genderbit,
                                phone, address, inputStream, justInsertedAid);
                        // If add new user successfully
                        if (checkAddUser > 0) {
                            successMessage = "Add new User successfuly .";
                            // Get session
                            HttpSession session = request.getSession();
                            session.setAttribute("messageAddSuccess", successMessage);
                            //request.getRequestDispatcher("userList").forward(request, response);
                            response.sendRedirect("userList");
                        }

                    } catch (Exception ex) {
                        Logger.getLogger(AddNewUserController.class.getName()).log(Level.SEVERE, null, ex);
                        request.getRequestDispatcher("./admin/Error.jsp").forward(request, response);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddNewUserController.class.getName()).log(Level.SEVERE, null, ex);
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
