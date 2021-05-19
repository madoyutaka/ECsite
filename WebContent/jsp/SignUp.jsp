<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ page import="java.util.ArrayList"%>
     <%@ page isELIgnored ="false" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>新規登録</title>
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

	<div class="page_layout signup">
		<h1 class="page_ttl signup_ttl">新規会員登録</h1>

			<c:choose>
			<c:when test="${list != null}">
				<c:forEach items="${list}" var = "error">
					<h1 class="error_text error_signup"><c:out value="${error}" /></h1>
				</c:forEach>
			</c:when>
			<c:when test="${list == null}">
			<br>
			</c:when>
		</c:choose>
		<br>

	<div class="page_container">
		<form action="http://localhost:8080/ECSite/SignUpServlet"  method="post">
		<!-- inputごとに仕分ける -->
          <div class="signup_container">
				<p class="signup_text">氏名(20文字以内)</p>
				<input type="text" name="userName"  value="${name}"  placeholder="山田 太郎">
		  </div>

          <div class="signup_container">
				<p class="signup_text">ユーザーID(20文字以内)</p>
				<input type="text" name="userId" value="${id}"  placeholder="tarou">
		  </div>

          <div class="signup_container">
				<p class="signup_text">パスワード(20文字以内)</p>
                <input type="password" name="password"  value="${pass}"  placeholder="tarou12345678">
          </div>

           <div class="signup_container">
				<p class="signup_text">メールアドレス(40文字以内)</p>
				<input type="text" name="emailAddress" value="${mail}"  placeholder="tarou@mail">
           </div>

           <div class="signup_container">
				<p class="signup_text">郵便番号(ハイフンなし半角数字7桁)</p>
				<input type="text" name="postalCode"  value="${pcode}" size="5"  placeholder="1112222">
           </div>

           <div class="signup_container">
				<p class="signup_text">住所(100文字以内)</p>
                <input type="text" name="address"  size="70"  value="${add}"  placeholder="東京都○○区○○1丁目1番1号">
           </div>

           <div class="signup_btn_cover">
		         <input class="btn signup_btn" type="submit" value="登録">
		   </div>
		</form>

		<form class="signup_back" action = "http://localhost:8080/ECSite/LoginServlet" method = "POST">
			<input class="btn signup_back_btn" type = "submit" name="btnLoginTransition" value = "すでに登録済みの方はこちら">
		</form>
	</div>


    </div>

	<div class="footer_wrapper">
	    <footer>
			<p class="footer_text">2021/05/14/ECSite</p>
		</footer>
	</div>

	</body>
</html>