<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
</head>
<body>
<!-- ブラウザ上にトップの画面を表示 -->

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