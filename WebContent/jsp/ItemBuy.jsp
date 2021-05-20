<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import = "bean.ItemBean" %>
<%@ page import = "itembuy.ItemBuyServlet" %>
<%@ page import="java.util.ArrayList.*" %>
<%@ page import="bean.UserBean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入確認画面</title>
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
	<div class="page_layout itembuy_layout">
		<h1 class="page_ttl">購入確認</h1>

		<div class="cart_container">

			<!-- カート内 -->
					<c:choose>
					<c:when test="${ fn:length(cartItemData) >= 1}">
						<c:forEach items="${cartItemData}" var="itemData" varStatus="status">
						<div class="item_buy_wrap">
							 <img class="item_image item_buy_image" src="img/${itemData.itemImage}">
							 <div class="item_text_wrap">
							 	<p class="item_buy_list">商品名：<c:out value="${itemData.itemName}"></c:out></p>
								<p class="item_buy_list">購入数：<c:out value="${cartData[status.index].itemBuyCount}"></c:out>個</p>
								<p class="item_buy_list">商品１個の金額：<c:out value="${itemData.itemPrice}"></c:out>円</p>
							 </div>
						</div>


						</c:forEach>
					</c:when>
				</c:choose>


			<!-- カート一覧 -->
            <h2 class="page_ttl">お届け先</h2>

			<!--　注文者情報入力 -->
			<form class="item_buy_check" action = "http://localhost:8080/ECSite/CartServlet" method = "POST">
				<div class="item_check_wrap">
					<p class="item_buy_list">氏名：</p>
					<input type = "text" name="btnTransition" value ="<c:out value="${loginUserData.userName}" default="取得失敗"/>">
				</div>


			<div class="item_check_wrap">
				<p class="item_buy_list">メールアドレス：</p>
				<input type = "email" name="btnTransition" value ="<c:out value="${loginUserData.emailAddress}" default="取得失敗"/>">
			</div>

			 <div class="item_check_wrap">
				<p class="item_buy_list">郵便番号：</p>
				<input type = "text" name="btnTransition" value ="<c:out value="${loginUserData.postalCode}" default="取得失敗"/>">
			</div>

			<div class="item_check_wrap">
				<p class="item_buy_list">住所：</p>
				<input type = "text" name="btnTransition" value ="<c:out value="${loginUserData.address}" default="取得失敗"/>">
			</div>
			</form>
		</div>

		<!--　購入ボタン -->
		<form class="item_buy_form"action = "http://localhost:8080/ECSite/ItemBuyCompletionServlet" method = "POST">
			<button class="btn" type = "submit" name="btnItemBuyCompletionTransition" value ="${itemData.itemNo}">購入する</button>
		</form>
	</div>
</body>
</html>