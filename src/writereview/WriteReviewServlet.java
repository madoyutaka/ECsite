package writereview;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;

@WebServlet("/WriteReviewServlet")
public class WriteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//保存用
	int setItemNo = 1;

    public WriteReviewServlet() {
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
		String reviewResultText = null;
		RequestDispatcher req = null;
		String reviewComment = null;
		double reviewScore;
		int loginUserNo = -1;
		HttpSession session = request.getSession(false);
		UserBean loginUserBean = (UserBean) session.getAttribute("loginUser");
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
		loginUserNo = loginUserBean.getUserNo();

		//レビュー画面に遷移する
		if(request.getParameter("btnWriteReviewTransition")!=null) {
			setItemNo = Integer.parseInt(request.getParameter("btnWriteReviewTransition"));
			//画面遷移
			System.out.println("レビュー画面に遷移します。");
			req = request.getRequestDispatcher("jsp/WriteReview.jsp");
			req.forward(request, response);
			return;
		}

		//レビューを保存する。
		if(request.getParameter("btnWriteReview")!=null) {
			//値を取得する
			reviewScore = Double.parseDouble(request.getParameter("reviewScore"));
			reviewComment = request.getParameter("reviewComment");
			WriteReviewLogic writeReviewLogic = new WriteReviewLogic();
			reviewResultText = writeReviewLogic.writeReviewLogic(loginUserNo, setItemNo, reviewScore, reviewComment);
			request.setAttribute("reviewResultText", reviewResultText);
			//画面遷移
			System.out.println("商品詳細画面に遷移します。");
			req = request.getRequestDispatcher("jsp/ItemDetail.jsp");
			req.forward(request, response);
			return;
		}

	}

}
