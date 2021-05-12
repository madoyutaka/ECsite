package itembuy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;

@WebServlet("/ItemBuyServlet")
public class ItemBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//購入確認用のサーブレット
    public ItemBuyServlet() {

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
				UserBean loginUserBean = (UserBean) session.getAttribute("loginUser");
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



				//インスタンスを生成し、処理を行った結果を格納する。

	}

}
