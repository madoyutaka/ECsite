package signup;

import java.io.IOException;

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

		//MIMEタイプとエンコーディング(文字コード)の設定
		response.setContentType("text/html;charset=UTF-8");
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

		//入力チェック
		if(userId=="" || password=="" || emailAddress=="" || pCode=="" || address=="" || userName=="") {
			request.setAttribute("message", "未入力の項目があります");
			request.getRequestDispatcher("/jsp/SignUp.jsp").forward(request, response);
			return;
		}else {

			if(userId.length()>=20||password.length()>=20||emailAddress.length()>=40||address.length()>=100||userName.length()>=20){
				request.setAttribute("message", "文字数オーバーです");
				request.getRequestDispatcher("/jsp/SignUp.jsp").forward(request, response);
				return;

			}else if(userId.matches("^[^\\x01-\\x7E]")||password.matches("^[^\\x01-\\x7E]")||emailAddress.matches("^[^\\x01-\\x7E]")){
				request.setAttribute("message", "ID,メールアドレス、パスワードは半角英数字のみ入力してください");
				request.getRequestDispatcher("/jsp/SignUp.jsp").forward(request, response);
				return;
			}else if(pCode.length()!=7||pCode.matches("^[^\\x01-\\x7E]")||pCode.matches("^[a-zA-Z]+$")){
				request.setAttribute("message", "郵便番号はハイフンなし半角数字7桁で入力してください");
				request.getRequestDispatcher("/jsp/SignUp.jsp").forward(request, response);
				return;
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
	}











