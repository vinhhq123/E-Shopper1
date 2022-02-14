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
        <title>Product List</title>
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
            <jsp:include page="SideBarManager.jsp"></jsp:include>

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
                                                <form class="form-inline" role="form" action="<%=request.getContextPath()%>/proSearch">
                                                <div class="form-group">
                                                    <label class="sr-only" for="exampleInputEmail2">Email address</label>
                                                    <input type="text" name="table_search" class="form-control" id="exampleInputEmail2" placeholder="Title or feature" value="${requestScope.searchValue}" onblur="this.value = removeSpaces(this.value);">
                                                </div>
                                                <div class="form-group">
                                                    <select class="select" aria-label="Default select example" name="category">
                                                        <c:if test="${empty valueCategory}">
                                                            <option selected value="">All Category</option>
                                                            <c:forEach items="${CategoryList}" var="cat">
                                                            <option value="${cat.getSettingId()}">${cat.getSettingValue()}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                        <c:if test="${not empty valueCategory}">
                                                            <option value="">All Roles</option>
                                                            <c:forEach items="${CategoryList}" var="cat">
                                                            <option value="${cat.getSettingId()}" <%=request.getAttribute("valueCategory").equals("${cat.getSettingId()}") ? "selected" : ""%>>${cat.getSettingValue()}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <select class="select" aria-label="Default select example" name="saler">
                                                        <c:if test="${empty valueSaler}">
                                                            <option selected value="">All Salers</option>
                                                            <c:forEach items="${UserList}" var="urs">
                                                            <option value="${urs.getUid()}">${urs.getFullname()}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                        <c:if test="${not empty valueSaler}">
                                                            <option value="">All Salers</option>
                                                            <c:forEach items="${UserList}" var="urs">
                                                            <option value="${urs.getUid()}"<%=request.getAttribute("valueSaler").equals("${urs.getUid()}") ? "selected" : ""%>>${urs.getFullname()}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <select class="select" aria-label="Default select example" name="status">
                                                        <c:if test="${empty valueStatus}">
                                                            <option value="" selected="">All Statuses</option>
                                                            <c:forEach items="${StatusList}" var="sta">
                                                            <option value="${sta.getSettingId()}">${sta.getSettingValue()}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                        <c:if test="${not empty valueStatus}">                         
                                                            <option value="">All Statuses</option>
                                                            <c:forEach items="${StatusList}" var="sta">
                                                            <option value="${sta.getSettingId()}"<%=request.getAttribute("valueStatus").equals("${sta.getSettingId()}") ? "selected" : ""%>>${sta.getSettingValue()}</option>
                                                            </c:forEach>
                                                        </c:if>
                                                    </select>
                                                </div>
                                                
                                                <button type="submit" class="btn btn-success">Search</button>
                                            </form>
                                        </div>
                                        <div class="col-sm-4">
                                            <button type="button" class="btn btn-success" style="float: right" onclick="window.location = '<%=request.getContextPath()%>/admin/ProductAdd.jsp'">Add New Product</button>
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
                                        <c:forEach items="${ProList}" var="pro">
                                            <tr>
                                                <td>${pro.getPid()}</td>
                                                <td>${pro.getTitle()}</td>
                                                <td>${pro.getLprice()}</td>
                                                <td>${pro.getSprice()}</td>
                                                <td>${pro.getFeatured()}</td>  
                                                
                                                <c:forEach items="${CategoryList}" var="cat">
                                                    <c:if test="${cat.getSettingId() eq pro.getCategoryID()}">
                                                        <td>${cat.getSettingValue()}</td>
                                                    </c:if>
                                                </c:forEach>
                                                
                                                <c:forEach items="${UserList}" var="urs">
                                                    <c:if test="${urs.getUid() eq pro.getSalesId()}">
                                                        <td>${urs.getFullname()}</td>
                                                    </c:if>
                                                </c:forEach>
                                                        
                                                 <c:forEach items="${StatusList}" var="sta">
                                                    <c:if test="${sta.getSettingId() eq pro.getProductStatus()}">
                                                        <td>${sta.getSettingValue()}</td>
                                                    </c:if>
                                                </c:forEach>
                                                <td>${pro.getQuantity()}</td> 
                                                <td>${pro.getUpdatedDate()}</td> 
                                                <td><button type="button" class="btn-xs btn-primary" onclick="window.location = '<%=request.getContextPath()%>/proEdit?pid=${pro.getPid()}'">Edit</button></td>
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
                                                        href="<%=request.getContextPath()%>/proList?currentPage=${currentPage-1}">Previous</a>
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
                                                                href="<%=request.getContextPath()%>/proList?currentPage=${i}">${i}</a>
                                                        </li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>

                                            <c:if test="${currentPage lt numOfPage}">
                                                <li><a
                                                        href="<%=request.getContextPath()%>/proList?currentPage=${currentPage+1}">Next</a>
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

