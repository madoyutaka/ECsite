<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ page import = "bean.ItemBean" %>
<%@ page import = "itemdetail.ItemDetailServlet" %>
<%@ page import = "itemsearch.ItemSearchServlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細画面</title>
</head>
<body>

<h1>商品詳細</h1>
	<% ItemBean itemData = (ItemBean)request.getAttribute("itemData"); %>

	<img src="./img/${itemData.itemImage}">

	<!-- ゲッターメソッドで変数の値を取得 -->
	${itemData.itemNo}
	${itemData.itemName}
	${itemData.itemPrice}
	${itemData.categoryName}



<br>


<!-- ブラウザ上にマイページ用の画面を表示 -->
<a href="http://localhost:8080/ECSite/TopServlet">TOP</a>


<br>

<form action = "http://localhost:8080/ECSite/CartServlet" method = "POST">
	<input type = "submit" name="btnCartInTransition" value = "カートに入れる">
	<br>
</form>
<form action = "http://localhost:8080/ECSite/FavoriteListCompletionServlet" method = "POST">
	<button type="submit" name=btnFavoriteListAddTransition value="${itemData.itemNo}">お気に入りに追加</button>
	<br>
	<button type="submit" name=btnFavoriteListDeleteTransition value="${itemData.itemNo}">お気に入りから削除</button>
	<br>
</form>
	<br>
	<br>
<form action = "http://localhost:8080/ECSite/WriteReviewServlet" method = "POST">
	<input type = "submit" name="btnWriteReviewTransition" value = "レビュー">
</form>

<p>レビューのダミーテキストレビューのダミーテキストレビューのダミーテキスト<br>
レビューのダミーテキストレビューのダミーテキストレビューのダミーテキストレビューのダミーテキスト<br>
レビューのダミーテキストレビューのダミーテキストレビューのダミーテキストレビューのダミーテキスト</p>


</body>

</html>
