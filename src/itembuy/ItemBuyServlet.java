package itembuy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartBean;
import bean.ItemBean;
import bean.UserBean;
import cart.CartLogic;
import jdbc.UserJdbc;

@WebServlet("/ItemBuyServlet")
public class ItemBuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//購入確認用のサーブレット
    public ItemBuyServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//requestで送られてきたパラメータのエンコーディングを設定する
				request.setCharacterEncoding("UTF-8");
			//保存用
				CartLogic newLogic = new CartLogic();
				HttpSession session = request.getSession(false);
				UserBean loginUserBean = (UserBean) session.getAttribute("loginUser");
				int loginUserNo = loginUserBean.getUserNo();
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
				loginUserNo = loginUserSession.getUserNo();
				UserJdbc userJdbc = new UserJdbc();
				UserBean userBean = userJdbc.getUserData(loginUserNo);

				ArrayList<CartBean> loginItemSession= (ArrayList<CartBean>) session.getAttribute("CartItem");
				ArrayList<ItemBean> ItemBeanList = newLogic.getCartItemData(loginItemSession);



				//購入確認画面へ遷移する
				if(request.getParameter("btnItemBuyTransition")!=null) {
					//表示用のデータを取得し、パラメータ名set
					userBean = userJdbc.getUserData(loginUserNo);
					request.setAttribute("loginUserData", userBean);
					request.setAttribute("cartData", loginItemSession);
					request.setAttribute("cartItemData", ItemBeanList);
					request.getRequestDispatcher("jsp/ItemBuy.jsp").forward(request, response);
					return;
				}

	}

}
