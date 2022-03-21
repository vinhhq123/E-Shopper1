/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.marketing;

import dal.PostDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

/**
 *
 * @author CHANHSIRO
 */

@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "PostController", urlPatterns = {"/post/list", "/post/add", "/post/delete", "/post/update", "/post/edit"})
public class PostController extends HttpServlet {

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
            case "/post/add":
                postDetail(request, response);
                break;
            case "/post/list":
                postList(request, response);
                break;
            case "/post/delete":
                postDelete(request, response);
                break;
            case "/post/update":
                postUpdate(request, response);
                break;
            case "/post/edit":
                postEdit(request, response);
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
    
    protected void postDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String authorRaw = request.getParameter("author");
        int author = Integer.parseInt(authorRaw);
        String content = request.getParameter("content");
        InputStream inputStream = null;
        Part filePart = request.getPart("image");
        if (filePart != null) {
            // Obtains input stream of the upload file
            inputStream = filePart.getInputStream();
            PostDAO postDao = new PostDAO();
        postDao.insertPost(content, inputStream, title, author);
        request.getRequestDispatcher("/post/list").forward(request, response);
        }
    }
    
    protected void postList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PostDAO pd = new PostDAO();
            ArrayList<PostList> postlist = pd.getPostList();
            request.setAttribute("postlist", postlist);
            request.getRequestDispatcher("/post/PostList.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    protected void postDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PostDAO pd = new PostDAO();
            int postId = Integer.parseInt(request.getParameter("postId"));
            pd.delete(postId);
            response.sendRedirect("./list");
        } catch (Exception ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void postUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PostDAO pd = new PostDAO();
            String postIdRaw = request.getParameter("postId");
            System.out.println("laaaaaaaa" + postIdRaw);
            int postId = Integer.parseInt(postIdRaw);
            PostList postUpdate = pd.getPostById(postId);
            request.setAttribute("postUpdate", postUpdate);
            request.getRequestDispatcher("/post/PostUpdate.jsp").forward(request, response);
            
        } catch (Exception ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void postEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String postTitle = request.getParameter("title");
            String postContent = request.getParameter("content");
            int postId = Integer.parseInt(request.getParameter("postId"));
            InputStream inputStream = null;
        Part filePart = request.getPart("image");
        if (filePart != null) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            // Obtains input stream of the upload file
            inputStream = filePart.getInputStream();
            PostDAO postDao = new PostDAO();
        postDao.updatePost(postId, postTitle, postContent, inputStream);
        response.sendRedirect("/OnlineShop1/post/list");
        }
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
