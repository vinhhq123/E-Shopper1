/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import dal.FeedbackDAO;
import dal.GoodsDAO;
import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.SettingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Feedback;
import model.Items;
import model.Product;
import model.Setting;
import resources.SendEmail;

/**
 *
 * @author hungn
 */
@WebServlet(name = "GoodsController", urlPatterns = {"/goods/goodsList", "/goods/goodsCate", "/goods/search", "/goods/detail",
    "/goods/addToCart", "/goods/removeProductCart", "/goods/addToCartContact","/goods/checkOut"})
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
            case "/goods/addToCart":
                addProductToCart(request, response);
                break;
            case "/goods/removeProductCart":
                removeProCart(request, response);
                break;
            case "/goods/addToCartContact":
                addProductToCartContact(request, response);
                break;
            case "/goods/checkOut":
                checkOut(request, response);
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
        SettingDAO sdao = new SettingDAO();
        List<Setting> listGoodsCate = sdao.getGoodsCategory();
        List<Product> listGoodsPage = dao.getGoodsSortByDate();
        List<Product> listFeatured = dao.getFeaturedGood();
        
        int page, numperpage = 12;
        int size = listGoodsPage.size();
        int num = (size % numperpage == 0 ? (size / numperpage) : ((size / numperpage)) + 1);
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
        request.setAttribute("listFeatured", listFeatured);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.getRequestDispatcher("/goods.jsp").forward(request, response);
    }

    protected void getGoodsByCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int category = Integer.parseInt(request.getParameter("id"));
        GoodsDAO dao = new GoodsDAO();
        SettingDAO sdao = new SettingDAO();
        List<Setting> listGoodsCate = sdao.getGoodsCategory();
        List<Product> listGoodsByCategory = dao.getGoodsByCategory(category);
        List<Product> listFeatured = dao.getFeaturedGood();
        int page, numperpage = 9;
        int size = listGoodsByCategory.size();
        int num = (size % numperpage == 0 ? (size / numperpage) : ((size / numperpage)) + 1);
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
        request.setAttribute("listFeatured", listFeatured);
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
        SettingDAO sdao = new SettingDAO();
        List<Setting> listGoodsCate = sdao.getGoodsCategory();
        List<Product> listFeatured = dao.getFeaturedGood();
        int page, numperpage = 9;
        int size = listSearch.size();
        int num = (size % numperpage == 0 ? (size / numperpage) : ((size / numperpage)) + 1);
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
        request.setAttribute("listFeatured", listFeatured);
        request.setAttribute("num", num);
        request.getRequestDispatcher("/goods.jsp").forward(request, response);
    }

    protected void getGoodsById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("pid"));
        
        GoodsDAO dao = new GoodsDAO();
        SettingDAO sdao = new SettingDAO();
        FeedbackDAO fdao = new FeedbackDAO();
        Product good = dao.getGoodsById(id);
        double star = dao.avgStar(id);
        dao.updateViews(id);
        List<Setting> listGoodsCate = sdao.getGoodsCategory();
        List<Feedback> listFeedback = fdao.getFeedbackOfEachGood(id);
        List<Product> listFeatured = dao.getFeaturedGood();
        request.setAttribute("good", good);
        request.setAttribute("star", star);
        request.setAttribute("listGoodsCate", listGoodsCate);
        request.setAttribute("listFeedback", listFeedback);
        request.setAttribute("listFeatured", listFeatured);
        request.getRequestDispatcher("/goods-details.jsp").forward(request, response);
    }

    protected void addProductToCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        //co roi
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        String tid = request.getParameter("pid");
        int num, id;
        try {
            num = 1;
            id = Integer.parseInt(tid);

            GoodsDAO pdb = new GoodsDAO();
            Product p = pdb.getGoodsById(id);
            //gia ban
            double price = p.getSprice();
            Items t = new Items(p, num, price);
            cart.addItem(t);
        } catch (NumberFormatException e) {
            num = 1;
        }
        List<Items> list = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }
    
    protected void addProductToCartContact(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        //co roi
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        String tid = request.getParameter("pid");
        int num, id;
        try {
            num = 1;
            id = Integer.parseInt(tid);

            GoodsDAO pdb = new GoodsDAO();
            Product p = pdb.getGoodsById(id);
            //gia ban
            double price = p.getSprice();
            Items t = new Items(p, num, price);
            cart.addItem(t);
        } catch (NumberFormatException e) {
            num = 1;
        }
        List<Items> list = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
        request.getRequestDispatcher("/cart-contact.jsp").forward(request, response);
    }

    protected void removeProCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        //co roi
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        int id = Integer.parseInt(request.getParameter("id"));
        cart.removeItem(id);
        List<Items> list = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }
    
    protected void checkOut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(true);
            Cart cart = null;
            Object o = session.getAttribute("cart");
            if (o != null) {
                cart = (Cart) o;
            } else {
                cart = new Cart();
            }
            OrderDAO ord = new OrderDAO();
            OrderDetailDAO ordd = new OrderDetailDAO();
            int oid = ord.getLastOrder().getOrderId() + 1;
            float totaCost = cart.getTotalMoney();
            int uid = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("fullname");
            String email = request.getParameter("email");
            int checkAddOrder = ord.addOrder(oid, totaCost, uid);
            if (checkAddOrder > 0){
                SendEmail sm = new SendEmail();
                sm.ContactMail(username, email);
               request.getRequestDispatcher("/cart-completion.jsp").forward(request, response); 
            }

        } catch (Exception ex) {
            Logger.getLogger(GoodsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
