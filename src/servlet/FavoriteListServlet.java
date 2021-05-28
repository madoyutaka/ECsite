package servlet;

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
import logic.FavoriteListLogic;


@WebServlet("/FavoriteListServlet")
public class FavoriteListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
					request.getRequestDispatcher("jsp/Login.jsp").forward(request, response);
					return;
				}

				//セッションから値を取得
				loginUserNo = loginUserBean.getUserNo();
				//インスタンスを生成し、処理を行った結果を格納する。
				FavoriteListLogic  faveLogic = new FavoriteListLogic();
				ArrayList<FavoriteBean> faveList = faveLogic.favoriteGetLogic(loginUserNo);
				//選択されたページ番号
				int selectNo = 0;
				//お気に入りリスト画面に遷移する。
				if(request.getParameter("selectFavoriteListPageNo")!=null) {
					selectNo = Integer.parseInt(request.getParameter("selectFavoriteListPageNo"));
					//お気に入り数が1以上の場合
					if(faveList.size() >= 1) {
						//ページ数を渡す
						int totalPageNo = faveLogic.getFavoriteListTotalPageNo(faveList);
						request.setAttribute("favoriteListTotalPageNo", totalPageNo);
						request.setAttribute("favoriteListPageNo", selectNo);
						//指定されたページが存在しない場合
						if(totalPageNo < selectNo) {
							request.setAttribute("errorText", "お探しのページは見つかりませんでした。");
							req = request.getRequestDispatcher("jsp/FavoriteList.jsp");
							req.forward(request, response);
							return;
						}
						//表示するためのリストを渡す
						request.setAttribute("loginUserFaves", faveLogic.getShowList(faveList, selectNo));
					}else {
						//表示するためのリストを渡す
						request.setAttribute("loginUserFaves", faveList);
					}
					//画面遷移
					System.out.println("お気に入りリスト画面に遷移します。");
					request.getRequestDispatcher("jsp/FavoriteList.jsp").forward(request, response);
					return;
				}



				if(request.getParameter("btnMyPageTransition")!=null) {

					System.out.println("マイページへ遷移します");
					request.getRequestDispatcher("jsp/MyPage.jsp").forward(request, response);
					return;
				}





	}
}
