<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<%@ page import = "bean.UserBean" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ</title>
</head>
<body>
<!-- ブラウザ上にマイページ用の画面を表示 -->
<a href="http://localhost:8080/ECSite/TopServlet">TOP</a>


<br>

<form action = "http://localhost:8080/ECSite/UserDataChangeServlet" method = "POST">
	<input type = "submit" name="btnUserDataChangeTransition" value = "登録情報を変更する">
	<br>
</form>
<form action = "http://localhost:8080/ECSite/MyPageServlet" method = "POST">
	<input type = "submit" name="btnFavoriteListTransition" value = "お気に入りリストを確認する">
	<br>
	<input type = "submit" name="btnItemBuyLogTransition" value = "購入履歴を確認する">
	<br>
</form>
	<br>
	<br>
<form action = "http://localhost:8080/ECSite/LogoutServlet" method = "POST">
	<input type = "submit" name="btnLogOut" value = "ログアウト">
</form>


</body>

</html>