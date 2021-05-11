<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "bean.ItemBean" %>
<%@ page import = "itemsearch.ItemSearchServlet" %>
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
<%  ArrayList<ItemBean> cartList = (ArrayList<ItemBean>) request.getAttribute("itemCartData"); %>
	<!-- カート一覧 -->
	<form action = "http://localhost:8080/ECSite/CartServlet" method = "POST">
			<!-- カート内の商品こちら -->
		<%for(ItemBean list: cartList) {%>
				<%= list.getItemName() %>
		 		<%= list.getItemPrice() %>
			<button type="submit" name="btnCartRemoveTransition"  value="${itemData.itemNo}">削除</button>
		<%} %>
	</form>

	<!--  商品詳細画面に戻る-->
	<form action = "http://localhost:8080/ECSite/ItemDetailServlet" method = "POST">
		<button type="submit" name="btnItemDetailTransition"  value="${itemData.itemNo}">商品詳細に戻る</button>
	</form>

	<!-- 購入画面に進む -->
	<form action = "http://localhost:8080/ECSite/CartServlet" method = "POST">
			<input type = "submit" name="btnTransition" value ="購入確認に進む">
	</form>
</body>
</html>