package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.SignUpLogic;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//requestのエンコーディング
		request.setCharacterEncoding("UTF-8");

		if(request.getParameter("btnSignUpTransition")!=null) {
			//画面遷移
			System.out.println("新規登録画面に遷移します。");
			request.getRequestDispatcher("jsp/SignUp.jsp").forward(request, response);
			return;
		}

		//値の受け取りと格納
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String emailAddress = request.getParameter("emailAddress");
		String pCode = request.getParameter("postalCode");
		String address = request.getParameter("address");
		String userName = request.getParameter("userName");

		//フォームでの値の保持用
		request.setAttribute("id", userId);
		request.setAttribute("pass", password);
		request.setAttribute("mail", emailAddress);
		request.setAttribute("pcode", pCode);
		request.setAttribute("add", address);
		request.setAttribute("name", userName);

			SignUpLogic newlogic = new SignUpLogic();
			ArrayList<String> list = newlogic.checkSULogic(userId,password,emailAddress,pCode,address,userName);


			if(list.isEmpty()==false) {
				request.setAttribute("list", list);
				request.getRequestDispatcher("/jsp/SignUp.jsp").forward(request, response);
			}else {
				request.setAttribute("password", password);
				request.setAttribute("userId", userId);
				request.getRequestDispatcher("/LoginServlet").forward(request, response);
			}

	}

}