package itembuylog;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ItemBuyLogBean;
import bean.UserBean;

@WebServlet("/ItemBuyLogServlet")
public class ItemBuyLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ItemBuyLogServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//requestで送られてきたパラメータのエンコーディングを設定する
		request.setCharacterEncoding("UTF-8");
		//保存用
		RequestDispatcher req = null;
		int loginUserNo;
		HttpSession session = request.getSession(false);
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
		loginUserNo = loginUserSession.getUserNo();

		//インスタンスを生成し、処理を行った結果を格納する。
		ItemBuyLogLogic itemBuyLogLogic = new ItemBuyLogLogic();
		ArrayList<ItemBuyLogBean> itemBuyLogList = itemBuyLogLogic.itemBuyLogGetLogic(loginUserNo);

		//選択されたページ番号
		int selectNo = 0;
		//購入履歴画面に遷移する。
		if(request.getParameter("selectItemBuyLogPageNo")!=null) {
			selectNo = Integer.parseInt(request.getParameter("selectItemBuyLogPageNo"));
			//お気に入り数が1以上の場合
			if(itemBuyLogList.size() >= 1) {
				//ページ数を渡す
				int totalPageNo = itemBuyLogLogic.getItemBuyLogListTotalPageNo(itemBuyLogList);
				request.setAttribute("itemBuyLogTotalPageNo", totalPageNo);
				request.setAttribute("itemBuyLogPageNo", selectNo);
				//指定されたページが存在しない場合
				if(totalPageNo < selectNo) {
					request.setAttribute("errorText", "お探しのページは見つかりませんでした。");
					req = request.getRequestDispatcher("jsp/ItemBuyLog.jsp");
					req.forward(request, response);
					return;
				}
				//表示するためのリストを渡す
				request.setAttribute("loginUserItemBuyLog", itemBuyLogLogic.getShowList(itemBuyLogList, selectNo));
			}else {
				//表示するためのリストを渡す
				request.setAttribute("loginUserItemBuyLog", itemBuyLogList);
			}
			//画面遷移
			System.out.println("購入履歴画面に遷移します。");
			request.getRequestDispatcher("jsp/ItemBuyLog.jsp").forward(request, response);
			return;
		}

		//購入履歴画面に遷移する。
		if(request.getParameter("btnItemBuyLogTransition")!=null) {
			request.setAttribute("loginUserItemBuyLog", itemBuyLogList);
			//画面遷移
			System.out.println("購入履歴更画面に遷移します。");
			req = request.getRequestDispatcher("jsp/ItemBuyLog.jsp");
			req.forward(request, response);
			return;
		}


	}

}
