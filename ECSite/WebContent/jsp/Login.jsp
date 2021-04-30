<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインページ</title>
</head>
<body>
<h1>ここはログインページです。</h1>
<!-- is or password miss  -->
<% String empty = (String)request.getAttribute("Empty");
if(empty != null){
  %>
<!-- ブラウザ上にログイン画面を表示 -->
<p><%= empty%></p>
<% } %>
<!-- IDとPassWordを入力 -->
<!-- actionで指定した場所に値をPOST -->
	<form action="http://localhost:8080/ECSite/LoginServlet" method="POST">
		ユーザーID:<input type="text" name="user_id">
		<br>
		パスワード：<input type="password" name="password">
		<br>
		<input type="submit" value="ログイン">
	</form>

	<a href="SignUp.jsp">新規登録へ</a>
</body>
</html>