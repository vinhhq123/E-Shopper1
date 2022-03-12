<%-- 
    Document   : orderinformation
    Created on : Mar 10, 2022, 1:31:38 PM
    Author     : HL2020
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
        <link href="<%=request.getContextPath()%>/admin/css/orderdetails.css" rel="stylesheet" type="text/css" />
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
                <div class="col-sm-9">
                    <div class="page-title">
                        <h2 class="title text-center">Order Information</h2>
                    </div>
                    <div class="panel-body table-responsive">
                        
                        <br>
                        <table class="table table-responsive" id="orderDetailTableEdit">
                            <tr>
                                <th>Name</th>
                                <th>Category</th>
                                <th>Thumbnail</th>
                                <th style="text-align:center">Unit Price</th>
                                <th style="text-align: center">Quantity</th>
                                
                                <th style="text-align:center">Cancel</th>
                                <th style="text-align:center">Feedback</th>
                            </tr>
                            <tr>
                                <c:forEach items="${OrderDetails}" var="ods">
                               
                                    <c:forEach items="${Products}" var="p">
                                        <c:choose>
                                            <c:when test="${p.getPid() == ods.getProductId() }">
                                                <td>${p.getTitle()}</td>
                                                <td><c:if test="${p.getCategoryID() == 13}">Shoes</c:if>
                                                    <c:if test="${p.getCategoryID() == 14}">Clothes</c:if>
                                                    <c:if test="${p.getCategoryID() == 15}">Bags</c:if>
                                                    </td>
                                                    <td><img src="data:image/jpg;base64,${p.getThumbnail()}" id="output1" width="110" /></td>    
                                                <td><span class="price">
                                                        <fmt:setLocale value = "vi_VN"/>
                                                        <fmt:formatNumber value="${p.getSprice()}" type="currency"/>
                                                    </span></td>
                                            <input type="hidden" value="${p.getSprice()}" name="productPrice">
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                                            
                                <td style="text-align:center">${ods.getQuantity()}</td>  
                                
<!--                                                        <input path="cartLines[${varStatus.index}].quantity" value="2"/>-->
                                    <td><input type="hidden" value="${ods.getProductId()}" name="productToUpdate">
                                        <input type="hidden" value="${ods.getOrderDetailId()}" name="orderDetailToUpdate">
                                        <button type="button" class="btn-xs btn-danger" onclick="if (confirm('Do you want to delete this product from this order?'))
                                                                    window.location = '<%=request.getContextPath()%>/order/removeproductinfo?odid=${ods.getOrderDetailId()}&orderId=${CurrentOrder.getOrderId()}'">Cancel</button>
                                       
                                    </td>
                                     <td><input type="hidden" value="${ods.getProductId()}" name="productToUpdate">
                                        <input type="hidden" value="${ods.getOrderDetailId()}" name="orderDetailToUpdate">
                                     
                                        <button type="button" class="btn-xs btn-success" onclick="if (confirm('Do you want send feedback to this order?'))
                                                                    window.location = '<%=request.getContextPath()%>/order/getorderfeedback?odid=${ods.getOrderDetailId()}&orderId=${CurrentOrder.getOrderId()}'">Feedback</button>
                                    </td>
                        </tr>
                            </tr>
                        </c:forEach>
                        </table>
                        
                        
                    <div class="page-title">
                        <h2 class="title text-right"><b>Total Cost</b></h2>
                        </div>
                                            <ul class="ulclass text-right">
                                                <li class="lii">Total : 
                                                    <span class="total">
                                                        <fmt:setLocale value = "vi_VN"/>
                                                        <fmt:formatNumber value="${CurrentOrder.getTotalCost()}" type="currency"/>
                                                    </span></li>
                                            </ul> 
                                                        <div class="col-lg-offset col-lg-8">
                                                            <button type="button" class="btn btn-primary" onclick="window.location = '<%=request.getContextPath()%>/order/getCustomerOrders'">Back Order List</button>
                                                        </div>
                        <br>
                        <c:if test="${requestScope.CustomerOrders.isEmpty()}">
                            <p style="text-align: center">No matching Orders found </p>
                        </c:if>                
                        <br>
                        <div class="table-foot">
                            <!--                            <ul class="pagination pagination-sm no-margin pull-right">
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
                        </ul>-->
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="row">

                        
                                <form action="<%=request.getContextPath()%>/goods/search" method="post" class="search_box pull-right">
                                    <div class="col-sm-12">
                                        <div class="search_box pull-right">
                                            <input value="${searchValue}" name="search" type="text" placeholder="Search Product"/>
                                            <!--                    <button type="submit">
                                                                    <i class="fa fa-search"></i>
                                                                </button>-->
                                        </div>
                                    </div>
                                </form>
                    </div><hr>
                    <div class="blog-post-area">
                        <div class="brands_products"><!--brands_products-->
                            <h2>Product Category</h2>
                            <div class="brands-name">
                                <ul class="nav nav-pills nav-stacked" style="text-align: center">
                                    <c:forEach items="${PostCategories}" var="pct">
                                        <li><a href="<%=request.getContextPath()%>/goods/goodsCate?id=${pct.settingId}"><b>${pct.settingValue}</b></a></li>
                                        </c:forEach>
<!--                                    <li><a href="<%=request.getContextPath()%>/blog/cate?id=${o.settingId}">Marketing</a></li>
                                    <li><a href="<%=request.getContextPath()%>/blog/cate?id=${o.settingId}">Promoted</a></li>-->
                                </ul>
                            </div>
                            <hr>    
                            <h2>Featured Products</h2>
                            <div class="brands-name">
                                <ul style="text-align: center;padding-right: 5%;">
                                    <c:forEach items="${FeaturedProducts}" var="fps">
                                        <li><a href="<%=request.getContextPath()%>/goods/detail?pid=${fps.getPid()}" target="_blank">
                                                <img src="data:image/jpg;base64,${fps.getThumbnail()}" alt="Product Image" style="width:130px;height: 130px;" class="features_items_img">
                                            </a>
                                            <p>${fps.getTitle()}</p>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
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
        <script src="${pageContext.request.contextPath}/admin/js/userProfile.js" type="text/javascript"></script>

    </body>
</html>
