package favoritelist;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.FavoriteBean;
import bean.UserBean;


@WebServlet("/FavoriteListServlet")
public class FavoriteListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//MIMタイプとエンコーディング(文字コード)の設定をする
				response.setContentType("text/html;charset=UTF-8");
			//requestで送られてきたパラメータのエンコーディングを設定する
				request.setCharacterEncoding("UTF-8");

				//保存用
				RequestDispatcher req = null;
				int loginUserNo;
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

				//セッションから値を取得
				loginUserNo = loginUserBean.getUserNo();

				//インスタンスを生成し、処理を行った結果を格納する。
				FavoriteListLogic  faveLogic = new FavoriteListLogic();
				ArrayList<FavoriteBean> faveList = faveLogic.favoriteGetLogic(loginUserNo);


				//お気に入りリスト画面に遷移する。
				if(request.getParameter("btnFavoriteListTransition")!=null) {
					request.setAttribute("loginUserFaves", faveList);


					//画面遷移
					System.out.println("お気に入りリスト画面に遷移します。");
					req = request.getRequestDispatcher("jsp/FavoriteList.jsp");
					req.forward(request, response);
					return;
				}

				if(request.getParameter("btnFavoriteListAddTransition")!=null) {

					System.out.println("お気に入りリストに商品を追加しました。");
					request.setAttribute("message", "お気に入りリストに商品を追加しました。");
					req = request.getRequestDispatcher("jsp/FavoriteCompletionList.jsp");
					req.forward(request, response);
					return;
				}

				if(request.getParameter("btnMyPageTransition")!=null) {

					System.out.println("マイページへ遷移します");
					req = request.getRequestDispatcher("jsp/MyPage.jsp");
					req.forward(request, response);
					return;
				}





	}
}
