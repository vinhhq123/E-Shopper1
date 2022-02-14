/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import dal.ProductDAO;
import dal.SettingDAO;
import dal.UserDAO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Product;
import model.Setting;
import model.User;

/**
 *
 * @author Edwars
 */
public class AddProductController extends HttpServlet {

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
            out.println("<title>Servlet AddProductController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    UserDAO useDB = new UserDAO();
    SettingDAO setDB = new SettingDAO();
    ProductDAO proDB = new ProductDAO();
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
            request.getRequestDispatcher("./admin/ProductAdd.jsp").forward(request, response);
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
        String title = "";
        String error = "";
        String lprice= "";
        String sprice= "";
        String feature = "";
        String breif = "";
        String category = "";
        String saler = "";
        String status = "";
        String update = "";
        String quan = "";
        String successMessage = "";
        String base64Image = "";
        int proStaus = 0;
        
        title = request.getParameter("title");
        lprice = request.getParameter("lprice");
        sprice = request.getParameter("sprice"); 
        feature = request.getParameter("feature");
        breif = request.getParameter("breif");
        category = request.getParameter("category");
        saler = request.getParameter("saler");
        status = request.getParameter("status");
        update = request.getParameter("update");
        quan = request.getParameter("quan");
        // If active radio button is selected
        if (request.getParameter("status").equals("1")) {
            // In Setting table in the database 
            // user has accountStatus is Active and registered 
            // but not verified has settingId = 26
            proStaus = 17;
        } else {
            // In Setting table in the database 
            // user has accountStatus is Inactive and registered 
            // but not verified has settingId = 6
            proStaus = 16;
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

        Product pro = null;
        // Get session
        HttpSession session = request.getSession();

        try {
            request.setAttribute("titleValue", title);
            request.setAttribute("lpriceValue", lprice);
            request.setAttribute("spriceValue", sprice);
            request.setAttribute("featureValue", feature);
            request.setAttribute("breifValue", breif);
            request.setAttribute("categoryValue", category);
            request.setAttribute("salerValue", saler);
            request.setAttribute("statusValue", status);
            request.setAttribute("updateValue", update);
            request.setAttribute("quanValue", quan);
             if (filePart.getSize() == 0) {
                error = "Please choose an image !!!";
                request.setAttribute("errorImage", error);
                request.getRequestDispatcher("./admin/AddNewUser.jsp").forward(request, response);
            } else {
                // Insert into Account table with user entered email and default password is 123

                int checkAddPro = proDB.addProduct(title, Double.parseDouble(lprice),Double.parseDouble(sprice), feature ,inputStream, Integer.parseInt(category),Integer.parseInt(saler),Integer.parseInt(status),Integer.parseInt(quan), breif, Date.valueOf(update));
                if (checkAddPro > 0) {
//                            boolean checkEmail = accountDAO.sendEmailActivation(email, name);
//                            if (checkEmail) {
                    successMessage = "Add new product successfuly .";
                    String avatar = proDB.getLastInsertedProduct().getThumbnail();
                    session.setAttribute("messageAddSuccess", successMessage);
                    request.setAttribute("imageValue", avatar);
                    request.getRequestDispatcher("./admin/ProductAdd.jsp").forward(request, response);
                    //request.getRequestDispatcher("userList").forward(request, response);
//                                response.sendRedirect("userList");

//                            }
                    // CHUA CHECK SEND EMAIL FAIL
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("./admin/Error.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
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
