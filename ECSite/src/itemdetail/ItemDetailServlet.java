package itemdetail;

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

@WebServlet("/ItemDetailServlet")
public class ItemDetailServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//MIMタイプとエンコーディング(文字コード)の設定をする
				response.setContentType("text/html;charset=UTF-8");
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

				//レビュー画面に遷移する
				if(request.getParameter("btnWriteReview")!=null) {
					request.setAttribute("itemData", itemBean);
					//ボタン押した時にセッションを確認
					if(loginUserSession == null) {
						//セッションがnullの場合
						req = request.getRequestDispatcher("jsp/Login.jsp");
						req.forward(request, response);
						return;
					}
					//画面遷移
					request.setAttribute("itemData", itemBean);
					System.out.println("レビュー画面に遷移します。");
					req = request.getRequestDispatcher("jsp/WriteReview.jsp");
					req.forward(request, response);
					return;
				}

				//商品詳細画面に遷移
				if(request.getParameter("btnItemDetailTransition")!=null) {
					System.out.println("選択された商品のitemNo："+request.getParameter("btnItemDetailTransition"));
					//値を渡す
					int Item = Integer.parseInt(request.getParameter("btnItemDetailTransition"));
					//インスタンスを生成し、処理を行った結果を格納する。
					ItemDetailLogic newLogic = new ItemDetailLogic();
					itemBean = newLogic.detailLogic(Item);
					//値を渡す
					request.setAttribute("itemData", itemBean);
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
