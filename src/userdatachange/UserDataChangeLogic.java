package userdatachange;

import jdbc.UserJdbc;

public class UserDataChangeLogic {
	public String dataChangeLogic(String btnName, String setNewData, int loginUserNo) {
	//	returnする文章を入れる
		String returnText = null;
		UserJdbc userjdbc = new UserJdbc();
		//どの情報を更新するか判定し、
		//入力できない文字が入力されていないか確認する。
		if(setNewData.equals("")) {
			returnText = "新しい情報が入力されていません。";

		//氏名が入力された場合
		}else if(btnName.equals("btnUserName")) {
			//20文字以内か確認する。
			if(setNewData.length()>20) {
				returnText = "20文字以内で入力してください。";
			}else{
			//データベースに接続する。
			returnText = userjdbc.userDataChange("user_name", setNewData, loginUserNo);
			}

		//IDが入力された場合
		}else if(btnName.equals("btnUserID")) {
			//20文字以内か確認する。
			if(setNewData.length()>20) {
					returnText = "20文字以内で入力してください。";
			//英数字(半角)が入力されている場合
			}else if(setNewData.matches("^[a-zA-Z0-9]+$")) {
				//データベースに接続する。
				returnText = userjdbc.userDataChange("user_id", setNewData, loginUserNo);
			}else{
				returnText  = "正しい入力方法で入力してください。";
			}

		//パスワードが入力された場合
		}else if(btnName.equals("btnPassword")) {

			//20文字以内か確認する。
			if(setNewData.length()>20) {
				returnText = "20文字以内で入力してください。";
			}else {
				//データベースに接続する。
				returnText = userjdbc.userDataChange("password", setNewData, loginUserNo);
			}

		//EmailAddressが入力された場合
		}else if(btnName.equals("btnEmailAddress")) {

			//40文字以内か確認する。
			if(setNewData.length()>40) {
				returnText = "40文字以内で入力してください。";
			//入力されているか確認。
			//@の前は!.#$%&'*+/=?^_`{|}~-　@の後は.-が使用可能
			}else if(setNewData.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")){
				//データベースに接続する。
				returnText = userjdbc.userDataChange("email_address", setNewData, loginUserNo);
			}else {
				returnText = "正しい入力方法で入力してください。";
			}

		}else if(btnName.equals("btnPostalCode")){

			//7文字かつ半角数字以外が入力されていないか確認する
			if(setNewData.matches("[+-]?\\d*(\\.\\d+)?") && setNewData.length()==7) {
				//データベースに接続する。
				returnText = userjdbc.userDataChange("postal_code", setNewData, loginUserNo);
			}else {
				returnText="正しい入力方法で入力してください。";
			}

		}else if(btnName.equals("btnAddress")) {

			//100文字以内か確認する。
			if(setNewData.length()>100) {
				returnText = "100文字以内で入力してください。";
			}else{
				//データベースに接続する。
				returnText = userjdbc.userDataChange("address", setNewData, loginUserNo);
			}

		}

		System.out.println(returnText+"を返します。");
		return returnText;
	}

}