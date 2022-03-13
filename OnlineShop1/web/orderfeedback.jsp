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
        <link href="<%=request.getContextPath()%>/admin/css/ratestar.css" rel="stylesheet" type="text/css" />
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
                        <h2 class="title text-center">Order Feedback</h2>
                    </div>
                    <div class="panel-body table-responsive">
                        
                        <br>
                        <table class="table table-responsive" id="orderDetailTableEdit">
                            <input type="hidden" class="form-control" id="fullname" placeholder="Fullname" readonly value="${CurrentOrder.getOrderId()}" name="name" required>    
                            <input type="hidden" class="form-control" id="fullname" placeholder="Fullname" readonly value="${OrderDetails.getOrderDetailId()}" name="name" required>   
                         <h2 class="title text-left">USER INFORMATION</h2>
                            
                          <ul class="text">
                              
                              <li class="lii">Name : <b><i>${CurrentCustomer.getFullname()}</i></b></li>
                              <li class="lii">Email :  <b><i>${CurrentCustomer.getEmail()}</i></b></li>
                              <li class="lii">Mobile : <b><i>${CurrentCustomer.getPhone()}</i></b></li>
                              <li class="lii">Address : <b><i>${CurrentCustomer.getAddress()}</i></b></li>
                          </ul>
                        </table>
                              <form action="<%=request.getContextPath()%>/feedback/add" method="POST" enctype="multipart/form-data">
                                  <input type="hidden" class="form-control" id="fullname" placeholder="Fullname" readonly value="${CurrentCustomer.getUid()}" name="cusid" required>
                                  <input type="hidden" class="form-control" id="fullname" placeholder="Fullname" readonly value="${OrderDetails.getProductId()}" name="pid" required>
                                        
                                        <b>    Comments: </b><br><textarea name="content" rows="5" cols="30" onkeyup="change(this);" >${content}</textarea>
                                         Bạn đã nhập <span id="char_cnt">0</span> ký tự. Bạn còn <span id="chars_left">300</span> ký tự nữa.
                                        
                                        <br>
                                       <br>
                                         <div class="form-group">                                            
                                          <label class="col-sm-2 control-label">Rating:</label>
                                           <div id="rating">
                                            <input type="radio" id="star5" name="star" value="5" />
                                            <label class = "full" for="star5" title="Awesome - 5 stars"></label>

                                            <input type="radio" id="star4" name="star" value="4" />
                                            <label class = "full" for="star4" title="Pretty good - 4 stars"></label>

                                            <input type="radio" id="star3" name="star" value="3" />
                                            <label class = "full" for="star3" title="Meh - 3 stars"></label>

                                            <input type="radio" id="star2" name="star" value="2" />
                                            <label class = "full" for="star2" title="Kinda bad - 2 stars"></label>

                                            <input type="radio" id="star1" name="star" value="1" />
                                            <label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                                           </div>
                                                </div><br>
                                      <br>
                                      <br>
                                                <div><b>Image: </b><input type="file" name="image" /></div>
                                        <br>
                                         
                                        <button type="submit" class="btn btn-success pull-right">
                                            Submit
                                        </button>
                                        <br>
                                        <br>
                                        <br>
                                        <button type="button" class=" btn btn-primary " onclick="window.location = '<%=request.getContextPath()%>/order/getCustomerOrders'">Back Order List</button>
                                    </form>
                        
                        <c:if test="${requestScope.CustomerOrders.isEmpty()}">
                            <p style="text-align: center">No matching Orders found </p>
                        </c:if>                
                        <br>
                        <div class="table-foot">
                            <!--                            <ul class="pagination pagination-sm no-margin pull-right">
                           

                            

                           
                        </ul>-->
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
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
                                <ul style="text-align: center;padding-right: 10%;">
                                    <c:forEach items="${FeaturedProducts}" var="fps">
                                        <li><a href="<%=request.getContextPath()%>/goods/detail?pid=${fps.getPid()}" target="_blank">
                                                <img src="data:image/jpg;base64,${fps.getThumbnail()}" alt="Product Image" style="width:210px;height: 220px;" class="features_items_img">
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
        <script src="${pageContext.request.contextPath}/admin/js/ratestar.js" type="text/javascript"></script>
         <script src="${pageContext.request.contextPath}/assets/js/feedbacktext.js"></script>
    </body>
</html>

