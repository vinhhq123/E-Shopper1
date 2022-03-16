<%-- 
    Document   : order-history
    Created on : 08-Mar-2022, 17:15:40
    Author     : VINH
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/prettyPhoto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/price-range.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/animate.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/responsive.css" rel="stylesheet">
        <!-- Order History -->
        <link href="${pageContext.request.contextPath}/admin/css/orderhistory.css" rel="stylesheet" type="text/css" />
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
        <title>Order History</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <section>
            <div class="container">
                <div class="col-sm-8">
                    <div class="page-title">
                        <h2 class="title text-center">Order History</h2>
                    </div>
                    <div class="panel-body table-responsive">
                        <form class="form-inline" role="form" action="<%=request.getContextPath()%>/order/filterCustomerOrders">
                            <div class="form-group" style="margin-right:12px;">
                                <label for="fromC" class="sr-only"><b>From </b></label>
                                <input type="date" class="form-control" id="fromC" name="customerFrom" 
                                       placeholder="From Order Time"  value="${requestScope.valueCFrom}" >
                            </div>
                            <div class="form-group" style="margin-right:12px;">
                                <label for="toC" class="sr-only"><b>To </b></label>
                                <input type="date" class="form-control" id="toC" name="customerTo"
                                       placeholder="To Order Time" value="${requestScope.valueCTo}">
                            </div>
                            <div class="form-group" style="margin-right:8px;">
                                <select class="select" aria-label="Default select example" name="status" style="height: 30px">
                                    <c:if test="${empty valueCStatus}">
                                        <option value="">All Statuses</option>
                                        <c:forEach items="${OrderStatuses}" var="ost">
                                            <option value="<c:out value="${ost}"/>">${ost}</option>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${not empty valueCStatus}">
                                        <option value="" >All Statuses</option>
                                        <c:forEach items="${OrderStatuses}" var="ost">
                                            <option value="<c:out value="${ost}"/>" <c:if test="${ost eq valueCStatus}">selected</c:if>>${ost}</option>
                                        </c:forEach>
                                    </c:if>

                                </select>
                            </div>
                            <button type="submit" class="btn btn-default" style="background: #FE980F;  color: #FFFFFF;">Filter</button>
                        </form>
                        <br>
                        <c:if test="${not empty messageCancelOrder}">
                            <b><h4 class="help-block" style="color: green" id="messageCancelOrder">${messageCancelOrder}</h4></b>
                                <c:remove var="messageCancelOrder"/>
                            <br>
                        </c:if>
                        <table class="table table-responsive" id="OrderHistoryTable">
                            <tr>
                                <th >ID</th>
                                <th >Order Time</th>
                                <th >Last Updated</th>
                                <th style="text-align:right">Total Cost</th>
                                <th style="text-align:right">Status</th>
                                <th style="text-align:right">Actions</th>
                            </tr>
                            <tr>
                                <c:forEach items="${CustomerOrders}" var="cos">
                                    <td><b>${cos.getOrderId()}</b></td>
                                    <td><fmt:formatDate value="${cos.getOrderDate()}" pattern="dd-MM-yyyy" /></td>
                                    <td><fmt:formatDate value="${cos.getUpdatedDate()}" pattern="dd-MM-yyyy" /></td> 
                                    <td align="right"><span class="subtotal">
                                            <fmt:setLocale value = "vi_VN"/>
                                            <fmt:formatNumber value="${cos.getTotalCost()}" type="currency" />
                                        </span></td> 
                                    <td align="right">                                                    
                                        <c:if test="${cos.getOrderStatus() == 25}">
                                            <span class="label label-primary">Ordered</span>
                                        </c:if>
                                        <c:if test="${cos.getOrderStatus() == 20}">
                                            <span class="label label-success">Delivered</span>
                                        </c:if>
                                        <c:if test="${cos.getOrderStatus() == 21}">
                                            <span class="label label-warning">Transporting</span>
                                        </c:if>
                                        <c:if test="${cos.getOrderStatus() == 22}">
                                            <span class="label label-danger">Canceled</span>
                                        </c:if>
                                    </td>  
                                    <td align="right">
                                        <c:if test="${cos.getOrderStatus() == 25}">
                                            <button type="button" class="btn-xs btn-danger" onclick="if (confirm('Do you want to cancel this order?'))
                                                        window.location = '<%=request.getContextPath()%>/order/cancelCustomerOrder?orderId=${cos.getOrderId()}'">Cancel</button>
                                        </c:if>
                                        <button type="button" class="btn-xs btn-default" style="background: #FE980F;  color: #FFFFFF;"  onclick="window.location = '<%=request.getContextPath()%>/order/getOrderInfo?orderId=${cos.getOrderId()}'">View</button>
                                    </td>

                                </tr>
                            </c:forEach>
                        </table>

                        <br>
                        <c:if test="${requestScope.CustomerOrders.isEmpty()}">
                            <p style="text-align: center">No matching Orders found </p>
                        </c:if>                
                        <br>
                        <div class="table-foot">
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <jsp:include page="siderpost-product.jsp"/>
                </div>
            </div>
        </section>
        <jsp:include page="footer.jsp"/>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.scrollUp.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/price-range.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.prettyPhoto.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

    </body>
</html>
