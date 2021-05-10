<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>お気に入り完了</title>
		<link rel="stylesheet"href="FavoriteCompletion.css">
	</head>
	<body>
	<header>
			<div class="menu" >
        <form action = "http://localhost:8080/ECSite/TopServlet" method = "POST">
		<input type = "submit" name="btnTopTransition" value = "トップ">
		</form>
        <form action = "http://localhost:8080/ECSite/MyPageServlet" method = "POST">
		<input type = "submit" name="btnMyPageTransition" value = "マイページ">
		</form>
		<form action = "http://localhost:8080/ECSite/CartServlet" method = "POST">
		<input type = "submit" name="btnCartTransition" value = "カート">
		</form>
        <form action = "http://localhost:8080/ECSite/ItemSearchServlet" method = "POST">
		<input type = "submit" name="btnItemSearchTransition" value = "商品一覧">
		</form>
	</div>
	</header>



	<br><br><br>

	<div style="text-align:center;"><h1>${message}</h1>
		<br><br><br>

		<form action = "http://localhost:8080/ECSite/FavoriteListServlet" method = "POST">
		<input type = "submit" name="btnFavoriteListTransition" value = "お気に入りリストを見る">
		</form>
	</div>
	</body>
</html>