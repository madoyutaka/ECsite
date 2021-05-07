package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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



}
