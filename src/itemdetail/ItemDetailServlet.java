package itemdetail;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ItemBean;
import bean.ReviewBean;
import bean.UserBean;
import jdbc.ReviewJdbc;

@WebServlet("/ItemDetailServlet")
public class ItemDetailServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//requestで送られてきたパラメータのエンコーディングを設定する
				request.setCharacterEncoding("UTF-8");
			//保存用
				RequestDispatcher req = null;
				int ItemNo= 0;
				System.out.println("確認中");
				ItemBean itemBean = new ItemBean();

				//セッション関連
				HttpSession session = request.getSession();
				UserBean loginUserSession;
				try {
					loginUserSession = (UserBean)session.getAttribute("loginUser");
				}catch(Exception ex) {
					loginUserSession = null;
				}

				//カート画面に遷移する。
				if(request.getParameter("btnCartIn")!=null) {
					//ボタン押した時にセッションを確認
					if(loginUserSession == null) {
						//セッションがnullの場合
						req = request.getRequestDispatcher("jsp/Login.jsp");
						req.forward(request, response);
						return;
					}

					//画面遷移
					request.setAttribute("itemData", itemBean);
					System.out.println("カート画面に遷移します。");
					req = request.getRequestDispatcher("jsp/Cart.jsp");
					req.forward(request, response);
					return;
				}

				//お気に入り完了画面に遷移する。
				if(request.getParameter("btnCartIn")!=null) {
					//ボタン押した時にセッションを確認
					if(loginUserSession == null) {
						//セッションがnullの場合
						request.setAttribute("itemData", itemBean);
						req = request.getRequestDispatcher("jsp/Login.jsp");
						req.forward(request, response);
						return;
					}
					//画面遷移
					System.out.println("お気に入り完了画面に遷移します。");
					request.setAttribute("itemData", itemBean);
					req = request.getRequestDispatcher("jsp/Cart.jsp");
					req.forward(request, response);
					return;
				}

				//商品詳細画面に遷移
				if(request.getParameter("btnItemDetailTransition")!=null) {
					System.out.println("選択された商品のitemNo："+request.getParameter("btnItemDetailTransition"));
					//値を渡す
					int item = Integer.parseInt(request.getParameter("btnItemDetailTransition"));
					//インスタンスを生成し、処理を行った結果を格納する。
					//商品詳細
					ItemDetailLogic newLogic = new ItemDetailLogic();
					itemBean = newLogic.detailLogic(item);
					//レビュー一覧
					ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();
					ReviewJdbc reviewJdbc = new ReviewJdbc();
					reviewList = reviewJdbc.getReviewData(item);
					//レビューの平均点を取得
					double reviewAverage = newLogic.getReviewAverage(reviewList, item);
					//ページ数取得
					int totalPageNo = newLogic.getReviewTotalPageNo(reviewList);
					int selectPageNo;
					//表示用のレビュー一覧、新しいものから5件表示
					ArrayList<ReviewBean> showReviewList = new ArrayList<ReviewBean>();
					if(reviewList.size() >= 1) {
							selectPageNo = Integer.parseInt(request.getParameter("selectReviewPageNo"));
							showReviewList = newLogic.getShowReviewList(reviewList, selectPageNo);
					}else {
						selectPageNo = 1;
						showReviewList = reviewList;
					}
					//値を渡す
					request.setAttribute("itemData", itemBean);
					request.setAttribute("reviewList", showReviewList);
					request.setAttribute("totalPageNo", totalPageNo);
					request.setAttribute("selectReviewPageNo", selectPageNo);
					request.setAttribute("reviewAverage", reviewAverage);
					req = request.getRequestDispatcher("/jsp/ItemDetail.jsp");
					req.forward(request, response);
					return;
				}


				//インスタンスを生成し、処理を行った結果を格納する。
				ItemDetailLogic newLogic = new ItemDetailLogic();
				itemBean = newLogic.detailLogic(ItemNo);

				request.setAttribute("itemData", itemBean);

				req = request.getRequestDispatcher("jsp/ItemDetail.jsp");
				req.forward(request, response);
				return;

	}

}
