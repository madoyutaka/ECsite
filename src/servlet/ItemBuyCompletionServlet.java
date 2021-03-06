package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartBean;
import bean.ItemBean;
import bean.UserBean;
import logic.CartLogic;
import logic.ItemBuyLogic;

@WebServlet("/ItemBuyCompletionServlet")
public class ItemBuyCompletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//購入完了用のサーブレット
    public ItemBuyCompletionServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//requestで送られてきたパラメータのエンコーディングを設定する
		request.setCharacterEncoding("UTF-8");
		//保存用
		String itemBuyResultText;
		String itemBuyLogResultText;
		RequestDispatcher req = null;
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
			req = request.getRequestDispatcher("jsp/Login.jsp");
			req.forward(request, response);
			return;
		}
		ArrayList<CartBean> loginItemSession= (ArrayList<CartBean>) session.getAttribute("CartItem");
		ArrayList<ItemBean> ItemBeanList = newLogic.getCartItemData(loginItemSession);

		//購入処理を行う。
		if(request.getParameter("btnItemBuyCompletionTransition")!=null) {

			//インスタンスを生成し、処理を行った結果を格納する。
			ItemBuyLogic itemBuyLogic = new ItemBuyLogic();
			//購入処理
			itemBuyResultText = itemBuyLogic.itemBuyStockLogic(loginItemSession);

			//画面遷移if文
			if(itemBuyResultText.equals("購入処理が完了しました。")) {
				//合計金額が入ったHashMap。KeyはItem_no
				HashMap<Integer, Integer> itemTotalPriceHmap = newLogic.getCartTotalPriceHmap(loginItemSession, ItemBeanList);
				//履歴保存処理
				itemBuyLogResultText = itemBuyLogic.itemBuy(loginItemSession, itemTotalPriceHmap, loginUserNo);
				//表示用のデータを取得し、パラメータ名set
				request.setAttribute("itemBuyResultText", itemBuyResultText);
				request.setAttribute("itemBuyLogResultText", itemBuyLogResultText);
				//中身を空にする。
				session.setAttribute("CartItem",new ArrayList<CartBean>());
				System.out.println("購入処理完了画面に遷移します。");
				req = request.getRequestDispatcher("jsp/ItemBuyCompletion.jsp");
				req.forward(request, response);
				return;
			}else{
				//中身変更無し
				session.setAttribute("CartItem",loginItemSession);
				//表示用のデータを取得し、パラメータ名set
				request.setAttribute("itemBuyResultText", itemBuyResultText);
				System.out.println("購入処理完了画面に遷移します。");
				req = request.getRequestDispatcher("jsp/ItemBuyCompletion.jsp");
				req.forward(request, response);
				return;
			}
		}

	}

}
