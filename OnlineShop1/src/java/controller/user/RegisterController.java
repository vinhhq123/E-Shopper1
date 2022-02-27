/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import resources.PasswordEncrypt;
import resources.Validate;

/**
 *
 * @author Edwars
 */
public class RegisterController extends HttpServlet {

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
            out.println("<title>Servlet RegisterController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("./user/Register.jsp").forward(request, response);
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
            UserDAO usDB = new UserDAO();
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String title = request.getParameter("title");
            Boolean gen =  request.getParameter("gender").equals("male");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String password = request.getParameter("password");
            String rePassword = request.getParameter("rePassword");
            
            request.setAttribute("name", name);
            request.setAttribute("phone", phone);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.setAttribute("password", password);
            request.setAttribute("repassword", rePassword);
            Validate validate = new Validate();
        try {
            if(usDB.checkAccountExist(email) != null)
            {
                String fail1 = "Email already exists!";
                request.setAttribute("fail1", fail1);
                request.getRequestDispatcher("./user/Register.jsp").forward(request, response);
            }
            else if (validate.checkPhone(phone) == false) {
                // check validate phone number
                String fail2 = "Phone number format is not correct!";
                request.setAttribute("fail2", fail2);
                request.getRequestDispatcher("./user/Register.jsp").forward(request, response);
            }
            else if(validate.checkPassword(password)== false)
            {
                String fail3 = "Password must be at least 6 characters including at least one lowercase letter, one uppercase letter, one number and one special character!";
                request.setAttribute("fail3", fail3);
                request.getRequestDispatcher("./user/Register.jsp").forward(request, response);
            }
            else if(!password.equals(rePassword)){
                String fail4 = "Password and RePassword are not matched!";
                request.setAttribute("fail4", fail4);
                request.getRequestDispatcher("./user/Register.jsp").forward(request, response);
            }
            else{
                PasswordEncrypt encryptedPass = new PasswordEncrypt();
                usDB.register(email, name, encryptedPass.generateEncryptedPassword(password),title,gen,phone,address);
                request.getRequestDispatcher("./user/Login.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
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
