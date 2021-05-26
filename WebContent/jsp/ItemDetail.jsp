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
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
<title>商品詳細画面</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
</head>
<body>
	<%@include file= "Header.jsp" %>


	<div class="page_layout itemdetail_layout">
		<h1 class="page_ttl">商品詳細</h1>

			<!-- エラーメッセージがある場合は表示し、ない場合は表示しない。 -->
			<c:choose>
				<c:when test="${resultText != null}">
					<h1 class="error_text review_com_text"><c:out value="${resultText}"/></h1>
				</c:when>
				<c:when test="${resultText == null}">
					<br>
				</c:when>
			</c:choose>

		<% ItemBean itemData = (ItemBean)request.getAttribute("itemData"); %>

		<div class="item_detail_container">
			<img class="item_image" src="./img/furniture/${itemData.itemImage}">


			<p class="item_detail_text">${itemData.itemName} ${itemData.itemPrice}円</p>
			<p class="item_detail_description">${itemData.itemDescription}</p>
			<p class="review_ave">レビュー平均：<c:out value="${reviewAverage}"/></p>

			<form class="cart_form" action = "http://localhost:8080/ECSite/CartServlet" method = "POST">
				<p class="item_quantity">個数</p>
				<c:if test = "${itemData.itemStock > 0}">
					<select class="itemNum" name="itemNum">
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

			<form class="review_form" action = "http://localhost:8080/ECSite/WriteReviewServlet" method = "POST">
				<button class="btn review_btn" type="submit" name="btnWriteReviewTransition" value="${itemData.itemNo}">レビュー</button>
			</form>

			<!-- loginUserItemBuyLogが0件の場合、1件以上の場合 -->
			<details open>
			<summary class="review_click">レビューを見る</summary>
				<p>
					<c:choose>
						<c:when test="${ fn:length(reviewList) >= 1}">
							<c:forEach items="${reviewList}" var="review">
									<p class="review_point">点数：<c:out value="${review.reviewScore}" default="取得失敗"/></p>
									<p class="review_comment">コメント：<c:out value="${review.reviewComment}" default="取得失敗"/></p>
									<br>
							</c:forEach>
						</c:when>

						<c:when test="${ fn:length(loginUserItemBuyLog) == 0}">
							<p class="review_text">レビューはありません。</p>
						</c:when>
					</c:choose>
				</p>

					<c:choose>
						<c:when test="${ fn:length(reviewList) >= 1}">
							<form name="btnReviewPagination" action="http://localhost:8080/ECSite/ItemDetailServlet" method="POST">
								<div class="pagination">
									<c:forEach begin="1" end="${totalPageNo}" step="1" var = "a">
										<c:choose>
											<c:when test="${selectReviewPageNo == a}">
												<button class="btn page_btn select_btn" type="submit" name="selectReviewPageNo" value="${a}">${a}</button>
											</c:when>
											<c:when test="${selectReviewPageNo != a}">
												<button class="btn page_btn " type="submit" name="selectReviewPageNo" value="${a}">${a}</button>
											</c:when>
										</c:choose>
									</c:forEach>
								</div>
								<input type ="hidden" name="btnItemDetailTransition" value="${itemData.itemNo}">
							</form>
						</c:when>
					</c:choose>

			</details>
		</div>

	</div>



</body>

</html>