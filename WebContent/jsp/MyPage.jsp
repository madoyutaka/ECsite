<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ page import = "bean.UserBean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
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

	<!-- ブラウザ上にマイページ用の画面を表示 -->

	<div class="page_layout mypage_layout">
			<h1 class="page_ttl">マイページ</h1>

        <div class="mypage_container">
				<form action = "http://localhost:8080/ECSite/UserDataChangeServlet" method = "POST">
			       <input  class="btn" type = "submit" name="btnUserDataChangeTransition" value = "登録情報を変更する">
		         </form>

				<form action = "http://localhost:8080/ECSite/FavoriteListServlet" method = "POST">
					<input  class="btn" type = "submit" name="btnFavoriteListTransition" value = "お気に入りリストを確認する">
					<input type ="hidden" name="selectFavoriteListPageNo" value="1">
				</form>

				<form action = "http://localhost:8080/ECSite/ItemBuyLogServlet" method = "POST">
					<input class="btn" type = "submit" name="btnItemBuyLogTransition" value = "購入履歴を確認する">
				</form>

				<form action = "http://localhost:8080/ECSite/LogoutServlet" method = "POST">
					<input class="btn" type = "submit" name="btnLogOut" value = "ログアウト">
				</form>

		</div>
	</div>

	<div class="footer_wrapper">
	    <footer>
			<p class="footer_text">2021/05/14/ECSite</p>
		</footer>
	</div>



</body>

</html>