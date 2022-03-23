<%-- 
    Document   : PostDetail
    Created on : Feb 10, 2022, 11:44:17 PM
    Author     : CHANHSIRO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Blog Single | E-Shopper</title>
    <link href="${pageContext.request.contextPath}/eShopper/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/eShopper/css/font-awesome.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/eShopper/css/prettyPhoto.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/eShopper/css/price-range.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/eShopper/css/animate.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/eShopper/css/main.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/eShopper/css/responsive.css" rel="stylesheet" />
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="images/ico/favicon.ico" />
    <link
      rel="apple-touch-icon-precomposed"
      sizes="144x144"
      href="${pageContext.request.contextPath}/images/ico/apple-touch-icon-144-precomposed.png"
    />
    <link
      rel="apple-touch-icon-precomposed"
      sizes="114x114"
      href="${pageContext.request.contextPath}/images/ico/apple-touch-icon-114-precomposed.png"
    />
    <link
      rel="apple-touch-icon-precomposed"
      sizes="72x72"
      href="${pageContext.request.contextPath}/images/ico/apple-touch-icon-72-precomposed.png"
    />
    <link
      rel="apple-touch-icon-precomposed"
      href="${pageContext.request.contextPath}/images/ico/apple-touch-icon-57-precomposed.png"
    />
    
    <script>
        function send() {
            var title = document.getElementById("tt").value;
            var image = document.getElementById("img").value;
            var textArea = document.getElementById("area").value;
            var breif = document.getElementById("brief").value;
            if(title == ""){
                alert("please write the title");
                document.getElementById("tt").style.borderColor = "red";
                return false;
            }
            if(image == ""){
                alert("please choose the image");
                document.getElementById("img").style.borderColor = "red";
                return false;
            }
            if(breif == ""){ 
                alert("please write the brief information");
                document.getElementById("brief").style.borderColor = "red";
                return false;
            }
            if(textArea == ""){ 
                alert("please write the content");
                document.getElementById("area").style.borderColor = "red";
                return false;
            } 
        }
    </script>
  </head>
    <body>
    <jsp:include page="../header.jsp"/>
    <!--/header-->

    <section>
      <div class="container">
        <div class="row">
          <div class="col-sm-2">
            
          </div>
          <div class="col-sm-8">
            <div class="blog-post-area">
              <h2 class="title text-center">POST SINGLE</h2>
              <div class="single-blog-post">
                <form onsubmit="return send()" style="border: 0.5px solid #FE980F; border-radius:4px" action="<%=request.getContextPath()%>/post/edit" method="POST" enctype="multipart/form-data">
                  <table style="
    				margin-left: auto;
					margin-right: auto;
				  ">
                  <tr><td><br></td></tr>
					<tr>
						<td>Title:</td>
						<td>
						  <input
							type="text"
                                                        id="tt"
							name="title"
                                                        value="${requestScope.postUpdate.postTitle}"
							placeholder="Title of the product"
						  />
						</td>
                                                <td>
						  <input
                                                      type="hidden"
							name="postId"
                                                        value="${requestScope.postUpdate.postId}"
							placeholder="id"
						  />
						</td>
					  </tr>
					  <tr>
						  <td><br></td>
					  </tr>
					  <tr>
						<td>Select Image:</td>
						<td>
                                                    <div class="form-group">
                                                    <label for="avatar" class="col-lg-2 col-sm-2 control-label">Image</label>
                                                    <div class="col-lg-offset-5 col-lg-15">
                                                        <div class="profile-pic">
                                                    <label class="-label" for="file">
                                                                <c:if test="${ not empty postUpdate.thumbnail}">
                                                                <img src="data:image/jpg;base64,${postUpdate.thumbnail}" id="output" width="200" />
                                                                </c:if>
                                                                <c:if test="${empty postUpdate.thumbnail}">
                                                                   
                                                                    <img src="${pageContext.request.contextPath}/admin/img/no-avatar.png" id="output" width="200" />
                                                                </c:if>
                                                            </label>
                                                            <input type="file" id="img" onchange="loadFile(event)" value="${postUpdate.thumbnail}" name="image" />
                                                    <c:if test="${not empty errorImage}">
                                                                <p class="help-block" style="color: red" id="errorEmailMessage">${errorImage}</p>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </div>
						</td>
					  </tr>
					  <tr>
						  <td><br></td>
					  </tr>
                                          
                                          <tr>
						  <td><br></td>
					  </tr>
                                          
                                          <tr>
                                              <td>Category:</td>
                                              <td>
                                                    <select class="select" aria-label="Default select example" name="category">
                                                        
                                                            <c:forEach items="${listPostCate}" var="l">
                                                            <option value="${l.settingId}">${l.settingValue}</option>
                                                            </c:forEach>
                                                    </select>
                                              </td>
					  </tr>
                                          <tr>
						  <td><br></td>
					  </tr>
                                          
                                          
                                          <tr>
						<td>Brief information:</td>
                                                <td>
						  <textarea style="border-radius: 4px;"
							name="brief"
							cols="60"
							rows="3"
                                                        id="brief"
							placeholder="brief information of the product"
						  >${requestScope.postUpdate.breifInformation}</textarea>
						</td>
					  </tr>
                                          <tr>
						  <td><br></td>
					  </tr>
					  <tr>
						<td>Content:</td>
						<td>
						  <textarea style="border-radius: 4px;"
							name="content"
							cols="60"
							rows="5"
                                                        id="area"
							placeholder="Content of the product"
						  >${requestScope.postUpdate.postContent}</textarea>
						</td>
					  </tr>
					  <tr>
						  <td><br></td>
					  </tr>
					  <tr>
						<td colspan="2">
						  <input
							type="submit"
                                                        value="POST"
							class="btn-secondary"
						  />
						</td>
					  </tr>
					  <tr><td><br></td></tr>
                  </table>
                </form>
              </div>
            </div>
            <!--/Repaly Box-->
          </div>
        </div>
      </div>
    </section>

    <footer id="footer">
      <!--Footer-->
      <div class="footer-top">
        <div class="container">
          <div class="row">
            <div class="col-sm-2">
              <div class="companyinfo">
                <h2><span>e</span>-shopper</h2>
                <p>
                  Lorem ipsum dolor sit amet, consectetur adipisicing elit,sed
                  do eiusmod tempor
                </p>
              </div>
            </div>
            <div class="col-sm-7">
              <div class="col-sm-3">
                <div class="video-gallery text-center">
                  <a href="#">
                    <div class="iframe-img">
                      <img src="${pageContext.request.contextPath}/eShopper/images/home/iframe1.png" alt="" />
                    </div>
                    <div class="overlay-icon">
                      <i class="fa fa-play-circle-o"></i>
                    </div>
                  </a>
                  <p>Circle of Hands</p>
                  <h2>24 DEC 2014</h2>
                </div>
              </div>

              <div class="col-sm-3">
                <div class="video-gallery text-center">
                  <a href="#">
                    <div class="iframe-img">
                      <img src="${pageContext.request.contextPath}/eShopper/images/home/iframe2.png" alt="" />
                    </div>
                    <div class="overlay-icon">
                      <i class="fa fa-play-circle-o"></i>
                    </div>
                  </a>
                  <p>Circle of Hands</p>
                  <h2>24 DEC 2014</h2>
                </div>
              </div>

              <div class="col-sm-3">
                <div class="video-gallery text-center">
                  <a href="#">
                    <div class="iframe-img">
                      <img src="${pageContext.request.contextPath}/eShopper/images/home/iframe3.png" alt="" />
                    </div>
                    <div class="overlay-icon">
                      <i class="fa fa-play-circle-o"></i>
                    </div>
                  </a>
                  <p>Circle of Hands</p>
                  <h2>24 DEC 2014</h2>
                </div>
              </div>

              <div class="col-sm-3">
                <div class="video-gallery text-center">
                  <a href="#">
                    <div class="iframe-img">
                      <img src="${pageContext.request.contextPath}/eShopper/images/home/iframe4.png" alt="" />
                    </div>
                    <div class="overlay-icon">
                      <i class="fa fa-play-circle-o"></i>
                    </div>
                  </a>
                  <p>Circle of Hands</p>
                  <h2>24 DEC 2014</h2>
                </div>
              </div>
            </div>
            <div class="col-sm-3">
              <div class="address">
                <img src="${pageContext.request.contextPath}/eShopper/images/home/map.png" alt="" />
                <p>505 S Atlantic Ave Virginia Beach, VA(Virginia)</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="footer-widget">
        <div class="container">
          <div class="row">
            <div class="col-sm-2">
              <div class="single-widget">
                <h2>Service</h2>
                <ul class="nav nav-pills nav-stacked">
                  <li><a href="">Online Help</a></li>
                  <li><a href="">Contact Us</a></li>
                  <li><a href="">Order Status</a></li>
                  <li><a href="">Change Location</a></li>
                  <li><a href="">FAQ’s</a></li>
                </ul>
              </div>
            </div>
            <div class="col-sm-2">
              <div class="single-widget">
                <h2>Quock Shop</h2>
                <ul class="nav nav-pills nav-stacked">
                  <li><a href="">T-Shirt</a></li>
                  <li><a href="">Mens</a></li>
                  <li><a href="">Womens</a></li>
                  <li><a href="">Gift Cards</a></li>
                  <li><a href="">Shoes</a></li>
                </ul>
              </div>
            </div>
            <div class="col-sm-2">
              <div class="single-widget">
                <h2>Policies</h2>
                <ul class="nav nav-pills nav-stacked">
                  <li><a href="">Terms of Use</a></li>
                  <li><a href="">Privecy Policy</a></li>
                  <li><a href="">Refund Policy</a></li>
                  <li><a href="">Billing System</a></li>
                  <li><a href="">Ticket System</a></li>
                </ul>
              </div>
            </div>
            <div class="col-sm-2">
              <div class="single-widget">
                <h2>About Shopper</h2>
                <ul class="nav nav-pills nav-stacked">
                  <li><a href="">Company Information</a></li>
                  <li><a href="">Careers</a></li>
                  <li><a href="">Store Location</a></li>
                  <li><a href="">Affillate Program</a></li>
                  <li><a href="">Copyright</a></li>
                </ul>
              </div>
            </div>
            <div class="col-sm-3 col-sm-offset-1">
              <div class="single-widget">
                <h2>About Shopper</h2>
                <form action="#" class="searchform">
                  <input type="text" placeholder="Your email address" />
                  <button type="submit" class="btn btn-default">
                    <i class="fa fa-arrow-circle-o-right"></i>
                  </button>
                  <p>
                    Get the most recent updates from <br />our site and be
                    updated your self...
                  </p>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="footer-bottom">
        <div class="container">
          <div class="row">
            <p class="pull-left">
              Copyright © 2013 E-SHOPPER Inc. All rights reserved.
            </p>
            <p class="pull-right">
              Designed by
              <span
                ><a target="_blank" href="http://www.themeum.com"
                  >Themeum</a
                ></span
              >
            </p>
          </div>
        </div>
      </div>
    </footer>
    <!--/Footer-->

    <script src="${pageContext.request.contextPath}/eShopper/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/eShopper/js/price-range.js"></script>
    <script src="${pageContext.request.contextPath}/eShopper/js/jquery.scrollUp.min.js"></script>
    <script src="${pageContext.request.contextPath}/eShopper/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/eShopper/js/jquery.prettyPhoto.js"></script>
    <script src="${pageContext.request.contextPath}/eShopper/js/main.js"></script>
    <script src="${pageContext.request.contextPath}/admin/js/AddNewUser.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/admin/js/UserDetail.js" type="text/javascript"></script>
  </body>
</html>
