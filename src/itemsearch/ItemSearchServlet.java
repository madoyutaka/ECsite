package itemsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ItemBean;
import itemdetail.ItemDetailLogic;


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
		RequestDispatcher req = null;
		String itemSearchWord = null;
		ArrayList<ItemBean> itemSearchList = new ArrayList<ItemBean>();
		ItemDetailLogic itemDetailLogic = new ItemDetailLogic();
		//カテゴリ表示用HashMap
		HashMap<Integer, String> categoryHmap = new HashMap<Integer, String>();
		categoryHmap.put(1, "椅子");
		categoryHmap.put(2, "収納");
		categoryHmap.put(3, "照明");
		categoryHmap.put(4, "寝具");
		categoryHmap.put(5, "机");
		categoryHmap.put(6, "その他");

		//トップ画面から商品検索画面に遷移する際の処理
		if(request.getParameter("btnItemListTransition")!=null) {
				req = request.getRequestDispatcher("jsp/ItemSearch.jsp");
				req.forward(request, response);
				return;
			}

		//カテゴリ検索
		if(request.getParameter("btnCategorySelect")!=null) {
			int categoryNo = Integer.parseInt(request.getParameter("btnCategorySelect"));
			ItemSearchLogic itemSearchLogic = new ItemSearchLogic();
			itemSearchList = itemSearchLogic.categorySearch(categoryNo);
			//複数の商品のレビューの平均点を保存するためのHashMap。KeyはitemNo。
			HashMap<Integer, Double> reviewAverageHmap = new HashMap<Integer, Double>();
			reviewAverageHmap = itemDetailLogic.getReviewAverageList(itemSearchList);
			//値を渡す
			request.setAttribute("reviewAverageHmap", reviewAverageHmap);
			request.setAttribute("categoryHmap", categoryHmap);
			request.setAttribute("itemSearchList", itemSearchList);
			request.setAttribute("selectCategoryNo", categoryNo);
			req = request.getRequestDispatcher("jsp/ItemSearch.jsp");
			req.forward(request, response);
			return;
		}

		//商品検索ボタンが押された場合の処理
		if(request.getParameter("btnItemSearchTransition")!=null) {
			//入力された文字を取得
			itemSearchWord = request.getParameter("itemSearchWord");
			//未入力の場合
			if(itemSearchWord.equals("")) {
				request.setAttribute("errorText", "検索ワードを入力してください。");
				request.setAttribute("categoryHmap", categoryHmap);
				req = request.getRequestDispatcher("jsp/ItemSearch.jsp");
				req.forward(request, response);
				return;
			}

			//検索する。
			ItemSearchLogic itemSearchLogic = new ItemSearchLogic();
			itemSearchList = itemSearchLogic.itemSearch(itemSearchWord);
			//複数の商品のレビューの平均点を保存するためのHashMap。KeyはitemNo。
			HashMap<Integer, Double> reviewAverageHmap = new HashMap<Integer, Double>();
			reviewAverageHmap = itemDetailLogic.getReviewAverageList(itemSearchList);
			//値を渡す
			request.setAttribute("itemSearchList", itemSearchList);
			request.setAttribute("reviewAverageHmap", reviewAverageHmap);
			request.setAttribute("categoryHmap", categoryHmap);
			req = request.getRequestDispatcher("jsp/ItemSearch.jsp");
			req.forward(request, response);
			return;
		}

	}

}

