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
	</head>
	<body>
	<h1>お気に入り一覧</h1>


<c:choose>
		<c:when test="${ fn:length(loginUserFaves) >= 1}">
			<c:forEach items="${loginUserFaves}" var = "a">
				<form name="btnItemDetail" action="http://localhost:8080/ECSite/ItemDetailServlet" method="POST">
					<button type="submit" name="btnItemDetailTransition" value="${a.itemNo}">${a.itemName}</button>
				</form>
			</c:forEach>
		</c:when>

		<c:when test="${ fn:length(loginUserFaves) == 0}">
			お気に入りリストに商品がありません。
		</c:when>

	</c:choose>


	<form action = "http://localhost:8080/ECSite/MyPageServlet" method = "POST">
	<input type = "submit" name="btnMyPageTransition" value = "マイページへ">
	</form>
	</body>
</html>