package logic;

import bean.UserBean;
import jdbc.UserJdbc;

public class LoginLogic {
	public UserBean accountLoginLogic(String UserId, String UserPass) {

		//	returnする文章を入れる
		UserJdbc jdbc = new UserJdbc();
		UserBean returnUser = null;


		returnUser  = jdbc.LoginAccount(UserId,UserPass);

		System.out.println(returnUser+"を返します。");
		return returnUser;

	}
}
