/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import dal.ProductDAO;
import dal.SettingDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
import model.Product;
import model.Setting;
import model.User;

/**
 *
 * @author Edwars
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "ProductController", urlPatterns = {"/product/list", "/product/search",
    "/product/getproduct", "/product/update", "/product/add"})
public class ProductController extends HttpServlet {

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
            case "/product/list":
                ListProduct(request, response);
                break;
            case "/product/add":
                AddProduct(request, response);
                break;
            case "/product/search":
                SearchProduct(request, response);
                break;
            case "/product/getproduct":
                GetProduct(request, response);
                break;
            case "/product/update":
                EditProduct(request, response);
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
    
    protected void ListProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         SettingDAO settingDAO = new SettingDAO();
        UserDAO userDAO = new UserDAO();
        ProductDAO proDAO = new ProductDAO();
        int currentPage = 1;
        // Set total records per page is 5
        int recordsPerPage = 5;

        // Get the current page from request if there any
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        List<Setting> settingList = new ArrayList<>();
        List<Setting> statusList = new ArrayList<>();
        List<Setting> categoryList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
        List<Product> proList = new ArrayList<>();
        
        try {
            statusList = settingDAO.getAllProStatus();
            categoryList = settingDAO.getAllProCategory();
            userList = userDAO.getSaler();
            settingList = settingDAO.getAllSetting();
            proList = proDAO.getProByPage(currentPage, recordsPerPage);
            
            int rows = proDAO.getNumberOfRows();
            // Count total number of page
            int numOfPage = rows / recordsPerPage;
            if (rows % recordsPerPage > 0) {
                numOfPage++;
            }
            request.setAttribute("SettingList", settingList);
            request.setAttribute("StatusList", statusList);
            request.setAttribute("CategoryList", categoryList);
            request.setAttribute("UserList", userList);
            request.setAttribute("ProList", proList);
            request.setAttribute("numOfPage", numOfPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
            request.setAttribute("currentPage", currentPage);
            request.getRequestDispatcher("/admin/ProductList.jsp").forward(request, response);
            
        } catch (Exception ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
    }
    protected void SearchProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SettingDAO settingDAO = new SettingDAO();
        UserDAO userDAO = new UserDAO();
        ProductDAO proDAO = new ProductDAO();
        int productStatus = 0;
        int productCat = 0;
        String title = request.getParameter("title").trim();
        productStatus = Integer.parseInt(request.getParameter("status"));
        productCat = Integer.parseInt(request.getParameter("category"));


        ProductDAO productDAO = new ProductDAO();
        List<Product> product = new ArrayList<>();
        List<Setting> statusList = new ArrayList<>();
        List<Setting> categoryList = new ArrayList<>();

        try {
            categoryList = settingDAO.getAllProCategory();
            statusList = settingDAO.getAllProStatus();
            product =productDAO.searchPro(title, productStatus,productCat );

            request.setAttribute("Product", product);
            request.setAttribute("Cat", categoryList);
            request.setAttribute("Sta", statusList);

            request.setAttribute("valueStatus", productStatus);
            request.setAttribute("valueCategory", productCat);
            request.setAttribute("valueSearch", title);
            request.getRequestDispatcher("/admin/ProductList.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Exception getListOrders ===== " + ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
    }

    protected void AddProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         SettingDAO settingDAO = new SettingDAO();
        UserDAO userDAO = new UserDAO();
        ProductDAO proDAO = new ProductDAO();
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
        status = request.getParameter("status");
        // If active radio button is selected

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
        List<Setting> sta = new ArrayList<>();
        List<Setting> cat = new ArrayList<>();
        List<User> sal = new ArrayList<>();
        Product pro = null;
        // Get session
        HttpSession session = request.getSession();

        try {
            sta = settingDAO.getAllProCategory();
            cat = settingDAO.getAllProStatus();
            
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
                request.getRequestDispatcher("/admin/ProductAdd.jsp").forward(request, response);
            } else {
                // Insert into Account table with user entered email and default password is 123

                int checkAddPro = proDAO.addProduct(title, Double.parseDouble(lprice),Double.parseDouble(sprice), feature ,inputStream, Integer.parseInt(category),Integer.parseInt(saler),Integer.parseInt(status),Integer.parseInt(quan), breif);
                if (checkAddPro > 0) {
//                            boolean checkEmail = accountDAO.sendEmailActivation(email, name);
//                            if (checkEmail) {
//                    successMessage = "New product is added successfully!";
//                    String avatar = proDAO.getLastInsertedProduct().getThumbnail();
//                    session.setAttribute("messageAddSuccess", successMessage);
//                    request.setAttribute("imageValue", avatar);
//                    request.getRequestDispatcher("/admin/ProductAdd.jsp").forward(request, response);
 response.sendRedirect("list");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
    }
    protected void GetProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         SettingDAO settingDAO = new SettingDAO();
         UserDAO userDAO = new UserDAO();
         Product currentProduct = new Product();
         ProductDAO proDAO = new ProductDAO();
         String pid = request.getParameter("pid");
        int productId = Integer.parseInt(pid);
        try {
            currentProduct = proDAO.getProductById(productId);
            String user = currentProduct.getSalesId()+ "";
            String Status = currentProduct.getProductStatus()+ "";  
            String Cat = currentProduct.getCategoryID()+ "";
            request.setAttribute("currentpro", currentProduct);
            request.setAttribute("sale", user);
            request.setAttribute("status", Cat);
            request.setAttribute("cat",Status );
            request.getRequestDispatcher("/admin/ProductDetails.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    protected void EditProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SettingDAO settingDAO = new SettingDAO();
        UserDAO userDAO = new UserDAO();
        ProductDAO proDAO = new ProductDAO();
        HttpSession session = request.getSession();
        String success = "";
        String error = "";
        String title = "";
        String lprice= "";
        String sprice= "";
        String feature = "";
        String breif = "";
        String category = "";
        String saler = "";
        String status = "";
        String quan = "";
        String successMessage = "";
        String base64Image = "";
         
        int pid = Integer.parseInt(request.getParameter("pid"));
        title = request.getParameter("title");
        lprice = request.getParameter("lprice");
        sprice = request.getParameter("sprice"); 
        feature = request.getParameter("feature");
        breif = request.getParameter("breif");
        category = request.getParameter("category");
        saler = request.getParameter("saler");
        status = request.getParameter("status");
        quan = request.getParameter("quan");
        // If active radio button is selected
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

        try {
            request.setAttribute("titleValue", title);
            request.setAttribute("lpriceValue", lprice);
            request.setAttribute("spriceValue", sprice);
            request.setAttribute("featureValue", feature);
            request.setAttribute("breifValue", breif);
            request.setAttribute("categoryValue", category);
            request.setAttribute("salerValue", saler);
            request.setAttribute("statusValue", status);
            request.setAttribute("quanValue", quan);
             if (filePart.getSize() == 0) {
                error = "Please choose an image !!!";
                request.setAttribute("errorImage", error);
                request.getRequestDispatcher("./admin/ProductDetails.jsp").forward(request, response);
            } else {
                // Insert into Account table with user entered email and default password is 123

                int checkUpdatePro = proDAO.updateProduct(title, Double.parseDouble(lprice),Double.parseDouble(sprice), feature ,inputStream, Integer.parseInt(category),Integer.parseInt(saler),Integer.parseInt(status),Integer.parseInt(quan), breif,pid);
                if (checkUpdatePro > 0) {
//                   success = "Update Customer Succesfully ";
                    response.sendRedirect("list");
                } else {
                error = "Unexcepted error occured. Please try again later !!!";
                session.setAttribute("errorEditMessage", error);
                response.sendRedirect("getproduct?pid=" + pid);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("./admin/Error.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("./admin/Error.jsp").forward(request, response);
        }
        }

}
