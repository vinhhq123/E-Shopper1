<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Import css for image -->
<link href="${pageContext.request.contextPath}/admin/css/UserDetail.css" rel="stylesheet">

<header id="header"><!--header-->


    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="<%=request.getContextPath()%>/home"><img src="${pageContext.request.contextPath}/assets/images/home/logo.png" alt="" /></a>
                    </div>
                </div>
                <div class="col-sm-8">
                    <div class="shop-menu pull-right">
                        <ul class="nav navbar-nav">
                            <li><a href="<%=request.getContextPath()%>/goods/addToCart"><i class="fa fa-shopping-cart"></i> Cart</a></li>
                                <c:if test="${sessionScope.account != null}">
                                <li class="dropdown user user-menu">

                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                        <i class="fa fa-user"></i>
                                        <span>${sessionScope.account.fullname} <i class="caret"></i></span>
                                    </a>
                                    <ul class="dropdown-menu dropdown-custom dropdown-menu-right">
                                        <li>
                                            <a href="#myModal-2" data-toggle="modal" >
                                                <i class="fa fa-user fa-fw pull-right"></i>
                                                My Profile
                                            </a>
                                            <a href="<%=request.getContextPath()%>/changepass">
                                                <i class="fa fa-user fa-fw pull-right"></i>
                                                Change Password
                                            </a>
                                            <a href="<%=request.getContextPath()%>/order/getCustomerOrders">
                                                <i class="fa fa-ticket fa-fw pull-right"></i>
                                                My Order
                                            </a>
                                            <!-- CURRENT USER IS ADMIN -->
                                            <c:if test="${account.getRole() == 1}">
                                                <a href="<%=request.getContextPath()%>/setting/list">
                                                    <i class="fa fa-cog fa-fw pull-right"></i>
                                                    Settings
                                                </a>
                                            </c:if>
                                            <!-- CURRENT USER IS MANAGER -->
                                            <!-- WILL CHANGE TO DASHBOARD LATER-->
                                            <c:if test="${account.getRole() == 2}">
                                                <a href="<%=request.getContextPath()%>/customer/list">
                                                    <i class="fa fa-users fa-fw pull-right"></i>
                                                    Customers
                                                </a>
                                            </c:if>
                                            <c:if test="${account.getRole() == 2}">
                                                <a href="<%=request.getContextPath()%>/product/list">
                                                    <i class="fa fa-users fa-fw pull-right"></i>
                                                    Product
                                                </a>
                                            </c:if>
                                            <!-- CURRENT USER IS SALES -->
                                            <c:if test="${account.getRole() == 3}">
                                                <a href="<%=request.getContextPath()%>/order/list">
                                                    <i class="fa fa-square fa-fw pull-right"></i>
                                                    Orders
                                                </a>
                                            </c:if>
                                            <!-- CURRENT USER IS MARKETING-->
                                            <c:if test="${account.getRole() == 4}">
                                                <a href="<%=request.getContextPath()%>/post/list">
                                                    <i class="fa fa-paperclip fa-fw pull-right"></i>
                                                    Posts list
                                                </a>
                                            </c:if>
                                            <c:if test="${account.getRole() == 4}">
                                                <a href="<%=request.getContextPath()%>/post/detail">
                                                    <i class="fa fa-paperclip fa-fw pull-right"></i>
                                                    Post Detail
                                                </a>
                                            </c:if>
                                            <c:if test="${account.getRole() == 4}">
                                                <a href="<%=request.getContextPath()%>/slider/list">
                                                    <i class="fa fa-paperclip fa-fw pull-right"></i>
                                                    Slider List
                                                </a>
                                            </c:if>
                                            <c:if test="${account.getRole() == 4}">
                                                <a href="<%=request.getContextPath()%>/slider/slider-detail.jsp ">
                                                    <i class="fa fa-paperclip fa-fw pull-right"></i> 
                                                    Slider Detail
                                                </a>
                                            </c:if>

                                            <a href="<%=request.getContextPath()%>/logout">
                                                <i class="fa fa-power-off fa-fw pull-right"></i> Logout</a>
                                        </li>
                                    </ul>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.account == null}">
                                <li><a href="${pageContext.request.contextPath}/Login.jsp"><i class="fa fa-lock"></i> Login</a></li>
                                </c:if>
                        </ul>
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
                                <li><a href="<%=request.getContextPath()%>/home" class="active">Home</a></li>
                                <li><a href="<%=request.getContextPath()%>/goods/goodsList">Shop<i></i></a>
                                </li> 
                                <li><a href="<%=request.getContextPath()%>/blog/bloglist">Blog<i class=""></i></a>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!--header-bottom-->
        <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal-2" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                        <h4 class="modal-title">User Profile</h4>
                    </div>
                    <div class="modal-body">

                        <form class="form-horizontal" role="form" method="POST"  name="editProfile" id="editProfile"  enctype="multipart/form-data" >
    <!--                        action="<%=request.getContextPath()%>/user/updateProfile" method="POST"-->
                            <h4 class="help-block" style="color: green;padding-left: 40px;" id="successEditMessage"></h4>
                            <div class="form-group">
                                <label for="avatar" class="col-lg-2 col-sm-2 control-label">Avatar</label>
                                <div class="col-lg-10">
                                    <div class="profile-pic">
                                        <label class="-label" for="file">
                                            <c:if test="${ not empty account.avatar}">
                                                <img src="data:image/jpg;base64,${account.avatar}" id="output" width="200" />
                                            </c:if>
                                            <c:if test="${empty account.avatar}">
                                                <img src="${pageContext.request.contextPath}/admin/img/user-bg.png" id="output" width="200" alt="default image"/>
                                            </c:if>
                                        </label>
                                        <input id="file" type="file" onchange="loadFile(event)" name="image"/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email" class="col-lg-2 col-sm-2 control-label">Email<span style="color:#ff0000"> (*)</span></label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="email" readonly value="${account.email}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="newFullname" class="col-lg-2 col-sm-2 control-label">Fullname<span style="color:#ff0000">(*)</span></label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="newFullname" placeholder="Fullname" name="newFullname" title="Fullname contains characters only" required maxlength="50" value="${account.fullname}">
                                    <p class="help-block" style="color: red" id="errorFullName"></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="newTitle" class="col-lg-2 col-sm-2 control-label">Title<span style="color:#ff0000"> (*)</span></label>
                                <div class="col-lg-10">
                                    <c:forEach items="${titles}" var="til">
                                        <div class="radio-inline">
                                            <label>
                                                <input type="radio" name="newTitle" id="optionsRadios5" value="<c:out value="${til}" />" 
                                                       <c:if test="${til == account.title}">checked</c:if>>
                                                <c:out value="${til}" />
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="newMobile" class="col-lg-2 col-sm-2 control-label">Mobile<span style="color:#ff0000"> (*)</span></label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="newMobile" placeholder="Mobile" 
                                           name="newMobile" pattern="[0-9]{10}" title="Phone contains numbers only" required maxlength="10" value="${account.phone}">
                                    <p class="help-block" style="color: red" id="errorPhone"></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="newAddress" class="col-lg-2 col-sm-2 control-label">Address<span style="color:#ff0000">(*)</span></label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="newAddress" placeholder="Address" name="newAddress" 
                                           title="Address cannot be null" required maxlength="200" value="${account.address}">
                                    <p class="help-block" style="color: red" id="errorAddress"></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-2 col-lg-10">
                                    <button type="button" class="btn btn-default get" id="saveC">Save changes</button>
                                </div>
                            </div>
                            <input type="hidden" name="currentUserId" value="${account.uid}" id="currentUserId">
                        </form>

                    </div>

                </div>
            </div>
        </div>
</header> 
<script src="https://code.jquery.com/jquery-2.1.4.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script>
<!-- User Detail -->
<script src="${pageContext.request.contextPath}/admin/js/UserDetail.js" type="text/javascript"></script>
<!-- User Profile -->
<script src="${pageContext.request.contextPath}/admin/js/userProfile1.js" type="text/javascript"></script>