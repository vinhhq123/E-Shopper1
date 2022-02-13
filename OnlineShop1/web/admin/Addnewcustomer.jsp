<%-- 
    Document   : Addnewcustomer
    Created on : Feb 12, 2022, 12:35:46 PM
    Author     : HL2020
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New User</title>
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
        <jsp:include page="HeaderAdmin.jsp"></jsp:include>
            <div class="wrapper row-offcanvas row-offcanvas-left">
                <!-- Left side column. contains the logo and sidebar -->
            <jsp:include page="SideBarManager.jsp"></jsp:include>

                <!-- Right side column. Contains the navbar and content of the page -->
                <aside class="right-side">

                    <!-- Main content -->
                    <section class="content">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel">
                                    <header class="panel-heading">
                                        <b>Add New Customer</b>

                                    </header>
                                    <!-- <div class="box-header"> -->
                                    <!-- <h3 class="box-title">Responsive Hover Table</h3> -->

                                    <!-- </div> -->
                                    <div class="row">
                                        <div class="col-lg-1"></div>
                                        <div class="col-lg-7">
                                            <div class="panel-body table-responsive">
                                                <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/customer/add" method="POST" name="/customer/add"  enctype="multipart/form-data">
                                                <c:if test="${not empty messageAddSuccess}">
                                                    <b><h4 class="help-block" style="color: green" id="successEditMessage">${messageAddSuccess}</h4></b>
                                                        <c:remove var="messageAddSuccess"/>
                                                    <br>
                                                </c:if>
                                                <div class="form-group">
                                                    <label for="avatar" class="col-lg-2 col-sm-2 control-label">Avatar</label>
                                                    <div class="col-lg-10">
                                                        <div class="profile-pic">
                                                            <label class="-label" for="file">
                                                                <c:if test="${ not empty imageValue}">
                                                                    <img src="data:image/jpg;base64,${imageValue}" id="output" width="200" />
                                                                </c:if>
                                                                <c:if test="${empty imageValue}">
                                                                    <img src="${pageContext.request.contextPath}/admin/img/user-bg.png" id="output" width="200" />
                                                                </c:if>
                                                            </label>
                                                            <input id="file" type="file" onchange="loadFile(event)" name="image"/>
                                                            <c:if test="${not empty errorImage}">
                                                                <p class="help-block" style="color: red" id="errorEmailMessage">${errorImage}</p>
                                                                <c:remove var="errorImage"/>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="inputEmail1" class="col-lg-2 col-sm-2 control-label">Email</label>
                                                    <div class="col-lg-10">
                                                        <input type="email" class="form-control" id="inputEmail1" placeholder="Email" name="email" value="${emailValue}" required pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$" title="Email must be in the right format">
                                                        <c:if test="${not empty error}">
                                                            <p class="help-block" style="color: red" id="errorEmailMessage">${error}</p>
                                                        </c:if>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="fullname" class="col-lg-2 col-sm-2 control-label">Fullname</label>
                                                    <div class="col-lg-10">
                                                        <input type="text" class="form-control" id="fullname" placeholder="Fullname" name="fullname" value="${nameValue}" onblur="this.value = removeSpaces(this.value);" required  >
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="title" class="col-lg-2 col-sm-2 control-label">Title</label>
                                                    <div class="col-lg-10">
                                                        <input type="text" class="form-control" id="title" placeholder="Title" name="title" value="${titleValue}" onblur="this.value = removeSpaces(this.value);" required>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">Gender</label>
                                                    <div class="col-lg-10">
                                                        <c:if test="${not empty genderValue}">
                                                            <div class="radio-inline">
                                                                <label>
                                                                    <input type="radio" name="gender" id="optionsRadios1" value="1" <%=request.getAttribute("genderValue").equals("1") ? "checked" : ""%>>
                                                                    Male
                                                                </label>
                                                            </div>
                                                            <div class="radio-inline">
                                                                <label>
                                                                    <input type="radio" name="gender" id="optionsRadios2" value="0" <%=request.getAttribute("genderValue").equals("0") ? "checked" : ""%>>
                                                                    Female
                                                                </label>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${empty genderValue}">
                                                            <div class="radio-inline">
                                                                <label>
                                                                    <input type="radio" name="gender" id="optionsRadios1" value="1" checked="">
                                                                    Male
                                                                </label>
                                                            </div>
                                                            <div class="radio-inline">
                                                                <label>
                                                                    <input type="radio" name="gender" id="optionsRadios2" value="0">
                                                                    Female
                                                                </label>
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="phone" class="col-lg-2 col-sm-2 control-label">Phone</label>
                                                    <div class="col-lg-10">
                                                        <input type="text" class="form-control" id="phone" placeholder="Phone" name="phone" required value="${phoneValue}" pattern="[0-9]{10}" title="Phone must be 10 digits and contains numbers only">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="address" class="col-lg-2 col-sm-2 control-label">Address</label>
                                                    <div class="col-lg-10">
                                                        <input type="text" class="form-control" id="address" placeholder="Address" name="address" value="${addressValue}" onblur="this.value = removeSpaces(this.value);" required>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="role" class="col-lg-2 col-sm-2 control-label">Role</label>
                                                    <div class="col-lg-10">
                                                        <select class="form-control m-b-10" name="role">
                                                            <c:if test="${empty roleValue}">
                                                              
                                                                <option value="5">Customer</option>
                                                            </c:if>
                                                            <c:if test="${not empty roleValue}">                                                              
                                                                <option value="5" <%=request.getAttribute("roleValue").equals("5") ? "selected" : ""%>>Customer</option>
                                                            </c:if>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">User Status</label>
                                                    <div class="col-lg-10">
                                                        <c:if test="${not empty statusValue}">
                                                            <div class="radio-inline">
                                                                <label>
                                                                    <input type="radio" name="status" id="optionsRadios3" value="1" <%=request.getAttribute("statusValue").equals("1") ? "checked" : ""%>>
                                                                    Active
                                                                </label>
                                                            </div>
                                                            <div class="radio-inline">
                                                                <label>
                                                                    <input type="radio" name="status" id="optionsRadios4" value="0" <%=request.getAttribute("statusValue").equals("0") ? "checked" : ""%>>
                                                                    Inactive
                                                                </label>
                                                            </div>
                                                        </c:if>
                                                        <c:if test="${empty statusValue}">
                                                            <div class="radio-inline">
                                                                <label>
                                                                    <input type="radio" name="status" id="optionsRadios3" value="1" checked="">
                                                                    Active
                                                                </label>
                                                            </div>
                                                            <div class="radio-inline">
                                                                <label>
                                                                    <input type="radio" name="status" id="optionsRadios4" value="0">
                                                                    Inactive
                                                                </label>
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-offset-2 col-lg-10">
                                                        <button type="submit" class="btn btn-success">Save</button>
                                                        <button type="button" class="btn btn-primary" onclick="window.location = '<%=request.getContextPath()%>/cusList'">Back</button>
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
            </aside><!-- /.right-side -->
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
