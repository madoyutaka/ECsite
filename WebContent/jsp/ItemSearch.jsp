<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ page import="itemsearch.ItemSearchServlet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.ItemBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
</head>
<body>
	<header>
	       <div class="header_wrap">
		        <button type="button" onclick="location.href='http://localhost:8080/ECSite/TopServlet'">TOP</button>

				<form action="http://localhost:8080/ECSite/ItemSearchServlet" method="POST">
								<input class ="header_word" type="text" name="itemSearchWord"/>
								<input class ="header_search_btn" type="image"  src="${pageContext.request.contextPath}/img/icon/search.png"  name="btnItemSearch" value="検索"/>
								<input type ="hidden" name="btnItemSearchTransition" value="itemSearchTransition">
				</form>

				<nav>
					<ul>
						<li>
							<form action = "http://localhost:8080/ECSite/CartServlet" method = "POST">
								<input class ="header_cart_btn" type = "image" src="${pageContext.request.contextPath}/img/icon/cart.png" name="btnCartTransition">
								<input type ="hidden" name="btnHeaderCartTransition" value="headerCartTransition">
							</form>
						</li>

						<li>
		        			<form action = "http://localhost:8080/ECSite/MyPageServlet" method = "POST">
								<input class ="header_mypage_btn" type = "image" src="${pageContext.request.contextPath}/img/icon/home.png"  name="btnMyPageTransition">
								<input type ="hidden" name="btnMyPageTransition" value="myPageTransition">
							</form>
						</li>
					</ul>
				</nav>
			</div>
	</header>

		<!-- エラーメッセージがある場合は表示し、ない場合は表示しない。 -->
		<c:choose>
			<c:when test="${errorText != null}">
				<c:out value="${errorText}"/>
			</c:when>
			<c:when test="${errorText == null}">
			</c:when>
		</c:choose>
	<br>
	<!-- itemSearchListがnullの場合、0件の場合、1件以上の場合 -->
	<c:choose>
		<c:when test="${itemSearchList == null}">
		</c:when>

		<c:when test="${ fn:length(itemSearchList) >= 1}">
		<h2 class="form_cover">
			<c:forEach items="${itemSearchList}" var="item">
					<!-- 商品画像 -->
					<form name="btnItemDetail" action="http://localhost:8080/ECSite/ItemDetailServlet" method="POST">
					<h3 class="item_select_btn">
						<button class="item" type="submit" name="btnItemDetailTransition" value="${item.itemNo}">
							<img src="./img/${item.itemImage}" >
							<p class="item_name"><c:out value="${item.itemName}" default="取得失敗"/></p>
						<p class="item_price">お値段：<c:out value="${item.itemPrice}" default="取得失敗"/>円</p>
						</button>
					</h3>
					</form>
					<br>
			</c:forEach>
		</h2>

		</c:when>

		<c:when test="${ fn:length(itemSearchList) == 0}">
			お探しの商品はありません。
		</c:when>

	</c:choose>



</body>
</html>