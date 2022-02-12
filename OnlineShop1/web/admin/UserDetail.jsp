<%-- 
    Document   : UserDetail
    Created on : 21-Jan-2022, 17:43:30
    Author     : VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Detail</title>
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
        <link href="${pageContext.request.contextPath}/admin/css/UserDetail.css" rel="stylesheet">
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
            <jsp:include page="SideBarAdmin.jsp"></jsp:include>

            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="panel">
                                <header class="panel-heading">
                                    <b>User Details</b>

                                </header>
                                <!-- <div class="box-header"> -->
                                <!-- <h3 class="box-title">Responsive Hover Table</h3> -->

                                <!-- </div> -->
                                <div class="row">
                                    <div class="col-lg-1"></div>
                                    <div class="col-lg-7">
                                        <div class="panel-body table-responsive">
                                            <form class="form-horizontal" role="form" action="<%=request.getContextPath()%>/editUser?uid=<c:out value="${currentUser.uid}"/>" method="POST" name="editUser"  enctype="multipart/form-data">
                                                <c:if test="${not empty successEditMessage}">
                                                    <h4 class="help-block" style="color: green" id="successEditMessage">${successEditMessage}</h4>
                                                    <c:remove var="successEditMessage"/>
                                                    <br>
                                                </c:if>
                                                <c:if test="${not empty errorEditMessage}">
                                                    <h4 class="help-block" style="color: red" id="errorEditMessage">${errorEditMessage}</h4>
                                                    <c:remove var="errorEditMessage"/>
                                                    <br>
                                                </c:if>
                                                <div class="form-group">
                                                    <label for="avatar" class="col-lg-2 col-sm-2 control-label">Avatar</label>
                                                    <div class="col-lg-10">
                                                        <div class="profile-pic">
                                                            <label class="-label" for="file">
                                                                <c:if test="${ not empty currentUser.avatar}">
                                                                    <img src="data:image/jpg;base64,${currentUser.avatar}" id="output" width="200" />
                                                                </c:if>
                                                                <c:if test="${empty currentUser.avatar}">
                                                                    <img src="${pageContext.request.contextPath}/admin/img/user-bg.png" id="output" width="200" alt="default image"/>
                                                                </c:if>
                                                            </label>
                                                            <input id="file" type="file" onchange="loadFile(event)" name="image"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="inputEmail1" class="col-lg-2 col-sm-2 control-label">Email</label>
                                                    <div class="col-lg-10">
                                                        <input type="email" class="form-control" id="inputEmail1" placeholder="Email" readonly value="${currentUser.email}" name="email"> 
                                                        <!--<p class="help-block">Example block-level help text here.</p>-->
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="fullname" class="col-lg-2 col-sm-2 control-label">Fullname</label>
                                                    <div class="col-lg-10">
                                                        <input type="text" class="form-control" id="fullname" placeholder="Fullname" value="${currentUser.fullname}" name="name" required>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="title" class="col-lg-2 col-sm-2 control-label">Title</label>
                                                    <div class="col-lg-10">
                                                        <input type="text" class="form-control" id="title" placeholder="Title" value="${currentUser.title}" name="title" required=>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">Gender</label>
                                                    <div class="col-lg-10">
                                                        <div class="radio-inline">
                                                            <label>
                                                                <input type="radio" name="gender" id="optionsRadios1" value="1" <%=request.getAttribute("userGender").equals("1") ? "checked" : ""%>>
                                                                Male
                                                            </label>
                                                        </div>
                                                        <div class="radio-inline">
                                                            <label>
                                                                <input type="radio" name="gender" id="optionsRadios2" value="0" <%=request.getAttribute("userGender").equals("0") ? "checked" : ""%>>
                                                                Female
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="phone" class="col-lg-2 col-sm-2 control-label">Phone</label>
                                                    <div class="col-lg-10">
                                                        <input type="text" class="form-control" id="phone" placeholder="Phone" value="${currentUser.phone}" name="phone" pattern="[0-9]{10}" title="Phone must be 10 digits and contains numbers only" required>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="address" class="col-lg-2 col-sm-2 control-label">Address</label>
                                                    <div class="col-lg-10">
                                                        <input type="text" class="form-control" id="address" placeholder="Address" value="${currentUser.address}" name="address" required>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="role" class="col-lg-2 col-sm-2 control-label">Role</label>
                                                    <div class="col-lg-10">
                                                        <select class="form-control m-b-10" name="role">
                                                            <option value="1" <%=request.getAttribute("currentUserRole").equals("1") ? "selected" : ""%>>Admin</option>
                                                            <option value="2" <%=request.getAttribute("currentUserRole").equals("2") ? "selected" : ""%>>Manager</option>
                                                            <option value="3" <%=request.getAttribute("currentUserRole").equals("3") ? "selected" : ""%>>Sales</option>
                                                            <option value="4" <%=request.getAttribute("currentUserRole").equals("4") ? "selected" : ""%>>Marketing</option>
                                                            <option value="5" <%=request.getAttribute("currentUserRole").equals("5") ? "selected" : ""%>>Customer</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">User Status</label>
                                                    <div class="col-lg-10">
                                                        <div class="radio-inline">
                                                            <label>
                                                                <input type="radio" name="status" id="optionsRadios3" value="1" <%=request.getAttribute("currentUserStatus").equals("1") ? "checked" : ""%>>
                                                                Active
                                                            </label>
                                                        </div>
                                                        <div class="radio-inline">
                                                            <label>
                                                                <input type="radio" name="status" id="optionsRadios4" value="0" <%=request.getAttribute("currentUserStatus").equals("0") ? "checked" : ""%>>
                                                                Inactive
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-lg-offset-2 col-lg-10">
                                                        <button type="submit" class="btn btn-success">Save</button>
                                                        <button type="button" class="btn btn-primary" onclick="window.location = '<%=request.getContextPath()%>/userList'">Back</button>
                                                    </div>
                                                </div>
                                                <input type="hidden" value="${currentUser.uid}" name="accountId"> 
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
    </body>
</html>

