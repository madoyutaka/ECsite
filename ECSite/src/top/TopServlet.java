package top;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;

@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {
	//トップの画面での処理を制御するサーブレット
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher req = null;
		req = request.getRequestDispatcher("jsp/Top.jsp");
		req.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher req = null;
		HttpSession session = request.getSession(false);
		UserBean loginUserSession;
		try {
			loginUserSession = (UserBean)session.getAttribute("loginUser");
		}catch(Exception ex) {
			loginUserSession = null;
		}

		//	ログインボタンを押したとき
		if(request.getParameter("btnLogin")!=null) {
			//セッションが継続している場合はマイページへ
				if(loginUserSession == null) {
				//セッションがnullの場合
				req = request.getRequestDispatcher("jsp/Login.jsp");
				req.forward(request, response);
				return;
			}
			req = request.getRequestDispatcher("jsp/MyPage.jsp");
			req.forward(request, response);
			return;
		}

	}

}
