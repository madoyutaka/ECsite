<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ page import="itembuylog.ItemBuyLogServlet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.ItemBuyLogBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入履歴</title>
</head>
<body>
	<h3>購入履歴</h3>
		<!-- エラーメッセージがある場合は表示し、ない場合は表示しない。 -->
		<c:choose>
			<c:when test="${errorText != null}">
				<c:out value="${errorText}"/>
			</c:when>
			<c:when test="${errorText == null}">
				<br>
			</c:when>
		</c:choose>

	<!-- loginUserItemBuyLogが0件の場合、1件以上の場合 -->
	<c:choose>
		<c:when test="${ fn:length(loginUserItemBuyLog) >= 1}">
			<c:forEach items="${loginUserItemBuyLog}" var="log">
				<form name="btnItemDetail" action="http://localhost:8080/ECSite/ItemDetailServlet" method="POST">
				<button type="submit" name="btnItemDetailTransition" value="${log.itemNo}">${log.itemName}</button>
				</form>
				購入日：<c:out value="${log.itemBuyDate}" default="取得失敗"/><br>
				購入数：<c:out value="${log.itemBuyCount}" default="取得失敗"/><br>
				商品画像：<c:out value="${log.itemImage}" default="取得失敗"/><br>
				<br>
			</c:forEach>
		</c:when>

		<c:when test="${ fn:length(loginUserItemBuyLog) == 0}">
			購入履歴はありません。
		</c:when>

	</c:choose>

</body>
</html>