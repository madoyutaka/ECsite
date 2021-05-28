<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ page import="servlet.FavoriteListServlet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.FavoriteBean" %>

<html>
	<head>
		<meta charset="UTF-8">
		<title>お気に入りリスト</title>
		<link rel="stylesheet"href="${pageContext.request.contextPath}/css/reset.css">
        <link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
	</head>
	<body>

	<%@include file= "Header.jsp" %>

	<div class="page_layout favoritelist_layout">
		<h1 class="page_ttl">お気に入り一覧</h1>

    <div class="favoritelist_table">
			<c:choose>
					<c:when test="${ fn:length(loginUserFaves) >= 1}">
						<c:forEach items="${loginUserFaves}" var = "a">
							<table>
								<tr>
									<td>
										<img class="item_image" src="${pageContext.request.contextPath}/img/furniture/${a.itemImage}">
									</td>

									<td class="favoritelist_td">
										<form name="btnItemDetail" action="http://localhost:8080/ECSite/ItemDetailServlet" method="POST">
											<button class="btn" type="submit" name="btnItemDetailTransition" value="${a.itemNo}">${a.itemName}</button>
											<input type ="hidden" name="selectReviewPageNo" value="1">
										</form>

										<form name="btnItemDetail" action="http://localhost:8080/ECSite/FavoriteListCompletionServlet" method="POST">
											<button class="btn delete_btn" type="submit" name="btnFavoriteListDeleteTransition" value="${a.itemNo}">×</button>
										</form>
								    </td>
								</tr>
							</table>
						</c:forEach>
					</c:when>

					<c:when test="${ fn:length(loginUserFaves) == 0}">
						<c:choose>
							<c:when test="${errorText==null}">
								<p class="error_text error_login">お気に入りリストに商品がありません。</p>
							</c:when>
							<c:when test="${errorText!=null}">
								<p class="error_text error_login">${errorText}</p>
							</c:when>
						</c:choose>
					</c:when>
			</c:choose>
	</div>

	<c:choose>
		<c:when test="${ fn:length(loginUserFaves) >= 1}">
			<form name="btnFavoritePagination" action="http://localhost:8080/ECSite/FavoriteListServlet" method="POST">
				<div class="pagination">
					<c:forEach begin="1" end="${favoriteListTotalPageNo}" step="1" var = "a">
						<c:choose>
							<c:when test="${favoriteListPageNo == a}">
								<button class="btn page_btn select_btn" type="submit" name="selectFavoriteListPageNo" value="${a}">${a}</button>
							</c:when>
							<c:when test="${favoriteListPageNo != a}">
								<button class="btn page_btn" type="submit" name="selectFavoriteListPageNo" value="${a}">${a}</button>
							</c:when>
						</c:choose>
					</c:forEach>
				</div>
			</form>
		</c:when>
	</c:choose>


		<form class="favoritelist_form" action = "http://localhost:8080/ECSite/MyPageServlet" method = "POST">
		  <input class="btn mypage_back_btn" type = "submit" name="btnMyPageTransition" value = "マイページへ">
		</form>
	</div>

	<%@include file= "Footer.jsp" %>

	</body>
</html>