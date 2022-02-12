<%-- 
    Document   : SideBarManager
    Created on : Feb 12, 2022, 12:38:07 PM
    Author     : HL2020
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <aside class="left-side sidebar-offcanvas">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="${pageContext.request.contextPath}/admin/img/avatar3.png" class="img-circle" alt="User Image" />
                </div>
                <div class="pull-left info">
                    <p>Hello, ${fullname}</p>

                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>
            <ul class="sidebar-menu">
                <li>
                    <a href="index.html">
                        <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                    </a>
                </li>
               
                <li>
                    <a href="general.html">
                        <i class="fa fa-book"></i> <span>Product List</span>
                    </a>
                </li>

                <li>
                     <a href="<%=request.getContextPath()%>/cusList">
                        <i class="fa fa-user"></i> <span>Customer List</span>
                    </a>
                </li>

                <li>
                    <a href="basic_form.html">
                        <i class="fa fa-check"></i> <span>Feedback List</span>
                    </a>
                </li>

                <li>
                    <a href="basic_form.html">
                        <i class="fa fa-square"></i> <span>Slider List</span>
                    </a>
                </li>

                <li>
                    <a href="basic_form.html">
                        <i class="fa fa-paperclip"></i> <span>Post List</span>
                    </a>
                </li>

               

            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
</html>

