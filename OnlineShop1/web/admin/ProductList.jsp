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
                                        <b>Product List</b>

                                    </header>
                                    <!-- <div class="box-header"> -->
                                    <!-- <h3 class="box-title">Responsive Hover Table</h3> -->

                                    <!-- </div> -->
                                    <div class="panel-body table-responsive">
                                        <div class="row">
                                            <div class="col-sm-8">
                                                <form class="form-inline" role="form" action="<%=request.getContextPath()%>/product/search">
                                                <div class="form-group">
                                                    <label class="sr-only" for="exampleInputEmail2">Email address</label>
                                                    <input type="text" name="table_search" class="form-control" id="exampleInputEmail2" placeholder="Title or feature" value="${requestScope.searchValue}" onblur="this.value = removeSpaces(this.value);">
                                                </div>
                                                <div class="form-group">
                                                    <select class="select" aria-label="Default select example" name="category">
                                                        <c:if test="${empty valueCategory}">
                                                            <option selected value="">All Category</option>
                                                            <option value="13">Shoes</option>
                                                            <option value="14">Clothes</option>
                                                            <option value="15">Bags</option>
                                                        </c:if>
                                                        <c:if test="${not empty valueCategory}">
                                                            <option value="">All Roles</option>
                                                            <option value="13" <%=request.getAttribute("valueCategory").equals("13") ? "selected" : ""%>>Shoes</option>
                                                            <option value="14" <%=request.getAttribute("valueCategory").equals("14") ? "selected" : ""%>>Clothes</option>
                                                            <option value="15" <%=request.getAttribute("valueCategory").equals("15") ? "selected" : ""%>>Bags</option>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <select class="select" aria-label="Default select example" name="saler">
                                                        <c:if test="${empty valueSaler}">
                                                            <option selected value="">All Roles</option>
                                                            <option value="4">Kylian Mbappe</option>
                                                                <option value="5">Kendall Jenner</option>
                                                                <option value="12">Nike</option>
                                                                <option value="13">Jordan</option>
                                                                <option value="14">Anta</option>
                                                        </c:if>
                                                        <c:if test="${not empty valueSaler}">
                                                            <option value="">All Roles</option>
                                                            <option value="4" <%=request.getAttribute("valueSaler").equals("4") ? "selected" : ""%>>Kylian Mbappe</option>
                                                                <option value="5" <%=request.getAttribute("valueSaler").equals("5") ? "selected" : ""%>>Kendall Jenner</option>
                                                                <option value="12" <%=request.getAttribute("valueSaler").equals("12") ? "selected" : ""%>>Nike</option>
                                                                <option value="13" <%=request.getAttribute("valueSaler").equals("13") ? "selected" : ""%>>Jordan</option>
                                                                <option value="14" <%=request.getAttribute("valueSaler").equals("14") ? "selected" : ""%>>Anta</option>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <select class="select" aria-label="Default select example" name="status">
                                                        <c:if test="${empty valueStatus}">
                                                            <option value="" selected="">All Statuses</option>
                                                            <option value="16">Empty</option>
                                                            <option value="17">Stocking</option>
                                                        </c:if>
                                                        <c:if test="${not empty valueStatus}">                         
                                                            <option value="">All Statuses</option>
                                                            <option value="16" <%=request.getAttribute("valueStatus").equals("16") ? "selected" : ""%>>Empty</option>
                                                            <option value="17" <%=request.getAttribute("valueStatus").equals("17") ? "selected" : ""%>>Stocking</option>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                
                                                <button type="submit" class="btn btn-success">Search</button>
                                            </form>
                                        </div>
                                        <div class="col-sm-4">
                                            <button type="button" class="btn btn-success" style="float: right" onclick="window.location = '<%=request.getContextPath()%>/admin/ProductAdd.jsp'">Add New User</button>
<!--                                                <a href="<%=request.getContextPath()%>/addNewUser" style="float: right"> Add new</a>-->
                                        </div>

                                    </div>
                                    <br>
                                    <table class="table table-striped" id="SettingListTable">
                                        <tr>
                                            <th onclick="sortTable(0)">Product ID</th>
                                            <th onclick="sortTable(1)">Title</th>
                                            <th onclick="sortTable(2)">List Price</th>
                                            <th onclick="sortTable(3)">Sale Price</th>
                                            <th onclick="sortTable(4)">Feature</th>
                                            <th onclick="sortTable(5)">Category</th>
                                            <th onclick="sortTable(6)">Saler</th>
                                            <th onclick="sortTable(7)">Status</th>
                                            <th onclick="sortTable(8)">Quantity</th>
                                            <th onclick="sortTable(9)">Update Date</th>
                                            <th>Actions</th>
                                        </tr>
                                        <c:forEach items="${proList}" var="con">
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
                                                <td><button type="button" class="btn-xs btn-primary" onclick="window.location = '<%=request.getContextPath()%>/product/getProduct?uid=${con.getPid()}'">Edit</button></td>
                                            </tr>
                                        </c:forEach>
                                    </table>

                                    <br>
                                    <c:if test="${requestScope.productList.isEmpty()}">
                                        <p style="text-align: center">No matching Product found </p>
                                    </c:if>                
                                    <br>
                                    <div class="table-foot">
                                        <ul class="pagination pagination-sm no-margin pull-right">
                                            <c:if test="${currentPage != 1}">
                                                <li><a
                                                        href="<%=request.getContextPath()%>/product/list?currentPage=${currentPage-1}">Previous</a>
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
                                                                href="<%=request.getContextPath()%>/product/list?currentPage=${i}">${i}</a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>

                                            <c:if test="${currentPage lt numOfPage}">
                                                <li><a
                                                        href="<%=request.getContextPath()%>/product/list?currentPage=${currentPage+1}">Next</a>
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

