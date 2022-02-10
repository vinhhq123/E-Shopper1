/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.SettingDAO;
import dal.SettingTypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Setting;
import model.SettingType;

/**
 *
 * @author HL2020
 */
public class SettingDetailEdit extends HttpServlet {

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
//            out.println("<title>Servlet SettingDetailEdit</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet SettingDetailEdit at " + request.getContextPath() + "</h1>");
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
        try {           
            int id = Integer.parseInt(request.getParameter("settingId"));
            //int id = 1;
            int value=0;
            SettingDAO db = new SettingDAO();
            Setting setting =db.getAllSettingTypeName(id);
            SettingTypeDAO settingTypeDAO = new SettingTypeDAO();
            List<SettingType> settingType = new ArrayList<>();
            settingType = settingTypeDAO.getAllSettingType();
            List<String> getValuebyTye =  new ArrayList<>();
            value = db.getValuebySetting(id);
      
            getValuebyTye= db.getValuebyType(value);
            
            request.setAttribute("valuebytye", getValuebyTye);
            request.setAttribute("setting", setting);
            request.setAttribute("typename", settingType);
            request.getRequestDispatcher("./admin/colorlib-regform-4/form_edit.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SettingDetailEdit.class.getName()).log(Level.SEVERE, null, ex);
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
//        int id=0;
//        id = Integer.parseInt(request.getParameter("id"));
       // respond.getWriter().print(id);
        try {
            
          //  int settingid = Integer.parseInt(request.getParameter("settingId"));
            Setting s = new Setting();
            s.setSettingId(Integer.parseInt(request.getParameter("settingId")));
            s.setSettingType(Integer.parseInt(request.getParameter("settingType")));
            s.setSettingValue(request.getParameter("settingValue"));
            s.setSettingOrder(Integer.parseInt(request.getParameter("settingOrder")));
            s.setSettingStatus(request.getParameter("settingStatus").equals("0"));
         
            SettingDAO db= new SettingDAO();
            db.editsetting(s);
            response.sendRedirect("settingList");
        } catch (Exception ex) {
            Logger.getLogger(SettingDetailEdit.class.getName()).log(Level.SEVERE, null, ex);
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
