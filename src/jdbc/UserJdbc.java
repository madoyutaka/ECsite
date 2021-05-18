package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.UserBean;

public class UserJdbc {
	//returnする文章を入れる
	String returnText = null;
	String query = null;
	boolean checkBoolean = false;

	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt =null;
	ResultSet rs = null;

	String url = "jdbc:mysql://localhost/ec_site_db?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String id = "root";
	String pw = "1qaz2wSX?";

//登録情報変更
	public String userDataChange(String columnName, String setNewData, int loginUserNo) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			stmt =conn.createStatement();

			System.out.println(columnName+"を"+setNewData+"に変更します");
			//IDとメールアドレスは値が重複していないか確認する。
			if(columnName.equals("user_id") || columnName.equals("email_address")) {
				if(columnName.equals("user_id")) {
					query = "SELECT * FROM user WHERE user_id=?";
				}else {
					query = "SELECT * FROM user WHERE email_address=?";
				}
				//PreparedStatementオブジェクトを使用
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, setNewData);
				//SQLの実行
				rs = pstmt.executeQuery();
				checkBoolean = rs.next();
				System.out.println("同じ値が入ったデータは存在するか？："+checkBoolean);
			}

			if(checkBoolean!=true) {
				//情報更新用UPDATE文を判断するためのif文
				if(columnName.equals("user_no")) {
					query = "UPDATE user SET user_no=? WHERE user_no=?";
				}else if(columnName.equals("user_id")) {
					query = "UPDATE user SET user_id=? WHERE user_no=?";
				}else if(columnName.equals("password")) {
					query = "UPDATE user SET password=? WHERE user_no=?";
				}else if(columnName.equals("email_address")) {
					query = "UPDATE user SET email_address=? WHERE user_no=?";
				}else if(columnName.equals("postal_code")) {
					query = "UPDATE user SET postal_code=? WHERE user_no=?";
				}else if(columnName.equals("address")) {
					query = "UPDATE user SET address=? WHERE user_no=?";
				}else if(columnName.equals("user_name")) {
					query = "UPDATE user SET user_name=? WHERE user_no=?";
				}

				//PreparedStatementオブジェクトを使用
				pstmt = conn.prepareStatement(query);

				//columnNameがpostal_codeの場合はINT
				if(columnName.equals("postal_code")) {
					pstmt.setInt(1, Integer.parseInt(setNewData));
					pstmt.setInt(2, loginUserNo);

				}else {
					pstmt.setString(1, setNewData);
					pstmt.setInt(2, loginUserNo);

				}

				//SQLの実行
				pstmt.executeUpdate();
				//returnTextの変更
				returnText = "情報の更新が完了しました。";
			}else {
				//returnTextの変更
				if(columnName.equals("user_id")) {
					returnText = "入力されたIDは他のユーザが登録済みです。";
				}else if(columnName.equals("email_address")) {
					returnText = "入力されたメールアドレスは他のユーザが登録済みです。";
				}
			}

		}catch(SQLException ex) {
			ex.printStackTrace();
			returnText = "エラーが発生しました。";
		}catch(Exception ex) {
			ex.printStackTrace();
			returnText = "エラーが発生しました。";

		}finally {
			try {
				if(conn != null) { conn.close(); }
				if(stmt != null) { stmt.close(); }
				if(pstmt != null) { pstmt.close(); }
				if(rs != null) { rs.close(); }

			}catch(SQLException ex){
				ex.printStackTrace();
				returnText = "エラーが発生しました。";
				}

		}

		return returnText;
	}


//ユーザー情報を取得する
	public UserBean getUserData(int loginUserNo) {
		UserBean returnBean = new UserBean();

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(url, id, pw);
			stmt =conn.createStatement();
		//SELECT文の用意
			query = "SELECT * FROM user WHERE user_no=?";

		//PreparedStatementオブジェクトを使用
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, loginUserNo);
		//SQLの実行
			rs = pstmt.executeQuery();
		//値をセットする
			while(rs.next()) {
				returnBean.setUserNo(rs.getInt("user_no"));
				returnBean.setUserId(rs.getString("user_id"));
				returnBean.setPassword(rs.getString("password"));
				returnBean.setEmailAddress(rs.getString("email_address"));
				returnBean.setPostalCode(rs.getInt("postal_code"));
				returnBean.setAddress(rs.getString("address"));
				returnBean.setUserName(rs.getString("user_name"));
			}


		}catch(SQLException ex) {

		}catch(Exception ex) {

		}finally {
			try {
				if(conn != null) { conn.close(); }
				if(stmt != null) { stmt.close(); }
				if(pstmt != null) { pstmt.close(); }
				if(rs != null) { rs.close(); }

			}catch(SQLException ex){
				ex.printStackTrace();
				returnText = "エラーが発生しました。";
				}

		}
		return returnBean;
	}


//ログイン処理
	public UserBean LoginAccount(String UserId, String UserPass) {

		UserBean returnUser = new  UserBean();

	  //ドライバの登録
		try {
			Class.forName("com.mysql.jdbc.Driver");//MySQL固有

			//ドライバの用意
			 conn = DriverManager.getConnection(url,id,pw);

			 //データベースの接続
			 conn.setAutoCommit(false);//オートコミットモードの解除

			 //SELECT文
			 query = "SELECT user_no, user_id, password FROM user WHERE user_id = ? AND password = ?";
		 	 pstmt = conn.prepareStatement(query);

		 	 pstmt.setString(1, UserId);
	         pstmt.setString(2, UserPass);

			 rs = pstmt.executeQuery();

              if( rs.next()) {
		           // 見つかったアカウント情報を戻り値にセット
            	   returnUser.setUserNo(rs.getInt("user_no"));
		           returnUser.setUserId(rs.getString("user_id"));
		           returnUser.setPassword(rs.getString("password"));
		       } else {
		    	   // アカウントがなければnull返す
		           return null;
		       }
		}

		catch (ClassNotFoundException ex){//ドライバ登録時の例外処理
			ex.printStackTrace();//例外処理

		}catch (SQLException ex){//DBMSの接続時の例外処理

		}finally {
			try {
				//接続の解除
				if (rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();

			}catch(Exception ex) {//例外処理

			}
		}
		return returnUser;
	}


	//新規登録
		public void  insert(UserBean ub) {
			try{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url,id,pw);

				stmt = conn.createStatement();

				String query = "insert into user(user_id,password,email_address,postal_code,address,user_name)"+"values(?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(query);

				pstmt.setString(1,ub.getUserId());
				pstmt.setString(2,ub.getPassword());
				pstmt.setString(3,ub.getEmailAddress());
				pstmt.setInt(4,ub.getPostalCode());
				pstmt.setString(5,ub.getAddress());
				pstmt.setString(6,ub.getUserName());
				pstmt.executeUpdate();
				 conn.commit();

			}catch(ClassNotFoundException ex){
				//例外処理
				ex.printStackTrace();
			}catch(SQLException ex){
				//例外処理
				ex.printStackTrace();
			}finally{
				try{
					if (stmt!=null)stmt.close();
					if (rs!=null) rs.close();
					if (stmt!=null) stmt.close();
					if (conn!=null) conn.close();
				}catch(Exception ex){
				}
			}
	}

		//新規登録重複チェック
				public  ArrayList<String> checkJdbc(String emailAddress, String userId) {
					ArrayList<String> overlapCheck = new ArrayList<String>();
				try {
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection(url, id, pw);
						stmt =conn.createStatement();

						//user_id重複チェック
					 	String query="select count(*) from user where user_id = ?";
						pstmt = conn.prepareStatement(query);
						pstmt.setString(1,userId);
						rs = pstmt.executeQuery();
						rs.next();
						int idCount = rs.getInt(1);

						//メールアドレス重複チェック
						query="select count(*) from user where email_address = ?";
						pstmt = conn.prepareStatement(query);
						pstmt.setString(1,emailAddress);
						rs = pstmt.executeQuery();
						rs.next();
						int mailCount = rs.getInt(1);

						if(idCount>=1) {
							overlapCheck.add("！このIDは既に使用されています");
						}
						if(mailCount>=1){
							overlapCheck.add("！このメールアドレスは既に使用されています");
						}
				}

				catch (ClassNotFoundException ex){//ドライバ登録時の例外処理
					ex.printStackTrace();//例外処理

				}catch (SQLException ex){//DBMSの接続時の例外処理

				}finally {
					try {
						//接続の解除
						if (rs !=null) rs.close();
						if(pstmt !=null) pstmt.close();
						if(conn !=null) conn.close();

					}catch(Exception ex) {//例外処理

					}
				}
				return overlapCheck;
				}
}
