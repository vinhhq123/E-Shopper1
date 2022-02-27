<%-- 
    Document   : ResetPassword
    Created on : Feb 27, 2022, 4:37:21 PM
    Author     : Edwars
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RESET PASSWORD</title>
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
            <div class="wrapper row-offcanvas row-offcanvas-left">
                <!-- Left side column. contains the logo and sidebar -->
                <!-- Right side column. Contains the navbar and content of the page -->

                    <!-- Main content -->
                    <section class="content">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel">
                                    <header class="panel-heading">
                                        <b>RESET PASSWORD</b>
                                    </header>
                                    <!-- <div class="box-header"> -->
                                    <!-- <h3 class="box-title">Responsive Hover Table</h3> -->

                                    <!-- </div> -->
                                    <div class="row">
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-7">
                                            <div class="panel-body table-responsive">
                                                <form class="form-horizontal" role="form" action="sendmail" method="POST">
                                                <c:if test="${not empty successMessage}">
                                                    <b><h4 class="help-block" style="color: green" id="successEditMessage">${successMessage}</h4></b>
                                                        <c:remove var="successMessage"/>
                                                    <br>
                                                </c:if>
                                                <div class="form-group">
                                                    <label for="inputEmail1" class="col-lg-2 col-sm-2 control-label">Input your Email</label>
                                                    <div class="col-lg-10">
                                                        <input  type="text" class="form-control" name="email" value ="${mailValue}" required pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$" title="Email must be in the right format">
                                                        <p style="color: red">${requestScope.fail1}</p>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-offset-2 col-lg-10">
                                                        <button type="submit" class="btn btn-success">Send</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div><!-- /.box-body -->
                                    </div>
                                    <div class="col-lg-4"></div>
                                </div>
                            </div><!-- /.box -->
                        </div>
                    </div>
                </section><!-- /.content -->
                <div class="footer-main">
                    Copyright &copy Online Shop , 2022
                </div>
        </div><!-- ./wrapper -->


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
