package logic;

import jdbc.ReviewJdbc;

public class WriteReviewLogic {

	public String writeReviewLogic(int loginUserNo, int setItemNo, double setReviewScore, String setReviewComment) {
		String resultText = null;
		ReviewJdbc reviewJdbc = new ReviewJdbc();
		String reviewComment = setReviewComment;

		//全角スペースを半角スペースにする
		reviewComment = setReviewComment.replaceFirst("^[\\h]+", " ").replaceFirst("[\\h]+$", " ");
		//文章の最初、最後に入力されたスペースの削除。
		reviewComment = reviewComment.trim();

		//コメントが未入力のレビューの場合
		if(reviewComment.equals("")) {
			resultText = reviewJdbc.writeNoCommentReview(loginUserNo, setItemNo, setReviewScore);
			return resultText;
		//レビューが150文字以内ではない場合
			}else if(reviewComment.length() > 150) {
			resultText = "コメントは150文字以内で入力してください。";
			return resultText;
		//コメントが入力されたレビューの場合
		}else {
			resultText = reviewJdbc.writeReview(loginUserNo, setItemNo, setReviewScore, reviewComment);
			return resultText;
		}

	}

}
