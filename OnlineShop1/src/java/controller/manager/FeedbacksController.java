/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import dal.FeedbackDAO;
import dal.OrderDAO;
import dal.OrderDetailDAO;
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Feedback;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.Setting;
import model.User;

/**
 *
 * @author HL2020
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "FeedbacksController", urlPatterns = {"/feedback/list","/feedback/edit","/feedback/getFeedback",
"/feedback/search","/feedback/add"})
public class FeedbacksController extends HttpServlet {

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
//            out.println("<title>Servlet FeedbacksController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet FeedbacksController at " + request.getContextPath() + "</h1>");
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
         String action = request.getServletPath();
        System.out.println(action);

        switch (action) {
            case "/feedback/list":
                FeedbacksList(request, response);
                break;
            case "/feedback/edit":
               UpdateFeedback(request, response);
                break;
            case "/feedback/search":
                FeedbacksSearch(request, response);
                break;
            case "/feedback/getFeedback":
                GetFeedbackID(request, response);
                break;
            case "/feedback/add":
                AddFeedback(request, response);
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

     protected void FeedbacksList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int currentPage = 1;
        int recordsPerPage = 5;

        
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
         List<String> feedsStatus = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        OrderDAO orderDAO = new OrderDAO();
        SettingDAO settingDAO = new SettingDAO();
        FeedbackDAO feedbackDAO = new FeedbackDAO();        
        List<User> customers = new ArrayList<>();
        List<String> orderStatuses = new ArrayList<>();
        List<Feedback> feedback = new ArrayList<>();
        List<Setting> settingList = new ArrayList<>();
        List<Product> product = new ArrayList<>();
        List<Setting> statusList = new ArrayList<>();
        try {
            feedsStatus = settingDAO.getSettingFeedbackValue();
            statusList = settingDAO.getAllFeedStatus();
            settingList = settingDAO.getAllSetting();
            customers = userDAO.getAllUserByRole(5);
            feedback = feedbackDAO.getFeedbackByPage(currentPage, recordsPerPage);
            product = feedbackDAO.getAllProduct();
            int rows = feedbackDAO.getNumberOfRows();
            int numOfPage = rows / recordsPerPage;
            if (rows % recordsPerPage > 0) {
                numOfPage++;
            }    
            request.setAttribute("FeedStatuses", feedsStatus);
            request.setAttribute("StatusList", statusList);
            request.setAttribute("Customers", customers);
            request.setAttribute("Product", product);
            request.setAttribute("Feedback", feedback);
            request.setAttribute("numOfPage", numOfPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("SettingList", settingList);
           
            request.getRequestDispatcher("/admin/FeedbackList.jsp").forward(request, response);

        } catch (Exception ex) {
            System.out.println("Exception  ===== " + ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
     }
      protected void FeedbacksSearch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ratestar = request.getParameter("role");
        int feedStatus = 0;
        int feedId = 0;
        int prostus=0;
        //String productstatus = "";
        //int ratestar = 0;
        String status = "";
      String productstatus = request.getParameter("proid");
         String searchField = request.getParameter("table_search").trim();
         try {
            feedId = Integer.parseInt(searchField);
        } catch (NumberFormatException ex) {
            feedId = 0;
        }
        System.out.println(feedId);

        if (request.getParameter("status") != null) {
            status = request.getParameter("status");
            switch (status) {
                case "Published":
                 feedStatus = 18;
                   break;
               case "Hidden":
                  feedStatus = 19;
                  break;
            }
        }
//        if (request.getParameter("product") != null) {
//            productstatus = request.getParameter("product");
//            switch (productstatus) {
////                case "Published":
////                    feedStatus = 18;
////                    break;
////                case "Hidden":
////                    feedStatus = 19;
////                    break;
//               case "Nike AF1":
//                    prostus = 1;
//                    break;
//                case "Nike SB Dunk Travis":
//                    prostus = 2;
//                    break;
//                case "Nike SB Low Ben Jerrys":
//                    prostus = 3;
//                    break;
//                     case "Nike SB Low Chicago":
//                    prostus = 4;
//                    break;
//                     case "Nike Heritage":
//                    prostus = 5;
//                    break;
//                     case "Nike Sportwear Futura 360":
//                    prostus = 6;
//                    break;
//                     case "Nike Backpack L21":
//                    prostus = 7;
//                    break;
//                     case "Nike Paris Saint Germain":
//                    prostus = 8;
//                    break;
//                    case "Nike AS M NSW TEE JDI ILLUSTRATION":
//                    prostus = 9;
//                    break;
//            }
//        }
         List<Setting> settingList = new ArrayList<>();
         List<Product> product = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        SettingDAO settingDAO = new SettingDAO();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        List<User> customers = new ArrayList<>();
        List<User> sales = new ArrayList<>();
        List<String> feedsStatus = new ArrayList<>();
          List<Feedback> feedback = new ArrayList<>();   
           List<Setting> statusList = new ArrayList<>();
        try {
             settingList = settingDAO.getAllSetting();
             statusList = settingDAO.getAllFeedStatus();
            product = feedbackDAO.getAllProduct();
            customers = userDAO.getAllUserByRole(5);           
            feedsStatus = settingDAO.getSettingFeedbackValue();
            feedback = feedbackDAO.searchFeedback(searchField, ratestar, feedStatus, feedId,productstatus);
            request.setAttribute("valueRole", ratestar);
            request.setAttribute("StatusList", statusList);
            request.setAttribute("Product", product);
            request.setAttribute("Feedback", feedback);  
            request.setAttribute("Customers", customers);         
            request.setAttribute("FeedStatuses", feedsStatus);
            request.setAttribute("valueStatus", status);
            request.setAttribute("valueSearch", searchField);
             request.setAttribute("valueProduct", productstatus);
            request.setAttribute("SettingList", settingList);
            request.getRequestDispatcher("/admin/FeedbackList.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Exception get ===== " + ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
      }
       protected void GetFeedbackID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int feedId = Integer.parseInt(request.getParameter("feedbackId"));
       

        UserDAO userDAO = new UserDAO();
        FeedbackDAO feedbackDAO = new FeedbackDAO();               
        SettingDAO settingDAO = new SettingDAO();
        ProductDAO productDAO = new ProductDAO();
        List<Feedback> feed = new ArrayList<>();
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<User> cus = new ArrayList<>();
        //List<Product> products = new ArrayList<>();
        User currentCustomer = new User();
        Feedback feedback = new Feedback();
        Product products = new Product();
         try {
            feedback = feedbackDAO.getFeedbackid(feedId); 
           currentCustomer = userDAO.getUserByUserId(feedback.getCustomerId());
           String Status = feedback.getFeedbackStatus() + "";
              
           products = productDAO.getTitlebyproId(feedback.getProductID());
           request.setAttribute("CurrentCustomer", currentCustomer);
            request.setAttribute("Feedback", feedback);
            request.setAttribute("Products", products);
           request.setAttribute("currentUserStatus",Status );
            request.getRequestDispatcher("/admin/FeedbackDetails.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Exception ===== " + ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
    }
      protected void UpdateFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      FeedbackDAO feedDao= new FeedbackDAO();
      int feedid=0;
      int feedStatus = 0;
      String note = "";
      String error = "";
      note = request.getParameter("note").trim();
      feedid = Integer.parseInt(request.getParameter("feedbackId"));
       HttpSession session = request.getSession();
        if (request.getParameter("status").equals("1")) {
            feedStatus = 18;
        } else {
            feedStatus = 19;
        }
         try {
            int checkUpdateUser = feedDao.updateFeedback(note, feedStatus, feedid);
            if (checkUpdateUser > 0) {
                response.sendRedirect("list");
            } else {
                error = "Unexcepted error occured. Please try again later !!!";
                session.setAttribute("errorEditMessage", error);
                response.sendRedirect("getFeedback?feedbackId=" + feedid);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
      } 
        protected void AddFeedback(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
         try {
             response.setContentType("text/html;charset=UTF-8");
               request.setCharacterEncoding("UTF-8");
            InputStream inputStream = null;
            String content = request.getParameter("content");
            String cusid=request.getParameter("cusid");
            String star=request.getParameter("star");
            String pid=request.getParameter("pid");
            //int status=Integer.parseInt("status");
            
            Part filePart = request.getPart("image");
            if (filePart != null) {
                System.out.println(filePart.getName());
                System.out.println(filePart.getSize());
                System.out.println(filePart.getContentType());
                // Obtains input stream of the upload file
                inputStream = filePart.getInputStream();}
            
            FeedbackDAO feedDAO= new FeedbackDAO();
            feedDAO.addNewFeedback(Integer.parseInt(cusid),Integer.parseInt(star), Integer.parseInt(pid), inputStream, 18, content);
            //request.getRequestDispatcher("/orderfeedback.jsp").forward(request, response);
            //response.sendRedirect("../goods/goodsList");
            response.sendRedirect("../goods/detail?pid="+ pid);
        } catch (SQLException ex) {
            Logger.getLogger(FeedbacksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
       }
    

