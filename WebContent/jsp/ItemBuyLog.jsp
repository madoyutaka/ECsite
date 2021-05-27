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
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
<title>購入履歴</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
</head>
<body>
<%@include file= "Header.jsp" %>


	<div class="page_layout cart_layout">
			<h1 class="page_ttl">購入履歴</h1>

			<div class="item_buylog_container">
				<!-- loginUserItemBuyLogが0件の場合、1件以上の場合 -->
						<c:choose>
							<c:when test="${ fn:length(loginUserItemBuyLog) >= 1}">
								<c:forEach items="${loginUserItemBuyLog}" var="log">
									<div class="item_buylog_wrap">
										<img class="item_image" src="img/furniture/${log.itemImage}">


										<form class="item_buylog_form" name="btnItemDetail" action="http://localhost:8080/ECSite/ItemDetailServlet" method="POST">
											<button class="btn item_buylog_btn" type="submit" name="btnItemDetailTransition" value="${log.itemNo}">${log.itemName}</button>
											<input type ="hidden" name="selectReviewPageNo" value="1">
										</form>

										<div class="item_buylog_text">
											<p class="item_buylog_day">購入日:<c:out value="${log.itemBuyDate}" default="取得失敗"/></p>
											<p class="item_buylog_num">購入数:<c:out value="${log.itemBuyCount}" default="取得失敗"/></p>
											<p class="item_buylog_price">購入金額:<c:out value="${log.itemTotalPrice}" default="取得失敗"/></p>
										</div>
									</div>


								</c:forEach>
							</c:when>

							<c:when test="${ fn:length(loginUserItemBuyLog) == 0}">
								<c:choose>
									<c:when test="${errorText==null}">
										<p class="error_text">購入履歴はありません。</p>
									</c:when>
									<c:when test="${errorText!=null}">
										<p class="error_text error_login">${errorText}</p>
									</c:when>
								</c:choose>
							</c:when>

						</c:choose>
			</div>

					<c:choose>
						<c:when test="${ fn:length(loginUserItemBuyLog) >= 1}">
							<form name="btnFavoritePagination" action="http://localhost:8080/ECSite/ItemBuyLogServlet" method="POST">
								<div class="pagination">
									<c:forEach begin="1" end="${itemBuyLogTotalPageNo}" step="1" var = "a">
										<c:choose>
											<c:when test="${itemBuyLogPageNo == a}">
												<button class="btn page_btn select_btn" type="submit" name="selectItemBuyLogPageNo" value="${a}">${a}</button>
											</c:when>
											<c:when test="${itemBuyLogPageNo != a}">
												<button class="btn page_btn" type="submit" name="selectItemBuyLogPageNo" value="${a}">${a}</button>
											</c:when>
										</c:choose>
									</c:forEach>
								</div>
							</form>
						</c:when>
					</c:choose>

	</div>

	　<div id="page_top"><a href="#"></a></div>
</body>
</html>