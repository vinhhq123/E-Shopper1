

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
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
        
        <style>
    .switch {
      position: relative;
      display: inline-block;
      width: 50px;
      height: 24px;
    }
    
    .switch input { 
      opacity: 0;
      width: 0;
      height: 0;
    }
    
    .slider {
      position: absolute;
      cursor: pointer;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
      background-color: #ccc;
      -webkit-transition: .4s;
      transition: .4s;
    }
    
    .slider:before {
      position: absolute;
      content: "";
      height: 16px;
      width:16px;
      left: 4px;
      bottom: 4px;
      background-color: white;
      -webkit-transition: .4s;
      transition: .4s;
    }
    
    input:checked + .slider {
      background-color: #2196F3;
    }
    
    input:focus + .slider {
      box-shadow: 0 0 1px #2196F3;
    }
    
    input:checked + .slider:before {
      -webkit-transform: translateX(26px);
      -ms-transform: translateX(26px);
      transform: translateX(26px);
    }
    
    /* Rounded sliders */
    .slider.round {
      border-radius: 34px;
    }
    
    .slider.round:before {
      border-radius: 50%;
    }
    
    * {
  box-sizing: border-box;
}

form.example input[type=text] {
  padding: 1px;
  font-size: 16px;
  border: 1px solid grey;
  float: left;
  width: 77%;
  background: #f1f1f1;
}

form.example button {
  float: left;
  width: 23%;
  
  background: #2196F3;
  color: white;
  font-size: 16px;
  border: 1px solid grey;
  border-left: none;
  cursor: pointer;
}

form.example button:hover {
  background: #0b7dda;
}

form.example::after {
  content: "";
  clear: both;
  display: table;
}
    </style>
    </head>
    <body class="skin-black">
        <!-- header logo: style can be found in header.less -->
        <jsp:include page="../admin/HeaderAdmin.jsp"></jsp:include>
            <div class="wrapper row-offcanvas row-offcanvas-left">
                <!-- Left side column. contains the logo and sidebar -->
            <jsp:include page="../admin/SideBarManager.jsp"></jsp:include>

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
                  <div style="float: right; margin-bottom: 20px; margin-right: 50px; margin-top: 10px;" class="search-form">
                    <form class="example" action="<%=request.getContextPath()%>/slider/list" style="margin:auto;max-width:300px">
                      <input type="text" placeholder="Search.." name="search2">
                      <button type="submit">Search</i></button>
                    </form>
                  </div>
                                        <div class="col-lg-7">
                                            <div class="panel-body table-responsive">
                        <c:forEach items="${sliders}" var="s">
                        <div class="single-blog-post">
                        <div class="header-slider">
                          <a style="float: right" href="<%=request.getContextPath()%>/slider/getslider?s_id=${s.s_id}"
                            ><button style="width: 70px">Edit</button></a
                          >
                          <a style="float: right; margin: 0 10px" href="#"
                            ><button style="width: 70px">View</button></a
                          >
                         

                            <a style="float: right;" href="#"
                            ><button style="width: 70px">Hide</button></a
                          >

                          <h4 style="text-align: center; margin-left: 100px">
                            ${s.s_title}
                          </h4>
                        </div>
                        <br /><br />
                        <div class="post-meta" style="">
                          <p>${s.s_id}</p>
                          <a href="">
                            <img
                              style="width: 140px; height: 140px"
                              src="data:image/jpg;base64,${s.s_image}"
                              alt=""
                            />
                          </a>
                          <p style="display: inline-block; margin-left: 50px; width: 400px;">
                            ${s.s_notes}
                          </p>
                        </div>
                        <a
                          style="margin-top: 20px"
                          class="btn btn-primary"
                          href="${s.back_link}"
                          >backlink</a
                        >
                      </div>
                        </c:forEach>
                                                
                                        
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
