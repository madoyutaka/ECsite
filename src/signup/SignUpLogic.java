package signup;

import java.io.Serializable;
import java.util.ArrayList;

import bean.UserBean;
import jdbc.UserJdbc;


public class SignUpLogic implements Serializable {
	//入力チェックのメソッド
	public ArrayList<String> checkSULogic(String userId,String password,String emailAddress,String pCode,String address,String userName){
		//エラー用リスト
		ArrayList<String> list = new ArrayList<String>();

			if(userId=="" || password=="" || emailAddress=="" || pCode=="" || address=="" || userName=="") {
			list.add("！入力されていない項目があります");
			}
			if(address.length()>=100||userName.length()>=20){
				list.add("！氏名は20文字以内で入力してください");
			}
			if(!userId.matches("^[a-zA-Z0-9]+$")||userId.length()>=20){
				list.add("！ユーザIDは半角英数字20文字以内で入力してください");
			}
			if(password.length()>=20||!password.matches("^[a-zA-Z0-9]+$")){
				list.add("！パスワードは半角英数字20文字以内で入力してください");
			}
			if(emailAddress.length()>=40){
				list.add("！メールアドレスは40文字以内で入力してください");
			}else if(!emailAddress.matches("^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")){
					list.add("！メールアドレスは正しい方法で入力してください");
			}
			if(pCode.length()!=7||!pCode.matches("^[0-9]+$")){
				list.add("！郵便番号はハイフンなし半角数字7文字で入力してください");
			}
			if(pCode.matches("^0+[0-9]+$")){
				list.add("！郵便番号は0以外から開始してください");
			}
			if(address.length()>=100){
				list.add("！住所は40文字以内で入力してください");
			}

			//重複チェック
			UserJdbc jdbc = new UserJdbc();
			ArrayList<String> overlapCheck = jdbc.checkJdbc(emailAddress,userId);

			if(overlapCheck!=null) {
				list.addAll(overlapCheck);
			}

			if(list.isEmpty()) {
				UserBean ub = new UserBean();
				//pCodeをint型に変換しそれぞれbeanにセット
				int postalCode = Integer.parseInt(pCode);
				ub.setUserId(userId);
				ub.setPassword(password);
				ub.setEmailAddress(emailAddress);
				ub.setPostalCode(postalCode);
				ub.setAddress(address);
				ub.setUserName(userName);

				UserJdbc uj = new UserJdbc();
				uj.insert(ub);
			}

		return list;

	}
}