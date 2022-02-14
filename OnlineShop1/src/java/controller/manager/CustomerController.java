/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import dal.SettingDAO;
import dal.UserDAO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Setting;
import model.User;
import resources.PasswordEncrypt;

/**
 *
 * @author HL2020
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "CustomerController", urlPatterns = {"/customer/list", "/customer/search",
    "/customer/getcustomer", "/customer/update", "/customer/add"})
public class CustomerController extends HttpServlet {

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
            case "/customer/list":
                ListCustomer(request, response);
                break;
            case "/customer/search":
                SearchCustomer(request, response);
                break;
            case "/customer/getcustomer":
                GetCustomerByID(request, response);
                break;
            case "/customer/update":
                UpdateCustomer(request, response);
                break;
            case "/customer/add":
                addnewCustomer(request, response);
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

     protected void ListCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SettingDAO settingDAO = new SettingDAO();
        UserDAO userDAO = new UserDAO();
        List<Setting> settingList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
      // Set total records per page is 4
         int Page1 = 1;  int Page2 = 4;
        // Get the current page from request if there any
        if (request.getParameter("currentPage") != null) {
            Page1 = Integer.parseInt(request.getParameter("currentPage"));
        }
        try {
            settingList = settingDAO.getAllSetting();
            userList = userDAO.getCusByPage(Page1, Page2);           
            int rows = userDAO.getNumberOfRowsCus();
            // Count total number of page
            int totalPage = rows / Page2;
            if (rows % Page2 > 0) {totalPage++;}
            
            request.setAttribute("SettingList", settingList);
            request.setAttribute("UserList", userList);
            request.setAttribute("numOfPage", totalPage);
            request.setAttribute("recordsPerPage", Page2);
            request.setAttribute("currentPage", Page1);
            request.getRequestDispatcher("/admin/CusList.jsp").forward(request, response);
            
        } catch (Exception ex) {
            //Logger.getLogger(CusListController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
     }
  protected void SearchCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        SettingDAO settingDAO = new SettingDAO();
        UserDAO userDAO = new UserDAO();
        List<Setting> settingList = new ArrayList<>();
        List<User> userList = new ArrayList<>(); 
        // get 
        
        String tablesearch = request.getParameter("table_search").trim();
        String rolecustomer = request.getParameter("role");
        String status = request.getParameter("status");

        if (status == null) {
            status = "";
        }

        try {
            
            userList = userDAO.searchUser(tablesearch,"5", status);
            settingList = settingDAO.getAllSetting();
            request.setAttribute("searchValue", tablesearch);
            request.setAttribute("valueRole", rolecustomer);
            request.setAttribute("valueStatus", status);
            request.setAttribute("SettingList", settingList);
            request.setAttribute("UserList", userList);
            request.setAttribute("searchNow", true);
            request.getRequestDispatcher("/admin/CusList.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }}
      
  
   protected void GetCustomerByID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       //
        UserDAO userDAO = new UserDAO();
        User currentCustomer = new User();
        String uid = request.getParameter("uid");
        int userId = Integer.parseInt(uid);
        String CustomerGender = "1";

        try {
            currentCustomer = userDAO.getUserByUserId(userId);
            if (!currentCustomer.isGender()) {
                CustomerGender = "0";
            }
            
            String userRole = currentCustomer.getRole()+"";
            String Status = currentCustomer.getAccountStatus() + "";           
            request.setAttribute("currentUser", currentCustomer);
            request.setAttribute("userGender", CustomerGender);
            request.setAttribute("currentUserRole", userRole);
            request.setAttribute("currentUserStatus",Status );
            request.getRequestDispatcher("/admin/CusDetail.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    protected void addnewCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Import
        UserDAO userDAO = new UserDAO();
        User user = null;
        PasswordEncrypt passwordEncrypt = new PasswordEncrypt();
        // Get session
        HttpSession session = request.getSession();
         // Input stream of the upload file
        InputStream inputImage = null;
        
        
        String email = "";
        String fullname = "";
        String phone = "";      
        String title = "";
        String address = "";
        String role = "5";
        String gender = "";
        String status = "";
        String success = "";
        String base64Image = "";
        String error = "";
        boolean gendercheck = true;
        int accSta = 0;
        email = request.getParameter("email").trim();
        phone = request.getParameter("phone");
        fullname = request.getParameter("fullname").trim();
        title = request.getParameter("title").trim();
        address = request.getParameter("address").trim();
        gender = request.getParameter("gender");
        status = request.getParameter("status");

        // If female radio button is selected
        if (request.getParameter("gender").equals("0")) {
            gendercheck = false;
        }
        if (request.getParameter("status").equals("1")) {
            accSta = 24;
        } else {accSta = 6;}

       
        // Obtains the upload file
        // part in this multipart request
        
        
        Part filePart = request.getPart("image");
        if (filePart != null) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            // Obtains input stream of the upload file
            inputImage = filePart.getInputStream();
        }

        
        

        try {
            User checkUserExisted = userDAO.checkAccountExist(email);
            request.setAttribute("emailValue", email);
            request.setAttribute("phoneValue", phone);
            request.setAttribute("nameValue", fullname);
            request.setAttribute("titleValue", title);
            request.setAttribute("addressValue", address);           
            request.setAttribute("genderValue", gender);
            request.setAttribute("statusValue", status);
            if (checkUserExisted != null) {
                // Get image to display in string
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputImage.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();
                base64Image = Base64.getEncoder().encodeToString(imageBytes);

                outputStream.close();

                error = "This email has already been registered !!!";
                request.setAttribute("imageValue", base64Image);
                request.setAttribute("error", error);
                request.getRequestDispatcher("/admin/Addnewcustomer.jsp").forward(request, response);
            } else if (filePart.getSize() == 0) {
                error = "Please choose an image !!!";
                request.setAttribute("errorImage", error);
                request.getRequestDispatcher("/admin/Addnewcustomer.jsp").forward(request, response);
            } else {
                // Insert into Account table with user entered email and default password is 123
                int convertedRole = Integer.parseInt(role);
                String password = passwordEncrypt.generateEncryptedPassword("123");
                int checkAddUser = userDAO.addNewUserWithImage(email, fullname, title,
                        gendercheck, phone, address, inputImage, convertedRole, accSta,password);
                if (checkAddUser > 0) {
                         response.sendRedirect("list");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
    }
    protected void UpdateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get
        UserDAO userDAO = new UserDAO();
        // Get session
        HttpSession session = request.getSession();
        // Input stream of the upload file
        InputStream inputImage = null;
        
        int uid = 0;       
        String fullname = "";
        String phone = "";
        String title = "";
        String address = "";
        String role = "5";
        String success = "";
        String error = "";
        boolean genderbit = true;
        int accountStaus = 0;
        int accID = 0;
        phone = request.getParameter("phone");
        fullname = request.getParameter("name").trim();
        title = request.getParameter("title").trim();
        address = request.getParameter("address").trim();
        //role = request.getParameter("5");
        uid = Integer.parseInt(request.getParameter("uid"));
        accID = Integer.parseInt(request.getParameter("accountId"));

        

// If female radio button is selected
        if (request.getParameter("gender").equals("0")) {
            genderbit = false;
        }
        if (request.getParameter("status").equals("1")) {
            accountStaus = 24;
        } else {
            accountStaus = 6;
        }

        // Obtains the upload file
        // part in this multipart request
        Part filePart = request.getPart("image");

        if (filePart.getSize() != 0) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            // Obtains input stream of the upload file
            inputImage = filePart.getInputStream();
        } else {
            inputImage = null;
        }

       
        int accountRole = Integer.parseInt(role);
        try {
            int checkUpdateUser = userDAO.updateUser(fullname, title, genderbit,
                    phone, address, inputImage, accountStaus, accountRole, uid);
            if (checkUpdateUser > 0) {
                response.sendRedirect("list");
            } else {
                error = "Unexcepted error occured. Please try again later !!!";
                session.setAttribute("errorEditMessage", error);
                response.sendRedirect("getcustomer?uid=" + uid);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
    }
}
    
    

