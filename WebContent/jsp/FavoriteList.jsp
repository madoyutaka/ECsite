<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ page import="favoritelist.FavoriteListServlet" %>
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

	<div class="page_layout favoritelist_layout">
		<h1 class="page_ttl">お気に入り一覧</h1>

    <div class="favoritelist_table">
			<c:choose>
					<c:when test="${ fn:length(loginUserFaves) >= 1}">
						<c:forEach items="${loginUserFaves}" var = "a">
							<table>
								<tr>
									<td>
										<img class="item_image" src="${pageContext.request.contextPath}/img/${a.itemImage}">
									</td>

									<td class="favoritelist_td">
										<form name="btnItemDetail" action="http://localhost:8080/ECSite/ItemDetailServlet" method="POST">
											<button class="btn" type="submit" name="btnItemDetailTransition" value="${a.itemNo}">${a.itemName}</button>
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
						<p class="error_text error_login">お気に入りリストに商品がありません。</p>
					</c:when>
			</c:choose>
	</div>

	<c:choose>
		<c:when test="${ fn:length(loginUserFaves) >= 1}">
			<form name="btnFavoritePagination" action="http://localhost:8080/ECSite/FavoriteListServlet" method="POST">
				<ul class="favorite_pagination">
					<c:forEach begin="1" end="${favoriteListTotalPageNo}" step="1" var = "a">
						<li><button class="btn" type="submit" name="selectFavoriteListPageNo" value="${a}">${a}</button></li>
					</c:forEach>
				</ul>
			</form>
		</c:when>
	</c:choose>


		<form class="favoritelist_form" action = "http://localhost:8080/ECSite/MyPageServlet" method = "POST">
		  <input class="btn mypage_back_btn" type = "submit" name="btnMyPageTransition" value = "マイページへ">
		</form>
	</div>

	<div class="footer_wrapper">
	    <footer>
			<p class="footer_text">2021/05/14/ECSite</p>
		</footer>
	</div>

	</body>
</html>