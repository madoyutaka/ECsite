package login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	//ログインの処理

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//MIMタイプとエンコーディング(文字コード)の設定をする
		response.setContentType("text/html;charset=UTF-8");
		//requestのエンコーディング
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		//カート用
		ArrayList<ItemBean> itemInCartList = new ArrayList<ItemBean>();


		//それぞれPOSTされたものを変数に格納
		String UserId = request.getParameter("user_id");
		String UserPass = request.getParameter("password");

		LoginLogic newlogic = new LoginLogic();
		UserBean returnUser = newlogic.accountLoginLogic(UserId,UserPass);

		if(request.getParameter("btnLoginTransition")!=null) {
			//画面遷移
			System.out.println("ログイン画面に遷移します。");
			RequestDispatcher req = request.getRequestDispatcher("jsp/Login.jsp");
			req.forward(request, response);
			return;
		}

	    	if(returnUser != null){
	    		// セッションにアカウント情報
		    	session = request.getSession();
	            session.setAttribute("loginUser", returnUser);
			
	            System.out.println("session start");
				request.setAttribute("loginUser",returnUser);//パラメータ名はloginUser
				session.setAttribute("CartItem",itemInCartList);//パラメータ名はCartItem
				//Mypage.jspに画面遷移
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/MyPage.jsp");
				rd.forward(request, response);
				return;
			}else{
				request.setAttribute("Empty", "ログインに失敗しました！");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/Login.jsp");
				rd.forward(request, response);
				return;
			}
	}
}
