package logout;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		RequestDispatcher req = null;
		HttpSession session = request.getSession();

		if(request.getParameter("btnLogOut")!=null) {
			System.out.println("ログアウトを行います。");
			if(session.getAttribute("loginUser")!=null) {
				session.invalidate();
			}

			System.out.println("ログアウトが完了しました、トップ画面に遷移します。");
			req = request.getRequestDispatcher("jsp/Top.jsp");
			req.forward(request, response);
		}

	}

}
