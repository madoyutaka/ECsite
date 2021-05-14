package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import bean.CartBean;
import bean.ItemBean;

public class ItemJdbc {
	String query = null;

	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt =null;
	ResultSet rs = null;

	String url = "jdbc:mysql://localhost/ec_site_db?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String id = "root";
	String pw = "1qaz2wSX?";

	//商品情報を取得する
		public ItemBean getItemData(int ItemNo) {
			ItemBean returnBean = new ItemBean();
            System.out.println(ItemNo);

			try {
				Class.forName("com.mysql.jdbc.Driver");

				conn = DriverManager.getConnection(url, id, pw);
				stmt =conn.createStatement();
			//SELECT文の用意
				query = "SELECT * FROM item JOIN category ON item.category_no=category.category_no WHERE item_no=?;";

			//PreparedStatementオブジェクトを使用
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, ItemNo);
			//SQLの実行
				rs = pstmt.executeQuery();

			//値をセットする
				while(rs.next()) {
					returnBean.setItemNo(rs.getInt("item_no"));
					returnBean.setItemName(rs.getString("item_name"));
					returnBean.setItemDescription(rs.getString("item_description"));
					returnBean.setItemPrice(rs.getInt("item_price"));
					returnBean.setItemImage(rs.getString("item_image"));
					returnBean.setItemStock(rs.getInt("item_stock"));
					returnBean.setCategoryNo(rs.getInt("category_no"));
					returnBean.setCategoryName(rs.getString("category_name"));
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

					}

			}
			return returnBean;
		}
//商品検索
	public ArrayList<ItemBean> itemSearch(String searchWord){
		ArrayList<ItemBean> itemSearchList = new ArrayList<ItemBean>();
		//戻り値用
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
			stmt =conn.createStatement();

			System.out.println(searchWord+"で商品を検索します。");
			query = "SELECT item_no, item_name, item_description, item_price, item_image, item_stock, item.category_no, category_name FROM item JOIN category ON item.category_no = category.category_no WHERE item_name LIKE ?";
			//PreparedStatementオブジェクトを使用
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,  "%"+searchWord+"%");
			//SQLの実行
			System.out.println(pstmt);
			rs = pstmt.executeQuery();

			//取得した値を格納
			while(rs.next()) {
				ItemBean itemBean = new ItemBean();
				itemBean.setItemNo(rs.getInt("item_no"));
				itemBean.setItemName(rs.getString("item_name"));
				itemBean.setItemDescription(rs.getString("item_description"));
				itemBean.setItemPrice(rs.getInt("item_price"));
				itemBean.setItemImage(rs.getString("item_image"));
				itemBean.setItemStock(rs.getInt("item_stock"));
				itemBean.setCategoryNo(rs.getInt("item.category_no"));
				itemBean.setCategoryName(rs.getString("category_name"));
				itemSearchList.add(itemBean);
			}


		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch(SQLException ex) {
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

		return itemSearchList;
	}

	//商品をランダムで1つ表示する
		public ItemBean randomItem(){
			//戻り値用
			ItemBean rendomItemBean = new ItemBean();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, id, pw);
				stmt =conn.createStatement();
				//itemの数を取得する
				query = "SELECT COUNT(item_no) AS item_count FROM item";
				rs = stmt.executeQuery(query);
				rs.next();
				int itemCount = rs.getInt("item_count");
				//取得用のrandomNo
				Random random = new Random();
				int randomNo = random.nextInt(itemCount)+1;
				//商品情報をを取得する
				query = "SELECT item_no, item_name, item_description, item_price, item_image, item_stock, item.category_no, category_name FROM item JOIN category ON item.category_no = category.category_no WHERE item_no = ?";
				//PreparedStatementオブジェクトを使用
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,  randomNo);
				//SQLの実行
				rs = pstmt.executeQuery();
				System.out.println("rs："+rs);

				//取得した値を格納
				rs.next();
				rendomItemBean.setItemNo(rs.getInt("item_no"));
				rendomItemBean.setItemName(rs.getString("item_name"));
				rendomItemBean.setItemDescription(rs.getString("item_description"));
				rendomItemBean.setItemPrice(rs.getInt("item_price"));
				rendomItemBean.setItemImage(rs.getString("item_image"));
				rendomItemBean.setItemStock(rs.getInt("item_stock"));
				rendomItemBean.setCategoryNo(rs.getInt("item.category_no"));
				rendomItemBean.setCategoryName(rs.getString("category_name"));

			}catch(ClassNotFoundException ex) {
				ex.printStackTrace();
			}catch(SQLException ex) {
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

			return rendomItemBean;
		}


	//購入した商品の在庫数を購入した分だけ減らす
		public String itemBuyStock(ArrayList<CartBean> loginItemSession) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, id, pw);
				stmt =conn.createStatement();

				//SQL文
				query = "UPDATE item set  item_stock = item_stock - ?  WHERE item_no LIKE ?";
				//PreparedStatementオブジェクトを使用
				pstmt = conn.prepareStatement(query);
				for(CartBean list: loginItemSession) {
					pstmt.setInt(1, list.getItemBuyCount());
					pstmt.setInt(2, list.getItemNo());
					//SQLの実行
					pstmt.executeUpdate();
				}

			}catch(ClassNotFoundException ex) {
				ex.printStackTrace();
				return "購入処理中にエラーが発生しました。";
			}catch(SQLException ex) {
				ex.printStackTrace();
				return "購入処理中にエラーが発生しました。";
			}finally {
				try {
					if(conn != null) { conn.close(); }
					if(stmt != null) { stmt.close(); }
					if(pstmt != null) { pstmt.close(); }
					if(rs != null) { rs.close(); }

				}catch(SQLException ex){
					ex.printStackTrace();
					return "購入処理中にエラーが発生しました。";
					}

				}
			return "購入処理が完了しました。";
		}

}
