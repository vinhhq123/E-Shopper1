<%-- 
    Document   : UserList
    Created on : 19-Jan-2022, 18:26:51
    Author     : VINH
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List</title>
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
                                        <b>User List</b>

                                    </header>
                                    <!-- <div class="box-header"> -->
                                    <!-- <h3 class="box-title">Responsive Hover Table</h3> -->

                                    <!-- </div> -->
                                    <div class="panel-body table-responsive">
                                        <div class="row">
                                            <div class="col-sm-8">
                                                <form class="form-inline" role="form" action="<%=request.getContextPath()%>/searchUser" method="get">
                                                <div class="form-group">
                                                    <label class="sr-only" for="exampleInputEmail2">Email address</label>
                                                    <input type="text" name="table_search" class="form-control" id="exampleInputEmail2" placeholder="Fullname, email or phone" value="${requestScope.searchValue}" onblur="this.value = removeSpaces(this.value);">
                                                </div>
                                                <div class="form-group">
                                                    <select class="select" aria-label="Default select example" name="status">
                                                        <c:if test="${empty valueStatus}">
                                                            <option value="" selected="">All Statuses</option>
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
                                                <div class="form-group">
                                                    <select class="select" aria-label="Default select example" name="role">
                                                        <c:if test="${empty valueRole}">
                                                            <option selected value="">All Roles</option>
                                                            <option value="1">Admin</option>
                                                            <option value="2">Manager</option>
                                                            <option value="3">Sales</option>
                                                            <option value="4">Marketing</option>
                                                            <option value="5">Customer</option>
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
                                                <button type="submit" class="btn btn-success">Search</button>
                                            </form>
                                        </div>
                                        <div class="col-sm-4">
                                            <button type="button" class="btn btn-success" style="float: right" onclick="window.location = './admin/AddNewUser.jsp'">Add New User</button>
<!--                                                <a href="<%=request.getContextPath()%>/addNewUser" style="float: right"> Add new</a>-->
                                        </div>

                                    </div>
                                    <br>
                                    <table class="table table-striped" id="SettingListTable">
                                        <tr>
                                            <th onclick="sortTable(0)">ID</th>
                                            <th onclick="sortTable(1)">Fullname</th>
                                            <th onclick="sortTable(2)">Title</th>
                                            <th onclick="sortTable(3)">Mobile</th>
                                            <th onclick="sortTable(4)">Email</th>
                                            <th onclick="sortTable(5)">Role</th>
                                            <th onclick="sortTable(6)">Status</th>
                                            <th>Actions</th>
                                        </tr>
                                        <c:forEach items="${UserList}" var="con">
                                            <tr>
                                                <td>${con.getUid()}</td>
                                                <td>${con.getFullname()}</td>
                                                <td>${con.getTitle()}</td>
                                                <td>${con.getPhone()}</td>  
                                                <td>${con.getEmail()}</td>  

                                                <c:forEach items="${SettingList}" var="set">
                                                    <c:if test="${set.getSettingId() eq con.getRole()}">
                                                        <td>${set.getSettingValue()}</td>
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${empty searchNow}">
                                                    <c:forEach items="${SettingList}" var="set">
                                                        <c:if test="${set.getSettingId() eq con.getAccountStatus()}">
                                                            <td><c:if test="${set.isSettingStatus()}">
                                                                    <span class="label label-success">Active</span>
                                                                </c:if>
                                                                <c:if test="${!set.isSettingStatus()}">
                                                                    <span class="label label-danger">Inactive</span>
                                                                </td>
                                                            </c:if>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${not empty searchNow}">
                                                    <c:if test="${con.getAccountStatus() == 1}">
                                                        <td> <span class="label label-success">Active</span></td>
                                                    </c:if>
                                                    <c:if test="${con.getAccountStatus() == 0 }">
                                                        <td> <span class="label label-danger">Inactive</span></td>
                                                    </c:if>
                                                </c:if>
                                                <td><button type="button" class="btn-xs btn-primary" onclick="window.location = '<%=request.getContextPath()%>/editUser?uid=${con.getUid()}'">Edit</button></td>
                                            </tr>
                                        </c:forEach>
                                    </table>

                                    <br>
                                    <c:if test="${requestScope.UserList.isEmpty()}">
                                        <p style="text-align: center">No matching User found </p>
                                    </c:if>                
                                    <br>
                                    <div class="table-foot">
                                        <ul class="pagination pagination-sm no-margin pull-right">
                                            <c:if test="${currentPage != 1}">
                                                <li><a
                                                        href="<%=request.getContextPath()%>/userList?currentPage=${currentPage-1}">Previous</a>
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
                                                                href="<%=request.getContextPath()%>/userList?currentPage=${i}">${i}</a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>

                                            <c:if test="${currentPage lt numOfPage}">
                                                <li><a
                                                        href="<%=request.getContextPath()%>/userList?currentPage=${currentPage+1}">Next</a>
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

