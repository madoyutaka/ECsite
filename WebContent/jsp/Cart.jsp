<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "bean.ItemBean" %>
<%@ page import = "itemsearch.ItemSearchServlet" %>
<%@ page import = "cart.CartServlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート画面</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
</head>
<body>
	<header>
	       <div class="header_wrap">
		       <form action="http://localhost:8080/ECSite/TopServlet" method="POST">
									<input class ="header_top_btn" type="image"  src="${pageContext.request.contextPath}/img/icon/logo.png"  name="btnItemSearch"/>
									<input type ="hidden" name="btnTopTransition" value="topTransition">
				</form>

				<form action="http://localhost:8080/ECSite/ItemSearchServlet" method="POST">
								<input class ="header_word" type="text" name="itemSearchWord"/>
								<input class ="header_search_btn" type="image"  src="${pageContext.request.contextPath}/img/icon/search.png"  name="btnItemSearch"/>
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


	<div class="page_layout cart_layout">
		<h1 class="page_ttl">ショッピングカート</h1>
		<div class="cart_container">
			<!-- カート一覧 -->
			<!-- カート内の商品こちら -->
			<c:choose>
				<c:when test="${ fn:length(cartItemData) >= 1}">
					<c:forEach items="${cartItemData}" var="itemData" varStatus="status">
						<form class="cart_form" action = "http://localhost:8080/ECSite/ItemDetailServlet" method = "POST">
							<img class="item_image cart_img" src="img/furniture/${itemData.itemImage}">
							<button class="btn cart_item_btn" type="submit" name="btnItemDetailTransition" value="${itemData.itemNo}">${itemData.itemName}</button>
						</form>

						<div class="cart_text">
							<p class="cart_item">購入数：<c:out value="${cartData[status.index].itemBuyCount}"></c:out>個<p>
							<p  class="cart_item">商品１個の金額：<c:out value="${itemData.itemPrice}"></c:out>円<p>
						</div>

						<form action = "http://localhost:8080/ECSite/CartServlet" method = "POST">
							<button class="btn cart_delete" type="submit" name="btnCartRemoveTransition"  value="${itemData.itemNo}">削除</button>
						</form>
						<br>
						<br>
					</c:forEach>
				</c:when>
			</c:choose>



			<!-- 購入画面に進む -->
			<form action = "http://localhost:8080/ECSite/ItemBuyServlet" method = "POST">
				<c:choose>
					<c:when test="${ fn:length(cartItemData) >= 1}">
						<input class="btn" type = "submit" name="btnItemBuyTransition" value ="購入確認に進む">
					</c:when>

					<c:when test="${ fn:length(cartItemData) == 0}">
						<input  type = "submit" disabled name="btnItemBuyTransition" value ="カートに商品が入っていません">
					</c:when>
				</c:choose>
			</form>

		</div>

	</div>

	<div id="page_top"><a href="#"></a></div>


</body>
</html>