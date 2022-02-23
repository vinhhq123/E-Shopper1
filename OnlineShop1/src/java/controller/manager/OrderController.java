/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.manager;

import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.ProductDAO;
import dal.SettingDAO;
import dal.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.User;

/**
 *
 * @author VINH
 */
@WebServlet(name = "OrderController", urlPatterns = {"/order/list", "/order/search",
    "/order/getOrder", "/order/updateSaleInfor", "/order/updateOrderQuantity",
    "/order/removeProduct"})
public class OrderController extends HttpServlet {

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
//            out.println("<title>Servlet OrderController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet OrderController at " + request.getContextPath() + "</h1>");
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
            case "/order/list":
                getListOrders(request, response);
                break;
            case "/order/search":
                searchOrder(request, response);
                break;
            case "/order/getOrder":
                getOrderByOrderId(request, response);
                break;
            case "/order/updateSaleInfor":
                updateOrderSalerInfor(request, response);
                break;
            case "/order/updateOrderQuantity":
                updateProductOrderQuantity(request, response);
                break;
            case "/order/removeProduct":
                removeProductFromOrder(request, response);
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

    protected void getListOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set current page when user access User List page is the first page
        int currentPage = 1;
        // Set total records per page is 5
        int recordsPerPage = 5;

        // Get the current page from request if there any
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        UserDAO userDAO = new UserDAO();
        OrderDAO orderDAO = new OrderDAO();
        SettingDAO settingDAO = new SettingDAO();
        List<Order> orders = new ArrayList<>();
        List<User> customers = new ArrayList<>();
        List<User> sales = new ArrayList<>();
        List<String> orderStatuses = new ArrayList<>();
        try {

            customers = userDAO.getAllUserByRole(5);
            sales = userDAO.getAllUserByRole(3);
            orders = orderDAO.getOrderByPage(currentPage, recordsPerPage);
            orderStatuses = settingDAO.getSettingOrderValue();
            int rows = orderDAO.getNumberOfRows();
            // Count total number of page
            int numOfPage = rows / recordsPerPage;
            if (rows % recordsPerPage > 0) {
                numOfPage++;
            }

            request.setAttribute("Orders", orders);
            request.setAttribute("Customers", customers);
            request.setAttribute("Sales", sales);
            request.setAttribute("OrderStatuses", orderStatuses);
            request.setAttribute("numOfPage", numOfPage);
            request.setAttribute("recordsPerPage", recordsPerPage);
            request.setAttribute("currentPage", currentPage);
            request.getRequestDispatcher("/admin/OrderList.jsp").forward(request, response);

        } catch (Exception ex) {
            System.out.println("Exception getListOrders ===== " + ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }

    }

    protected void searchOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int salesId = 0;
        int orderStatus = 0;
        int orderId = 0;

        String salesName = "";
        String status = "";
        String dateFrom = "";
        String dateTo = "";
        String searchField = request.getParameter("table_search").trim();

        try {
            orderId = Integer.parseInt(searchField);
        } catch (NumberFormatException ex) {
            orderId = 0;
        }
        System.out.println(orderId);

        if (request.getParameter("status") != null) {
            status = request.getParameter("status");
            switch (status) {
                case "Ordered":
                    orderStatus = 25;
                    break;
                case "Delivered":
                    orderStatus = 20;
                    break;
                case "Transporting":
                    orderStatus = 21;
                    break;
                case "Canceled":
                    orderStatus = 22;
                    break;
            }
        }

        salesId = Integer.parseInt(request.getParameter("salename"));
        if (request.getParameter("from") != null) {
            dateFrom = request.getParameter("from");
        }
        if (request.getParameter("to") != null) {
            dateTo = request.getParameter("to");
        }

        System.out.println("date From " + dateFrom);
        System.out.println("date To" + dateTo);

        UserDAO userDAO = new UserDAO();
        OrderDAO orderDAO = new OrderDAO();
        SettingDAO settingDAO = new SettingDAO();
        List<Order> orders = new ArrayList<>();
        List<User> customers = new ArrayList<>();
        List<User> sales = new ArrayList<>();
        List<String> orderStatuses = new ArrayList<>();

        try {
            customers = userDAO.getAllUserByRole(5);
            sales = userDAO.getAllUserByRole(3);
            orderStatuses = settingDAO.getSettingOrderValue();
            orders = orderDAO.searchOrder(searchField, dateFrom, dateTo, salesId, orderStatus, orderId);

            request.setAttribute("Orders", orders);
            request.setAttribute("Customers", customers);
            request.setAttribute("Sales", sales);
            request.setAttribute("OrderStatuses", orderStatuses);
            request.setAttribute("valueStatus", status);
            request.setAttribute("valueSalesId", salesId);
            request.setAttribute("valueSearch", searchField);
//                SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
//                request.setAttribute("valueFrom", sdf.parse(dateFrom));
//                request.setAttribute("valueTo", sdf.parse(dateTo));
            request.setAttribute("valueFrom", dateFrom);
            request.setAttribute("valueTo", dateTo);
            request.getRequestDispatcher("/admin/OrderList.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Exception getListOrders ===== " + ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
    }

    protected void getOrderByOrderId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String orderStatus = "";

        UserDAO userDAO = new UserDAO();
        OrderDAO orderDAO = new OrderDAO();
        SettingDAO settingDAO = new SettingDAO();
        ProductDAO productDAO = new ProductDAO();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<User> sales = new ArrayList<>();
        List<String> orderStatuses = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        Order order = new Order();
        User currentCustomer = new User();
        User currentSale = new User();
        User currentLogedInUser = new User();
        // Get session
        HttpSession session = request.getSession();
        // get account saved in session 
        currentLogedInUser = (User) session.getAttribute("account");

        try {
            order = orderDAO.getOrderByOrderId(orderId);
            currentCustomer = userDAO.getUserByUserId(order.getCustomerId());
            currentSale = userDAO.getUserByUserId(order.getSalesId());
            orderStatuses = settingDAO.getSettingOrderValue();
            sales = userDAO.getAllUserByRole(3);

            orderDetails = orderDetailDAO.getOrderDetailsByOrderId(orderId);
            products = productDAO.getAllProducts();

            switch (order.getOrderStatus()) {
                case 20:
                    orderStatus = "Delivered";
                    break;
                case 21:
                    orderStatus = "Transporting";
                    break;
                case 22:
                    orderStatus = "Canceled";
                    break;
                case 25:
                    orderStatus = "Ordered";
                    break;
            }

            int currentSaleId = order.getSalesId();
            int currentUserRole = currentLogedInUser.getRole();
            int currentUserId = currentLogedInUser.getUid();

            System.out.println(currentSaleId + " " + currentUserRole + " " + currentUserId);

            request.setAttribute("CurrentOrder", order);
            request.setAttribute("CurrentCustomer", currentCustomer);
            request.setAttribute("CurrentSale", currentSale);
            request.setAttribute("OrderStatuses", orderStatuses);
            request.setAttribute("Sales", sales);
            request.setAttribute("CurrentOrderStatus", orderStatus);
            request.setAttribute("Products", products);
            request.setAttribute("OrderDetails", orderDetails);
            session.setAttribute("CurrentUserRole", currentUserRole);
            session.setAttribute("CurrentOrderSalerId", currentSaleId);
            session.setAttribute("CurrentLogedInUserId", currentUserId);
            request.getRequestDispatcher("/admin/OrderDetail.jsp").forward(request, response);
        } catch (Exception ex) {
            System.out.println("Exception getOrderByOrderId ===== " + ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
    }

    protected void updateOrderSalerInfor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int status = 0;
        String salesNote = "";
        int salerId = 0;
        // GET CHOSEN SALER ID
        String saler = request.getParameter("saler");
        String orderStatus = request.getParameter("status");
        salesNote = request.getParameter("note");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        if (saler != null) {
            salerId = Integer.parseInt(saler);
        }
        switch (orderStatus) {
            case "Ordered":
                status = 25;
                break;
            case "Delivered":
                status = 20;
                break;
            case "Transporting":
                status = 21;
                break;
            case "Canceled":
                status = 22;
                break;
        }

        OrderDAO orderDAO = new OrderDAO();
        // Get session
        HttpSession session = request.getSession();
        try {
            int check = orderDAO.updateOrderSaleInfor(salerId, status, salesNote, orderId);
            if (check > 0) {
                String message = "Update saler successfully.";
                session.setAttribute("messageUpdateSaleInfor", message);
                response.sendRedirect("getOrder?orderId=" + orderId);
            } else {
                String messageFail = "Unexpected error occurs.";
                session.setAttribute("messageUpdateSaleInforFail", messageFail);
                response.sendRedirect("getOrder?orderId=" + orderId);
            }
        } catch (SQLException ex) {
            System.out.println("Exception updateOrderSalerInfor ===== " + ex);
            request.getRequestDispatcher("/admin/Error.jsp").forward(request, response);
        }
    }

    protected void updateProductOrderQuantity(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        float totalCost = 0;
        boolean checkUpdateQuan = false;
        // Get session
        HttpSession session = request.getSession();

        int currentOrderId = Integer.parseInt(request.getParameter("currentOrderId"));
        String[] productIds = request.getParameterValues("productToUpdate");
        String[] currentOrderDetailIds = request.getParameterValues("orderDetailToUpdate");

        String[] quantities = request.getParameterValues("quantity");
        String[] productPrices = request.getParameterValues("productPrice");

        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        OrderDAO orderDAO = new OrderDAO();
        OrderDetail orderDetail = null;
        ProductDAO productDAO = new ProductDAO();

        for (int i = 0; i < productIds.length; i++) {
            orderDetail = new OrderDetail();

            int orderDetailId = Integer.parseInt(currentOrderDetailIds[i]);
            orderDetail.setOrderDetailId(orderDetailId);
            try {
                // GET CURRENT ORDER DETAIL BEFORE CHANGE QUANTITY
                OrderDetail orderDetailBeforeChanged = orderDetailDAO.getOrderDetailsByOrderDetailId(orderDetailId);
                // QUANTITY OF THIS ORDER DETAIL BEFORE UPDATE
                int quantityBeforeUpdate = orderDetailBeforeChanged.getQuantity();
                // NEW QUANTIY TO UPDATE
                int newQuantity = Integer.parseInt(quantities[i]);
                // CHECK IF QUANTITY IS INCREASE OR DECREASE
                int quantityToUpdateDatabase = quantityBeforeUpdate - newQuantity;
                // CASE INCREASE QUANTITY
                if (quantityToUpdateDatabase < 0) {
                    // product increase their quantity
                    int newQuantityToUpdate = quantityToUpdateDatabase * (-1);
                    int currentProductId = Integer.parseInt(productIds[i]);
                    // update product quantity in product table
                    Product productToUpdateQuantity = productDAO.getProductById(currentProductId);
                    int newUpdateProductTableQuantity = productToUpdateQuantity.getQuantity() - newQuantityToUpdate;
                    // CHECK IF PRODUCT IS STOCKING
                    if (newUpdateProductTableQuantity >= 0) {
                        // Update product table quantity
                        int row = productDAO.updateProductQuantity(newUpdateProductTableQuantity, currentProductId);
                        if (row > 0) {
                            // Update product table success
                            orderDetail.setQuantity(newQuantity);
                            float price = Float.parseFloat(productPrices[i]);
                            float subCost = price * newQuantity;
                            orderDetail.setSubCost(subCost);
                            totalCost += subCost;

                            try {
                                int checkUpdateQuantity = orderDetailDAO.updateProductOrderQuantity(orderDetailId, newQuantity, subCost);
                                if (checkUpdateQuantity > 0) {
                                    checkUpdateQuan = true;
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println("Exception ===== " + ex);
                            }
                        }
                    } else {
                        // ERROR CAUSE NO MORE PRODUCT IN PRODUCT TABLE
                        String message = "Product " + productToUpdateQuantity.getTitle() + " is out of stock !!!";
                        session.setAttribute("messageUpdateFail", message);
                        response.sendRedirect("getOrder?orderId=" + currentOrderId);
                    }
                } else if (quantityToUpdateDatabase > 0) {
                    // NEW QUANTITY IS DECREASE
                    int currentProductId = Integer.parseInt(productIds[i]);
                    Product productToUpdateQuantity = productDAO.getProductById(currentProductId);
                    int newUpdateProductTableQuantity = productToUpdateQuantity.getQuantity() + quantityToUpdateDatabase;
                    // Update product table quantity
                    int row = productDAO.updateProductQuantity(newUpdateProductTableQuantity, currentProductId);
                    if (row > 0) {
                        // Update product table success
                        orderDetail.setQuantity(newQuantity);
                        float price = Float.parseFloat(productPrices[i]);
                        float subCost = price * newQuantity;
                        orderDetail.setSubCost(subCost);
                        totalCost += subCost;

                        try {
                            int checkUpdateQuantity = orderDetailDAO.updateProductOrderQuantity(orderDetailId, newQuantity, subCost);
                            if (checkUpdateQuantity > 0) {
                                checkUpdateQuan = true;
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Exception ===== " + ex);
                        }
                    }
                } else {
                    // PRODUCT QUANTITY IN ORDER DETAILS NOT CHANGED
                    float subCost = orderDetailDAO.getOrderDetailsByOrderDetailId(orderDetailId).getSubCost();
                    totalCost += subCost;
                }

            } catch (Exception ex) {
                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (checkUpdateQuan) {
            try {
                int check = orderDAO.updateTotalCost(currentOrderId, totalCost);
                if (check > 0) {
                    String message = "Update product succesfully.";
                    session.setAttribute("messageUpdateSuccess", message);
                    response.sendRedirect("getOrder?orderId=" + currentOrderId);
                } else {
                    String message = "Unexpected error occurs.Please try again later !!!";
                    session.setAttribute("messageUpdateFail", message);
                    response.sendRedirect("getOrder?orderId=" + currentOrderId);
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Exception ===== " + ex);
            }
        }

    }

    protected void removeProductFromOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int orderDetailId = Integer.parseInt(request.getParameter("odid"));
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        System.out.println("OrderDetail ID === " + orderDetailId);

        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        OrderDAO orderDAO = new OrderDAO();
        OrderDetail orderDetail = new OrderDetail();
        Order order = new Order();
        // Get session
        HttpSession session = request.getSession();

        try {

            order = orderDAO.getOrderByOrderId(orderId);
            orderDetail = orderDetailDAO.getOrderDetailsByOrderDetailId(orderDetailId);
            if (orderDetail != null) {
                float subTotaltoMinus = orderDetail.getSubCost();
                float newTotal = order.getTotalCost() - subTotaltoMinus;

                int check = orderDAO.updateTotalCost(orderId, newTotal);
                if (check > 0) {
                    int checkDelete = orderDetailDAO.deleteOrderDetail(orderDetailId);
                    if (checkDelete > 0) {
                        String message = "Remove product succesfully.";
                        session.setAttribute("messageUpdateSuccess", message);
                        response.sendRedirect("getOrder?orderId=" + orderId);
                    } else {
                        String message = "Unexpected error occurs.Please try again later !!!";
                        session.setAttribute("messageUpdateFail", message);
                        response.sendRedirect("getOrder?orderId=" + orderId);
                    }
                } else {
                    String message = "Unexpected error occurs.Please try again later !!!";
                    session.setAttribute("messageUpdateFail", message);
                    response.sendRedirect("getOrder?orderId=" + orderId);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception ===== " + ex);
        }

    }
}
