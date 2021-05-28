<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import = "bean.ItemBean" %>
<%@ page import = "servlet.ItemBuyServlet" %>
<%@ page import="java.util.ArrayList.*" %>
<%@ page import="bean.UserBean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" >
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
<title>購入確認画面</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
</head>
<body>
<%@include file= "Header.jsp" %>

	<div class="page_layout itembuy_layout">
		<h1 class="page_ttl">購入確認</h1>

		<div class="cart_container">

			<!-- カート内 -->
					<c:choose>
					<c:when test="${ fn:length(cartItemData) >= 1}">
						<c:forEach items="${cartItemData}" var="itemData" varStatus="status">
						<div class="item_buy_wrap">
							 <img class="item_image item_buy_image" src="img/furniture/${itemData.itemImage}">
							 <div class="item_text_wrap">
							 	<p class="item_buy_list">商品名：<c:out value="${itemData.itemName}"></c:out></p>
								<p class="item_buy_list">購入数：<c:out value="${cartData[status.index].itemBuyCount}"></c:out>個</p>
								<p class="item_buy_list">購入金額：<c:out value="${itemTotalPriceHmap[itemData.itemNo]}"></c:out>円</p>

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

			<div class="item_pay_wrap">
				<p class="item_pay_list">お支払方法</p>

				<div class="item_pay_how">
					<p class="item_pay_btn"><input type="radio" name="hyouka" value="credit" checked="checked">クレジットカード決済</p>

					<p class="item_pay_btn"><input type="radio" name="hyouka" value="convenience">コンビニ決済</p>

					<p class="item_pay_btn"><input type="radio" name="hyouka" value="bank">銀行振込</p>

					<p class="item_pay_btn"><input type="radio" name="hyouka" value="bank">現金引換</p>
				</div>
			</div>
		</div>

		<!--　購入ボタン -->
		<form class="item_buy_form"action = "http://localhost:8080/ECSite/ItemBuyCompletionServlet" method = "POST">
			<button class="btn" type = "submit" name="btnItemBuyCompletionTransition" value ="${itemData.itemNo}">購入する</button>
		</form>
	</div>
</body>
</html>