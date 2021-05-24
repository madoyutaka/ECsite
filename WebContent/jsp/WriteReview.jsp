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
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/reset.css">
<link rel="stylesheet"href="${pageContext.request.contextPath}/css/ECSiteLayout.css">
</head>
<body>

  <%@include file= "Header.jsp" %>

    <div class="page_layout review_layout">
			<!-- エラーメッセージがある場合は表示し、ない場合は表示しない。 -->
			<c:choose>
				<c:when test="${errorText != null}">
					<h1 class="error_text"><c:out value="${errorText}"/></h1>
				</c:when>
				<c:when test="${errorText == null}">
					<br>
				</c:when>
			</c:choose>

       		<div class="review_container">
				<form action="http://localhost:8080/ECSite/WriteReviewServlet" method="POST">
					<p class="review_text">点数を選択</p>

					<select class="review_select" name="reviewScore">
					<%
						for(double scoreNo = 1.0; scoreNo <= 5.0; scoreNo+=1.0){
					%>
					<option value=<%=scoreNo%>><%=scoreNo%></option>
					<%
						}
					%>
					</select>
					<p class="review_text">レビューを書き込む</p>

					<div class="review_flex">
						<textarea class="review_area" name="reviewComment" rows=10 cols=25></textarea>
						<input class="btn review_btn" type="submit" name="btnWriteReview" value="送信"/>
					</div>

				</form>
			</div>
	</div>
<%@include file= "Footer.jsp" %>

</body>
</html>