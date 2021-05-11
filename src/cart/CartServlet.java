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

import bean.ItemBean;
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
			//MIMタイプとエンコーディング(文字コード)の設定をする
				response.setContentType("text/html;charset=UTF-8");
			//requestで送られてきたパラメータのエンコーディングを設定する
				request.setCharacterEncoding("UTF-8");
			//保存用
				RequestDispatcher req = null;
				HttpSession session = request.getSession();
				UserBean loginUserSession;
				@SuppressWarnings("unchecked")
				ArrayList<ItemBean> loginItemSession= (ArrayList<ItemBean>) session.getAttribute("CartItem");

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

					//インスタンスを生成し、処理を行った結果を格納する。
					CartLogic newLogic = new CartLogic();
					loginItemSession = newLogic.cartIn(setItemNo, loginItemSession);
					//パラメータ名set
					request.setAttribute("itemCartData", loginItemSession);
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
					//パラメータ名set
					request.setAttribute("itemCartData", loginItemSession);
					//画面遷移
					System.out.println("カート画面に遷移します。");
					req = request.getRequestDispatcher("jsp/Cart.jsp");
					req.forward(request, response);
					return;
				}

	}

}
