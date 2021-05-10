package writereview;

import jdbc.ReviewJdbc;

public class WriteReviewLogic {

	public String writeReviewLogic(int loginUserNo, int setItemNo, double setReviewScore, String setReviewComment) {
		String resultText = null;
		ReviewJdbc reviewJdbc = new ReviewJdbc();

		//レビューが150文字以内ではない場合
		if(setReviewComment.length() >= 150) {
			resultText = "コメントは150文字以内で入力してください。";
			return resultText;
		//コメントが未入力のレビューの場合
		}else if(setReviewComment.equals("")){
			reviewJdbc.writeNoCommentReview(loginUserNo, setItemNo, setReviewScore);
			return resultText;
		//コメントが入力されたレビューの場合
		}else {
			reviewJdbc.writeReview(loginUserNo, setItemNo, setReviewScore, setReviewComment);
			return resultText;
		}

	}

}
