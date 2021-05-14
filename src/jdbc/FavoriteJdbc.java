package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.FavoriteBean;

public class FavoriteJdbc {

	//returnする文章を入れる
		String query = null;

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost/ec_site_db?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1qaz2wSX?";


//お気に入りリストに入っている値を取得
	public ArrayList<FavoriteBean> getFaves(int loginUserNo){
		ArrayList<FavoriteBean> faveList = new ArrayList<FavoriteBean>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			stmt =conn.createStatement();

			//SQl文
			query = " select * from favorite join item on favorite.item_no = item.item_no where user_no=?"; //はセッションから取得

			//PreparedStatementオブジェクトを使用
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, loginUserNo);

			//SQLの実行
			rs = pstmt.executeQuery();

			//値を格納
			while(rs.next()){
							FavoriteBean faveBean = new FavoriteBean();
							faveBean.setItemName(rs.getString("item_name"));
							faveBean.setItemNo(rs.getInt("item_no"));
							faveBean.setFavoriteNo(rs.getInt("favorite_no"));
							faveList.add(faveBean);
			}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			} finally {
				try {
					if (rs!=null) rs.close();
					if (stmt!=null) stmt.close();
					if (conn!=null) conn.close();			//接続の解除
				}catch(Exception ex) {}

				}
		return faveList;
	}



//お気に入りリストから削除
	public FavoriteBean daleteFaves(int favoriteNo) {
		try {
			//SQLの実行(発行)
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			stmt =conn.createStatement();
				//SQl文の用意
				query = "delete from favorite where favorite_no = ?";
				//SQL文の実行
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, favoriteNo);


				pstmt.executeUpdate();



		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			} finally {
				try {
					if (rs!=null) rs.close();
					if (stmt!=null) stmt.close();
					if (conn!=null) conn.close();			//接続の解除
				}catch(Exception ex) {}

				}

		return null;
	}



//お気に入りに追加(重複チェック付き)
	public String addFaves(int itemNo,int loginUserNo) {
		String returnText=null;
		try {

			//SQLの実行(発行)
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			stmt =conn.createStatement();
			//user_id重複チェック
		 	String query=" select count(*) from favorite where user_no=? and item_no=?;";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,loginUserNo);
			pstmt.setInt(2,itemNo);
			rs = pstmt.executeQuery();
			rs.next();
			int favoriteCount = rs.getInt(1);

			if(favoriteCount>=1) {
				returnText="！この商品はすでにお気に入りリストに追加されています";
			}else if (favoriteCount==0){

				//SQl文の用意
				query = "INSERT INTO favorite(item_no, user_no) VALUES(?, ?);";
				//SQL文の実行
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, itemNo);
				pstmt.setInt(2, loginUserNo);

				pstmt.executeUpdate();
				}

		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			} finally {
				try {
					if (rs!=null) rs.close();
					if (stmt!=null) stmt.close();
					if (conn!=null) conn.close();			//接続の解除
				}catch(Exception ex) {}
				}
		return returnText;
	}
}
