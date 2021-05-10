<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ page import="writereview.WriteReviewServlet" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レビュー入力画面</title>
</head>
<body>
		<!-- エラーメッセージがある場合は表示し、ない場合は表示しない。 -->
		<c:choose>
			<c:when test="${errorText != null}">
				<c:out value="${errorText}"/>
			</c:when>
			<c:when test="${errorText == null}">
				<br>
			</c:when>
		</c:choose>

		<form action="http://localhost:8080/ECSite/WriteReviewServlet" method="POST">
			<a>点数を選択</a>
			<select name="reviewScore">
			<%
				for(double scoreNo = 1.0; scoreNo <= 5.0; scoreNo+=0.5){
			%>
			<option value=<%=scoreNo%>><%=scoreNo%></option>
			<%
				}
			%>
			</select>
			<br>
			<a>レビューを入力</a>
			<br>
			<textarea name="reviewComment" rows=10 cols=25></textarea>
			<br>
			<input type="submit" name="btnWriteReview" value="送信"/>
		</form>

</body>
</html>