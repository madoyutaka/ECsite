<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ page import="userdatachange.UserDataChangeServlet" %>
<%@ page import="mypage.MyPageServlet" %>
<%@ page import="java.util.ArrayList.*" %>
<%@ page import="bean.UserBean.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録情報変更</title>
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

　　<div class="page_layout userdatachange_layout">
        <h1 class="page_ttl login_ttl">登録情報変更画面</h1>

		<form action="http://localhost:8080/ECSite/UserDataChangeServlet" method="POST">
			<!-- エラーメッセージがある場合は表示し、ない場合は表示しない。 -->
			<c:choose>
				<c:when test="${resultText != null}">
					<h1 class="error_text error_login"><c:out value="${resultText}"/></h1>
				</c:when>
				<c:when test="${resultText == null}">
					<br>
				</c:when>
			</c:choose>

			<!-- password用の変数 -->
			<% String passwordResultText = "エラーが発生しました";%>
			<!-- passwordがnullではない場合と、passwordがnull(取得失敗)の場合 -->
			<c:choose>
				<c:when test="${loginUserData.password != null }">
					<% passwordResultText="設定済み"; %>
				</c:when>
				<c:when test="${loginUserData.password  == null}">
					<% passwordResultText="取得失敗"; %>
				</c:when>
			</c:choose>




		<table class="user_data_change">
			<tr>
				<td>
					氏名：
				</td>
				<td>
					<c:out value="${loginUserData.userName}" default="取得失敗"/>
				</td>
			</tr>
			<tr>
				<td>
					氏名の変更(20文字以内)
				</td>
				<td>
					<input type="text" name="newUserName" placeholder="山田太郎"/><input class="btn" type="submit" name="btnUserName" value="送信">
				</td>
			</tr>

			<tr>
				<td>
					<br>
				</td>
			</tr>

			<tr>
				<td>
					ユーザーID：
				</td>
				<td>
					<c:out value="${loginUserData.userId}" default="取得失敗"/>
				</td>
			</tr>
			<tr>
				<td>
					ユーザーIDの変更(半角英数字、20文字以内)
				</td>
				<td>
					<input type="text" name="newUserID" placeholder="tarou"/><input class="btn" type="submit" name="btnUserID" value="送信">
				</td>
			</tr>

			<tr>
				<td>
					<br>
				</td>
			</tr>


			<tr>
				<td>
					パスワード：
				</td>
				<td>
					<%= passwordResultText %>
				</td>
			</tr>
			<tr>
				<td>
					パスワードの変更(20文字以内)
				</td>
				<td>
					<input type="password" name="newPassword"/><input class="btn" type="submit" name="btnPassword" value="送信">
				</td>
			</tr>

			<tr>
				<td>
					<br>
				</td>
			</tr>

			<tr>
				<td>
					メールアドレス：
				</td>
				<td>
					<c:out value="${loginUserData.emailAddress}" default="取得失敗"/>
				</td>
			</tr>
			<tr>
				<td>
					メールアドレスの変更(40文字以内)
				</td>
				<td>
					<input type="text" name="newEmailAddress" placeholder="tarou@mail"/><input class="btn" type="submit" name="btnEmailAddress" value="送信">
				</td>
			</tr>

			<tr>
				<td>
					<br>
				</td>
			</tr>

			<tr>
				<td>
					郵便番号
				</td>
				<td>
					<c:out value="${loginUserData.postalCode}" default="取得失敗"/>
				</td>
			</tr>
			<tr>
				<td>
					郵便番号の変更(-無し、7文字)
				</td>
				<td>
					<input type="text" name="newPostalCode" placeholder="1112222"/><input class="btn" type="submit" name="btnPostalCode" value="送信">
				</td>
			</tr>

			<tr>
				<td>
					<br>
				</td>
			</tr>

			<tr>
				<td>
					住所
				</td>
				<td>
					<c:out value="${loginUserData.address}" default="取得失敗"/>
				</td>
			</tr>
			<tr>
				<td>
					住所の変更(100文字以内)
				</td>
				<td>
					<input type="text" name="newAddress" placeholder="東京都○○区○○1丁目1番1号"/><input class="btn" type="submit" name="btnAddress" value="送信">
				</td>
			</tr>
		</table>

		</form>
	</div>

	<div class="footer_wrapper">
	    <footer>
			<p class="footer_text">2021/05/14/ECSite</p>
		</footer>
	</div>
</body>
</html>