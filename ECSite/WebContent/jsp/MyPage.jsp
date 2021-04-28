<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "bean.UserBean" %>
    <%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ</title>
</head>
<body>
<!-- ブラウザ上にマイページ用の画面を表示 -->
<a href="/top/TopServlet">TOP</a>


<% UserBean user = (UserBean) request.getAttribute("LoginUser");%>
		<%= user.getLoginId() %>さんのマイページ

		<br>
<a href="#">登録情報変更</a>
<br>
<a href="#">お気に入りリスト</a>
<br>
<a href="#">購入履歴</a>
<br>
<a href="Top.jsp" value="logout">ログアウト</a>
</body>
</html>