package SignUp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import jdbc.UserJdbc;

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {


	//セッション管理
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		if(session==null) {
			System.out.println("セッション×");
			return;
		}
		System.out.println("セッション継続中");

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");


		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		String email_address = request.getParameter("email_address");
		String p_code = request.getParameter("postal_code");
		String address = request.getParameter("address");
		String user_name = request.getParameter("user_name");

		if(user_id=="" || password=="" || email_address=="" || p_code=="" || address=="" || user_name=="") {
			request.setAttribute("message", "未入力の項目があります");
			request.getRequestDispatcher("/jsp/SignUp.jsp").forward(request, response);
			return;

		}else if(p_code.length()!=7){
			request.setAttribute("message", "郵便番号はハイフンなし半角数字7桁で入力してください");
			request.getRequestDispatcher("/jsp/SignUp.jsp").forward(request, response);
			return;

		}else if(user_id.length()>=20||password.length()>=20||email_address.length()>=40||address.length()>=20||user_name.length()>=100){
			request.setAttribute("message", "文字数オーバーです");
			request.getRequestDispatcher("/jsp/SignUp.jsp").forward(request, response);
			return;

		}
		else if(user_id.matches("^[a-zA-Z0-9]+$")||password.matches("^[a-zA-Z0-9]+$")||email_address.matches("^[a-zA-Z0-9]+$")){
			request.setAttribute("message", "ID,メールアドレス、パスワードは半角英数字のみ入力してください");
			request.getRequestDispatcher("/jsp/SignUp.jsp").forward(request, response);
			return;


		}
		else {

			int postal_code = Integer.parseInt(request.getParameter("postal_code"));
			UserBean ub = new UserBean(user_id, password, email_address, postal_code, address, user_name);

			UserJdbc uj = new UserJdbc();
			uj.insert(ub);


			RequestDispatcher rd = request.getRequestDispatcher("/jsp/MyPage.jsp");
			rd.forward(request, response);
		}


		}





	}

