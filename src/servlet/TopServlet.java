package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ItemBean;
import bean.ReviewBean;
import bean.UserBean;
import jdbc.ItemJdbc;
import jdbc.ReviewJdbc;
import logic.ItemDetailLogic;

@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {
	//トップの画面での処理を制御するサーブレット
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ボタンの文字変更処理
		HttpSession session = request.getSession(false);
		UserBean loginUserSession;
		try {
			loginUserSession = (UserBean)session.getAttribute("loginUser");
		}catch(Exception ex) {
			loginUserSession = null;
		}
		if(loginUserSession == null) {
			System.out.println("ログインボタンを表示します");
			request.setAttribute("LoginButton", "LoginButton");
		}

		ItemJdbc itemJdbc = new ItemJdbc();
		ItemBean randomItemBean = itemJdbc.randomItem();
		//レビュー一覧
		ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();
		ReviewJdbc reviewJdbc = new ReviewJdbc();
		reviewList = reviewJdbc.getReviewData(randomItemBean.getItemNo());
		//レビューの平均点を取得
		ItemDetailLogic itemDetailLogic = new ItemDetailLogic();
		double reviewAverage = itemDetailLogic.getReviewAverage(reviewList, randomItemBean.getItemNo());
		//値を渡す
		request.setAttribute("randomItem", randomItemBean);
		request.setAttribute("reviewAverage", reviewAverage);
		request.getRequestDispatcher("jsp/Top.jsp").forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//requestで送られてきたパラメータのエンコーディングを設定する
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		UserBean loginUserSession;
		try {
			loginUserSession = (UserBean)session.getAttribute("loginUser");
		}catch(Exception ex) {
			loginUserSession = null;
		}

		//	トップボタンを押したとき
			if(request.getParameter("btnTopTransition")!=null) {

				if(loginUserSession == null) {
					System.out.println("ログインボタンを表示します");
					request.setAttribute("LoginButton", "LoginButton");
				}

				ItemJdbc itemJdbc = new ItemJdbc();
				ItemBean randomItemBean = itemJdbc.randomItem();
				//レビュー一覧
				ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();
				ReviewJdbc reviewJdbc = new ReviewJdbc();
				reviewList = reviewJdbc.getReviewData(randomItemBean.getItemNo());
				//レビューの平均点を取得
				ItemDetailLogic itemDetailLogic = new ItemDetailLogic();
				double reviewAverage = itemDetailLogic.getReviewAverage(reviewList, randomItemBean.getItemNo());
				//値を渡す
				request.setAttribute("randomItem", randomItemBean);
				request.setAttribute("reviewAverage", reviewAverage);
				request.getRequestDispatcher("jsp/Top.jsp").forward(request, response);
				return;
			}

		//	ログインボタンを押したとき
		if(request.getParameter("btnLoginTransition")!=null) {
			//セッションが継続している場合はマイページへ
				if(loginUserSession == null) {
				//セッションがnullの場合
				request.getRequestDispatcher("jsp/Login.jsp").forward(request, response);
				return;
			}
			request.getRequestDispatcher("jsp/MyPage.jsp").forward(request, response);
			return;
		}

	}

}