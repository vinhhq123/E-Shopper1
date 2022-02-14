/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.marketing;

import dal.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PostList;

/**
 *
 * @author hungn
 */
@WebServlet(name = "BlogController", urlPatterns = {"/blog/bloglist", "/blog/detail", "/blog/cate", "/blog/search"})
public class BlogController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        //get data
//        PostDAO dao = new PostDAO();
//        List<PostList> list = dao.getBlogSortByDate();
//        List<PostList> listPostCate = dao.getBlogCategory();
//        //set data
//        request.setAttribute("listBlog", list);
//        request.setAttribute("listPostCate", listPostCate);
//        request.getRequestDispatcher("blog.jsp").forward(request, response);
//           
//    }
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
            case "/blog/bloglist":
                getAllBlog(request, response);
                break;
            case "/blog/detail":
                getBlogById(request, response);
                break;
            case "/blog/cate":
                getBlogByCateId(request, response);
                break;
            case "/blog/search":
                searchBlog(request, response);
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

    protected void getAllBlog(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //get data
        PostDAO dao = new PostDAO();
        List<PostList> list = dao.getBlogSortByDate();
        List<PostList> listPostCate = dao.getBlogCategory();
        //set data
        request.setAttribute("listBlog", list);
        request.setAttribute("listPostCate", listPostCate);
        request.getRequestDispatcher("/blog.jsp").forward(request, response);
    }

    protected void getBlogById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("postId");
        PostDAO dao = new PostDAO();
        PostList blog = dao.getBlogById(id);
        List<model.PostList> listPostCate = dao.getBlogCategory();
        request.setAttribute("listPostCate", listPostCate);
        request.setAttribute("blog", blog);
        request.getRequestDispatcher("/blog-details.jsp").forward(request, response);

    }

    protected void getBlogByCateId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String category = request.getParameter("id");
        PostDAO dao = new PostDAO();
        //List<model.PostList> list = dao.getBlogSortByDate();
        List<model.PostList> list = dao.getBlogByPostCategory(category);
        List<model.PostList> listPostCate = dao.getBlogCategory();
        request.setAttribute("listPostCate", listPostCate);
        request.setAttribute("listBlog", list);
        request.getRequestDispatcher("/blog.jsp").forward(request, response);
    }

    protected void searchBlog(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String searchBlog = request.getParameter("search");
        PostDAO dao = new PostDAO();
        List<model.PostList> list = dao.searchBlogByTitle(searchBlog);
        List<model.PostList> listPostCate = dao.getBlogCategory();
        request.setAttribute("listPostCate", listPostCate);
        request.setAttribute("listBlog", list);
        request.getRequestDispatcher("/blog.jsp").forward(request, response);

    }
}
