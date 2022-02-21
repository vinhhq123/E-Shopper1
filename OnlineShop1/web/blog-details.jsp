<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Blog Single | E-Shopper</title>
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

        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-8">
                        <div class="blog-post-area">
                            <h2 class="title text-center">BLOG DETAIL</h2>
                            <div class="single-blog-post">
                                <h3>${blog.postTitle}</h3>
                                <div class="post-meta">
                                    <ul>
                                        <li><i class="fa fa-user"></i>${blog.postAuthor.fullname}</li>
                                        <li><i class='fas fa-tags'></i>${blog.category.settingValue}</li>
                                        <li><i class="fa fa-calendar"></i>${blog.updateDate}</li>
                                    </ul>
                                </div>
                                <a href="">
                                    <img src="${blog.thumbnail}" alt="">
                                </a>
                                <p>
                                    ${blog.postContent}
                                </p>
                                <div class="pager-area">
                                    <ul class="pager pull-right">
<!--                                        <li><a href="#">Pre</a></li>
                                        <li><a href="#">Next</a></li>-->
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="blog-post-area">
                            <div class="brands_products"><!--brands_products-->
                                <h2>Blog Category</h2>
                                <div class="brands-name">
                                    <ul class="nav nav-pills nav-stacked">
                                        <c:forEach items="${listPostCate}" var="o">
                                            <li><a href="<%=request.getContextPath()%>/blog/cate?id=${o.settingId}">${o.settingValue}</a></li>
                                            </c:forEach>

                                    </ul>
                                </div>
                            </div>
                        </div><!--/blog-post-area-->
                    </div>	
                </div>
            </div>
        </section>

        <jsp:include page="footer.jsp"/>


        <script src="${pageContext.request.contextPath}/assets/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/price-range.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.scrollUp.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.prettyPhoto.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
    </body>
</html>
