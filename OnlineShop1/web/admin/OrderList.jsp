<%-- 
    Document   : OrderList
    Created on : 16-Feb-2022, 13:09:40
    Author     : VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order List</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
        <link href="${pageContext.request.contextPath}/admin/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${pageContext.request.contextPath}/admin/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="${pageContext.request.contextPath}/admin/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- google font -->
        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <!-- Theme style -->
        <link href="${pageContext.request.contextPath}/admin/css/style.css" rel="stylesheet" type="text/css" />
        <!-- Order Details CSS -->
        <link href="${pageContext.request.contextPath}/admin/css/orderDetail.css" rel="stylesheet" type="text/css" />
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="skin-black">
        <!-- header logo: style can be found in header.less -->
        <jsp:include page="HeaderAdmin.jsp"></jsp:include>
            <div class="wrapper row-offcanvas row-offcanvas-left">
                <!-- Left side column. contains the logo and sidebar -->
            <jsp:include page="SideBarAdmin.jsp"></jsp:include>

                <!-- Right side column. Contains the navbar and content of the page -->
                <aside class="right-side">

                    <!-- Main content -->
                    <section class="content">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel">
                                    <header class="panel-heading">
                                        <b>Order List</b>

                                    </header>
                                    <!-- <div class="box-header"> -->
                                    <!-- <h3 class="box-title">Responsive Hover Table</h3> -->

                                    <!-- </div> -->
                                    <div class="panel-body table-responsive">
                                        <div class="row">
                                            <div class="col-sm-11">
                                                <form class="form-inline" role="form" action="<%=request.getContextPath()%>/order/search" method="POST">
                                                <div class="form-group" style="margin-right:12px;">
                                                    <label class="sr-only" for="exampleInputEmail2">Email address</label>
                                                    <input type="text" name="table_search" class="form-control" id="exampleInputEmail2" placeholder="ID , Customer Name" value="${requestScope.valueSearch}" onblur="this.value = removeSpaces(this.value);">
                                                </div>
                                                <div class="form-group" style="margin-right:12px;">
                                                    <label for="from" class="sr-only"><b>From </b></label>
                                                    <input type="text" class="form-control" id="from" name="from" 
                                                           placeholder="From" onfocus="(this.type = 'date')" value="${requestScope.valueFrom}" >
                                                </div>
                                                <div class="form-group" style="margin-right:12px;">
                                                    <label for="to" class="sr-only"><b>To </b></label>
                                                    <input type="text" class="form-control" id="to" name="to"
                                                           placeholder="To" onfocus="(this.type = 'date')" value="${requestScope.valueTo}">
                                                </div>
                                                <div class="form-group" style="margin-right:8px;">
                                                    <select class="select" aria-label="Default select example" name="salename" style="height: 30px">
                                                        <c:if test="${empty valueSalesId}">
                                                            <option value="0" >All Sales Name</option>
                                                            <c:forEach items="${Sales}" var="ss">
                                                                <option value="<c:out value="${ss.getUid()}"/>" > ${ss.getFullname()}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                        <c:if test="${not empty valueSalesId}">
                                                            <option value="0" >All Sales Name</option>
                                                            <c:forEach items="${Sales}" var="ss">
                                                                <option value="<c:out value="${ss.getUid()}"/>" <c:if test="${ss.getUid() == valueSalesId}">selected</c:if>> ${ss.getFullname()}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group" style="margin-right:8px;">
                                                    <select class="select" aria-label="Default select example" name="status" style="height: 30px">
                                                        <c:if test="${empty valueStatus}">
                                                            <option value="" >All Statuses</option>
                                                            <c:forEach items="${OrderStatuses}" var="os">
                                                                <option value="<c:out value="${os}" />"> ${os}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                        <c:if test="${not empty valueStatus}"> 
                                                            <option value="" >All Statuses</option>
                                                            <c:forEach items="${OrderStatuses}" var="os">
                                                                <option value="<c:out value="${os}" />" <c:if test="${os eq valueStatus}">selected</c:if>> ${os}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <button type="submit" class="btn btn-success">Search</button>
                                            </form>
                                        </div>
                                        <div class="col-sm-1">
                                        </div>
                                    </div>
                                    <br>

                                    <table class="table table-striped" id="SettingListTable">
                                        <tr>
                                            <th onclick="sortTable(0)">ID</th>
                                            <th onclick="sortTable(1)">Order Date</th>
                                            <th onclick="sortTable(2)">Customer Name</th>
                                            <th onclick="sortTable(3)">Email</th>
                                            <th onclick="sortTable(4)">Mobile</th>
                                            <th onclick="sortTable(5)">Total Cost</th>
                                            <th onclick="sortTable(6)">Sale Names</th>
                                            <th onclick="sortTable(7)">Status</th>
                                        </tr>
                                        <c:forEach items="${Orders}" var="order">
                                            <tr>
                                                <td><b><a href="#">${order.getOrderId()}</a></b></td>
                                                <td><fmt:formatDate value="${order.getOrderDate()}" pattern="dd-MM-yyyy" /></td>
                                                <c:forEach items="${Customers}" var="cus">
                                                    <c:choose>
                                                        <c:when test="${cus.getUid() == order.getCustomerId()}">
                                                            <td>${cus.getFullname()}</td>
                                                            <td>${cus.getEmail()}</td>
                                                            <td>${cus.getPhone()}</td>
                                                        </c:when>
                                                    </c:choose>
                                                </c:forEach>
                                                <td><span class="subtotal">
                                                        <fmt:formatNumber value="${order.getTotalCost()}"/>
                                                    </span></td>  
                                                    <c:forEach items="${Sales}" var="sa">
                                                        <c:choose>
                                                            <c:when test="${sa.getUid() == order.getSalesId()}">
                                                            <td>${sa.getFullname()}</td>
                                                        </c:when>
                                                    </c:choose>
                                                </c:forEach>
                                                <td>
                                                    <c:if test="${order.getOrderStatus() == 25}">
                                                        <span class="label label-primary">Ordered</span>
                                                    </c:if>
                                                    <c:if test="${order.getOrderStatus() == 20}">
                                                        <span class="label label-success">Delivered</span>
                                                    </c:if>
                                                    <c:if test="${order.getOrderStatus() == 21}">
                                                        <span class="label label-warning">Transporting</span>
                                                    </c:if>
                                                    <c:if test="${order.getOrderStatus() == 22}">
                                                        <span class="label label-danger">Canceled</span>
                                                    </c:if>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>

                                    <br>
                                    <c:if test="${requestScope.Orders.isEmpty()}">
                                        <p style="text-align: center">No matching Orders found </p>
                                    </c:if> 
                                    <br>
                                    <div class="table-foot">
                                        <ul class="pagination pagination-sm no-margin pull-right">
                                            <c:if test="${currentPage != 1}">
                                                <li><a
                                                        href="<%=request.getContextPath()%>/order/list?currentPage=${currentPage-1}">Previous</a>
                                                </li>
                                            </c:if>

                                            <c:forEach begin="1" end="${numOfPage}" var="i">
                                                <c:choose>
                                                    <c:when test="${currentPage eq i}">
                                                        <li class="active"><a>
                                                                ${i} <span class="sr-only">(current)</span></a>
                                                        </li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li><a
                                                                href="<%=request.getContextPath()%>/order/list?currentPage=${i}">${i}</a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>

                                            <c:if test="${currentPage lt numOfPage}">
                                                <li><a
                                                        href="<%=request.getContextPath()%>/order/list?currentPage=${currentPage+1}">Next</a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </div>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div>
                    </div>
                </section><!-- /.content -->
                <div class="footer-main">
                    Copyright &copy Online Shop , 2022
                </div>
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->


        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/admin/js/jquery.min.js" type="text/javascript"></script>

        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/admin/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Director App -->
        <script src="${pageContext.request.contextPath}/admin/js/Director/app.js" type="text/javascript"></script>
        <!-- Director App -->
        <script src="${pageContext.request.contextPath}/admin/js/SettingList.js" type="text/javascript"></script>
    </body>
</html>
