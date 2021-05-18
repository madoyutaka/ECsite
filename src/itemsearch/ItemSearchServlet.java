package itemsearch;

import java.io.IOException;
import java.util.ArrayList;

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
		//requestで送られてきたパラメータのエンコーディングを設定する
		request.setCharacterEncoding("UTF-8");
		//保存用
		String itemSearchWord = null;
		ArrayList<ItemBean> itemSearchList = new ArrayList<ItemBean>();

		//トップ画面から商品検索画面に遷移する際の処理
		if(request.getParameter("btnItemListTransition")!=null) {
				request.getRequestDispatcher("jsp/ItemSearch.jsp").forward(request, response);
				return;
			}

		//商品検索ボタンが押された場合の処理
		if(request.getParameter("btnItemSearchTransition")!=null) {
			//入力された文字を取得
			itemSearchWord = request.getParameter("itemSearchWord");

			//未入力の場合
			if(itemSearchWord.equals("")) {
				request.setAttribute("errorText", "検索ワードを入力してください。");
				request.getRequestDispatcher("jsp/ItemSearch.jsp").forward(request, response);
				return;
			}

			//検索する。
			ItemSearchLogic itemSearchLogic = new ItemSearchLogic();
			itemSearchList = itemSearchLogic.itemSearch(itemSearchWord);
			//値を渡す
			request.setAttribute("itemSearchList", itemSearchList);
			request.getRequestDispatcher("jsp/ItemSearch.jsp").forward(request, response);
			return;
		}

	}

}