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
        <link href="<%=request.getContextPath()%>/admin/css/orderdetails.css" rel="stylesheet" type="text/css" />
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
                                                    <li class="lii">Order ID :  <b><i>${CurrentOrder.getOrderId()}</i></b></li>
                                                <li class="lii">Customer Name : <b><i>${CurrentCustomer.getFullname()}</i></b></li>
                                                <li class="lii">Email :  <b><i>${CurrentCustomer.getEmail()}</i></b></li>
                                                <li class="lii">Mobile : <b><i>${CurrentCustomer.getPhone()}</i></b></li>
                                                <li class="lii">Order Date : <b><i><fmt:formatDate value="${CurrentOrder.getOrderDate()}" pattern="dd-MM-yyyy" /></i></b></li>
                                            </ul>
                                            <h3><b>Order Summary</b></h3>
                                            <ul class="ulclass">
                                                <li class="lii">Total : 
                                                    <span class="total">
                                                        <fmt:setLocale value = "vi_VN"/>
                                                        <fmt:formatNumber value="${CurrentOrder.getTotalCost()}" type="currency"/>
                                                    </span></li>
                                            </ul>
                                            <br> 
                                        </div>
                                        <!--  THIS IS FOR ASSIGNED SALES AND MANAGER ONLY -->
                                        <c:if test="${Valid == 1}">
                                            <div class="col-lg-6" >
                                                <h3><b>Sales Information</b></h3><br>
                                                <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/order/updateSaleInfor?orderId=<c:out value="${CurrentOrder.getOrderId()}" />" method="POST">
                                                    <c:if test="${not empty messageUpdateSaleInfor}">
                                                        <b><h4 class="help-block" style="color: green" id="successEditMessage">${messageUpdateSaleInfor}</h4></b>
                                                            <c:remove var="messageUpdateSaleInfor"/>
                                                        <br>
                                                    </c:if>
                                                    <c:if test="${not empty messageUpdateSaleInforFail}">
                                                        <b><h4 class="help-block" style="color: red" id="successEditMessage">${messageUpdateSaleInforFail}</h4></b>
                                                            <c:remove var="messageUpdateSaleInforFail"/>
                                                        <br>
                                                    </c:if>
                                                    <div class="form-group">
                                                        <label for="saler" class="col-lg-2 col-sm-2 control-label">Assigned</label>
                                                        <div class="col-lg-10">
                                                            <select class="form-control m-b-10" name="saler" <c:if test="${!(CurrentUserRole == 1 || CurrentUserRole == 2)}">disabled</c:if> >
                                                                <c:forEach items="${Sales}" var="sal">
                                                                    <option value="<c:out value="${sal.getUid()}"/>" <c:if test="${sal.getUid() == CurrentSale.getUid()}">selected="true"</c:if>>${sal.getFullname()}</option>
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
                                                        <label for="note" class="col-lg-2 col-sm-2 control-label">Notes</label>
                                                        <div class="col-lg-10">
                                                            <textarea id="note" placeholder="Sale Notes" name="note" style="width: 430px;"  rows="3" cols="30" maxlength="200">${CurrentOrder.getSalesNote()}</textarea>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <div class="col-lg-offset-2 col-lg-5">
                                                            <button type="submit" class="btn btn-success">Save changes</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </c:if>
                                    </div>

                                    <h3>This order includes the following products</h3>
                                    <br>

                                    <!--  THIS TABLE IS FOR OTHER SALES ONLY-->
                                    <c:if test="${Valid == 0}">


                                        <table class="table table-striped" id="orderDetailTable">
                                            <tr>
                                                <th>Name</th>
                                                <th>Category</th>
                                                <th>Unit Price</th>
                                                <th>Quantity</th>
                                                <th>Sub Total</th>
                                            </tr>
                                            <c:forEach items="${OrderDetails}" var="ods">
                                                <tr>
                                                    <c:forEach items="${Products}" var="p">
                                                        <c:choose>
                                                            <c:when test="${p.getPid() == ods.getProductId() }">
                                                                <td>${p.getTitle()}</td>
                                                                <td><c:if test="${p.getCategoryID() == 13}">Shoes</c:if>
                                                                    <c:if test="${p.getCategoryID() == 14}">Clothes</c:if>
                                                                    <c:if test="${p.getCategoryID() == 15}">Bags</c:if>
                                                                    </td>
                                                                    <td><span class="price">
                                                                        <fmt:formatNumber value="${p.getSprice()}" type="currency"/>
                                                                    </span></td>
                                                                </c:when>
                                                            </c:choose>
                                                        </c:forEach>

                                                    <td><b>${ods.getQuantity()}</b></td>  
                                                    <td><span class="subtotal">
                                                            <fmt:formatNumber value="${ods.getSubCost()}" type="currency"/>
                                                        </span></td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </c:if>

                                    <!--  THIS FORM IS FOR ASSIGNED SALES AND THE MANAGER-->
                                    <c:if test="${Valid == 1}">
                                        <div class="row">
                                            <div class="col-sm-9"></div>
                                            <div class="col-sm-3">
                                                <a href="#myModal-1" data-toggle="modal" class="btn btn-success" style="float: right">
                                                    <i class="fa fa-plus"></i> <span> Add another product</span>
                                                </a>
                                            </div>
                                        </div><br>

                                        <c:if test="${not empty messageUpdateSuccess}">
                                            <b><h4 class="help-block" style="color: green" id="editQuantity">${messageUpdateSuccess}</h4></b>
                                                <c:remove var="messageUpdateSuccess"/>
                                            <br>
                                        </c:if>
                                        <c:if test="${not empty messageUpdateFail}">
                                            <b><h4 class="help-block" style="color: red" id="editQuantity">${messageUpdateFail}</h4></b>
                                                <c:remove var="messageUpdateFail"/>
                                            <br>
                                        </c:if>
                                        <form method="POST" action="${pageContext.request.contextPath}/order/updateOrderQuantity">
                                            <input type="hidden" name="currentOrderId" value="${CurrentOrder.getOrderId()}">
                                            <table class="table table-striped" id="orderDetailTableEdit">
                                                <tr>
                                                    <th>Name</th>
                                                    <th>Category</th>
                                                    <th>Unit Price</th>
                                                    <th>Quantity</th>
                                                    <th>Sub Total</th>
                                                    <th>Action</th>
                                                </tr>
                                                <c:forEach items="${OrderDetails}" var="ods">
                                                    <tr>
                                                        <c:forEach items="${Products}" var="p">
                                                            <c:choose>
                                                                <c:when test="${p.getPid() == ods.getProductId() }">
                                                                    <td>${p.getTitle()}</td>
                                                                    <td><c:if test="${p.getCategoryID() == 13}">Shoes</c:if>
                                                                        <c:if test="${p.getCategoryID() == 14}">Clothes</c:if>
                                                                        <c:if test="${p.getCategoryID() == 15}">Bags</c:if>
                                                                        </td>
                                                                        <td><span class="price">
                                                                            <fmt:formatNumber value="${p.getSprice()}" type="currency"/>
                                                                        </span></td>
                                                                <input type="hidden" value="${p.getSprice()}" name="productPrice">
                                                            </c:when>
                                                        </c:choose>
                                                    </c:forEach>

                                                                <td><input name="quantity" value="${ods.getQuantity()}" pattern="^[1-9][0-9]*" required maxlength="9"/></td>  
                                                    <td><span class="subtotal">
                                                            <fmt:formatNumber value="${ods.getSubCost()}" type="currency"/>
                                                        </span></td>
    <!--                                                        <input path="cartLines[${varStatus.index}].quantity" value="2"/>-->
                                                    <td><input type="hidden" value="${ods.getProductId()}" name="productToUpdate">
                                                        <input type="hidden" value="${ods.getOrderDetailId()}" name="orderDetailToUpdate">
                                                        <button type="button" class="btn-xs btn-danger" onclick="if (confirm('Do you want to delete this product from this order?'))
                                                                    window.location = '<%=request.getContextPath()%>/order/removeProduct?odid=${ods.getOrderDetailId()}&orderId=${CurrentOrder.getOrderId()}'">Remove</button></td>
                                                    </tr>
                                                </c:forEach>
                                            </table>

                                            <div class="col-lg-offset-9">
                                                <button type="submit" class="btn btn-success">Update Quantity</button>
                                                <button type="button" class="btn btn-primary" onclick="window.location = '<%=request.getContextPath()%>/order/list'">Back</button>
                                            </div>
                                        </form>

                                    </c:if>
                                    <br>
                                    <c:if test="${Valid == 0}">
                                        <div class="col-lg-offset-10">
                                            <button type="button" class="btn btn-primary" onclick="window.location = '<%=request.getContextPath()%>/order/list'">Back</button>
                                        </div>
                                    </c:if>

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

        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal-1" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                        <h4 class="modal-title">Add another Product</h4>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/order/addProductToOrderDetail" method="POST">
                            <div class="form-group">
                                <label for="newProductId" class="col-lg-2 col-sm-2 control-label">Product</label>
                                <div class="col-lg-10">
                                    <select class="form-control m-b-10" name="newProductId" required>
                                        <c:forEach items="${ProductToAddNew}" var="pan">
                                            <option value="<c:out value="${pan.getPid()}"/>">${pan.getTitle()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="newQuantity" class="col-lg-2 col-sm-2 control-label">Quantity<span class="requiredd" style="color:#ff0000">(*)</span></label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="newQuantity" placeholder="Quantity" name="newQuantity" pattern="^[1-9][0-9]*" title="Quantity must be a number" required maxlength="9">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-2 col-lg-10">
                                    <button type="submit" class="btn btn-success">Add New</button>
                                </div>
                            </div>
                            <input type="hidden" name="currentOrderId" value="${CurrentOrder.getOrderId()}">
                        </form>

                    </div>

                </div>
            </div>
        </div>


        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/admin/js/jquery.min.js" type="text/javascript"></script>

        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/admin/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Director App -->
        <script src="${pageContext.request.contextPath}/admin/js/Director/app.js" type="text/javascript"></script>
    </body>
</html>
