<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ page import="java.util.ArrayList"%>
<%String id = (String)request.getAttribute("id");%>
<%String pass = (String)request.getAttribute("pass");%>
<%String mail = (String)request.getAttribute("mail");%>
<%String add = (String)request.getAttribute("add");%>
<%String pcode = (String)request.getAttribute("pcode");%>
<%String name = (String)request.getAttribute("name");%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>新規登録</title>
		<link rel="stylesheet"href="SignUp.css">
	</head>
	<body>
		<h1>新規会員登録</h1>



			${message}<br>
		<form action="http://localhost:8080/ECSite/SignUpServlet"  method="post">

		氏名(20文字以内)<input type="text" name="userName"  value="${name}"  placeholder="山田 太郎"><br>

		ユーザーID(20文字以内)<input type="text" name="userId" value="${id}"  placeholder="tarou"><br>

		パスワード(20文字以内)<input type="password" name="password"  value="${pass}"  placeholder="tarou12345678"><br>

		メールアドレス(40文字以内)<input type="text" name="emailAddress" value="${mail}"  placeholder="tarou@mail"><br>

		郵便番号(ハイフンなし半角数字7桁)<input type="text" name="postalCode"  value="${pcode}" size="5"  placeholder="1112222"><br>

		住所(100文字以内)<input type="text" name="address"  size="70"  value="${add}"  placeholder="東京都○○区○○1丁目1番1号"><br>


		<input type="submit" value="登録"><br><br>

</form>

<form action = "http://localhost:8080/ECSite/LoginServlet" method = "POST">
	<input type = "submit" name="btnLoginTransition" value = "すでに登録済みの方はこちら">
</form>

	</body>
</html>