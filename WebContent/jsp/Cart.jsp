<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
	<h1>ショッピングカート</h1>
	<!-- カート一覧 -->
			<!-- カート内の商品こちら -->
		<c:choose>
			<c:when test="${ fn:length(cartItemData) >= 1}">
				<c:forEach items="${cartItemData}" var="itemData" varStatus="status">
					<form action = "http://localhost:8080/ECSite/ItemDetailServlet" method = "POST">
						<button type="submit" name="btnItemDetailTransition" value="${itemData.itemNo}">${itemData.itemName}</button>
					</form>
						<c:out value="購入数：${cartData[status.index].itemBuyCount}"></c:out>
						<c:out value="商品１個の金額：${itemData.itemPrice}"></c:out>
					<form action = "http://localhost:8080/ECSite/CartServlet" method = "POST">
						<button type="submit" name="btnCartRemoveTransition"  value="${itemData.itemNo}">削除</button>
					</form>
					<br>
					<br>
				</c:forEach>
			</c:when>
		</c:choose>


	<!-- 購入画面に進む -->
	<form action = "http://localhost:8080/ECSite/CartServlet" method = "POST">
			<input type = "submit" name="btnTransition" value ="購入確認に進む">
	</form>
</body>
</html>