<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
</head>
<body>
<!-- ブラウザ上にトップの画面を表示 -->
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

	<!-- トップ画像 -->
	<div class="top_image">
		<div class="login_text">
			<h1>テスト</h1>
			<p>あああああああああ</p>

			<form action = "http://localhost:8080/ECSite/TopServlet" method = "POST">
				<input  class="login_btn" type = "submit" name="btnLoginTransition" value = "ログイン">
				<input type ="hidden" name="btnLoginTransition" value="loginTransition">
			</form>
		</div>
	</div>
	
	<div class="section_wrap">
		<section class="about">
			<h2 class="section_ttl">About</h2>
			<p>あああああああああ</p>
			<p>あああああああああ</p>
			<p>あああああああああ</p>
		</section>

		<section class="random_item">
		     <h2 class="section_ttl">オススメ家具</h2>
		<div class="item_list">
				<img src="${pageContext.request.contextPath}/img/sofa.jpg">
				<div class="item_box">
				 	<h3>ソファ</h3>
				    <p>あああああああああ</p>
					<p>あああああああああ</p>
					<p>あああああああああ</p>
				</div>
		</div>

		</section>
	</div>

	<footer>
		<p class="footer_text">2021/05/14/ECSite</p>
	</footer>

</body>
</html>
