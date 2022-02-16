<%-- 
    Document   : OrderDetail
    Created on : 16-Feb-2022, 14:12:07
    Author     : VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
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
                                        <b>Order Details</b>

                                    </header>
                                    <!-- <div class="box-header"> -->
                                    <!-- <h3 class="box-title">Responsive Hover Table</h3> -->

                                    <!-- </div> -->

                                    <div class="row">
                                        <div class="col-lg-5" >
                                            <h3><b>Customer Information</b></h3>
                                            <ul class="ulclass">
                                                <li>Order ID : ${orderInfo.customerName}</li>
                                            <li>Customer Name: ${orderInfo.customerName}</li>
                                            <li>Email: ${orderInfo.customerEmail}</li>
                                            <li>Mobile: ${orderInfo.customerPhone}</li>
                                            <li>Order Date: ${orderInfo.customerAddress}</li>
                                        </ul>
                                        <h3><b>Order Summary</b></h3>
                                        <ul class="ulclass">
                                            <li>Total:
                                                <span class="total">
                                                    <fmt:formatNumber value="123" type="currency"/>
                                                </span></li>
                                        </ul>
                                                <br> 
                                    </div>
                                                <div class="col-lg-6" >
                                                    <h3><b>Sales Information</b></h3><br>
                                        <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/user/add" method="POST" name="addNewUser"  enctype="multipart/form-data">
                                            <c:if test="${not empty messageAddSuccess}">
                                                <b><h4 class="help-block" style="color: green" id="successEditMessage">${messageAddSuccess}</h4></b>
                                                    <c:remove var="messageAddSuccess"/>
                                                <br>
                                            </c:if>
                                            <div class="form-group">
                                                <label for="saler" class="col-lg-2 col-sm-2 control-label">Assigned</label>
                                                <div class="col-lg-10">
                                                    <select class="form-control m-b-10" name="saler" disabled="">
                                                        <option value="1">Saler 1</option>
                                                        <option value="1" selected="">Saler 2</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="status" class="col-lg-2 col-sm-2 control-label">Status</label>
                                                <div class="col-lg-10">
                                                    <select class="form-control m-b-10" name="status">
                                                        <option value="1">Ordered</option>
                                                        <option value="2">Transporting</option>
                                                        <option value="3">Delivered</option>
                                                        <option value="4">Canceled</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="saleNote" class="col-lg-2 col-sm-2 control-label">Sale Notes</label>
                                                <div class="col-lg-10">
                                                    <input type="text" class="form-control" id="saleNote" placeholder="Sale Notes" name="saleNote" value="${addressValue}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-lg-offset-2 col-lg-5">
                                                    <button type="submit" class="btn btn-success">Save changes</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <h3>This order includes the following products</h3>
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
    </body>
</html>
