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

		//HttpSession session = request.getSession(false);


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

		//入力チェック
			//エラー用リスト
		ArrayList<String> list = new ArrayList<String>();

		if(userId=="" || password=="" || emailAddress=="" || pCode=="" || address=="" || userName=="") {
			list.add("!入力されていない項目があります");
		}else {
		if(userId.length()>=20||password.length()>=20||emailAddress.length()>=40||address.length()>=100||userName.length()>=20){
			list.add("!文字数オーバーの項目があります");
		}
		if(!emailAddress.matches("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")) {
			list.add("!メールアドレスは正しい方法で入力してください");
		}
		if(!userId.matches("^[a-zA-Z0-9]+$")||!password.matches("^[a-zA-Z0-9]+$")){
			list.add("!IDとパスワードは半角英数字で入力してください");
		}
		if(pCode.length()!=7||!pCode.matches("^[0-9]+$")){
			list.add("!郵便番号はハイフンなし半角数字7文字で入力してください");
		}
		}

		if(list.isEmpty()==false) {
			request.setAttribute("list", list);
			request.getRequestDispatcher("/jsp/SignUp.jsp").forward(request, response);
		}else {
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
}












