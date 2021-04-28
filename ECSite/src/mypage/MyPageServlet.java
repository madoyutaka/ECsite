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
import jdbc.UserJdbc;

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
		int loginUserNo;
		UserJdbc userJdbc = new UserJdbc();
		UserBean userBean = new UserBean();
		RequestDispatcher req = null;

	//セッションが継続していなかったときは処理を行わずにログイン画面へ
		if(request.getSession(false)==null) {
			System.out.println("セッションが開始していません。");
			//画面遷移
			req = request.getRequestDispatcher("jsp/login.jsp");
			req.forward(request, response);
		}

		//セッションから値を取得
		HttpSession session = request.getSession(true);
		UserBean loginUserBean = (UserBean) session.getAttribute("LoginUser");
		loginUserNo = loginUserBean.getUserNo();

		//登録情報変更画面に遷移する。
		if(request.getParameter("btnUserDataChange")!=null) {
			userBean = userJdbc.getUserData(loginUserNo);
			request.setAttribute("userData", userBean);

			//画面遷移
			System.out.println("登録情報変更画面に遷移します。");
			req = request.getRequestDispatcher("jsp/UserDataChange.jsp");
			req.forward(request, response);

		//お気に入りリストに遷移する。
		}else if(request.getParameter("btnFavoriteList")!=null) {
			//画面遷移、仮
			System.out.println("お気に入りリストに遷移します。");
			req = request.getRequestDispatcher("jsp/Top.jsp");
			req.forward(request, response);

		//購入履歴に遷移する
		}else if(request.getParameter("btnItemBuyLog")!=null) {
			//画面遷移、仮
			System.out.println("購入履歴に遷移します。");
			req = request.getRequestDispatcher("jsp/Top.jsp");
			req.forward(request, response);

		//ログアウトを行い、トップ画面に遷移する。
		}else if(request.getParameter("btnLogOut")!=null) {
			//画面遷移、仮
			System.out.println("ログアウトを行います。");
			session.invalidate();
			System.out.println("ログアウトが完了しました、トップ画面に遷移します。");
			System.out.println("現在のセッション："+request.getSession(false));
			req = request.getRequestDispatcher("jsp/Top.jsp");
			req.forward(request, response);
		}
	}

}
