<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ page import="itembuylog.ItemBuyLogServlet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.ItemBuyLogBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入履歴</title>
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

	<div class="page_layout cart_layout">
			<h1 class="page_ttl">購入履歴</h1>
			<!-- エラーメッセージがある場合は表示し、ない場合は表示しない。 -->
			<c:choose>
				<c:when test="${errorText != null}">
					<h1 class="error_text"><c:out value="${errorText}"/></h1>
				</c:when>
				<c:when test="${errorText == null}">
				</c:when>
			</c:choose>

			<div class="item_buylog_container">
				<!-- loginUserItemBuyLogが0件の場合、1件以上の場合 -->
						<c:choose>
							<c:when test="${ fn:length(loginUserItemBuyLog) >= 1}">
								<c:forEach items="${loginUserItemBuyLog}" var="log">
									<div class="item_buylog_wrap">
										<img class="item_image" src="img/${log.itemImage}">


										<form class="item_buylog_form" name="btnItemDetail" action="http://localhost:8080/ECSite/ItemDetailServlet" method="POST">
											<button class="btn item_buylog_btn" type="submit" name="btnItemDetailTransition" value="${log.itemNo}">${log.itemName}</button>
										</form>

										<div class="item_buylog_text">
											<p class="item_buylog_day">購入日：<c:out value="${log.itemBuyDate}" default="取得失敗"/></p>
											<p class="item_buylog_num">購入数：<c:out value="${log.itemBuyCount}" default="取得失敗"/></p>
										</div>
									</div>


								</c:forEach>
							</c:when>

							<c:when test="${ fn:length(loginUserItemBuyLog) == 0}">
								<p class="error_text">購入履歴はありません。</p>
							</c:when>

						</c:choose>
			</div>

					<c:choose>
						<c:when test="${ fn:length(loginUserItemBuyLog) >= 1}">
							<form name="btnFavoritePagination" action="http://localhost:8080/ECSite/ItemBuyLogServlet" method="POST">
								<div class="pagination">
									<c:forEach begin="1" end="${itemBuyLogListTotalPageNo}" step="1" var = "a">
										<button class="btn page_btn" type="submit" name="selectItemBuyLogPageNo" value="${a}">${a}</button>
									</c:forEach>
								</div>
							</form>
						</c:when>
					</c:choose>

	</div>

	　<div id="page_top"><a href="#"></a></div>
</body>
</html>