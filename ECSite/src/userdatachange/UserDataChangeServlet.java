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

@WebServlet("/UserDataChamgeServlet")
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
		String btnName = null;
		String newSetName = null;
		int loginUserNo = -1;

		//	セッションが継続していなかったときは処理を行わずにログイン画面へ
		if(request.getSession(false)==null) {
			System.out.println("セッションが開始していません。");
			//画面遷移
			RequestDispatcher req = request.getRequestDispatcher("jsp/login.jsp");
			req.forward(request, response);
		}

		//セッションから値を取得
		HttpSession session = request.getSession(true);
		UserBean loginUserBean = (UserBean) session.getAttribute("LoginUser");
		loginUserNo = loginUserBean.getUserNo();

		if(request.getParameter("btnUserName")!=null){
			System.out.println("名前を変更します");
			btnName = "btnUserName";
			newSetName = request.getParameter("newUserName");

		}else if(request.getParameter("btnUserID")!=null) {
			System.out.println("IDを変更します");
			btnName = "btnUserID";
			newSetName = request.getParameter("newUserID");

		}else if(request.getParameter("btnPassword")!=null) {
			System.out.println("パスワードを変更します");
			btnName = "btnPassword";
			newSetName = request.getParameter("newPassword");

		}else if(request.getParameter("btnEmailAddress")!=null) {
			System.out.println("メールアドレスを変更します");
			btnName = "btnEmailAddress";
			newSetName = request.getParameter("newEmailAddress");

		}else if(request.getParameter("btnPostalCode")!=null) {
			System.out.println("郵便番号を変更します");
			btnName = "btnPostalCode";
			newSetName = request.getParameter("newPostalCode");

		}else if(request.getParameter("btnAddress")!=null) {
			System.out.println("住所を変更します");
			btnName = "btnAddress";
			newSetName = request.getParameter("newAddress");

		}

		//インスタンスを生成し、処理を行った結果を格納する。
		UserDataChangeLogic newLogic = new UserDataChangeLogic();
		String resultText = newLogic.dataChangeLogic(btnName, newSetName, loginUserNo);

		UserJdbc userJdbc = new UserJdbc();
		UserBean userBean = new UserBean();

		//再読み込みのために取得
		userBean = userJdbc.getUserData(loginUserNo);
		request.setAttribute("userData", userBean);

		if(resultText.equals("情報の更新が完了しました。")) {
			request.setAttribute("resultText", resultText);
			RequestDispatcher req = request.getRequestDispatcher("jsp/UserDataChange.jsp");
			req.forward(request, response);
		} else {
			request.setAttribute("resultText", resultText);
			RequestDispatcher req = request.getRequestDispatcher("jsp/UserDataChange.jsp");
			req.forward(request, response);

		}

	}

}