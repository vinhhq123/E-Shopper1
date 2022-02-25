<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header id="header"><!--header-->


    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/assets/images/home/logo.png" alt="" /></a>
                    </div>
                </div>
                <div class="col-sm-8">
                    <div class="shop-menu pull-right">
                        <ul class="nav navbar-nav">
                            <c:if test="${sessionScope.account != null}">
                                <li><a href="<%=request.getContextPath()%>/setting/list"><i class="fa fa-user"></i> ${sessionScope.account.fullname}</a></li>
                                </c:if>

                            <!--                                                                        <li><a href="./admin/SettingList.jsp"><i class="fa fa-user"></i> Account</a></li>-->
<!--                            <li><a href="#"><i class="fa fa-star"></i> Wishlist</a></li>
                            <li><a href="checkout.html"><i class="fa fa-crosshairs"></i> Checkout</a></li>-->
                            <li><a href="<%=request.getContextPath()%>/cart.jsp"><i class="fa fa-shopping-cart"></i> Cart</a></li>
                                <c:if test="${sessionScope.account != null}">
                                <li><a href="${pageContext.request.contextPath}/Login.jsp"><i class="fa fa-lock"></i> Logout</a></li>
                                </c:if>
                                <c:if test="${sessionScope.account == null}">
                                <li><a href="${pageContext.request.contextPath}/Login.jsp"><i class="fa fa-lock"></i> Login</a></li>
                                </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/header-middle-->

    <div class="header-bottom"><!--header-bottom-->
        <div class="container">
            <div class="row">
                <div class="col-sm-9">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div class="mainmenu pull-left">
                        <ul class="nav navbar-nav collapse navbar-collapse">
                            <li><a href="${pageContext.request.contextPath}/index.jsp" class="active">Home</a></li>
                            <li><a href="<%=request.getContextPath()%>/goods/goodsList">Shop<i></i></a>
                            </li> 
                            <li class="dropdown"><a href="#" >Blog<i class="fa fa-angle-down"></i></a>
                                        <ul role="menu" class="sub-menu">
                                            <li class=""><a href="<%=request.getContextPath()%>/blog/bloglist">Blog<i class=""></i></a>
                                            </li>
                                            <li class=""><a href="<%=request.getContextPath()%>/post/list">Post List<i class=""></i></a>
                            </li>
                                            <li class=""><a href="<%=request.getContextPath()%>/post/PostDetail.jsp">Post Detail<i class=""></i></a>
                            </li>
                                            
                                        </ul>
                                    </li> 
                        </ul>
                    </div>
                </div>
                <form action="search" method="post">
                    <div class="col-sm-3">
                        <div class="search_box pull-right">
                            <input value="${searchValue}" name="search" type="text" placeholder="Search"/>
<!--                            <button type="submit">
                                <i class="fa fa-search"></i>
                            </button>-->
                        </div>
                    </div>
                </form>

            </div>
        </div>
    </div> <!--header-bottom-->
</header> 
