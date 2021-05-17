<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインページ</title>
</head>
<body>
	
	<header>
	       <div class="header_wrap">
		       <form action="http://localhost:8080/ECSite/TopServlet" method="POST">
									<input class ="header_top_btn" type="image"  src="${pageContext.request.contextPath}/img/icon/logo.png"  name="btnItemSearch"/>
									<input type ="hidden" name="btnTopTransition" value="topTransition">
				</form>

				<form action="http://localhost:8080/ECSite/ItemSearchServlet" method="POST">
								<input class ="header_word" type="text" name="itemSearchWord"/>
								<input class ="header_search_btn" type="image"  src="${pageContext.request.contextPath}/img/icon/search.png"  name="btnItemSearch"/>
								<input type ="hidden" name="btnItemSearchTransition" value="itemSearchTransition">
				</form>

				<nav>
					<ul>
						<li>
							<form action = "http://localhost:8080/ECSite/CartServlet" method = "POST">
								<input class ="header_cart_btn" type = "image" src="${pageContext.request.contextPath}/img/icon/cart.png" name="btnCartTransition">
								<input type ="hidden" name="btnHeaderCartTransition" value="headerCartTransition">
							</form>
						</li>

						<li>
		        			<form action = "http://localhost:8080/ECSite/MyPageServlet" method = "POST">
								<input class ="header_mypage_btn" type = "image" src="${pageContext.request.contextPath}/img/icon/home.png"  name="btnMyPageTransition">
								<input type ="hidden" name="btnMyPageTransition" value="myPageTransition">
							</form>
						</li>
					</ul>
				</nav>
			</div>
	</header>
	
	<div class="page_layout">
			<h1 class="login_ttl">ログイン画面</h1>
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
							 パスワード：<input type="password" name="password">
						</p>

						 <input class="login_btn" type="submit" value="ログイン">

					</div>
				</form>

				<form action = "http://localhost:8080/ECSite/SignUpServlet" method = "POST">
					<input class="sinup_btn" type = "submit" name="btnSignUpTransition" value = "新規登録はこちら">
				</form>
			</div>

		</div>
</body>
</html>
