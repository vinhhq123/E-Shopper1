/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.marketing;

import dal.PostDAO;
import dal.SliderDAO;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.PostList;
import model.Slider;

/**
 *
 * @author CHANHSIRO
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "SliderController", urlPatterns = {"/slider/detail", "/slider/list"})
public class SliderController extends HttpServlet {

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
            case "/slider/detail":
                sliderDetail(request, response);
                break;
            case "/slider/list":
                slidersList(request, response);
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
    
    protected void sliderDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String s_title = request.getParameter("s_title");
        Part filePart = request.getPart("s_image");
        String s_backlink = request.getParameter("s_backlink");
        String s_notes = request.getParameter("s_notes");
        InputStream inputStream = null;
        
        if (filePart != null) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            // Obtains input stream of the upload file
            inputStream = filePart.getInputStream();
            SliderDAO sliderDAO = new SliderDAO();
            sliderDAO.insertSlider(s_title, inputStream, s_backlink, s_notes);
        request.getRequestDispatcher("/slider/list").forward(request, response);
        }
    }
    
    protected void slidersList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SliderDAO sd = new SliderDAO();
            ArrayList<Slider> sliders = sd.getSliders();
            request.setAttribute("sliders", sliders);
            request.getRequestDispatcher("/slider/sliders-list.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
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