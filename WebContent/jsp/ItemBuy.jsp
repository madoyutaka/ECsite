<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "bean.ItemBean" %>
<%@ page import = "itembuy.ItemBuyServlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入確認画面</title>
</head>
<body>
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
		氏名：<input type = "text" name="btnTransition" value ="name">

		メールアドレス：<input type = "email" name="btnTransition" value ="mailadress">

		郵便番号：<input type = "text" name="btnTransition" value ="num">

		住所：<input type = "text" name="btnTransition" value ="name">
	</form>

	<!--　購入ボタン -->
	<form action = "http://localhost:8080/ECSite/ItemBuyCompletionServlet" method = "POST">
			<input type = "submit" name="btnItemBuyCompletionTransition" value ="購入する">
	</form>
</body>
</html>