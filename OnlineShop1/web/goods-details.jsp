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
        <title>Product Details | E-Shopper</title>
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

        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2>Category</h2>
                            <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                                <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                                    <c:forEach items="${listGoodsCate}" var="o">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h4 class="panel-title"><a href="<%=request.getContextPath()%>/goods/goodsCate?id=${o.settingId}">${o.settingValue}</a></h4>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div><!--/category-productsr-->

                                <div class="left-sidebar">
                                    <h2>Feature Products</h2>
                                    <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <img src="images/shop/product12.jpg" alt="" />
                                                <h2>$56</h2>
                                                <p>Easy Polo Black Edition</p>
                                                <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                            </div>
                                        </div>
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <img src="images/shop/product12.jpg" alt="" />
                                                <h2>$56</h2>
                                                <p>Easy Polo Black Edition</p>
                                                <a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
                                            </div>
                                        </div>
                                    </div><!--/category-productsr-->
                                </div>
                            </div><

                        </div>
                    </div>

                    <div class="col-sm-9 padding-right">
                        <div class="product-details"><!--product-details-->
                            <div class="col-sm-5">
                                <div class="view-product">
                                    <img src="data:image/jpg;base64,${good.getThumbnail()}" alt="" />
                                </div>
                            </div>
                            <div class="col-sm-7">
                                <div class="product-information"><!--/product-information-->
                                    <img src="images/product-details/new.jpg" class="newarrival" alt="" />
                                    <i class="fa fa-star"> ${good.ratedStars}</i>
                                    <p></p>
                                    <i class="fa fa-user"> ${good.author.fullname}</i>
                                    <p></p>
                                    <h2>${good.title}</h2>
                                    <p>Product ID: ${good.pid}</p>
                                    <p>Product Category: ${good.cate.settingValue}</p>
                                    <img src="images/product-details/rating.png" alt="" />
                                    <span>
                                        <span>
                                            <fmt:setLocale value = "vi_VN"/>
                                            <font color="gray" size="-0.5"><strike>
                                                <fmt:formatNumber value="${good.lprice}" type="currency" /></strike> </font>
                                            <font color="red">
                                            <fmt:formatNumber value="${good.sprice}" type="currency" /></font>
                                        </span>
                                    </span>
                                    <p>${good.breif}</p>
                                    <a href=""><img src="images/product-details/share.png" class="share img-responsive"  alt="" /></a>
                                </div><!--/product-information-->
                                <a href="${pageContext.request.contextPath}/eShopper/cart.html" class="btn btn-fefault cart">
                                    <i class="fa fa-shopping-cart"></i>
                                    Buy Now
                                </a>
                                <a href="<%=request.getContextPath()%>/goods/addToCart?pid=${good.pid}" class="btn btn-fefault cart">
                                    <i class="fa fa-shopping-cart"></i>
                                    Add to cart
                                </a>
                            </div>
                        </div><!--/product-details-->

                        <div class="category-tab shop-details-tab"><!--category-tab-->
                            <div class="col-sm-12">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a href="#reviews" data-toggle="tab">Reviews</a></li>
                                </ul>
                            </div>
                            <c:forEach items="${listFeedback}" var="o">
                                <div class="tab-content">
                                    <div class="tab-pane fade active in" id="reviews" >
                                        <div class="col-sm-12">
                                            <ul class="media-list">
                                                <li class="media">
                                                    <div class="media-body">
                                                        <ul class="sinlge-post-meta">
                                                            <li><i class="fa fa-user"></i>${o.saler.fullname}</li>
                                                            <!--                                                            <li><i class="fa fa-clock-o"></i> 1:33 pm</li>-->
                                                            <li><i class="fa fa-calendar"></i>${o.updatedDate}</li>
                                                        </ul>
                                                        <ul class="rating-area ratings">
                                                            <li class="rate-this">Rate:</li>
                                                            <li>
                                                                <c:if test="${o.ratedStart==1}">
                                                                    <i class="fa fa-star color"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                </c:if>
                                                                <c:if test="${o.ratedStart==2}">
                                                                    <i class="fa fa-star color"></i>
                                                                    <i class="fa fa-star color"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                </c:if>
                                                                <c:if test="${o.ratedStart==3}">
                                                                    <i class="fa fa-star color"></i>
                                                                    <i class="fa fa-star color"></i>
                                                                    <i class="fa fa-star color"></i>
                                                                    <i class="fa fa-star"></i>
                                                                    <i class="fa fa-star"></i>
                                                                </c:if>
                                                                <c:if test="${o.ratedStart==4}">
                                                                    <i class="fa fa-star color"></i>
                                                                    <i class="fa fa-star color"></i>
                                                                    <i class="fa fa-star color"></i>
                                                                    <i class="fa fa-star color"></i>
                                                                    <i class="fa fa-star"></i>
                                                                </c:if>
                                                                <c:if test="${o.ratedStart==5}">
                                                                    <i class="fa fa-star color"></i>
                                                                    <i class="fa fa-star color"></i>
                                                                    <i class="fa fa-star color"></i>
                                                                    <i class="fa fa-star color"></i>
                                                                    <i class="fa fa-star color"></i>
                                                                </c:if>
                                                            </li>
                                                            <li class="color"></li>
                                                        </ul> 
                                                        <img src="" alt="" />
                                                        <p>${o.feedbackContent}</p>
                                                        <!--                                                    <a class="btn btn-primary" href=""><i class="fa fa-reply"></i>Replay</a>-->
                                                    </div>
                                                </li>
                                            </ul> 
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <c:if test="${sessionScope.account != null}">
                                <a href="${pageContext.request.contextPath}/send-feedback.jsp" type="button" class="btn btn-default">
                                    Send Feedback
                                </a>
                            </c:if>
                        </div><!--/category-tab-->

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
