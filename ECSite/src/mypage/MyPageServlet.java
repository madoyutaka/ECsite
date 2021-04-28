package mypage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MyPageServlet")
public class MyPageServlet extends HttpServlet {
	//マイページの処理を制御するサーブレット

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/Login.jsp");
			rd.forward(request, response);
			return;
		}else if(session != null) {
			session.invalidate();
			System.out.println("session end");
		}else{
			RequestDispatcher rd =getServletContext().getRequestDispatcher("/jsp/Top.jsp");
			rd.forward(request, response);
		}



	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("Empty", "Not");
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/Login.jsp");
		rd.forward(request, response);
	}

}
