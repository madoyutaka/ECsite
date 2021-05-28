	<!-- 各jspのbody内最上部に↓を追加してください-->
<%--			 <%@include file= "Header.jsp" %>			--%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page import="itemsearch.ItemSearchServlet" %>
<!DOCTYPE html>
<html>

	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
<!-- headの中に↓を追加してCSSを読み込む -->
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
				<c:choose>
					<c:when test="${itemSearchWord != null}">
						<input class ="header_word" type="text" name="itemSearchWord" value="${itemSearchWord}"/>
					</c:when>
					<c:when test="${itemSearchWord == null}">
						<input class ="header_word" type="text" name="itemSearchWord"/>
					</c:when>
				</c:choose>
				<input class ="header_search_btn" type="image"  src="${pageContext.request.contextPath}/img/icon/search.png"  name="btnItemSearch"/>
				<input type ="hidden" name="btnItemSearchTransition" value="itemSearchTransition">
				<input type ="hidden" name="selectItemSearchListPageNo" value="1">
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
	</body>

</html>