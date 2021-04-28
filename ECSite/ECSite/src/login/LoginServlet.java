package login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import jdbc.UserJdbc;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	//ログインの処理

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//requestのエンコーディング
		request.setCharacterEncoding("UTF-8");

		//それぞれPOSTされたものを変数に格納
		String UserId = request.getParameter("user_id");
		String UserPass = request.getParameter("password");

		//インスタンス化
		UserBean user = new UserBean();
		user.setLoginId(UserId);
		user.setLoginPass(UserPass);

		//インスタンス化
		UserJdbc jdbc = new UserJdbc();

		UserBean returnUser  = jdbc.LoginAccount(user);


	    if(returnUser != null) {

	    	// セッションにアカウント情報
            HttpSession session = request.getSession();
            session.setAttribute("LoginUser", returnUser);
            System.out.println("session start");

				request.setAttribute("LoginUser", returnUser);//パラメータ名はLoginUser
				//Mypage.jspに画面遷移
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/MyPage.jsp");
				rd.forward(request, response);
			}else {
				request.setAttribute("Empty", "Not");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/Login.jsp");
				rd.forward(request, response);
			}
	}

}
