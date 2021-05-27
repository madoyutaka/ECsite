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
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
<title>登録情報変更画面</title>
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
</head>
<body>
 <%@include file= "Header.jsp" %>

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




		<div class="user_data_change">

			<div class="data_change_text">
				<p class="data_change_ttl">
					氏名：
				</p>

				<p>
					<c:out value="${loginUserData.userName}" default="取得失敗"/>
				</p>
			</div>

			<div class="data_change_text">
				<p class="data_change_ttl">
					氏名の変更(20文字以内)
				</p>

				<p>
					<input type="text" name="newUserName" placeholder="山田太郎"/><input class="btn" type="submit" name="btnUserName" value="送信">
				</p>
			</div>


			<div class="data_change_text">
				<p class="data_change_ttl">
					ユーザーID：
				</p>

				<p>
					<c:out value="${loginUserData.userId}" default="取得失敗"/>
				</p>

			</div>

			<div class="data_change_text">
				<p class="data_change_ttl">
					ユーザーIDの変更(半角英数字、20文字以内)
				</p>

				<p>
					<input type="text" name="newUserID" placeholder="tarou"/><input class="btn" type="submit" name="btnUserID" value="送信">
				</p>
			</div>


			<div class="data_change_text">
				<p class="data_change_ttl">
					パスワード：
				</p>

				<p>
					<%= passwordResultText %>
				</p>


			</div>

			<div class="data_change_text">
				<p class="data_change_ttl">
					パスワードの変更(20文字以内)
				</p>

				<p>
					<input type="password" name="newPassword"/><input class="btn" type="submit" name="btnPassword" value="送信">
				</p>
			</div>

			<div class="data_change_text">
				<p class="data_change_ttl">
					メールアドレス：
				</p>

				<p>
					<c:out value="${loginUserData.emailAddress}" default="取得失敗"/>
				</p>
			</div>

			<div class="data_change_text">
				<p class="data_change_ttl">
					メールアドレスの変更(40文字以内)
				</p>

				<p>
					<input type="text" name="newEmailAddress" placeholder="tarou@mail"/><input class="btn" type="submit" name="btnEmailAddress" value="送信">
				</p>
			</div>


			<div class="data_change_text">
				<p class="data_change_ttl">
					郵便番号
				</p>

				<p>
					<c:out value="${loginUserData.postalCode}" default="取得失敗"/>
				</p>

			</div>

			<div class="data_change_text">
				<p class="data_change_ttl">
					郵便番号の変更(-無し、7文字)
				</p>

				<p>
					<input type="text" name="newPostalCode" placeholder="1112222"/><input class="btn" type="submit" name="btnPostalCode" value="送信">
				</p>
			</div>

			<div class="data_change_text">
				<p class="data_change_ttl">
					住所
				</p>

				<p>
					<c:out value="${loginUserData.address}" default="取得失敗"/>
				</p>
			</div>

			<div class="data_change_text">
				<p class="data_change_ttl">
					住所の変更(100文字以内)
				</p>

				<p>
					<input type="text" name="newAddress" placeholder="東京都○○区○○1丁目1番1号"/><input class="btn" type="submit" name="btnAddress" value="送信">
				</p>
			</div>
		</div>

		</form>
	</div>



</body>

</html>