package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.CartBean;
import bean.ItemBuyLogBean;

public class ItemBuyLogJdbc {
	//returnする文章を入れる
		String query = null;

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost/ec_site_db?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1qaz2wSX?";


	//商品購入履歴の取得
	public ArrayList<ItemBuyLogBean> getItemBuyLog(int loginUserNo) {
		ArrayList<ItemBuyLogBean> itemBuyLogList = new ArrayList<ItemBuyLogBean>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			stmt =conn.createStatement();

			System.out.println(loginUserNo+"の購入履歴を取得します。");
			//履歴を取得する
			query = "SELECT * FROM itembuylog JOIN item ON itembuylog.item_no = item.item_no WHERE user_no=?";
			//PreparedStatementオブジェクトを使用
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, loginUserNo);
			//SQLの実行
			rs = pstmt.executeQuery();

			//取得した値を格納
			while(rs.next()) {
					ItemBuyLogBean itemBuyLogBean = new ItemBuyLogBean();
					itemBuyLogBean.setItemBuyCount(rs.getInt("item_buy_count"));
					itemBuyLogBean.setItemBuyDate(rs.getDate("item_buy_date"));
					itemBuyLogBean.setItemNo(rs.getInt("item_no"));
					itemBuyLogBean.setItemName(rs.getString("item_name"));
					itemBuyLogBean.setItemImage(rs.getString("item_Image"));
					itemBuyLogList.add(itemBuyLogBean);
			}


	}catch(SQLException ex) {
		ex.printStackTrace();

	}catch(Exception ex) {
		ex.printStackTrace();

	}finally {
		try {
			if(conn != null) { conn.close(); }
			if(stmt != null) { stmt.close(); }
			if(pstmt != null) { pstmt.close(); }
			if(rs != null) { rs.close(); }

		}catch(SQLException ex){
			ex.printStackTrace();

			}

	}

	return itemBuyLogList;
	}


	//商品購入履歴の保存
		public String setItemBuyLog(ArrayList<CartBean> cartList, String buyDate, int userNo) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, id, pw);
				stmt =conn.createStatement();

				//履歴を保存する
				query = "INSERT INTO itembuylog(item_buy_count, item_buy_date, item_no, user_no)"
						+"VALUES(?, ?, ?, ?)";
				//PreparedStatementオブジェクトを使用
				pstmt = conn.prepareStatement(query);
				for(CartBean list: cartList) {
					pstmt.setInt(1, list.getItemBuyCount());
					pstmt.setString(2, buyDate);
					pstmt.setInt(3, list.getItemNo());
					pstmt.setInt(4, userNo);
					//SQLの実行
					pstmt.executeUpdate();
				}

		}catch(SQLException ex) {
			ex.printStackTrace();
			return "履歴保存処理中にエラーが発生しました。";
		}catch(Exception ex) {
			ex.printStackTrace();
			return "履歴保存処理中にエラーが発生しました。";
		}finally {
			try {
				if(conn != null) { conn.close(); }
				if(stmt != null) { stmt.close(); }
				if(pstmt != null) { pstmt.close(); }
				if(rs != null) { rs.close(); }

			}catch(SQLException ex){
				ex.printStackTrace();
				return "履歴保存処理中にエラーが発生しました。";
				}

		}
			return "購入履歴はマイページからご確認ください。";

		}




}
