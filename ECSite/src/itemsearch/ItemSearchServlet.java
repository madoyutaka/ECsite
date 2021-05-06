package itemsearch;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ItemBean;


@WebServlet("/ItemSearchServlet")
public class ItemSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ItemSearchServlet() {
        super();
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
		String itemSearchWord = null;
		ArrayList<ItemBean> itemSearchList = new ArrayList<ItemBean>();

		//トップ画面から商品検索画面に遷移する際の処理
		if(request.getParameter("btnItemListTransition")!=null) {
				req = request.getRequestDispatcher("jsp/ItemSearch.jsp");
				req.forward(request, response);
				return;
			}

		//商品検索ボタンが押された場合の処理
		if(request.getParameter("btnItemSearch")!=null) {
			//入力された文字を取得
			itemSearchWord = request.getParameter("itemSearchWord");

			//未入力の場合
			if(itemSearchWord.equals("")) {
				request.setAttribute("errorText", "検索ワードを入力してください。");
				req = request.getRequestDispatcher("jsp/ItemSearch.jsp");
				req.forward(request, response);
				return;
			}

			//検索する。
			ItemSearchLogic itemSearchLogic = new ItemSearchLogic();
			itemSearchList = itemSearchLogic.itemSearch(itemSearchWord);
			//値を渡す
			request.setAttribute("itemSearchList", itemSearchList);
			req = request.getRequestDispatcher("jsp/ItemSearch.jsp");
			req.forward(request, response);
			return;
		}

		//商品詳細画面に遷移
		if(request.getParameter("btnItemDetailTransition")!=null) {
			System.out.println("選択された商品のitemNo："+request.getParameter("btnItemDetailTransition"));
			//値を渡す
			request.setAttribute("itemNo", request.getParameter("btnItemDetailTransition"));
			req = request.getRequestDispatcher("jsp/ItemDetail.jsp");
			req.forward(request, response);
			return;
		}
	}

}
