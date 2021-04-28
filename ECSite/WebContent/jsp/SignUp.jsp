<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>新規登録</title>
		<link rel="stylesheet"href="SignUp.css">
	</head>
	<body>
		<h1>新規会員登録</h1>

		<span class="label label-danger">${message}</span><br>
		<form action="http://localhost:8080/ECSite/SignUpServlet"  method="post">

		氏名(20文字以内)<input type="text" name="user_name"   maxlength='20' placeholder="山田 太郎"><br><br>

		ユーザーID(20文字以内)<input type="text" name="user_id"  maxlength='20' placeholder="tarou"><br><br>

		パスワード(20文字以内)<input type="password" name="password"   maxlength='20'  placeholder="tarou12345678"><br><br>

		メールアドレス(40文字以内)<input type="text" name="email_address"  maxlength='40' placeholder="tarou@mail"><br><br>

		郵便番号(ハイフンなし半角数字7桁)<input type="text" name="postal_code" size="5"  maxlength='7' placeholder="1112222"><br><br>

		住所(100文字以内)<textarea name="address" cols="40" rows="3"  maxlength='100' placeholder="東京都○○区○○1丁目1番1号"></textarea><br><br>


		<input type="submit" value="登録"><br><br>

		<button type="button" onclick="location.href='Login.jsp'">すでに登録済みの方はこちら</button>



		</form>
	</body>
</html>
