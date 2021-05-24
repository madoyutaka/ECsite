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
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
<title>購入完了画面</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
</head>
<body>
<%@include file= "Header.jsp" %>

	<div class="page_layout item_buy_completion_layout">
		<h1 class="page_ttl">${itemBuyResultText}</h1>

		<div class="ItemBuyCompletion">
			<form action = "http://localhost:8080/ECSite/MyPageServlet" method = "POST">
				<input class="btn" type = "submit" name="btnMyPageTransition" value = "マイページへ">
			</form>
		</div>
	</div>

<%@include file= "Footer.jsp" %>
</body>
</html>
