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

	<h1>購入確認画面です。</h1>

	<!-- カート内 -->
			<c:choose>
			<c:when test="${ fn:length(cartItemData) >= 1}">
				<c:forEach items="${cartItemData}" var="itemData" varStatus="status">
					<c:out value="商品名：${itemData.itemName}"></c:out>
					<c:out value="購入数：${cartData[status.index].itemBuyCount}"></c:out>
					<c:out value="商品１個の金額：${itemData.itemPrice}"></c:out>
					<br>
					<br>
				</c:forEach>
			</c:when>
		</c:choose>


	<!-- カート一覧 -->


	<!--　注文者情報入力 -->
	<form action = "http://localhost:8080/ECSite/CartServlet" method = "POST">
		氏名：<input type = "text" name="btnTransition" value ="<c:out value="${loginUserData.userName}" default="取得失敗"/>">

		メールアドレス：<input type = "email" name="btnTransition" value ="<c:out value="${loginUserData.emailAddress}" default="取得失敗"/>">

		郵便番号：<input type = "text" name="btnTransition" value ="<c:out value="${loginUserData.postalCode}" default="取得失敗"/>">

		住所：<input type = "text" name="btnTransition" value ="<c:out value="${loginUserData.address}" default="取得失敗"/>">
	</form>

	<!--　購入ボタン -->
	<form action = "http://localhost:8080/ECSite/ItemBuyCompletionServlet" method = "POST">
		<button type = "submit" name="btnItemBuyCompletionTransition" value ="${itemData.itemNo}">購入する</button>
	</form>
</body>
</html>