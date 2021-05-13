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
		        <button type="button" onclick="location.href='http://localhost:8080/ECSite/TopServlet'">TOP</button>

				<form action="http://localhost:8080/ECSite/ItemSearchServlet" method="POST">
								<input class ="header_word" type="text" name="itemSearchWord"/>
								<input class ="header_search_btn" type="submit"  src="${pageContext.request.contextPath}/img/icon/search.png"  name="btnItemSearch" value="検索"/>
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
