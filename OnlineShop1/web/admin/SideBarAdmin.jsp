po<%-- 
    Document   : SideBar
    Created on : 09-Feb-2022, 18:05:47
    Author     : VINH
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
                    <p>Hello, ${sessionScope.account.fullname}</p>

                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>
            <ul class="sidebar-menu">
                <c:if test="${account.role == 1}">
                    <li>
                        <a href="<%=request.getContextPath()%>/setting/list">
                            <i class="fa fa-wrench"></i> <span>Setting List</span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/user/list">
                            <i class="fa fa-users"></i> <span>User List</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${account.role == 1 || account.role == 2}">

                    <li>
                        <a href="${pageContext.request.contextPath}/dashboard/view">
                            <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                        </a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/product/list">
                            <i class="fa fa-book"></i> <span>Product List</span>
                        </a>
                    </li>

                    <li>
                        <a href="<%=request.getContextPath()%>/customer/list">
                            <i class="fa fa-user"></i> <span>Customer List</span>
                        </a>
                    </li>

                    <li>
                        <a href="<%=request.getContextPath()%>/feedback/list">
                            <i class="fa fa-check"></i> <span>Feedback List</span>
                        </a>
                    </li>

                </c:if>
                <li>
                    <a href="<%=request.getContextPath()%>/order/list">
                        <i class="fa fa-square"></i> <span>Order List</span>
                    </a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/changepass">
                        <i class="fa fa-book"></i> <span>Change Password</span>
                    </a>
                </li>

            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
</html>
