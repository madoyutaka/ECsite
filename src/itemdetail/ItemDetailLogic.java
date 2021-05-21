package itemdetail;

import java.util.ArrayList;

import bean.ItemBean;
import bean.ReviewBean;
import jdbc.ItemJdbc;
import jdbc.ReviewJdbc;

public class ItemDetailLogic {
	public ItemBean detailLogic(int ItemNo) {
		  //returnする文章を入れる
			ItemBean returnBean = null;
			ItemJdbc itemjdbc = new ItemJdbc();


					//データベースに接続する。
				    returnBean = itemjdbc.getItemData(ItemNo);
				    System.out.println(returnBean);


			System.out.println(returnBean+"を返します。");
			return returnBean;
		}

//レビューの平均を取得
	public double getReviewAverage(ArrayList<ReviewBean> reviewList, int itemNo) {
		ReviewJdbc reviewJdbc = new ReviewJdbc();
		int reviewCount = reviewJdbc.reviewCount(itemNo);
		double reviewAverage;
		double totalReviewScore = 0;
		for(ReviewBean review: reviewList) {
			totalReviewScore += review.getReviewScore();
		}
		System.out.println("レビューの合計："+ totalReviewScore + "レビューの数："+reviewCount);
		reviewAverage = totalReviewScore / reviewCount;
		//小数点第二位で四捨五入
		if(reviewList.size() > 0) {
			System.out.println("四捨五入前："+reviewAverage);
			reviewAverage = ((double)Math.round(reviewAverage * 10)) / 10;
			System.out.println("四捨五入後："+reviewAverage);
		}else{
			reviewAverage = 0.0;
		}

		return reviewAverage;
	}
}
