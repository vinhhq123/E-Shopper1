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
                                                <form class="form-inline" role="form" action="<%=request.getContextPath()%>">
                                                <div class="form-group" style="margin-right:12px;">
                                                    <label class="sr-only" for="exampleInputEmail2">Email address</label>
                                                    <input type="text" name="table_search" class="form-control" id="exampleInputEmail2" placeholder="ID , Customer Name" value="${requestScope.searchValue}" onblur="this.value = removeSpaces(this.value);">
                                                </div>
                                                <div class="form-group" style="margin-right:12px;">
                                                    <label for="from" class="sr-only"><b>From </b></label>
                                                    <input type="text" class="form-control" id="from" name="from" 
                                                           placeholder="From" onfocus="(this.type = 'date')">
                                                </div>
                                                <div class="form-group" style="margin-right:12px;">
                                                    <label for="to" class="sr-only"><b>To </b></label>
                                                    <input type="text" class="form-control" id="to" name="to"
                                                           placeholder="To" onfocus="(this.type = 'date')">
                                                </div>
                                                <div class="form-group" style="margin-right:8px;">
                                                    <select class="select" aria-label="Default select example" name="salename" style="height: 30px">
                                                        <c:if test="${empty valueRole}">
                                                            <option selected value="">All Sales</option>
                                                            <option value="1">Sale 1</option>
                                                            <option value="2">Sale 2</option>
                                                        </c:if>
                                                        <c:if test="${not empty valueRole}">
                                                            <option value="">All Roles</option>
                                                            <option value="1" <%=request.getAttribute("valueRole").equals("1") ? "selected" : ""%>>Admin</option>
                                                            <option value="2" <%=request.getAttribute("valueRole").equals("2") ? "selected" : ""%>>Manager</option>
                                                            <option value="3" <%=request.getAttribute("valueRole").equals("3") ? "selected" : ""%>>Sales</option>
                                                            <option value="4" <%=request.getAttribute("valueRole").equals("4") ? "selected" : ""%>>Marketing</option>
                                                            <option value="5" <%=request.getAttribute("valueRole").equals("5") ? "selected" : ""%>>Customer</option>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group" style="margin-right:8px;">
                                                    <select class="select" aria-label="Default select example" name="status" style="height: 30px">
                                                        <c:if test="${empty valueStatus}">
                                                            <option value="" selected="">All Status</option>
                                                            <option value="1">Active</option>
                                                            <option value="0">Inactive</option>
                                                        </c:if>
                                                        <c:if test="${not empty valueStatus}">                         
                                                            <option value="">All Statuses</option>
                                                            <option value="1" <%=request.getAttribute("valueStatus").equals("1") ? "selected" : ""%>>Active</option>
                                                            <option value="0" <%=request.getAttribute("valueStatus").equals("0") ? "selected" : ""%>>Inactive</option>
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
                                        <tr>
                                            <td><b><a href="#">1</a></b></td>
                                            <td>12/02/2022</td>
                                            <td>Customer 1</td>
                                            <td>Customer Email 1</td>  
                                            <td>1234567890</td>  
                                            <td>1234567890</td>  
                                            <td>Sales 1</td>  
                                            <td><span class="label label-danger">Canceled</span></td>
                                        </tr>
                                        <tr>
                                            <td><b><a href="#">2</a></b></td>
                                            <td>12/02/2022</td>
                                            <td>Customer 1</td>
                                            <td>Customer Email 1</td>  
                                            <td>1234567890</td>  
                                            <td>1234567890</td>  
                                            <td>Sales 1</td>  
                                            <td><span class="label label-success">Delivered</span></td>
                                        </tr>
                                        <tr>
                                            <td><b><a href="#">3</a></b></td>
                                            <td>12/01/2022</td>
                                            <td>Customer 2</td>
                                            <td>Customer Email 1</td>  
                                            <td>1234567890</td>  
                                            <td>1234567890</td>  
                                            <td>Sales 4</td>  
                                            <td><span class="label label-warning">Transporting</span></td>
                                        </tr>
                                        <tr>
                                            <td><b><a href="#">4</a></b></td>
                                            <td>12/09/2022</td>
                                            <td>Customer 6</td>
                                            <td>Customer Email 1</td>  
                                            <td>1234567890</td>  
                                            <td>1234567890</td>  
                                            <td>Sales 5</td>  
                                            <td><span class="label label-primary">Ordered</span></td>
                                        </tr>
                                    </table>

                                    <br>
                                    <!-- message no
                                    
                                    order found-->
                                    <br>
                                    <div class="table-foot">
                                        <ul class="pagination pagination-sm no-margin pull-right">
                                            <c:if test="${currentPage != 1}">
                                                <li><a
                                                        href="<%=request.getContextPath()%>/user/list?currentPage=${currentPage-1}">Previous</a>
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
                                                                href="<%=request.getContextPath()%>/user/list?currentPage=${i}">${i}</a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>

                                            <c:if test="${currentPage lt numOfPage}">
                                                <li><a
                                                        href="<%=request.getContextPath()%>/user/list?currentPage=${currentPage+1}">Next</a>
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
