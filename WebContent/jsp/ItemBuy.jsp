<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入確認画面</title>
</head>
<body>
	<h1>購入確認画面です。</h1>

	<!-- カート内 -->


	<!-- カート一覧 -->


	<!--　注文者情報入力 -->
	<form action = "http://localhost:8080/ECSite/CartServlet" method = "POST">
		氏名：<input type = "text" name="btnTransition" value ="name">

		メールアドレス：<input type = "email" name="btnTransition" value ="mailadress">

		郵便番号：<input type = "text" name="btnTransition" value ="num">

		住所：<input type = "text" name="btnTransition" value ="name">
	</form>

	<!--　購入ボタン -->
	<form action = "http://localhost:8080/ECSite/ItemBuyCompletionServlet" method = "POST">
			<input type = "submit" name="btnItemBuyCompletionTransition" value ="購入する">
	</form>
</body>
</html>