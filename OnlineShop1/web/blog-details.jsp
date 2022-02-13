<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                            <h2 class="title text-center"></h2>
                            <div class="single-blog-post">
                                <h3>${blog.postTitle}</h3>
                                <div class="post-meta">
                                    <ul>
                                        <li><i class="fa fa-user"></i>${blog.postAuthor}</li>
                                        <li><i class="fa fa-clock-o"></i>${blog.category}</li>
                                        <li><i class="fa fa-calendar"></i>${blog.updateDate}</li>
                                    </ul>
<!--                                    <span>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star-half-o"></i>
                                    </span>-->
                                </div>
                                <a href="">
                                    <img src="${blog.thumbnail}" alt="">
                                </a>
                                <p>
                                    ${blog.postContent}
                                </p>
                                <div class="pager-area">
                                    <ul class="pager pull-right">
                                        <li><a href="#">Pre</a></li>
                                        <li><a href="#">Next</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        
<!--                        <div class="rating-area">
                            <ul class="ratings">
                                <li class="rate-this">Rate this item:</li>
                                <li>
                                    <i class="fa fa-star color"></i>
                                    <i class="fa fa-star color"></i>
                                    <i class="fa fa-star color"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                </li>
                                <li class="color">(6 votes)</li>
                            </ul>
                            <ul class="tag">
                                <li>TAG:</li>
                                <li><a class="color" href="">Pink <span>/</span></a></li>
                                <li><a class="color" href="">T-Shirt <span>/</span></a></li>
                                <li><a class="color" href="">Girls</a></li>
                            </ul>
                        </div>/rating-area

                        <div class="socials-share">
                            <a href=""><img src="images/blog/socials.png" alt=""></a>
                        </div>/socials-share

                        <div class="media commnets">
                            <a class="pull-left" href="#">
                                <img class="media-object" src="images/blog/man-one.jpg" alt="">
                            </a>
                            <div class="media-body">
                                <h4 class="media-heading">Annie Davis</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.  Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                                <div class="blog-socials">
                                    <ul>
                                        <li><a href=""><i class="fa fa-facebook"></i></a></li>
                                        <li><a href=""><i class="fa fa-twitter"></i></a></li>
                                        <li><a href=""><i class="fa fa-dribbble"></i></a></li>
                                        <li><a href=""><i class="fa fa-google-plus"></i></a></li>
                                    </ul>
                                    <a class="btn btn-primary" href="">Other Posts</a>
                                </div>
                            </div>
                        </div>Comments
                        <div class="response-area">
                            <h2>3 RESPONSES</h2>
                            <ul class="media-list">
                                <li class="media">

                                    <a class="pull-left" href="#">
                                        <img class="media-object" src="images/blog/man-two.jpg" alt="">
                                    </a>
                                    <div class="media-body">
                                        <ul class="sinlge-post-meta">
                                            <li><i class="fa fa-user"></i>Janis Gallagher</li>
                                            <li><i class="fa fa-clock-o"></i> 1:33 pm</li>
                                            <li><i class="fa fa-calendar"></i> DEC 5, 2013</li>
                                        </ul>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.  Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                                        <a class="btn btn-primary" href=""><i class="fa fa-reply"></i>Replay</a>
                                    </div>
                                </li>
                                <li class="media second-media">
                                    <a class="pull-left" href="#">
                                        <img class="media-object" src="images/blog/man-three.jpg" alt="">
                                    </a>
                                    <div class="media-body">
                                        <ul class="sinlge-post-meta">
                                            <li><i class="fa fa-user"></i>Janis Gallagher</li>
                                            <li><i class="fa fa-clock-o"></i> 1:33 pm</li>
                                            <li><i class="fa fa-calendar"></i> DEC 5, 2013</li>
                                        </ul>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.  Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                                        <a class="btn btn-primary" href=""><i class="fa fa-reply"></i>Replay</a>
                                    </div>
                                </li>
                                <li class="media">
                                    <a class="pull-left" href="#">
                                        <img class="media-object" src="images/blog/man-four.jpg" alt="">
                                    </a>
                                    <div class="media-body">
                                        <ul class="sinlge-post-meta">
                                            <li><i class="fa fa-user"></i>Janis Gallagher</li>
                                            <li><i class="fa fa-clock-o"></i> 1:33 pm</li>
                                            <li><i class="fa fa-calendar"></i> DEC 5, 2013</li>
                                        </ul>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.  Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                                        <a class="btn btn-primary" href=""><i class="fa fa-reply"></i>Replay</a>
                                    </div>
                                </li>
                            </ul>					
                        </div>/Response-area
                        <div class="replay-box">
                            <div class="row">
                                <div class="col-sm-4">
                                    <h2>Leave a replay</h2>
                                    <form>
                                        <div class="blank-arrow">
                                            <label>Your Name</label>
                                        </div>
                                        <span>*</span>
                                        <input type="text" placeholder="write your name...">
                                        <div class="blank-arrow">
                                            <label>Email Address</label>
                                        </div>
                                        <span>*</span>
                                        <input type="email" placeholder="your email address...">
                                        <div class="blank-arrow">
                                            <label>Web Site</label>
                                        </div>
                                        <input type="email" placeholder="current city...">
                                    </form>
                                </div>
                                <div class="col-sm-8">
                                    <div class="text-area">
                                        <div class="blank-arrow">
                                            <label>Your Name</label>
                                        </div>
                                        <span>*</span>
                                        <textarea name="message" rows="11"></textarea>
                                        <a class="btn btn-primary" href="">post comment</a>
                                    </div>
                                </div>
                            </div>
                        </div>/Repaly Box-->
                    </div>
                    <div class="col-sm-4">
                        <div class="blog-post-area">
                            <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordian" href="#sportswear">
                                                <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                                                Post Category
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="sportswear" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ul>
                                                <c:forEach items="${listPostCate}" var="o">
                                                    <li><a href="<%=request.getContextPath()%>/blog/cate?id=${o.category}">${o.category}</a></li>
                                                </c:forEach>                                                       
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                               
                            </div>
<!--                            <h2 class="title text-center">Hot Blog</h2>
                            <div class="single-blog-post">
                                <h3>Girls Pink T Shirt arrived in store</h3>
                                <div class="post-meta">
                                    <ul>
                                        <li><i class="fa fa-user"></i> Mac Doe</li>
                                        <li><i class="fa fa-clock-o"></i> 1:33 pm</li>
                                        <li><i class="fa fa-calendar"></i> DEC 5, 2013</li>
                                    </ul>
                                    <span>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star-half-o"></i>
                                    </span>
                                </div>
                                <a href="">
                                    <img src="images/blog/blog-one.jpg" alt="">
                                </a>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                                <a  class="btn btn-primary" href="">Read More</a>
                            </div>
                            <div class="single-blog-post">
                                <h3>Girls Pink T Shirt arrived in store</h3>
                                <div class="post-meta">
                                    <ul>
                                        <li><i class="fa fa-user"></i> Mac Doe</li>
                                        <li><i class="fa fa-clock-o"></i> 1:33 pm</li>
                                        <li><i class="fa fa-calendar"></i> DEC 5, 2013</li>
                                    </ul>
                                    <span>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star-half-o"></i>
                                    </span>
                                </div>
                                <a href="">
                                    <img src="images/blog/blog-two.jpg" alt="">
                                </a>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                                <a  class="btn btn-primary" href="">Read More</a>
                            </div>
                            <div class="single-blog-post">
                                <h3>Girls Pink T Shirt arrived in store</h3>
                                <div class="post-meta">
                                    <ul>
                                        <li><i class="fa fa-user"></i> Mac Doe</li>
                                        <li><i class="fa fa-clock-o"></i> 1:33 pm</li>
                                        <li><i class="fa fa-calendar"></i> DEC 5, 2013</li>
                                    </ul>
                                    <span>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star-half-o"></i>
                                    </span>
                                </div>
                                <a href="">
                                    <img src="images/blog/blog-three.jpg" alt="">
                                </a>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                                <a  class="btn btn-primary" href="">Read More</a>
                            </div>-->
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