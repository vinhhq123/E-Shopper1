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
                <div class="col-sm-3">
                    <div class="left-sidebar">
                        <h2>Product Category</h2>
                        <!--                        <div class="panel-group category-products" id="accordian">category-productsr-->
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
                            <div class="panel-group"><!--category-productsr-->
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
                            </div><!--/category-productsr-->
                        </div>
                        <!--                        </div>-->
                    </div>
                </div>
                <div class="table-responsive cart_info col-sm-9">
                    <table class="table table-condensed">
                        <thead>
                            <tr class="cart_menu">
                                <td class="image">Item</td>
                                <td class="description"></td>
                                <td class="price">Price</td>
                                <td class="quantity">Quantity</td>
                                <td class="total">Total</td>
                                <td></td>
                            </tr>
                        </thead>

                        <tbody>
                            <c:set var="o" value="${sessionScope.cart}"/>
                            <c:forEach items="${o.items}" var="i">
                                <tr>
                                    <td class="cart_product">
                                        <a href=""><img src="data:image/jpg;base64,${i.product.getThumbnail()}" alt="" width="100px" height="100px"></a>
                                    </td>
                                    <td class="cart_description">
                                        <h4><a>${i.product.title}</a></h4>
                                        <p>Product Id: ${i.product.pid}</p>
                                    </td>
                                    <td class="cart_price">
                                        <fmt:setLocale value = "vi_VN"/>
                                        <p><fmt:formatNumber value="${i.product.sprice}" type="currency" /></p>
                                    </td>
                                    <td class="cart_quantity">
                                        <div class="cart_quantity_button">
                                            <!--									<a class="cart_quantity_up" href=""> + </a>-->
                                            <input class="cart_quantity_input" readonly type="text" name="quantity" value="${i.quantity}" autocomplete="off" size="2">
                                            <!--									<a class="cart_quantity_down" href=""> - </a>-->
                                        </div>
                                    </td>
                                    <td class="cart_total">
                                        <fmt:setLocale value = "vi_VN"/>
                                        <p class="cart_total_price"><fmt:formatNumber value="${i.product.sprice * i.quantity}" type="currency" /></p>
                                    </td>
                                    <td class="cart_delete">
                                        <form action="<%=request.getContextPath()%>/goods/removeProductCart" method="post">
                                            <!--                                            <a class="cart_quantity_delete" href=""><i class="fa fa-times">-->
                                            <input type="hidden" name="id" value="${i.product.pid}"/>
                                            <input type="submit" value="Remove"/>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="container shopper-informations col-sm-9">
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="shopper-info">
                                <p>Shopper Information</p>
                                <form>
                                    <input type="text" placeholder="Display Name">
                                    <input type="text" placeholder="User Name">
                                    <input type="password" placeholder="Password">
                                    <input type="password" placeholder="Confirm password">
                                </form>
                                <a class="btn btn-primary" href="">Get Quotes</a>
                                <a class="btn btn-primary" href="">Continue</a>
                            </div>
                        </div>
                        <div class="col-sm-5 clearfix">
                            <div class="bill-to">
                                <p>Bill To</p>
                                <div class="form-one">
                                    <form>
                                        <input type="text" placeholder="Company Name">
                                        <input type="text" placeholder="Email*">
                                        <input type="text" placeholder="Title">
                                        <input type="text" placeholder="First Name *">
                                        <input type="text" placeholder="Middle Name">
                                        <input type="text" placeholder="Last Name *">
                                        <input type="text" placeholder="Address 1 *">
                                        <input type="text" placeholder="Address 2">
                                    </form>
                                </div>
                                <div class="form-two">
                                    <form>
                                        <input type="text" placeholder="Zip / Postal Code *">
                                        <select>
                                            <option>-- Country --</option>
                                            <option>United States</option>
                                            <option>Bangladesh</option>
                                            <option>UK</option>
                                            <option>India</option>
                                            <option>Pakistan</option>
                                            <option>Ucrane</option>
                                            <option>Canada</option>
                                            <option>Dubai</option>
                                        </select>
                                        <select>
                                            <option>-- State / Province / Region --</option>
                                            <option>United States</option>
                                            <option>Bangladesh</option>
                                            <option>UK</option>
                                            <option>India</option>
                                            <option>Pakistan</option>
                                            <option>Ucrane</option>
                                            <option>Canada</option>
                                            <option>Dubai</option>
                                        </select>
                                        <input type="password" placeholder="Confirm password">
                                        <input type="text" placeholder="Phone *">
                                        <input type="text" placeholder="Mobile Phone">
                                        <input type="text" placeholder="Fax">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="order-message">
                                <p>Shipping Order</p>
                                <textarea name="message"  placeholder="Notes about your order, Special Notes for Delivery" rows="16"></textarea>
                                <label><input type="checkbox"> Shipping to bill address</label>
                            </div>	
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


