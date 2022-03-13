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
        <title>Home | E-Shopper</title>
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
        <form action="<%=request.getContextPath()%>/goods/search" method="post" class="search_box pull-right"><!--search box -->
            <div class="col-sm-12">
                <div class="search_box pull-right">
                    <input value="${searchValue}" name="search" type="text" placeholder="Search"/>
                </div>
            </div>
        </form>
        <section id="slider"><!--slider-->
            <div class="container">
                <div class="row">
                    <div>
                        <div id="slider-carousel" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
                                <li data-target="#slider-carousel" data-slide-to="1"></li>
                                <li data-target="#slider-carousel" data-slide-to="2"></li>
                            </ol>

                            <div class="carousel-inner">

                                <c:forEach items="${sliders}" var="s" begin="0" end="0">
                                    <div class="item active">
                                            <a href="${s.back_link}">
                                                <img style="position: relative;width: 1000px;height: 450px; top: 10px" src="data:image/jpg;base64,${s.s_image}" alt="" />
                                            </a>
    <!--                                        <h2 style="position: absolute; top: 150px; left: 150px">${s.s_title}</h2>
                                            <p style="position: absolute; top: 200px; left: 150px">${s.s_notes}</p>-->
    <!--                                        <a href="${s.back_link}" style="position: absolute;top: 250px; left: 150px"><button type="button" class="btn btn-default get">Get it now</button></a>-->
                                    </div>
                                </c:forEach>


                                <c:forEach items="${sliders}" var="s" begin="1" end="2">
                                    <div class="item">
                                        <a href="${s.back_link}">
                                            <img style="position: relative;width: 1000px;height: 450px; top: 10px" src="data:image/jpg;base64,${s.s_image}" alt="" />
                                        </a>
<!--                                        <h2 style="position: absolute; top: 150px; left: 150px">${s.s_title}</h2>
                                        <p style="position: absolute; top: 200px; left: 150px">${s.s_notes}</p>-->
<!--                                        <a href="${s.back_link}" style="position: absolute;top: 250px; left: 150px"><button type="button" class="btn btn-default get">Get it now</button></a>-->
                                    </div>
                                </c:forEach>

                            </div>


                            <a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
                                <i class="fa fa-angle-left"></i>
                            </a>
                            <a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </div>

                    </div>
                </div>
            </div>
        </section><!--/slider-->

        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2>Product Category</h2>
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
                                    <div class="single-products row">
                                        <div class="col-sm-5">
                                            <img src="data:image/jpg;base64,${o.getThumbnail()}" width="100" height="100"/>
                                        </div>
                                        <div class="col-sm-7">
                                            <font color="#fe980f">${o.title}<p></p></font>
                                            <a href="<%=request.getContextPath()%>/goods/detail?pid=${o.pid}" class="btn btn-default add-to-cart">Show</a>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div><!--/category-productsr-->
                        </div>
                    </div>

                    <div class="col-sm-9 padding-right">
                        <div class="features_items"><!--features_items-->
                            <h2 class="title text-center">Latest products</h2>                          
                            <div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="item active">	
                                        <c:forEach items="${listGoods}" var="o" begin="0" end="2">
                                            <div class="col-sm-4">
                                                <div class="product-image-wrapper">
                                                    <div class="single-products">
                                                        <div class="productinfo text-center">
                                                            <img src="data:image/jpg;base64,${o.getThumbnail()}" alt="" width="100" height="200"/>
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
                                                            <a href="<%=request.getContextPath()%>/goods/detail?pid=${o.pid}" class="btn btn-default add-to-cart">Show</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="item">	
                                        <c:forEach items="${listGoods}" var="o" begin="3" end="5">
                                            <div class="col-sm-4">
                                                <div class="product-image-wrapper">
                                                    <div class="single-products">
                                                        <div class="productinfo text-center">
                                                            <img src="data:image/jpg;base64,${o.getThumbnail()}" alt="" height="200"/>
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
                                                            <a href="<%=request.getContextPath()%>/goods/detail?pid=${o.pid}" class="btn btn-default add-to-cart">Show</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
                                    <i class="fa fa-angle-left"></i>
                                </a>
                                <a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
                                    <i class="fa fa-angle-right"></i>
                                </a>			
                            </div>

                        </div><!--features_items-->

                        <div class="product-details"><!--product-details-->
                            <h2 class="title text-center">hot posts</h2>
                            <c:forEach items="${listHotBlogs}" var="o">
                                <div class="col-sm-5">
                                    <div class="">
                                        <img src="data:image/jpg;base64,${o.getThumbnail()}" alt="" height="150" width="300">
                                    </div>
                                </div>
                                <div class="col-sm-7">
                                    <div class="blog-post-area single-blog-post">
                                        <h3>${o.postTitle}</h3>
                                        <div class="post-meta"> 
                                            <p><i class='fas fa-tags'></i>Blog Category: ${o.category.settingValue}</p>
                                        </div>
                                        <a class="btn btn-primary" href="<%=request.getContextPath()%>/blog/detail?postId=${o.postId}">Read More</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div><!--/recommended_items-->

                </div>
            </div>
        </div>
    </section>

    <jsp:include page="footer.jsp"/>



    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.scrollUp.min.js"></script>
    <script src="assets/js/price-range.js"></script>
    <script src="assets/js/jquery.prettyPhoto.js"></script>
    <script src="assets/js/main.js"></script>
</body>
</html>
