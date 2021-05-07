package mypage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;

@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//MIMタイプとエンコーディング(文字コード)の設定をする
		response.setContentType("text/html;charset=UTF-8");
	//requestで送られてきたパラメータのエンコーディングを設定する
		request.setCharacterEncoding("UTF-8");
	//保存用
		RequestDispatcher req = null;
		HttpSession session = request.getSession(false);
		UserBean loginUserSession;
		try {
			loginUserSession = (UserBean)session.getAttribute("loginUser");
		}catch(Exception ex) {
			loginUserSession = null;
		}

	//セッションが継続していなかったときは処理を行わずにログイン画面へ
		if(loginUserSession == null) {
			System.out.println("セッションが開始していません。");
			//画面遷移
			req = request.getRequestDispatcher("jsp/Login.jsp");
			req.forward(request, response);
			return;
		}


		//お気に入りリストに遷移する。
		if(request.getParameter("btnFavoriteListTransition")!=null) {
			//画面遷移、仮
			System.out.println("お気に入りリストに遷移します。");
			req = request.getRequestDispatcher("jsp/Top.jsp");
			req.forward(request, response);
			return;
		}

	}

}


