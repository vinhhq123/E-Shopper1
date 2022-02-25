/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import dal.GoodsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import model.Setting;

/**
 *
 * @author hungn
 */
@WebServlet(name = "GoodsController", urlPatterns = {"/goods/goodsList", "/goods/goodsCate", "/goods/search", "/goods/detail"})
public class GoodsController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        //processRequest(request, response);
        String action = request.getServletPath();
        System.out.println(action);

        switch (action) {
            case "/goods/goodsList":
                getGoods(request, response);
                break;
            case "/goods/goodsCate":
                getGoodsByCategory(request, response);
                break;
            case "/goods/search":
                searchGoods(request, response);
                break;
            case "/goods/detail":
                getGoodsById(request, response);
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
        // processRequest(request, response);
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

    protected void getGoods(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        GoodsDAO dao = new GoodsDAO();
        List<Setting> listGoodsCate = dao.getGoodsCategory();
        List<Product> listGoodsPage = dao.getGoodsSortByDate();
        int page, numperpage = 9;
        int size = listGoodsPage.size();
        int num = (size % 3 == 0 ? (size / 3) : ((size / 3)) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);
        List<Product> listGoods = dao.getProductByPage(listGoodsPage, start, end);
        
        request.setAttribute("listGoods", listGoods);
        request.setAttribute("listGoodsCate", listGoodsCate);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.getRequestDispatcher("/goods.jsp").forward(request, response);
    }
    
    protected void getGoodsByCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int category = Integer.parseInt(request.getParameter("id"));
        GoodsDAO dao = new GoodsDAO();
        List<Setting> listGoodsCate = dao.getGoodsCategory();
        List<Product> listGoodsByCategory = dao.getGoodsByCategory(category);
        int page, numperpage = 9;
        int size = listGoodsByCategory.size();
        int num = (size % 3 == 0 ? (size / 3) : ((size / 3)) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);
        List<Product> listGoods = dao.getProductByPage(listGoodsByCategory, start, end);
        
        request.setAttribute("listGoodsCate", listGoodsCate);
        request.setAttribute("listGoods", listGoods);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.getRequestDispatcher("/goods.jsp").forward(request, response);
    }
    
    protected void searchGoods(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String searchGoods = request.getParameter("search");
        GoodsDAO dao = new GoodsDAO();
        List<Product> listSearch = dao.searchGoodByTitle(searchGoods);
        List<Setting> listGoodsCate = dao.getGoodsCategory();
        int page, numperpage = 9;
        int size = listSearch.size();
        int num = (size % 3 == 0 ? (size / 3) : ((size / 3)) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * numperpage;
        end = Math.min(page * numperpage, size);
        List<Product> listSearchGoods = dao.getProductByPage(listSearch, start, end);
        
        request.setAttribute("listGoodsCate", listGoodsCate);
        request.setAttribute("listGoods", listSearchGoods);
        request.setAttribute("searchValue", searchGoods);
        request.getRequestDispatcher("/goods.jsp").forward(request, response);
    }
    
    protected void getGoodsById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("pid"));
        GoodsDAO dao = new GoodsDAO();
        Product good = dao.getGoodsById(id);
        List<Setting> listGoodsCate = dao.getGoodsCategory();
        
        request.setAttribute("good", good);
        request.setAttribute("listGoodsCate", listGoodsCate);
        request.getRequestDispatcher("/goods-details.jsp").forward(request, response);
    }
}
