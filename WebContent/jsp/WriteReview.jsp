<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ page import="writereview.WriteReviewServlet" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レビュー入力画面</title>
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

    <div class="page_layout">
			<!-- エラーメッセージがある場合は表示し、ない場合は表示しない。 -->
			<c:choose>
				<c:when test="${errorText != null}">
					<h1 class="error_text"><c:out value="${errorText}"/></h1>
				</c:when>
				<c:when test="${errorText == null}">
					<br>
				</c:when>
			</c:choose>

       		<div class="review_container">
				<form action="http://localhost:8080/ECSite/WriteReviewServlet" method="POST">
					<p class="review_text">点数を選択</p>

					<select class="review_select" name="reviewScore">
					<%
						for(double scoreNo = 1.0; scoreNo <= 5.0; scoreNo+=0.5){
					%>
					<option value=<%=scoreNo%>><%=scoreNo%></option>
					<%
						}
					%>
					</select>
					<p class="review_text">レビューを書き込む</p>

					<div class="review_flex">
						<textarea class="review_area" name="reviewComment" rows=10 cols=25></textarea>
						<input class="btn review_btn" type="submit" name="btnWriteReview" value="送信"/>
					</div>

				</form>
			</div>
	</div>

	<footer>
	  <p class="footer_text">2021/05/14/ECSite</p>
    </footer>

</body>
</html>
