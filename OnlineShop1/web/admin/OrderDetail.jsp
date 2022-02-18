<%-- 
    Document   : OrderDetail
    Created on : 16-Feb-2022, 14:12:07
    Author     : VINH
    SOURCE : https://o7planning.org/10605/create-a-java-shopping-cart-web-application-using-spring-mvc-and-hibernate
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
                                    <div class="panel-body table-responsive">
                                        <div class="row">
                                            <div class="col-lg-5" >
                                                <h3><b>Customer Information</b></h3>
                                                <ul class="ulclass">
                                                    <li>Order ID :  <b><i>${CurrentOrder.getOrderId()}</i></b></li>
                                                <li>Customer Name : <b><i>${CurrentCustomer.getFullname()}</i></b></li>
                                                <li>Email :  <b><i>${CurrentCustomer.getEmail()}</i></b></li>
                                                <li>Mobile : <b><i>${CurrentCustomer.getPhone()}</i></b></li>
                                                <li>Order Date : <b><i><fmt:formatDate value="${CurrentOrder.getOrderDate()}" pattern="dd-MM-yyyy" /></i></b></li>
                                            </ul>
                                            <h3><b>Order Summary</b></h3>
                                            <ul class="ulclass">
                                                <li>Total : 
                                                    <span class="total">
                                                        <fmt:formatNumber value="${CurrentOrder.getTotalCost()}" type="currency"/>
                                                    </span></li>
                                            </ul>
                                            <br> 
                                        </div>
                                        <!--  THIS IS FOR ASSIGNED SALES AND MANAGER ONLY -->
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
                                                        <select class="form-control m-b-10" name="saler" disabled>
                                                            <c:forEach items="${Sales}" var="sal">
                                                                <option value="<c:out value="${sal.getUid()}"/>" <c:if test="${sal.getUid() == CurrentSale.getUid()}">selected</c:if>>${sal.getFullname()}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="status" class="col-lg-2 col-sm-2 control-label">Status</label>
                                                    <div class="col-lg-10">
                                                        <select class="form-control m-b-10" name="status">
                                                            <c:forEach items="${OrderStatuses}" var="oss">
                                                                <option value="<c:out value="${oss}"/>" <c:if test="${oss eq CurrentOrderStatus}">selected</c:if>>${oss}</option>
                                                            </c:forEach>
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

                                    <!--  THIS TABLE IS FOR OTHER SALES ONLY-->
                                    <table class="table table-striped" id="orderDetailTable">
                                        <tr>
                                            <th>Name</th>
                                            <th>Category</th>
                                            <th>Unit Price</th>
                                            <th>Quantity</th>
                                            <th>Sub Total</th>
                                        </tr>
                                        <tr>
                                            <td>Product 1</td>
                                            <td>Category 1</td>  
                                            <td><span class="price">
                                                    <fmt:formatNumber value="12323" type="currency"/>
                                                </span></td>  
                                            <td>2</td>  
                                            <td><span class="subtotal">
                                                    <fmt:formatNumber value="2356" type="currency"/>
                                                </span></td>  
                                        </tr>
                                        <tr>
                                            <td>Product 2</td>
                                            <td>Category 2</td>  
                                            <td><span class="price">
                                                    <fmt:formatNumber value="123123" type="currency"/>
                                                </span></td>  
                                            <td>2</td>  
                                            <td><span class="subtotal">
                                                    <fmt:formatNumber value="12465" type="currency"/>
                                                </span></td>  
                                        </tr>
                                        <tr>
                                            <td>Product 3</td>
                                            <td>Category 1</td>  
                                            <td><span class="price">
                                                    <fmt:formatNumber value="1234" type="currency"/>
                                                </span></td>  
                                            <td>4</td>  
                                            <td><span class="subtotal">
                                                    <fmt:formatNumber value="1235" type="currency"/>
                                                </span></td>  
                                        </tr>
                                    </table>
                                    <!--  THIS FORM IS FOR ASSIGNED SALES AND THE MANAGER-->
                                    <form:form method="POST" action="${pageContext.request.contextPath}/shoppingCart">
                                        <table class="table table-striped" id="orderDetailTableEdit">
                                            <tr>
                                                <th>Name</th>
                                                <th>Category</th>
                                                <th>Unit Price</th>
                                                <th>Quantity</th>
                                                <th>Sub Total</th>
                                                <th>Action</th>
                                            </tr>
                                            <tr>
                                                <td>Product 1</td>
                                                <td>Category 1</td>  
                                                <td><span class="price">
                                                        <fmt:formatNumber value="123" type="currency"/>
                                                    </span></td>  
                                                <td><input
                                                        path="cartLines[${varStatus.index}].quantity" value="2"/></td>  
                                                <td><span class="subtotal">
                                                        <fmt:formatNumber value="123" type="currency"/>
                                                    </span></td>  
                                                <td><button type="button" class="btn-xs btn-danger" onclick="window.location = '<%=request.getContextPath()%>/user/getUser?uid=${con.getUid()}'">Remove</button></td>
                                            </tr>

                                            <tr>
                                                <td>Product 2</td>
                                                <td>Category 2</td>  
                                                <td><span class="price">
                                                        <fmt:formatNumber value="123" type="currency"/>
                                                    </span></td>  
                                                <td><input
                                                        path="cartLines[${varStatus.index}].quantity" value="2"/></td>  
                                                <td><span class="subtotal">
                                                        <fmt:formatNumber value="123" type="currency"/>
                                                    </span></td>  
                                                <td><button type="button" class="btn-xs btn-danger" onclick="window.location = '<%=request.getContextPath()%>/user/getUser?uid=${con.getUid()}'">Remove</button></td>
                                            </tr>
                                        </table>

                                        <div class="col-lg-offset-9 ">
                                            <button type="submit" class="btn btn-success">Update Quantity</button>
                                            <button type="button" class="btn btn-primary" onclick="window.location = '<%=request.getContextPath()%>/user/list'">Back</button>
                                        </div>
                                    </form:form>


                                </div><!-- /.box -->
                            </div>
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
