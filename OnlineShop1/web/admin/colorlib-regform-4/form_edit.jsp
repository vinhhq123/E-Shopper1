<%-- 
    Document   : form_edit
    Created on : Jan 15, 2022, 3:51:11 PM
    Author     : HL2020
--%>
<%@page import="model.Setting"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">

    <!-- Title Page-->
    <title>Au Register Forms by Colorlib</title>

    <!-- Icons font CSS-->
    <link href="${pageContext.request.contextPath}/admin/colorlib-regform-4/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="${pageContext.request.contextPath}/admin/colorlib-regform-4/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Vendor CSS-->
    <link href="${pageContext.request.contextPath}/admin/colorlib-regform-4/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="${pageContext.request.contextPath}/admin/colorlib-regform-4/vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="${pageContext.request.contextPath}/admin/colorlib-regform-4/css/main.css" rel="stylesheet" media="all">
</head>

<body>
    <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
        <div class="wrapper wrapper--w680">
            <div class="card card-4">
                <div class="card-body">
                    <h2 class="title">Setting Details</h2>
                    <form action="settingEdit" method="POST">
                        <input type="hidden" name="settingId" value="${requestScope.setting.getSettingId()}"/>
                        <div class="row row-space"> 
                             <div class="input-group">
                            <label class="label">Setting type</label>
                            <div class="rs-select2 js-select-simple select--no-search">
                                <select name="settingType">
                                   <c:forEach items="${requestScope.typename}" var="ty">
                                       <option value="${ty}">${ty}</option>
                                   </c:forEach>
                                </select>
                                <div class="select-dropdown"></div>
                            </div>
                        </div>
                             <div class="input-group">
                            <label class="label">Setting Value</label>
                            <div class="rs-select2 js-select-simple select--no-search">
                                <select name="settingValue">
                                    <c:forEach items="${requestScope.valuebytye}" var="tyy">
                                       <option value="${tyy}">${tyy} </option>
                                   </c:forEach>
                                </select>
                                <div class="select-dropdown"></div>
                            </div>
                        </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Setting Order</label>
                                    <input class="input--style-4" type="text" value="${requestScope.setting.getSettingOrder()}" name="settingOrder">
                                </div>
                            </div>
                            
                        </div>
                        <div class="row row-space">
                             
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Status</label>
                                    <div class="p-t-10">
                                        <label class="radio-container m-r-45">Active
                                            <input type="radio" 
                                      ${(requestScope.setting.isSettingStatus()) ? "checked=\"checked\"" : ""} name="settingStatus"              
                                      value="0" >
                                            <span class="checkmark"></span>
                                        </label>
                                        <label class="radio-container">Deactive
                                            <input type="radio" 
                                                    ${(!requestScope.setting.isSettingStatus()) ? "checked=\"checked\"" : ""} 
                                            name="settingStatus" value="1">
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                       
                        <div class="p-t-15">
                            <button class="btn btn--radius-2 btn--blue" type="submit">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

<!--     Jquery JS
    <script src="vendor/jquery/jquery.min.js"></script>
     Vendor JS
    <script src="vendor/select2/select2.min.js"></script>
    <script src="vendor/datepicker/moment.min.js"></script>
    <script src="vendor/datepicker/daterangepicker.js"></script>

     Main JS
    <script src="js/global.js"></script>-->

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
<!-- end document-->
