<%-- 
    Document   : siderpost-product
    Created on : 16-Mar-2022, 12:51:54
    Author     : VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <div class="row">

        <div class="search_box pull-right">
            <form action="<%=request.getContextPath()%>/blog/search" method="post">
                <input value="${searchValue}" name="search" type="text" placeholder="Search Post Title"/>
            </form>
        </div>
    </div><hr>

    <div class="blog-post-area">
        <div class="brands_products"><!--brands_products-->
            <h2>Post Category</h2>
            <div class="brands-name">
                <ul class="nav nav-pills nav-stacked" style="text-align: center">
                    <c:forEach items="${PostCategories}" var="pct">
                        <li><a href="<%=request.getContextPath()%>/blog/cate?id=${pct.settingId}"><b>${pct.settingValue}</b></a></li>
                                </c:forEach>
                </ul>
            </div>
            <hr>    
            <h2>Featured Products</h2>
            <div class="brands-name">
                <ul style="text-align: center;padding-right: 10%;">
                    <c:forEach items="${FeaturedProducts}" var="fps">
                        <li><a href="<%=request.getContextPath()%>/goods/detail?pid=${fps.getPid()}" target="_blank">
                                <img src="data:image/jpg;base64,${fps.getThumbnail()}" alt="Product Image" style="width:210px;height: 220px;" class="features_items_img">
                            </a>
                            <p>${fps.getTitle()}</p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</html>
