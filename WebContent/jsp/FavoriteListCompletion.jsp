<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>お気に入り完了</title>
		<link rel="stylesheet"href="${pageContext.request.contextPath}/css/reset.css">
        <link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
	</head>
	<body>
	<%@include file= "Header.jsp" %>

		<div class="page_layout completion_layout">
			<h1 class="error_text favorite_comp_text">${returnText}</h1>
			<form class="favorite_form"action = "http://localhost:8080/ECSite/FavoriteListServlet" method = "POST">
				<input class="btn" type = "submit" name="btnFavoriteListTransition" value = "お気に入りリストを見る">
				<input type ="hidden" name="selectFavoriteListPageNo" value="1">
			</form>

		</div>

		<%@include file= "Footer.jsp" %>
	</body>
</html>