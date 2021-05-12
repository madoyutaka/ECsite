<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ page import = "java.util.ArrayList" %>
<%@ page import = "bean.ItemBean" %>
<%@ page import = "itembuycompletion.ItemBuyCompletionServlet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入完了画面</title>
</head>
<body>
		<c:choose>
			<c:when test="${itemBuyResultText != null}">
				<c:out value="${itemBuyResultText}"/>
			</c:when>
			<c:when test="${itemBuyResultText == null}">
				<br>
			</c:when>
		</c:choose>

				<c:choose>
			<c:when test="${itemBuyLogResultText != null}">
				<c:out value="${itemBuyLogResultText}"/>
			</c:when>
			<c:when test="${itemBuyLogResultText == null}">
				<br>
			</c:when>
		</c:choose>

	<form action = "http://localhost:8080/ECSite/MyPageServlet" method = "POST">
		<input type = "submit" name="btnMyPageTransition" value = "マイページへ">
	</form>

</body>
</html>