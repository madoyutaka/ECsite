package signup;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import jdbc.UserJdbc;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//requestのエンコーディング
		request.setCharacterEncoding("UTF-8");

		if(request.getParameter("btnSignUpTransition")!=null) {
			//画面遷移
			System.out.println("新規登録画面に遷移します。");
			RequestDispatcher req = request.getRequestDispatcher("jsp/SignUp.jsp");
			req.forward(request, response);
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
		}

		UserBean ub = new UserBean();
		//pCodeをint型に変換しそれぞれbeanにセット
		int postalCode = Integer.parseInt(pCode);
		ub.setUserId(userId);
		ub.setPassword(password);
		ub.setEmailAddress(emailAddress);
		ub.setPostalCode(postalCode);
		ub.setAddress(address);
		ub.setUserName(userName);

		UserJdbc uj = new UserJdbc();
		uj.insert(ub);


			//画面遷移
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/MyPage.jsp");
			rd.forward(request, response);
		}
}












