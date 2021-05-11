<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ page import = "bean.ItemBean" %>
<%@ page import = "itemdetail.ItemDetailServlet" %>
<%@ page import = "itemsearch.ItemSearchServlet" %>
<%@ page import = "writereview.WriteReviewServlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細画面</title>
</head>
<body>

<h1>商品詳細</h1>

		<!-- エラーメッセージがある場合は表示し、ない場合は表示しない。 -->
		<c:choose>
			<c:when test="${resultText != null}">
				<c:out value="${resultText}"/>
			</c:when>
			<c:when test="${resultText == null}">
				<br>
			</c:when>
		</c:choose>

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
	<input type = "submit" name="btnFavoriteListAddTransition" value = "お気に入りに追加">
	<br>
	<input type = "submit" name="btnFavoriteListDeleteTransition" value = "お気に入りから削除">
	<br>
</form>
	<br>
	<br>
<form action = "http://localhost:8080/ECSite/WriteReviewServlet" method = "POST">
	<button type="submit" name="btnWriteReviewTransition" value="${itemData.itemNo}">レビュー</button>
</form>

	<!-- loginUserItemBuyLogが0件の場合、1件以上の場合 -->
	<details>
	<summary>レビュー</summary>
		<p>
			<c:choose>
				<c:when test="${ fn:length(reviewList) >= 1}">
					<c:forEach items="${reviewList}" var="review">
							点数：<c:out value="${review.reviewScore}" default="取得失敗"/><br>
							コメント：<c:out value="${review.reviewComment}" default="取得失敗"/><br>
							<br>
					</c:forEach>
				</c:when>

				<c:when test="${ fn:length(loginUserItemBuyLog) == 0}">
					購入履歴はありません。
				</c:when>

			</c:choose>
		</p>
	</details>

</body>

</html>