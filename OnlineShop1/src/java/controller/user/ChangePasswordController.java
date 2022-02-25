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
import model.User;
import resources.PasswordEncrypt;
import resources.Validate;

/**
 *
 * @author Edwars
 */
public class ChangePasswordController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    UserDAO userDAO = new UserDAO();
    User user = new User();
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
        request.getRequestDispatcher("./user/ChangePassword.jsp").forward(request, response);
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
        try {
            String email = "";
            String curPass = "";
            String newPass = "";
            String reNewPass = "";
            String successMessage = "";
            
            email = request.getParameter("email");
            curPass = request.getParameter("curPass");
            newPass = request.getParameter("newPass");
            reNewPass = request.getParameter("reNewPass");
            Validate validate = new Validate();
            PasswordEncrypt encryptedPass = new PasswordEncrypt();
            if(userDAO.checkPassword( encryptedPass.generateEncryptedPassword(curPass) ) == null){
                String fail1 = "Your password is not correct!";
                request.setAttribute("fail1", fail1);
                request.getRequestDispatcher("./user/ChangePassword.jsp").forward(request, response);
            }
            else if(validate.checkPassword(newPass)== false)
            {
                String fail2 = "Password must be at least 6 characters including at least one lowercase letter, one uppercase letter, one number and one special character!";
                request.setAttribute("fail2", fail2);
                request.getRequestDispatcher("./user/ChangePassword.jsp").forward(request, response);
            }
            else if(!newPass.equals(reNewPass)){
                String fail3 = "Password and RePassword are not matched!";
                request.setAttribute("fail3", fail3);
                request.getRequestDispatcher("./user/ChangePassword.jsp").forward(request, response);
            }
            else{
                int changePass = userDAO.ChangePass(encryptedPass.generateEncryptedPassword(newPass), email);
                if(changePass > 0){
                    successMessage = "You have successfully changed your password!";
                    request.getRequestDispatcher("./user/ChangePassword.jsp").forward(request, response);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
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
