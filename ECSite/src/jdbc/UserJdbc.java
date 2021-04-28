package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.UserBean;

public class UserJdbc {

	public void  insert(UserBean ub) {



		String url = "jdbc:mysql://localhost/ec_site_db?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&"
				+ "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
				+ "serverTimezone=UTC";
		String id = "root";
		String pw = "1qaz2wSX?";

		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");		


			conn = DriverManager.getConnection(url,id,pw);		//接続

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

		}finally{		//必ず実行する処理

			try{
				if (stmt!=null)stmt.close();
				if (rs!=null) rs.close();				
				if (stmt!=null) stmt.close();
				if (conn!=null) conn.close();
			}catch(Exception ex){
			}
}
	}
}
