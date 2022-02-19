/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * SOURCE : https://stackoverflow.com/questions/11645516/giving-multiple-url-patterns-to-servlet-filter
 */
package controller.admin;

import dal.PostDAO;
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
import javax.servlet.RequestDispatcher;
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
import resources.SendEmail;

/**
 *
 * @author VINH
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "UserController", urlPatterns = {"/user/list", "/user/search",
    "/user/getUser", "/user/update", "/user/add", "/user/toadd"})
public class UserController extends HttpServlet {

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
            case "/user/add":
                addNewUser(request, response);
                break;
            case "/user/update":
                updateUser(request, response);
                break;
            case "/user/search":
                searchUser(request, response);
                break;
            case "/user/getUser":
                getUser(request, response);
                break;
            case "/user/list":
                userList(request, response);
                break;
            case "/user/toadd":
                toAdd(request, response);
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

    protected void userList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set current page when user access User List page is the first page
        int currentPage = 1;
        // Set total records per page is 5
        int recordsPerPage = 5;

        // Get the current page from request if there any
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        SettingDAO settingDAO = new SettingDAO();
        UserDAO userDAO = new UserDAO();
        List<Setting> settingList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        try {
            settingList = settingDAO.getAllSetting();
            userList = userDAO.getUserByPage(currentPage, recordsPerPage);

            int rows = userDAO.getNumberOfRows();
            // Count total number of page
            int numOfPage = rows / recordsPerPage;
            if (rows % recordsPerPage > 0) {
                numOfPage++;
            }

            request.setAttribute("SettingList", settingList);
            request.setAttribute("UserList", userList);
            request.setAttribute("numOfPage", numOfPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
            request.setAttribute("currentPage", currentPage);
            request.getRequestDispatcher("/admin/UserList.jsp").forward(request, response);

        } catch (Exception ex) {
//            Logger.getLogger(UserListController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }

    }

    protected void searchUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameter from request
        String searchField = request.getParameter("table_search").trim();
        String role = request.getParameter("role");
        String status = request.getParameter("status");

        if (status == null) {
            status = "";
        }

        SettingDAO settingDAO = new SettingDAO();
        UserDAO userDAO = new UserDAO();
        List<Setting> settingList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        try {

            userList = userDAO.searchUser(searchField, role, status);

            settingList = settingDAO.getAllSetting();

            request.setAttribute("searchValue", searchField);
            request.setAttribute("valueRole", role);
            request.setAttribute("valueStatus", status);
            request.setAttribute("SettingList", settingList);
            request.setAttribute("UserList", userList);
            request.setAttribute("searchNow", true);
            request.getRequestDispatcher("/admin/UserList.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
    }

    protected void getUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uid = request.getParameter("uid");
        int userId = Integer.parseInt(uid);
        String userGender = "1";
        String userRole = "";

        UserDAO userDAO = new UserDAO();
        User currentUser = new User();
        List<String> roles = new ArrayList<>();

        try {
            currentUser = userDAO.getUserByUserId(userId);
            if (!currentUser.isGender()) {
                userGender = "0";
            }
            switch (currentUser.getRole()) {
                case 1:
                    userRole = "admin";
                    break;
                case 2:
                    userRole = "manager";
                    break;
                case 3:
                    userRole = "sales";
                    break;
                case 4:
                    userRole = "marketing";
                    break;
                case 5:
                    userRole = "customer";
                    break;
            }

            String accountStatus = currentUser.getAccountStatus() + "";
            roles = userDAO.getSystemRole();

            request.setAttribute("roles", roles);
            request.setAttribute("currentUser", currentUser);
            request.setAttribute("userGender", userGender);
            request.setAttribute("currentUserRole", userRole);
            request.setAttribute("currentUserStatus", accountStatus);
            request.getRequestDispatcher("/admin/UserDetail.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void updateUser(HttpServletRequest request, HttpServletResponse response)
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
        int accountRole = 0;

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
        List<String> roles = new ArrayList<>();
        // Get session
        HttpSession session = request.getSession();

        switch (role) {
            case "admin":
                accountRole = 1;
                break;
            case "manager":
                accountRole = 2;
                break;
            case "sales":
                accountRole = 3;
                break;
            case "marketing":
                accountRole = 4;
                break;
            case "customer":
                accountRole = 5;
                break;
        }
        try {
            roles = userDAO.getSystemRole();
            int checkUpdateUser = userDAO.updateUser(name, title, genderbit,
                    phone, address, inputStream, accountStaus, accountRole, uid);
            request.setAttribute("roles", roles);
            if (checkUpdateUser > 0) {
                successMessage = "Update user succesfully ";
                session.setAttribute("successEditMessage", successMessage);
                response.sendRedirect("getUser?uid=" + uid);
            } else {
                error = "Unexcepted error occured. Please try again later !!!";
                session.setAttribute("errorEditMessage", error);
                response.sendRedirect("getUser?uid=" + uid);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
    }

    protected void addNewUser(HttpServletRequest request, HttpServletResponse response)
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
        int convertedRole = 0;

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
        SendEmail se = new SendEmail();
        PasswordEncrypt passwordEncrypt = new PasswordEncrypt();
        List<String> roles = new ArrayList<>();
        
                switch (role) {
            case "admin":
                convertedRole = 1;
                break;
            case "manager":
                convertedRole = 2;
                break;
            case "sales":
                convertedRole = 3;
                break;
            case "marketing":
                convertedRole = 4;
                break;
            case "customer":
                convertedRole = 5;
                break;
        }
                
        // Get session
        HttpSession session = request.getSession();

        try {
            User checkUserExisted = userDAO.checkAccountExist(email);
            roles = userDAO.getSystemRole();
            request.setAttribute("emailValue", email);
            request.setAttribute("phoneValue", phone);
            request.setAttribute("nameValue", name);
            request.setAttribute("titleValue", title);
            request.setAttribute("addressValue", address);
            request.setAttribute("roleValue", role);
            request.setAttribute("genderValue", gender);
            request.setAttribute("statusValue", status);
            request.setAttribute("roles", roles);
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
                request.getRequestDispatcher("/admin/AddNewUser.jsp").forward(request, response);
            } else if (filePart.getSize() == 0) {
                error = "Please choose an image !!!";
                request.setAttribute("errorImage", error);
                request.getRequestDispatcher("/admin/AddNewUser.jsp").forward(request, response);
            } else {
                // Insert into Account table with user entered email and default password is 123
                
                String password = passwordEncrypt.generateEncryptedPassword("123");
                int checkAddUser = userDAO.addNewUserWithImage(email, name, title,
                        genderbit, phone, address, inputStream, convertedRole, accountStaus, password);
                if (checkAddUser > 0) {
                    boolean checkEmail = se.sendEmailActivation(email, name);
                    if (checkEmail) {
                        successMessage = "Add new User successfuly .";
                        String avatar = userDAO.getLastInsertedUser().getAvatar();
                        session.setAttribute("messageAddSuccess", successMessage);
                        request.setAttribute("imageValue", avatar);
                        request.getRequestDispatcher("/admin/AddNewUser.jsp").forward(request, response);

                    }
                    // CHUA CHECK SEND EMAIL FAIL
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }

    }

    protected void toAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> roles = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        roles = userDAO.getSystemRole();
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("/admin/AddNewUser.jsp").forward(request, response);

    }

}
