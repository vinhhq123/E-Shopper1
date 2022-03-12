<%@page import="java.io.Console"%>
<%@page import="java.text.DecimalFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Shop | E-Shopper</title>
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
<!--                    <button type="submit">
                        <i class="fa fa-search"></i>
                    </button>-->
                </div>
            </div>
        </form>
        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2>Category</h2>
                            <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                                <c:forEach items="${listGoodsCate}" var="o">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            <h4 class="panel-title"><a href="<%=request.getContextPath()%>/goods/goodsCate?id=${o.settingId}">${o.settingValue}</a></h4>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div><!--/category-productsr-->
                        </div>

                        <div class="left-sidebar">
                            <h2>Feature Products</h2>
                            <div class="panel-group"><!--category-productsr-->
                                <c:forEach items="${listFeatured}" var="o">
                                    <div class="single-products">
                                        <div class="text-center">
                                            <img src="data:image/jpg;base64,${o.getThumbnail()}" width="250" height="200"/>
                                            <font color="#fe980f">${o.title}<p>Views: ${o.views}</p></font>
                                            <p>
                                                <fmt:setLocale value = "vi_VN"/>
                                            <strike>
                                                <fmt:formatNumber value="${o.lprice}" type="currency" />
                                            </strike>
                                            <font color="red" size="+1"> <strong>
                                                <fmt:formatNumber value="${o.sprice}" type="currency" />  
                                            </strong>
                                            </font>
                                            </p>
                                            <a href="<%=request.getContextPath()%>/goods/detail?pid=${o.pid}" class="btn btn-default add-to-cart">Show more detail</a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div><!--/category-productsr-->
                        </div>
                    </div>

                    <div class="col-sm-9 padding-right">
                        <div class="features_items"><!--features_items-->
                            <h2 class="title text-center">Goods List</h2>
                            <c:forEach items="${listGoods}" var="o">
                                <div class="col-sm-4">
                                    <div class="product-image-wrapper">
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <img src="data:image/jpg;base64,${o.getThumbnail()}" alt="" width="100" height="250"/>
                                                <h2>${o.title}</h2>
                                                <p>
                                                    <fmt:setLocale value = "vi_VN"/>
                                                <strike>
                                                    <fmt:formatNumber value="${o.lprice}" type="currency" />
                                                </strike>
                                                <font color="red" size="+1"> <strong>
                                                    <fmt:formatNumber value="${o.sprice}" type="currency" />  
                                                </strong>
                                                </font>
                                                </p>
                                                <a href="<%=request.getContextPath()%>/goods/detail?pid=${o.pid}" class="btn btn-default add-to-cart">Show more detail</a>
                                            </div>
                                        </div>
                                        <div class="choose">
                                            <ul class="nav nav-pills nav-justified">
                                                <li><i class="fa fa-user"></i> ${o.author.fullname}</li>
                                                <li>
                                                    <i class="fa fa-star color"> <fmt:formatNumber pattern="#.#" value="${o.ratedStars}"/></i>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                        </div><!--features_items-->

                        <div class="pagination-area">
                            <c:set var="page" value="${requestScope.page}"/>
                            <ul class="pagination">
                                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                    <li><a class="${i==page?"active":""}" href="<%=request.getContextPath()%>/goods/goodsList?page=${i}">${i}</a></li>
                                    <!--                                        <li><a href="" class="active">1</a></li>-->
                                </c:forEach>
                                <li><a class="${i==page?"active":""}" href="<%=request.getContextPath()%>/goods/goodsList?page=${page+1}"><i class="fa fa-angle-double-right"></i></a></li>
                            </ul>
                        </div>
                    </div>
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
