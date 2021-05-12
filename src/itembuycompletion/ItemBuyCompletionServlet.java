package itembuycompletion;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ItemBean;
import bean.UserBean;

@WebServlet("/ItemBuyCompletionServlet")
public class ItemBuyCompletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//購入完了用のサーブレット
    public ItemBuyCompletionServlet() {

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
				HttpSession session = request.getSession();
				UserBean loginUserSession;
				ItemBean itemBean = new ItemBean();
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

				//購入完了画面に遷移する。
				if(request.getParameter("btnItemBuyCompletionTransition")!=null) {
					//変更するかも
					request.setAttribute("itemData", itemBean);
					//画面遷移
					System.out.println("購入完了画面に遷移します。");
					req = request.getRequestDispatcher("jsp/ItemBuyCompletion.jsp");
					req.forward(request, response);
					return;
				}

				//カートの中身を空にする

				//購入履歴へのデータ受け渡し
	}

}
