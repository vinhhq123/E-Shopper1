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
import model.Setting;

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
        List<PostList> listpage = dao.getBlogSortByDate();
        List<Setting> listPostCate = dao.getBlogCategory();
        List<PostList> listHotPost = dao.getFeaturedBlogs();
        int page, numperpage = 3;
        int size = listpage.size();
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
        List<PostList> list = dao.getBlogByPage(listpage, start, end);
        //set data
        request.setAttribute("listBlog", list);
        request.setAttribute("listPostCate", listPostCate);
        request.setAttribute("page", page);
        request.setAttribute("num", num);
        request.setAttribute("listHotBlogs", listHotPost);
        request.getRequestDispatcher("/blog.jsp").forward(request, response);
    }

    protected void getBlogById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("postId"));
        PostDAO dao = new PostDAO();
        PostList blog = dao.getBlogById(id);
        List<Setting> listPostCate = dao.getBlogCategory();
        List<PostList> listHotPost = dao.getFeaturedBlogs();
        request.setAttribute("listPostCate", listPostCate);
        request.setAttribute("blog", blog);
        request.setAttribute("listHotBlogs", listHotPost);
        request.getRequestDispatcher("/blog-details.jsp").forward(request, response);
    }

    protected void getBlogByCateId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String category = request.getParameter("id");
        PostDAO dao = new PostDAO();
        List<PostList> listpage = dao.getBlogByPostCategory(category);
        List<Setting> listPostCate = dao.getBlogCategory();
        List<PostList> listHotPost = dao.getFeaturedBlogs();
        int page, numperpage = 3;
        int size = listpage.size();
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
        List<PostList> list = dao.getBlogByPage(listpage, start, end);
        request.setAttribute("listPostCate", listPostCate);
        request.setAttribute("listBlog", list);
        request.setAttribute("listHotBlogs", listHotPost);
        request.getRequestDispatcher("/blog.jsp").forward(request, response);
    }

    protected void searchBlog(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String searchBlog = request.getParameter("search");
        PostDAO dao = new PostDAO();
        List<PostList> list = dao.searchBlogByTitle(searchBlog);
        List<Setting> listPostCate = dao.getBlogCategory();
        List<PostList> listHotPost = dao.getFeaturedBlogs();
        request.setAttribute("listPostCate", listPostCate);
        request.setAttribute("listBlog", list);
        request.setAttribute("searchValue", searchBlog);
        request.setAttribute("listHotBlogs", listHotPost);
        request.getRequestDispatcher("/blog.jsp").forward(request, response);

    }
}
