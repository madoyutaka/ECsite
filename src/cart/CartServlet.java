package cart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartBean;
import bean.UserBean;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	//保存用
	int setItemNo = 1;

    public CartServlet() {

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//requestで送られてきたパラメータのエンコーディングを設定する
				request.setCharacterEncoding("UTF-8");
			//保存用
				RequestDispatcher req = null;
				HttpSession session = request.getSession();
				UserBean loginUserSession;
				@SuppressWarnings("unchecked")
				ArrayList<CartBean> loginItemSession= (ArrayList<CartBean>) session.getAttribute("CartItem");

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

				//カート画面へ遷移する。
				if(request.getParameter("btnCartTransition")!=null) {

					//値を渡す
					setItemNo = Integer.parseInt(request.getParameter("btnCartTransition"));
					//テスト用
					int setItemBuyCount = 1;

					//インスタンスを生成し、処理を行った結果を格納する。
					CartLogic newLogic = new CartLogic();
					loginItemSession = newLogic.cartIn(setItemNo, setItemBuyCount, loginItemSession);
					session.setAttribute("CartItem",loginItemSession);
					//表示用のデータを取得し、パラメータ名set
					request.setAttribute("itemCartData", newLogic.getCartItemData(loginItemSession));
					//画面遷移
					System.out.println("カート画面に遷移します。");
					req = request.getRequestDispatcher("jsp/Cart.jsp");
					req.forward(request, response);
					return;
				}

				//削除ボタンでカート画面へ遷移する。
				if(request.getParameter("btnCartRemoveTransition")!=null) {
					//インスタンスを生成し、処理を行った結果を格納する。
					CartLogic newLogic = new CartLogic();
					System.out.println(loginItemSession);
					loginItemSession = newLogic.remove(setItemNo, loginItemSession);
					session.setAttribute("CartItem",loginItemSession);
					//パラメータ名set
					request.setAttribute("itemCartData", newLogic.getCartItemData(loginItemSession));
					//画面遷移
					System.out.println("カート画面に遷移します。");
					req = request.getRequestDispatcher("jsp/Cart.jsp");
					req.forward(request, response);
					return;
				}

				//ヘッダー用
				if(request.getParameter("btnHeaderCartTransition")!=null) {
					//画面遷移
					request.setAttribute("itemCartData", loginItemSession);
					System.out.println("カート画面に遷移します。");
					req = request.getRequestDispatcher("jsp/Cart.jsp");
					req.forward(request, response);
					return;
				}

	}

}
