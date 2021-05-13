<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ page import="itemsearch.ItemSearchServlet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.ItemBean" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
</head>
<body>
		<!-- エラーメッセージがある場合は表示し、ない場合は表示しない。 -->
		<c:choose>
			<c:when test="${errorText != null}">
				<c:out value="${errorText}"/>
			</c:when>
			<c:when test="${errorText == null}">
			</c:when>
		</c:choose>
	<br>
	<!-- itemSearchListがnullの場合、0件の場合、1件以上の場合 -->
	<c:choose>
		<c:when test="${itemSearchList == null}">
		</c:when>

		<c:when test="${ fn:length(itemSearchList) >= 1}">
			<c:forEach items="${itemSearchList}" var="item">
				<form name="btnItemDetail" action="http://localhost:8080/ECSite/ItemDetailServlet" method="POST">
				<button type="submit" name="btnItemDetailTransition" value="${item.itemNo}">${item.itemName}</button>
				</form>
				商品説明：<c:out value="${item.itemDescription}" default="取得失敗"/><br>
				商品価格：<c:out value="${item.itemPrice}" default="取得失敗"/><br>
				商品画像：<c:out value="${item.itemImage}" default="取得失敗"/><br>
				商品在庫：<c:out value="${item.itemStock}" default="取得失敗"/><br>
				カテゴリ名：<c:out value="${item.categoryName}" default="取得失敗"/><br>
				<br>
			</c:forEach>
		</c:when>

		<c:when test="${ fn:length(itemSearchList) == 0}">
			お探しの商品はありません。
		</c:when>

	</c:choose>



</body>
</html>
