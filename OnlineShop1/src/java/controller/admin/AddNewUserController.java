/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.UserDAO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Base64;
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
 * @author VINH
 */
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
        String gender = "";
        String status = "";
        String successMessage = "";
        String base64Image = "";
        boolean genderbit = true;
        int accountStaus = 0;

        email = request.getParameter("email").trim();
        phone = request.getParameter("phone");
        name = request.getParameter("fullname").trim();
        title = request.getParameter("title").trim();
        address = request.getParameter("address").trim();
        role = request.getParameter("role");
        gender = request.getParameter("gender");
        status = request.getParameter("status");

        // If female radio button is selected
        if (request.getParameter("gender").equals("0")) {
            genderbit = false;
        }

        // If active radio button is selected
        if (request.getParameter("status").equals("1")) {
            // In Setting table in the database 
            // user has accountStatus is Active and registered 
            // but not verified has settingId = 26
            accountStaus = 24;
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
        User user = null;
        // Get session
        HttpSession session = request.getSession();

        try {
            User checkUserExisted = userDAO.checkAccountExist(email);
            request.setAttribute("emailValue", email);
            request.setAttribute("phoneValue", phone);
            request.setAttribute("nameValue", name);
            request.setAttribute("titleValue", title);
            request.setAttribute("addressValue", address);
            request.setAttribute("roleValue", role);
            request.setAttribute("genderValue", gender);
            request.setAttribute("statusValue", status);
            if (checkUserExisted != null) {
                // Get image to display in string
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();
                base64Image = Base64.getEncoder().encodeToString(imageBytes);

                outputStream.close();

                error = "This email has already been registered !!!";
                request.setAttribute("imageValue", base64Image);
                request.setAttribute("error", error);
                request.getRequestDispatcher("./admin/AddNewUser.jsp").forward(request, response);
            } else if (filePart.getSize() == 0) {
                error = "Please choose an image !!!";
                request.setAttribute("errorImage", error);
                request.getRequestDispatcher("./admin/AddNewUser.jsp").forward(request, response);
            } else {
                // Insert into Account table with user entered email and default password is 123
                int convertedRole = Integer.parseInt(role);
                int checkAddUser = userDAO.addNewUserWithImage(email, name, title,
                        genderbit, phone, address, inputStream, convertedRole, accountStaus);
                if (checkAddUser > 0) {
//                            boolean checkEmail = accountDAO.sendEmailActivation(email, name);
//                            if (checkEmail) {
                    successMessage = "Add new User successfuly .";
                    session.setAttribute("messageAddSuccess", successMessage);
                    request.getRequestDispatcher("./admin/AddNewUser.jsp").forward(request, response);
                    //request.getRequestDispatcher("userList").forward(request, response);
//                                response.sendRedirect("userList");

//                            }
                    // CHUA CHECK SEND EMAIL FAIL
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddNewUserController.class.getName()).log(Level.SEVERE, null, ex);
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
