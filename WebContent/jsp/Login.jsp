<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
<title>ログインページ</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
</head>
<body>
 <%@include file= "Header.jsp" %>

	<div class="page_layout">
		<h1 class="page_ttl login_ttl">ログイン画面</h1>
		<!-- is or password miss  -->

		<% String empty = (String)request.getAttribute("Empty");
		if(empty != null){
		  %>
		<!-- ブラウザ上にログイン画面を表示 -->
		<h1 class="error_text error_login"><%= empty%></h1>
		<% } %>

		<!-- IDとPassWordを入力 -->
		<!-- actionで指定した場所に値をPOST -->
		<div class="login_layout">
			<form class="login_form"action="http://localhost:8080/ECSite/LoginServlet" method="POST">
				<div class="login_wrap">
					<p class="login_text">
						 ユーザーID:<input type="text" name="user_id">
					</p>

					<p class="login_text">
						 パスワード:<input type="password" name="password">
					</p>

					 <input class="btn login_btn" type="submit" value="ログイン">

				</div>
			</form>

			<form action = "http://localhost:8080/ECSite/SignUpServlet" method = "POST">
				<input class="btn sinup_btn" type = "submit" name="btnSignUpTransition" value = "新規登録はこちら">
			</form>
		</div>

	</div>

	 <%@include file= "Footer.jsp" %>



</body>

</html>