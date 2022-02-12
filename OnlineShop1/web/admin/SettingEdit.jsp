<%-- 
    Document   : SettingEdit
    Created on : Feb 12, 2022, 7:55:49 PM
    Author     : HL2020
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
                                    <b>Setting Details</b>

                                </header>
                                <!-- <div class="box-header"> -->
                                <!-- <h3 class="box-title">Responsive Hover Table</h3> -->

                                <!-- </div> -->
                                <div class="row">
                                    <div class="col-lg-1"></div>
                                    <div class="col-lg-7">
                                        <div class="panel-body table-responsive">
                                            <form class="form-horizontal"  action="settingEdit" method="POST">
                                               <input type="hidden" name="settingId" value="${requestScope.setting.getSettingId()}"/>
                                                
                                                <div class="form-group">
                                                    <label class="col-lg-2 col-sm-2 control-label">Setting Type</label>
                                                    <div class="col-lg-10">
                                                        
                                                   <select class="form-control m-b-10" name="settingType">
                                                   <c:forEach items="${requestScope.typename}" var="ty">
                                                       <option value="${ty}">${ty}</option>
                                                   </c:forEach>
                                                        </select>
                                                        
                                                    </div>
                                                </div>
                                            
                                                <div class="form-group">
                                                   <label class="col-lg-2 col-sm-2 control-label">Setting Value</label>
                                                    <div class="col-lg-10">
                                                        
                                                       <select class="form-control m-b-10" name="settingValue">
                                                            <c:forEach items="${requestScope.valuebytye}" var="tyy">
                                                               <option value="${tyy}">${tyy} </option>
                                                           </c:forEach>
                                                        </select>
                                      
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label  class="col-lg-2 col-sm-2 control-label">Setting Order</label>
                                                    <div class="col-lg-10">
                                                       
                                                       <input class="form-control" placeholder="Order" type="text"  value="${requestScope.setting.getSettingOrder()}" name="settingOrder" >
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    
                                                    <div class="col-lg-10">
                                                        <label class="col-sm-2 control-label col-lg-2">Setting Status</label>
                                                        <div class="radio-inline">
                                                            <label>
                                                                <input type="radio"  ${(requestScope.setting.isSettingStatus()) ? "checked=\"checked\"" : ""} name="settingStatus"              
                                                value="0" >
                                                                Active
                                                            </label>
                                                        </div>
                                                        <div class="radio-inline">
                                                            <label>
                                                                <input type="radio" ${(!requestScope.setting.isSettingStatus()) ? "checked=\"checked\"" : ""} 
                                            name="settingStatus" value="1">
                                                                Inactive
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                               
                                                <div class="form-group">
                                                    <div class="col-lg-offset-2 col-lg-10">
                                                        <button type="submit" class="btn btn-success">Save</button>
                                                        <button type="button" class="btn btn-primary" onclick="window.location = '<%=request.getContextPath()%>/settingList'">Back</button>
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
    </body>
</html>

