<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
</head>
<body>
<!-- ブラウザ上にトップの画面を表示 -->
	<header>
	        <button type="button" onclick="location.href='http://localhost:8080/ECSite/TopServlet'">検索</button>
			<nav><ul>
	        <li><form action = "http://localhost:8080/ECSite/FavoriteListCompletionServlet" method = "POST">
			<input type = "submit" name="btnMyPageTransition" value = "マイページ">
			</form></li>
			<li><form action = "http://localhost:8080/ECSite/CartServlet" method = "POST">
			<input type = "submit" name="btnHeaderCartTransition" value = "カート">
			</form></li>
	        <li><form action = "http://localhost:8080/ECSite/ItemSearchServlet" method = "POST">
			<input type = "submit" name="btnItemListTransition" value = "商品一覧">
			</form></li>
			</ul></nav>
	</header>

<form action = "http://localhost:8080/ECSite/TopServlet" method = "POST">
	<input type = "submit" name="btnLoginTransition" value = "ログイン">
	<br>
</form>
<form action = "http://localhost:8080/ECSite/ItemSearchServlet" method = "POST">
	<input type = "submit" name="btnItemListTransition" value = "商品を見る">
	<br>
</form>

</body>
</html>
