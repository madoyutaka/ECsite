package logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ItemBean;
import bean.UserBean;
import jdbc.ItemJdbc;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean loginUserSession;
		try {
			loginUserSession = (UserBean)session.getAttribute("loginUser");
		}catch(Exception ex) {
			loginUserSession = null;
		}

		if(request.getParameter("btnLogOut")!=null) {
			System.out.println("ログアウトを行います。");
			if(loginUserSession!=null) {
				session.invalidate();
			}

			ItemJdbc itemJdbc = new ItemJdbc();
			ItemBean randomItemBean = itemJdbc.randomItem();
			//値を渡す
			System.out.println("トップにログインボタンを表示します");
			request.setAttribute("LoginButton", "LoginButton");
			System.out.println("ログアウトが完了しました、トップ画面に遷移します。");
			request.setAttribute("randomItem", randomItemBean);
			request.getRequestDispatcher("jsp/Top.jsp").forward(request, response);
			return;
		}

	}

}