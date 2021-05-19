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

	<div class="page_layout itemdetail_layout">
		<h1 class="page_ttl">商品詳細</h1>

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

		<div class="item_detail_container">
			<img class="item_image" src="./img/${itemData.itemImage}">


			<p class="item_detail_text">${itemData.itemName} ${itemData.itemPrice}円</p>

			<form action = "http://localhost:8080/ECSite/CartServlet" method = "POST">
				<a class="item_quantity">個数</a>
				<c:if test = "${itemData.itemStock > 0}">
					<select name="itemNum">
								<c:forEach begin = "1" end = "${itemData.itemStock}" step ="1" var ="i">
								 　<option><c:out value="${i}"/></option>
					            </c:forEach>
					</select>
				</c:if>

				<c:if test="${itemData.itemStock == 0}">
						<select name="itemNum" disabled>
						<option><c:out value="0"/></option>
						</select>
				</c:if>

			<!-- 在庫がない場合は、カートに入れられないようにする。 -->
				<c:choose>
					<c:when test="${itemData.itemStock > 0}">
						<button class="btn cart_in_btn" type="submit" name="btnCartTransition" value="${itemData.itemNo}">カートに入れる</button>
					</c:when>

					<c:when test="${itemData.itemStock == 0}">
						<button type="submit" disabled name="btnCartTransition" value="${itemData.itemNo}">在庫なし</button>
					</c:when>
				</c:choose>
			</form>


			<form action = "http://localhost:8080/ECSite/FavoriteListCompletionServlet" method = "POST">
				<button class="btn favorite_add_btn" type="submit" name="btnFavoriteListAddTransition" value="${itemData.itemNo}">お気に入りに追加</button>
				<button class="btn  favorite_del_btn" type="submit" name="btnFavoriteListDeleteTransition" value="${itemData.itemNo}">お気に入りから削除</button>
			</form>

			<form action = "http://localhost:8080/ECSite/WriteReviewServlet" method = "POST">
				<button class="btn review_btn" type="submit" name="btnWriteReviewTransition" value="${itemData.itemNo}">レビュー</button>
			</form>

			<!-- loginUserItemBuyLogが0件の場合、1件以上の場合 -->
			<details>
			<summary class="review_click">レビューを見る</summary>
				<p>
					<c:choose>
						<c:when test="${ fn:length(reviewList) >= 1}">
							<c:forEach items="${reviewList}" var="review">
									<p class="review_point">点数：<c:out value="${review.reviewScore}" default="取得失敗"/></p>
									<p class="review_comment">コメント：<c:out value="${review.reviewComment}" default="取得失敗"/></p>
							</c:forEach>
						</c:when>

						<c:when test="${ fn:length(loginUserItemBuyLog) == 0}">
							<p class="review_text">レビューはありません。</p>
						</c:when>

					</c:choose>
				</p>
			</details>
		</div>

	</div>


</body>

</html>
