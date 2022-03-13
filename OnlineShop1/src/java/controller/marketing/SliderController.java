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
@WebServlet(name = "SliderController", urlPatterns = {"/slider/detail", "/slider/list", "/slider/getslider", "/slider/edit", "/slider/status"})
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
            case "/slider/getslider":
                getSliderById(request, response);
                break;
            case "/slider/edit":
                sliderEdit(request, response);
                break;
            case "/slider/status":
                sliderStatus(request, response);
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
            String search = request.getParameter("search2");
            if(search == null){
                ArrayList<Slider> sliders = sd.getSliders("", "");
            request.setAttribute("sliders", sliders);
            request.getRequestDispatcher("/slider/sliders-list.jsp").forward(request, response);
            } else {
                String status = request.getParameter("status");
                ArrayList<Slider> sliders = sd.getSliders(search, status);
            request.setAttribute("search1", search);
            request.setAttribute("sliders", sliders);
            request.getRequestDispatcher("/slider/sliders-list.jsp").forward(request, response);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void getSliderById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SliderDAO sd = new SliderDAO();
            String sliderIdRaw = request.getParameter("s_id");
            int s_id = Integer.parseInt(sliderIdRaw);
            Slider slider = sd.getSliderById(s_id);
            request.setAttribute("slider", slider);
            request.getRequestDispatcher("/slider/slider-edit.jsp").forward(request, response);
            
        } catch (Exception ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void sliderEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String s_title = request.getParameter("s_title");
            String back_link = request.getParameter("s_backlink");
            int s_id = Integer.parseInt(request.getParameter("s_id"));
            String s_notes = request.getParameter("s_notes");
            InputStream inputStream = null;
        Part filePart = request.getPart("s_image");
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            // Obtains input stream of the upload file
            inputStream = filePart.getInputStream();
            SliderDAO sd = new SliderDAO();
        sd.update(s_id, s_title, inputStream, back_link, s_notes);
        request.getRequestDispatcher("/slider/list").forward(request, response);
        
        } catch (Exception ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void sliderStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int s_id = Integer.parseInt(request.getParameter("s_id"));
            int s_status = Integer.parseInt(request.getParameter("s_status"));
            if(s_status == 1){
                SliderDAO sd = new SliderDAO();
                sd.updateStatus(s_id, 2);
            } else {
                SliderDAO sd = new SliderDAO();
                sd.updateStatus(s_id, 1);
            }
            
            response.sendRedirect("../slider/list");
        
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
