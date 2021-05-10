package userdatachange;

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

@WebServlet("/UserDataChangeServlet")
public class UserDataChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserDataChangeServlet() {
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
		String btnName = null;
		String setNewData = null;
		int loginUserNo = -1;
		HttpSession session = request.getSession();
		UserBean loginUserSession;
		try {
			loginUserSession = (UserBean)session.getAttribute("loginUser");
		}catch(Exception ex) {
			loginUserSession = null;
		}
		//	セッションが継続していなかったときは処理を行わずにログイン画面へ
		if(loginUserSession == null) {
			System.out.println("セッションが開始していません。");
			//画面遷移
			req = request.getRequestDispatcher("jsp/Login.jsp");
			req.forward(request, response);
			return;
		}

		//セッションから値を取得
		loginUserNo = loginUserSession.getUserNo();
		UserJdbc userJdbc = new UserJdbc();
		UserBean userBean = userJdbc.getUserData(loginUserNo);

		//登録情報変更画面に遷移する。
		if(request.getParameter("btnUserDataChangeTransition")!=null) {
			request.setAttribute("loginUserData", userBean);
			//画面遷移
			System.out.println("登録情報変更画面に遷移します。");
			req = request.getRequestDispatcher("jsp/UserDataChange.jsp");
			req.forward(request, response);
			return;
		}

		if(request.getParameter("btnUserName")!=null){
			btnName = "btnUserName";
			setNewData = request.getParameter("newUserName");

		}else if(request.getParameter("btnUserID")!=null) {
			btnName = "btnUserID";
			setNewData = request.getParameter("newUserID");

		}else if(request.getParameter("btnPassword")!=null) {
			btnName = "btnPassword";
			setNewData = request.getParameter("newPassword");

		}else if(request.getParameter("btnEmailAddress")!=null) {
			btnName = "btnEmailAddress";
			setNewData = request.getParameter("newEmailAddress");

		}else if(request.getParameter("btnPostalCode")!=null) {
			btnName = "btnPostalCode";
			setNewData = request.getParameter("newPostalCode");

		}else if(request.getParameter("btnAddress")!=null) {
			btnName = "btnAddress";
			setNewData = request.getParameter("newAddress");

		}

		//インスタンスを生成し、処理を行った結果を格納する。
		UserDataChangeLogic newLogic = new UserDataChangeLogic();
		String resultText = newLogic.dataChangeLogic(btnName, setNewData, loginUserNo);

		//再読み込みのために取得
		userBean = userJdbc.getUserData(loginUserNo);
		request.setAttribute("loginUserData", userBean);

			request.setAttribute("resultText", resultText);
			req = request.getRequestDispatcher("jsp/UserDataChange.jsp");
			req.forward(request, response);
			return;

	}

}
