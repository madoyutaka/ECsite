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
<title>Insert title here</title>
</head>
<body>
	<form action="http://localhost:8080/ECSite/UserDataChamgeServlet" method="POST">
		<!-- エラーメッセージがある場合は表示し、ない場合は表示しない。 -->
		<c:choose>
			<c:when test="${resultText != null}">
				<c:out value="${resultText}"/>
			</c:when>
			<c:when test="${resultText == null}">
				<c:out value=""/>
			</c:when>
		</c:choose>

		<!-- password用の変数 -->
		<% String passwordResultText = "エラーが発生しました";%>
		<!-- passwordがnullではない場合と、passwordがnull(取得失敗)の場合 -->
		<c:choose>
			<c:when test="${userData.password != null }">
				<% passwordResultText="設定済み"; %>
			</c:when>
			<c:when test="${userData.password  == null}">
				<% passwordResultText="取得失敗"; %>
			</c:when>
		</c:choose>



		<br>
	<table>
		<tr>
			<td>
				氏名：
			</td>
			<td>
				<c:out value="${userData.userName}" default="取得失敗"/>
			</td>
		</tr>
		<tr>
			<td>
				氏名の変更(20文字以内)
			</td>
			<td>
				<input type="text" name="newUserName" placeholder="山田太郎"/><input type="submit" name="btnUserName" value="送信">
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
				<c:out value="${userData.userId}" default="取得失敗"/>
			</td>
		</tr>
		<tr>
			<td>
				ユーザーIDの変更(半角英数字、20文字以内)
			</td>
			<td>
				<input type="text" name="newUserID" placeholder="tarou"/><input type="submit" name="btnUserID" value="送信">
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
				<input type="password" name="newPassword"/><input type="submit" name="btnPassword" value="送信">
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
				<c:out value="${userData.emailAddress}" default="取得失敗"/>
			</td>
		</tr>
		<tr>
			<td>
				メールアドレスの変更(40文字以内)
			</td>
			<td>
				<input type="text" name="newEmailAddress" placeholder="tarou@mail"/><input type="submit" name="btnEmailAddress" value="送信">
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
				<c:out value="${userData.postalCode}" default="取得失敗"/>
			</td>
		</tr>
		<tr>
			<td>
				郵便番号の変更(-無し、7文字)
			</td>
			<td>
				<input type="text" name="newPostalCode" placeholder="1112222"/><input type="submit" name="btnPostalCode" value="送信">
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
				<c:out value="${userData.address}" default="取得失敗"/>
			</td>
		</tr>
		<tr>
			<td>
				住所の変更(100文字以内)
			</td>
			<td>
				<input type="text" name="newAddress" placeholder="東京都○○区○○1丁目1番1号"/><input type="submit" name="btnAddress" value="送信">
			</td>
		</tr>
	</table>

	</form>
</body>
</html>