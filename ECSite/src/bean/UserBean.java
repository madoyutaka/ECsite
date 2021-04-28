package bean;
import java.io.Serializable;

public class UserBean  implements Serializable{
	//値の受け渡しと格納

	//カプセル化
	private int UserNo;
	private String LoginId = "";
    private String LoginPass = "";

	public int getUserNo() {
		return UserNo;
	}


	public void setUserNo(int userNo) {
		UserNo = userNo;
	}

	public String getLoginId() {
		return LoginId;
	}

	public void setLoginId(String loginId) {
		LoginId = loginId;
	}

	public String getLoginPass() {
		return LoginPass;
	}

	public void setLoginPass(String loginPass) {
		LoginPass = loginPass;
	}








}
