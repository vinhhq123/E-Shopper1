<%-- 
    Document   : SettingList
    Created on : 08-Jan-2022, 17:33:06
    Author     : VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Setting List</title>
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
        <link href="${pageContext.request.contextPath}/view/css/font-awesome.min.css" rel="stylesheet">
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
                                        <b>Setting List</b>

                                    </header>
                                    <!-- <div class="box-header"> -->
                                    <!-- <h3 class="box-title">Responsive Hover Table</h3> -->

                                    <!-- </div> -->
                                    <div class="panel-body table-responsive">
                                        <div class="box-tools m-b-15">
                                            <div class="row">
                                                <div class="col-sm-8">
                                                    <form class="form-inline" role="form" action="<%=request.getContextPath()%>/setting/search" method="get">
                                                    <div class="form-group">
                                                        <label class="sr-only" for="exampleInputEmail2">Email address</label>
                                                        <input type="text" name="table_search" class="form-control" id="exampleInputEmail2" placeholder="Search by value" value="${requestScope.searchValue}" onblur="this.value = removeSpaces(this.value);">
                                                    </div>
                                                    <div class="form-group">
                                                        <select class="select" aria-label="Default select example" name="status">
                                                            <option selected value="">All Statuses</option>
                                                            <option value="1">Active</option>
                                                            <option value="0">Inactive</option>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <select class="select" aria-label="Default select example" name="type">
                                                            <option selected value="">All types</option>
                                                            <c:forEach items="${requestScope.types}" var="ty">
                                                                <option value="${ty}">${ty}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <button type="submit" class="btn btn-success">Search</button>
                                                </form>
                                            </div>
                                            <div class="col-sm-4">
                                                 <button type="button" class="btn btn-success" style="float: right" onclick="window.location = '<%=request.getContextPath()%>/admin/SettingAdd.jsp'">Add new</button>
                                            </div>

                                        </div>

                                    </div>
                                    <table class="table table-striped" id="SettingListTable">
                                        <tr>
                                            <th onclick="sortTable(0)">ID</th>
                                            <th onclick="sortTable(1)">Type</th>
                                            <th onclick="sortTable(2)">Value</th>
                                            <th onclick="sortTable(3)">Order</th>
                                            <th onclick="sortTable(4)">Status</th>
                                            <th>Actions</th>
                                        </tr>
                                        <c:forEach items="${SettingList}" var="con">
                                            <tr>
                                                <td>${con.getSettingId()}</td>
                                                <td>
                                                    <c:forEach var="type" begin="1" end="9" step="1">
                                                        <c:if test="${type ==  con.getSettingType()}">
                                                            ${types[type-1]}
                                                        </c:if>    
                                                    </c:forEach>
                                                </td>
                                                <td>${con.getSettingValue()}</td>
                                                <td>${con.getSettingOrder()}</td>
                                                <td><c:if test="${con.isSettingStatus()}">
                                                        <span class="label label-success">Active</span></td>
                                                    </c:if>
                                                    <c:if test="${! con.isSettingStatus()}">
                                                <span class="label label-danger">Inactive</span></td>
                                            </c:if>
                                            <td><a href="<%=request.getContextPath()%>/setting/getSettingID?settingId=${con.getSettingId()}">Edit</a>            
                                                <c:if test="${con.isSettingStatus()}">
                                                    <a href="<%=request.getContextPath()%>/setting/activate?sid=${con.getSettingId()}&status=${con.isSettingStatus()}" onclick="return confirm('Do you want to deacivate this seting?')">Deactivate</a>
                                                </c:if>
                                                <c:if test="${! con.isSettingStatus()}">
                                                    <a href="<%=request.getContextPath()%>/setting/activate?sid=${con.getSettingId()}&status=${con.isSettingStatus()}" onclick="return confirm('Do you want to acivate this setting?')">Activate</a>
                                                </c:if>
                                            </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    <br>
                                    <c:if test="${requestScope.SettingList.isEmpty()}">
                                        <p style="text-align: center">No matching Setting found </p>
                                    </c:if>                
                                    <br>
                                    <div class="table-foot">
                                        <ul class="pagination pagination-sm no-margin pull-right">
                                            <c:if test="${requestScope.currentPage != 1}">
                                                <li><a
                                                        href="<%=request.getContextPath()%>/setting/list?currentPage=${requestScope.currentPage-1}">Previous</a>
                                                </li>
                                            </c:if>

                                            <c:forEach begin="1" end="${requestScope.numOfPage}" var="i">
                                                <c:choose>
                                                    <c:when test="${requestScope.currentPage eq i}">
                                                        <li class="active"><a>
                                                                ${i} <span class="sr-only">(current)</span></a>
                                                        </li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <li><a
                                                                href="<%=request.getContextPath()%>/setting/list?currentPage=${i}">${i}</a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>

                                            <c:if test="${requestScope.currentPage lt requestScope.numOfPage}">
                                                <li><a
                                                        href="<%=request.getContextPath()%>/setting/list?currentPage=${requestScope.currentPage+1}">Next</a>
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
        <!-- Setting List -->
        <script src="${pageContext.request.contextPath}/admin/js/SettingList.js" type="text/javascript"></script>
    </body>
</html>
