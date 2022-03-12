/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import controller.marketing.PostController;
import dal.GoodsDAO;
import dal.PostDAO;
import dal.SettingDAO;
import dal.SliderDAO;
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
import model.PostList;
import model.Product;
import model.Setting;
import model.Slider;

/**
 *
 * @author hungn
 */
public class HomeController extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            GoodsDAO dao = new GoodsDAO();
            SettingDAO sdao = new SettingDAO();
            SliderDAO sd = new SliderDAO();
            PostDAO pdao = new PostDAO();
            ArrayList<Slider> sliders = sd.getSlidersByStatus();
            List<Setting> listGoodsCate = sdao.getGoodsCategory();
            List<Product> listGoods = dao.getSomeLatestProduct();
            List<Product> listFeatured = dao.getFeaturedGood();
            List<PostList> listHotPost = pdao.getFeaturedBlogs();
            request.setAttribute("sliders", sliders);
            request.setAttribute("listGoodsCate", listGoodsCate);
            request.setAttribute("listFeatured", listFeatured);
            request.setAttribute("listGoods", listGoods);
            request.setAttribute("listHotBlogs", listHotPost);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
