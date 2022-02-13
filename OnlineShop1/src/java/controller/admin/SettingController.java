/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.SettingDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Setting;
import model.User;

/**
 *
 * @author VINH
 */
@WebServlet(name = "SettingController", urlPatterns = {"/setting/list","/setting/search"
,"/setting/activate","/setting/edit","/setting/add","/setting/getSettingID"})
public class SettingController extends HttpServlet {

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
//            out.println("<title>Servlet SettingController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet SettingController at " + request.getContextPath() + "</h1>");
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
            case "/setting/list":
                settingList(request, response);
                break;
            case "/setting/search":
                searchSetting(request, response);
                break;
            case "/setting/activate":
                changeSettingStatus(request, response);
                break;
            case "/setting/edit":
                settingUpdate(request, response);
                break;
            case "/setting/add":
                AddNewSetting(request, response);
                break;
            case "/setting/getSettingID":
                getSettingID(request, response);
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

    protected void settingList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullname = "";
        // Set current page when user access Setting List page is the first page
        int currentPage = 1;
        // Set total records per page is 5
        int recordsPerPage = 5;

        // Get the current page from request
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        SettingDAO settingDAO = new SettingDAO();
        //SettingTypeDAO settingTypeDAO = new SettingTypeDAO();
        List<Setting> settingList = new ArrayList<>();
        List<String> settingType = new ArrayList<>();
        User user = new User();

        // Read setting properties file
        ResourceBundle rb = ResourceBundle.getBundle("resources.setting");
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = rb.getString(key);
            settingType.add(value);
        }

        try {

            // Get session
            HttpSession session = request.getSession();
            // get account saved in session 
            user = (User) session.getAttribute("account");

            // Get all the setting in the selected page
            settingList = settingDAO.getSettingByPage(currentPage, recordsPerPage);
//            fullname = user.getFullname();

            // Count total number of Setting in the Setting table
            int rows = settingDAO.getNumberOfRows();
            // Count total number of page
            int numOfPage = rows / recordsPerPage;
            if (rows % recordsPerPage > 0) {
                numOfPage++;
            }

            request.setAttribute("SettingList", settingList);
            //session.setAttribute("fullname", fullname);
            request.setAttribute("types", settingType);
            request.setAttribute("numOfPage", numOfPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
            request.setAttribute("currentPage", currentPage);
            request.getRequestDispatcher("/admin/SettingList.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(SettingController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);

        }
    }

    protected void searchSetting(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameter from request
        String searchField = request.getParameter("table_search").trim();
        String type = request.getParameter("type");
        String status = request.getParameter("status");

        // If user does not filter search by SettingStatus
        if (status == null) {
            status = "";
        }

        SettingDAO settingDAO = new SettingDAO();
        List<Setting> settingSearch = new ArrayList<>();
        List<String> settingTypes = new ArrayList<>();

        // Read setting properties file
        ResourceBundle rb = ResourceBundle.getBundle("resources.setting");
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = rb.getString(key);
            settingTypes.add(value);
        }

        try {
            // Search Setting by selected filter and return an arrayList result
            settingSearch = settingDAO.searchSeting(searchField, type, status);

            request.setAttribute("SettingList", settingSearch);
            request.setAttribute("types", settingTypes);
            request.setAttribute("searchValue", searchField);
            request.getRequestDispatcher("/admin/SettingList.jsp").forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(SettingController.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);

        }
    }

    protected void changeSettingStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean check = false;
        // Setting status to save in the database
        int state = 0;
        // Get parameter from request
        int sid = Integer.parseInt(request.getParameter("sid"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        // If status is TRUE(Active) -- > status changes to FALSE (InActive) (state == 0)
        if (!status) {
            state = 1;
        } else {
            // If status is FALSE (InActive) -- > status changes to TRUE(Active) (state == 1)
            state = 0;
        }

        SettingDAO settingDAO = new SettingDAO();

        try {
            check = settingDAO.changeSettingStatus(sid, state);
            // If change setting status successfully
            if (check) {
                response.sendRedirect("list");
            } else {
                // Redirect user to error page
                request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(SettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      protected void settingUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          int urole=0;
        String role;
//        int id=0;
//        id = Integer.parseInt(request.getParameter("id"));
       // respond.getWriter().print(id);
        try {
            Setting s = new Setting();
            s.setSettingId(Integer.parseInt(request.getParameter("settingId")));
           // s.setSettingType(Integer.parseInt(request.getParameter("settingType")));
            role=request.getParameter("settingType");
            switch(role){
                case "User Role":urole=1;
                break;
                case "Account Status":urole=2;
                break;
                case "Post Category":urole=3;
                break;
                case "Post Status":urole=4;
                break;
                case "Product Category":urole=5;
                break;
                case "Product Status":urole=6;
                break;
                case "Feedback Status":urole=7;
                break;
                case "Order Status":urole=8;
                break;
            }
            
            s.setSettingType(urole);
            s.setSettingValue(request.getParameter("settingValue"));
            s.setSettingOrder(Integer.parseInt(request.getParameter("settingOrder")));
            s.setSettingStatus(request.getParameter("settingStatus").equals("0"));
         
            SettingDAO db= new SettingDAO();
            db.editsetting(s);
            response.sendRedirect("list");
        } catch (Exception ex) {
            Logger.getLogger(SettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      protected void getSettingID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           try {           
            int id = Integer.parseInt(request.getParameter("settingId"));
            //int id = 1;
            int value=0;
            SettingDAO db = new SettingDAO();
            Setting setting =db.getAllSettingTypeName(id);
          
            List<String> settingType = new ArrayList<>();
            
            ResourceBundle rb = ResourceBundle.getBundle("resources.setting");
            Enumeration<String> keys = rb.getKeys();
            while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String val = rb.getString(key);
            settingType.add(val);
          }
           
            
            List<String> getValuebyTye =  new ArrayList<>();
            value = db.getValuebySetting(id);      
            getValuebyTye= db.getValuebyType(value);
            
            request.setAttribute("valuebytye", getValuebyTye);
            request.setAttribute("setting", setting);
            request.setAttribute("typename", settingType);
            request.getRequestDispatcher("/admin/SettingEdit.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      protected void AddNewSetting(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           try {
            
            Setting s=new Setting();
            
            s.setSettingType(Integer.parseInt(request.getParameter("type")));
            s.setSettingValue(request.getParameter("value"));
            s.setSettingOrder(Integer.parseInt(request.getParameter("order")));
 //        s.setSettingStatus(request.getParameter("value"));
            s.setSettingStatus(request.getParameter("status").equals("0"));
            SettingDAO db =new SettingDAO();
            db.insertSetting(s);
            response.sendRedirect("list");

            //s.setId(Integer.parseInt(request.getParameter("id")));
        } catch (SQLException ex) {
            Logger.getLogger(SettingController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      

}
