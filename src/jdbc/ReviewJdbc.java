package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.ReviewBean;

public class ReviewJdbc {
	//returnする文章を入れる
		String returnText = null;
		String query = null;
		ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost/ec_site_db?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String id = "root";
		String pw = "1qaz2wSX?";

		//レビューを取得する
		public ArrayList<ReviewBean> getReviewData(int setItemNo) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, id, pw);
				stmt =conn.createStatement();
				//レビューを新しい順で取得する。
				query = "SELECT * FROM review WHERE item_no=? ORDER BY review_no DESC";

				//PreparedStatementオブジェクトを使用
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, setItemNo);
				//SQLの実行
				rs = pstmt.executeQuery();

				//取得した値を格納
				while(rs.next()) {
					ReviewBean reviewBean = new ReviewBean();
					reviewBean.setReviewComment(rs.getString("review_comment"));
					reviewBean.setReviewScore(rs.getInt("review_score"));
					reviewBean.setItemNo(rs.getInt("item_no"));
					reviewList.add(reviewBean);
					System.out.println(reviewBean+"を追加しました。");
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

			return reviewList;
		}


		//レビューを保存
		public String writeReview(int loginUserNo, int setItemNo, double setReviewScore, String setReviewComment) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, id, pw);
				stmt =conn.createStatement();

				System.out.println(loginUserNo+"が入力したレビュー「"+setReviewScore+"、"+setReviewComment+"」を保存します。");
				query = "INSERT INTO review(review_comment, review_score, item_no, user_no) VALUE(?, ?, ?, ?)";

				//PreparedStatementオブジェクトを使用
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, setReviewComment);
				pstmt.setDouble(2, setReviewScore);
				pstmt.setInt(3, setItemNo);
				pstmt.setInt(4, loginUserNo);
				//SQLの実行
				pstmt.executeUpdate();
				//returnTextの変更
				returnText = "レビューが完了しました。";
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

		//コメント無しのレビューを保存するwriteNoCommentReview
		public String writeNoCommentReview(int loginUserNo, int setItemNo, double setReviewScore) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url, id, pw);
				stmt =conn.createStatement();

				System.out.println(loginUserNo+"が入力したレビュー「"+setReviewScore+"、コメント未記入」を保存します。");
				//IDとメールアドレスは値が重複していないか確認する。
				query = "INSERT INTO review(review_score, item_no, user_no) VALUE(?, ?, ?)";

				//PreparedStatementオブジェクトを使用
				pstmt = conn.prepareStatement(query);
				pstmt.setDouble(1, setReviewScore);
				pstmt.setInt(2, setItemNo);
				pstmt.setInt(3, loginUserNo);
				//SQLの実行
				pstmt.executeUpdate();
				//returnTextの変更
				returnText = "レビューが完了しました。";
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


}
