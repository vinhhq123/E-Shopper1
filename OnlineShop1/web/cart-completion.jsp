<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Cart Completion | E-Shopper</title>
        <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/prettyPhoto.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/price-range.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/animate.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/responsive.css" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    </head><!--/head-->

    <body>
        <jsp:include page="header.jsp"/>
        <form action="<%=request.getContextPath()%>/goods/search" method="post" class="search_box pull-right">
            <div class="col-sm-12">
                <div class="search_box pull-right">
                    <input value="${searchValue}" name="search" type="text" placeholder="Search"/>
                </div>
            </div>
        </form>
        <section id="cart_items">           
            <div class="container">
                <div class="row">
                    <!--                    <div class="col-sm-3">
                                            <div class="left-sidebar">
                                                <h2>Product Category</h2>
                                                                        <div class="panel-group category-products" id="accordian">category-productsr
                                                <div class="panel-group category-products" id="accordian">category-productsr
                    <c:forEach items="${listGoodsCate}" var="o">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title"><a href="<%=request.getContextPath()%>/goods/goodsCate?id=${o.settingId}">${o.settingValue}</a></h4>
                            </div>
                        </div>
                    </c:forEach>
                </div>/category-productsr

                <div class="left-sidebar">
                    <h2>Feature Products</h2>
                    <div class="panel-group">category-productsr
                    <c:forEach items="${listFeatured}" var="o">
                        <div class="single-products row">
                            <div class="col-sm-5">
                                <img src="data:image/jpg;base64,${o.getThumbnail()}" width="100" height="100"/>
                            </div>
                            <div class="col-sm-7">
                                <font color="#fe980f">${o.title}<p>Views: ${o.views}</p></font>
                                <p>
                        <fmt:setLocale value = "vi_VN"/>
                    <strike>
                        <fmt:formatNumber value="${o.lprice}" type="currency" />
                    </strike>
                    <font color="red" size="+0.5"> <strong>
                        <fmt:formatNumber value="${o.sprice}" type="currency" />  
                    </strong>
                    </font>
                    </p>
                    <a href="<%=request.getContextPath()%>/goods/detail?pid=${o.pid}" class="btn btn-default add-to-cart">Show</a>
                </div>
            </div>
                    </c:forEach>
                </div>/category-productsr
            </div>
                                    </div>
        </div>
    </div>-->
                    <div class="col-sm-12">
                        <span style="bottom: 20px">
                            <h2 style="font-family: sans-serif;color: #FE980F;text-align: center">BẠN ĐÃ ĐẶT HÀNG THÀNH CÔNG</h2>
                            <p style="font-family: sans-serif;text-align: center">Cảm ơn bạn đã lựa chọn sản phẩm của chúng tôi<br>Sản phẩm sẽ được chuyển đến bạn trong thời gian sớm nhất.<br>Chúc bạn có trải nghiệm sản phẩm tuyệt vời.</p>
                            <p style="text-align: center"> <i style="font-family: sans-serif;">From Shop With Love <3</i></p>
                        </span> 
                        <table class="table-responsive cart_info table table-condensed"style="margin-bottom: 25px;">
                            <thead>
                                <tr class="cart_menu">
                                    <td class="image">Item</td>
                                    <td class="description"></td>
                                    <td class="price">Price</td>
                                    <td class="quantity">Quantity</td>
                                    <td class="total">Total</td>
                                </tr>
                            </thead>

                            <tbody>
                                <c:set var="o" value="${sessionScope.cart}"/>
                                <c:forEach items="${o.items}" var="i">
                                    <tr>
                                        <td class="cart_product col-sm-2">
                                            <a href=""><img src="data:image/jpg;base64,${i.product.getThumbnail()}" alt="" width="100px" height="100px"></a>
                                        </td>
                                        <td class="cart_description col-sm-4">
                                            <h4><a>${i.product.title}</a></h4>
                                        </td>
                                        <td class="cart_price col-sm-2">
                                            <fmt:setLocale value = "vi_VN"/>
                                            <p><fmt:formatNumber value="${i.product.sprice}" type="currency" /></p>
                                        </td>
                                        <td class="cart_quantity col-sm-2">
                                            <div class="cart_quantity_button">
                                                <!--									<a class="cart_quantity_up" href=""> + </a>-->
                                                <input class="cart_quantity_input" readonly type="text" name="quantity" value="${i.quantity}" autocomplete="off" size="2">
                                                <!--									<a class="cart_quantity_down" href=""> - </a>-->
                                            </div>
                                        </td>
                                        <td class="cart_total col-sm-2">
                                            <fmt:setLocale value = "vi_VN"/>
                                            <p class="cart_total_price"><fmt:formatNumber value="${i.product.sprice * i.quantity}" type="currency" /></p>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="container shopper-informations" style="margin-bottom: 30px; text-align: center">
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/goods/goodsList">Continue Shopping</a>
                </div>
            </div>
        </section>

        <jsp:include page="footer.jsp"/><!--/Footer-->



        <script src="${pageContext.request.contextPath}/assets/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/price-range.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.scrollUp.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.prettyPhoto.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
    </body>
</html>


