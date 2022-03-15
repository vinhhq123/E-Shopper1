

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
            .row.content {height: 550px}
            .row.content{margin-left:360px}
            /* Set gray background color and 100% height */
            .sidenav {
                background-color: #f1f1f1;
                height: 100%;
            }

            /* On small screens, set height to 'auto' for the grid */
            @media screen and (max-width: 767px) {
                .row.content {height: auto;} 

            }
            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            td, th {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }

            table, tr, td {
                text-align: center;
            }
            
            table, th, td{
    border:1px solid #868585;
}
table{
    border-collapse:collapse;
}
table tr:nth-child(odd){
    background-color:#eee;
}
table tr:nth-child(even){
    background-color:white;
}
table tr:nth-child(1){
    background-color:skyblue;
}

        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Slider</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="keywords" content="Admin, Bootstrap 3, Template, Theme, Responsive">
        <!-- bootstrap 3.0.2 -->
        <link href="${pageContext.request.contextPath}/admin/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${pageContext.request.contextPath}/admin/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="${pageContext.request.contextPath}/admin/css/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- google font -->
        <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <!-- Theme style -->
        <link href="${pageContext.request.contextPath}/admin/css/style.css" rel="stylesheet" type="text/css" />
        <!-- Import css for image -->
        <link href="${pageContext.request.contextPath}/admin/css/UserDetail.css" rel="stylesheet" type="text/css">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

    </head>
    <body class="skin-black">
        <!-- header logo: style can be found in header.less -->
        <jsp:include page="../admin/HeaderAdmin.jsp"></jsp:include>
            <div class="wrapper row-offcanvas row-offcanvas-left">
                <!-- Left side column. contains the logo and sidebar -->
            <jsp:include page="../admin/SideBarAdmin.jsp"></jsp:include>

                <!-- Right side column. Contains the navbar and content of the page -->
                <!-- /.right-side -->
                <!-- ./wrapper -->

                <div class="container-fluid">
                    <div class="row content">

                        <br>

                        <div class="col-sm-9">
                            <div class="well">
                                <h3>TOP 3 BEST CUSTOMERS</h3>
                                <table>
                                    <tr>
                                        <th >Customer Name</th>
                                        <th >Email</th>
                                        <th >Mobile</th>
                                        <th >Total Cost</th>
                                    </tr>
                                <c:forEach items="${top3Customer}" var="t">
                                    <tr>
                                        <td>${t.user.fullname}</td>
                                        <td>${t.user.email}</td>
                                        <td>${t.user.phone}</td>
                                        <td>${t.totalCost}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <div class="row">
                            <div class="col-sm-3">
                                <div class="well">
                                    <h4 style="text-align: center">Total Customers</h4>
                                    <p style="text-align: center"> ${totalUser}</p>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="well">
                                    <h4 style="text-align: center">Revenues 7 days</h4>
                                    <p style="text-align: center">${totalRevenue7days}đ</p> 
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="well">
                                    <h4 style="text-align: center">Total Product</h4>
                                    <p style="text-align: center">${totalProduct}</p> 
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <div class="well">
                                    <h4 style="text-align: center">Rated Star</h4>
                                    <p style="text-align: center">${ratedStar}<img style="max-width: 15% !important; margin-bottom: 5px;" src="${pageContext.request.contextPath}/admin/img/star-icon.png" alt=""/></p> 
                                </div>
                            </div>
                        </div>
                        <div style="border: 1px solid #dddddd; margin-bottom: 15px;">
                            <div class="row" style="margin-bottom: 15px;  margin-top: 15px">
                                <form action="${pageContext.request.contextPath}/dashboard/view" style="text-align: center;">
                                    <label>DATE: </label>
                                    <input type="date" value="${toDay}" name="daily">
                                    <input type="date" value="${toDay2}" name="daily2">
                                    <input style="margin-left:20px; background-color: green; color: greenyellow" value="Search" type="submit">
                                </form>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="well">
                                        <h4>Daily Revenue</h4>
                                        <p>${dailyInformation[0]}</p>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="well">
                                        <h4>Daily Customers</h4>
                                        <p>${dailyInformation[1]}</p>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="well">
                                        <h4>Total Canceled</h4>
                                        <p>${dailyCaneled}</p> 
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-7">
                                <div class="well">
                                    <h3>TOP 3 BEST SELLERS</h3>
                                    <table>
                                        <tr>
                                            <th >Customer Name</th>
                                            <th >Email</th>
                                            <th >Mobile</th>
                                            <th >Total Amount</th>
                                        </tr>
                                        <c:forEach items="${top3BestSeller}" var="t">
                                            <tr>
                                                <td>${t.user.fullname}</td>
                                                <td>${t.user.email}</td>
                                                <td>${t.user.phone}</td>
                                                <td>${t.customerId}</td>
                                            </tr>
                                        </c:forEach>
                                    </table> 
                                </div>
                            </div>
                            <div class="col-sm-5">
                                <div class="well" style="padding-bottom: 60px">
                                    <h4>TOP 3 BEST SELLING PRODUCT</h4>
                                    <table style="margin-top: 25px">
                                        <tr>
                                            <th >Product Name</th>
                                            <th >Total Quantity</th>
                                        </tr>
                                        <c:forEach items="${bestSellingPro}" var="b">
                                            <tr>
                                                <td>${b.title}</td>
                                                <td>${b.quantity}</td>
                                            </tr>
                                        </c:forEach>
                                    </table> 
                                </div>
                            </div>
                        </div>


                        <div>
                            <h4 style="text-align: center">LAST ORDERS</h4>
                        </div>
                        <table>
                            <tr>
                                <th>Order Date</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Total Cost</th>
                            </tr>
                            <c:forEach items="${lastOrders}" var="l">
                            <tr>
                                <td>${l.orderDate}</td>
                                <td>${l.user.fullname}</td>
                                <td>${l.user.email}</td>
                                <td>${l.user.phone}</td>
                                <td>${l.totalCost}</td>                    
                            </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-main">
            Copyright &copy Online Shop , 2022   
        </div>

        <!-- jQuery 2.0.2 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/admin/js/jquery.min.js" type="text/javascript"></script>

        <!-- Bootstrap -->
        <script src="${pageContext.request.contextPath}/admin/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- Director App -->
        <script src="${pageContext.request.contextPath}/admin/js/Director/app.js" type="text/javascript"></script>
        <!-- User Detail -->
        <script src="${pageContext.request.contextPath}/admin/js/UserDetail.js" type="text/javascript"></script>
        <!-- Add New User -->
        <script src="${pageContext.request.contextPath}/admin/js/AddNewUser.js" type="text/javascript"></script>

    </body>
</html>
