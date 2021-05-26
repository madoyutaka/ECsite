<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ page import="itemsearch.ItemSearchServlet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.ItemBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
</head>
<body>
 <%@include file= "Header.jsp" %>

	<!-- カテゴリボタン↓ -->
			<div class="category_wrap">
						<p class="category_ttl">カテゴリ一覧</p>
							<form  class="category_container" action = "http://localhost:8080/ECSite/ItemSearchServlet" method = "POST">
								<c:forEach items="${categoryHmap}" var="category">
									<c:choose>
										<c:when test="${category.key == selectCategoryNo}">
											<button class="btn category_btn select_btn" type="submit" name="btnCategorySelect" value="${category.key}">${category.value}</button>
										</c:when>
										<c:when test="${category.key != selectCategoryNo}">
											<button class="btn category_btn" type="submit" name="btnCategorySelect" value="${category.key}">${category.value}</button>
										</c:when>
									</c:choose>
									<input type ="hidden" name="selectItemSearchListPageNo" value="1">
								</c:forEach>
							</form>

			</div>
<!-- カテゴリボタン↑ -->



	<div class="page_layout search_layout">


			<!-- エラーメッセージがある場合は表示し、ない場合は表示しない。 -->
			<c:choose>
				<c:when test="${errorText != null}">
						<h1 class="error_text"><c:out value="${errorText}"/></h1>
				</c:when>
				<c:when test="${errorText == null}">
				</c:when>
			</c:choose>
		<br>


		<!-- itemSearchListがnullの場合、0件の場合、1件以上の場合 -->
		<c:choose>
			<c:when test="${itemSearchList == null}">
			</c:when>

			<c:when test="${ fn:length(itemSearchList) >= 1}">
			<div class="form_cover">
				<c:forEach items="${itemSearchList}" var="item">
						<!-- 商品画像 -->
						<form  class="item_form" name="btnItemDetail" action="http://localhost:8080/ECSite/ItemDetailServlet" method="POST">
						<h3 class="item_select_btn">
							<button class="item" type="submit" name="btnItemDetailTransition" value="${item.itemNo}">
							<input type ="hidden" name="selectReviewPageNo" value="1">
								<div class="imgPosition">
								<img src="img/furniture/${item.itemImage}" class="item_image">
										<c:choose>
											<c:when test="${reviewAverageHmap[item.itemNo] >= 4.0}">
												<img class ="highRatingImg" src="img/icon/imghighrating.png">
											</c:when>
										</c:choose>
								</div>
								<p class="item_name"><c:out value="${item.itemName}" default="取得失敗"/></p>
							<p class="item_price">お値段：<c:out value="${item.itemPrice}" default="取得失敗"/>円</p>
							</button>
						</h3>
						</form>
						<br>
					</c:forEach>
				</div>
			</c:when>
			</c:choose>
		

		<c:choose>
			<c:when test="${ fn:length(itemSearchList) >= 1}">
				<form name="btnFavoritePagination" action="http://localhost:8080/ECSite/ItemSearchServlet" method="POST">
					<div class="pagination">
						<c:forEach begin="1" end="${itemSearchListTotalPageNo}" step="1" var = "a">
							<button class="btn page_btn" type="submit" name="selectItemSearchListPageNo" value="${a}">${a}</button>
						</c:forEach>
					</div>

					<c:choose>
						<c:when test="${itemSearchWord!= null}">
							<input type ="hidden" name="btnItemSearchTransition" value="btnItemSearchTransition">
							<input type ="hidden" name="itemSearchWord" value="${itemSearchWord}">
						</c:when>
						<c:when test="${selectCategoryNo != null}">
							<input type ="hidden" name="btnCategorySelect" value="${selectCategoryNo}">
						</c:when>
					</c:choose>

				</form>
			</c:when>
		</c:choose>


</div>
</body>
</html>
