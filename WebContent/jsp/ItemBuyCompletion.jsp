<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入完了画面</title>
</head>
<body>
	<h1>購入完了しました！！！</h1>

	<form action = "http://localhost:8080/ECSite/MyPageServlet" method = "POST">
		<input type = "submit" name="btnMyPageTransition" value = "マイページへ">
	</form>

</body>
</html>