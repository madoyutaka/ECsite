package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;

public class UserJdbc {
	//JDBCでSQL文実行に必要な情報を格納する変数の宣言
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;

	 //登録コードの用意
	 //データベース関連の情報
	 String url = "jdbc:mysql://localhost/ec_site_db"
	 		+ "?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	 String id = "root";
	 String pass ="1qaz2wSX?";//MYSQLのpass



	public UserBean LoginAccount(UserBean user) {

		UserBean returnUser = new  UserBean();

	  //ドライバの登録
		try {
			Class.forName("com.mysql.jdbc.Driver");//MySQL固有

			//ドライバの用意
			 conn = DriverManager.getConnection(url,id,pass);

			 //データベースの接続
			 conn.setAutoCommit(false);//オートコミットモードの解除

//			 LoginLogic login = new LoginLogic();
//			 login.LoginAccount(user);

			 //SELECT文
			 String query = "SELECT user_no, user_id, password FROM user WHERE user_id = ? AND password = ?";
		 	 pstmt = conn.prepareStatement(query);

		 	 pstmt.setString(1, user.getLoginId());
	         pstmt.setString(2, user.getLoginPass());

			 rs = pstmt.executeQuery();

              if( rs.next()) {
		           // 見つかったアカウント情報を戻り値にセット
            	   returnUser.setUserNo(rs.getInt("user_no"));
		           returnUser.setLoginId(rs.getString("user_id"));
		           returnUser.setLoginPass(rs.getString("password"));
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
}