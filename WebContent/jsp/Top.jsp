<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ page import="top.TopServlet" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
<title>トップページ</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
</head>
<body>
<!-- ブラウザ上にトップの画面を表示 -->
 <%@include file= "Header.jsp" %>


	<!-- トップ画像 -->
	<div class="top_image">
		<div class="top_text">
			<h1>何かタイトル的なの</h1>
			<p>何かキャッチコピー</p>




		<c:choose>
			<c:when test="${LoginButton != null}">
				<form action = "http://localhost:8080/ECSite/TopServlet" method = "POST">
					<input  class="top_login_btn" type = "submit" name="btnLoginTransition" value = "ログイン">
					<input type ="hidden" name="btnLoginTransition" value="loginTransition">
				</form>
			</c:when>

			<c:when test="${LoginButton == null}">
				<form action = "http://localhost:8080/ECSite/MyPageServlet" method = "POST">
					<input  class="top_login_btn" type = "submit" name="btnLoginTransition" value = "マイページ">
					<input type ="hidden" name="btnMyPageTransition" value="MyPageTransition">
				</form>
			</c:when>

			<c:when test="${LoginButton == null}">
				<form action = "http://localhost:8080/ECSite/MyPageServlet" method = "POST">
					<input  class="top_login_btn" type = "submit" name="btnLoginTransition" value = "マイページ">
					<input type ="hidden" name="btnMyPageTransition" value="MyPageTransition">
				</form>
			</c:when>
		</c:choose>
		</div>
	</div>

	<div class="section_wrap">
		<section class="about">
			<h2 class="section_ttl">About</h2>
			<p class="about_text">このECサイトは、ECサイト開発の研修中に作成されました。</p>
			<p class="about_text">このサイトでは家具を閲覧可能です。好きな家具をご覧ください。</p>
			<p class="about_text">商品の詳細を閲覧したい場合や購入、お気に入り、レビューするためにはログインが必要です。</p>
			<p  class="about_text">このECサイトは少数メンバーで作成されております。どうぞ、ご覧ください。</p>
		</section>

		<section class="random_item">
		     <h2 class="section_ttl">オススメ家具</h2>

		     	<form name="btnItemDetail" action="http://localhost:8080/ECSite/ItemDetailServlet" method="POST">
					<h3 class="item_select_btn">
						<button class="item" type="submit" name="btnItemDetailTransition" value="${randomItem.itemNo}">
						<input type ="hidden" name="selectReviewPageNo" value="1">
							<div class="imgPosition">
								<img src="img/furniture/${randomItem.itemImage}" class="item_image">
										<c:choose>
											<c:when test="${reviewAverage >= 4.0}">
												<img class ="highRatingImg" src="img/icon/imghighrating.png">
											</c:when>
										</c:choose>
								</div>
							<p class="item_name"><c:out value="${randomItem.itemName}" default="取得失敗"/></p>
						<p class="item_price">お値段：<c:out value="${randomItem.itemPrice}" default="取得失敗"/>円</p>
						</button>
					</h3>
				</form>

		</section>
	</div>

<div id="page_top"><a href="#"></a></div>


 <%@include file= "Footer.jsp" %>
</body>
</html>