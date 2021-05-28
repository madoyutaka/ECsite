package logic;

import java.util.ArrayList;
import java.util.HashMap;

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

		return returnBean;
	}

	//複数の商品のレビューの平均点を取得し、HashMapに保存。
	public HashMap<Integer, Double> getReviewAverageList(ArrayList<ItemBean> itemSearchList){
		ReviewJdbc reviewJdbc = new ReviewJdbc();
		//複数の商品のレビューの平均点を保存するためのHashMap。KeyはitemNo。
		HashMap<Integer, Double> reviewAverageHmap = new HashMap<Integer, Double>();
		//レビューの平均点を保存するためのreviewAverage
		//レビューを保存するためのreviewList
		for(ItemBean item: itemSearchList) {
			//レビューを取得
			ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();
			reviewList = reviewJdbc.getReviewData(item.getItemNo());
			//レビューの平均点を取得
			Double reviewAverage = getReviewAverage(reviewList, item.getItemNo());
			//HashMapに保存
			reviewAverageHmap.put(item.getItemNo(), reviewAverage);
		}

		return reviewAverageHmap;
	}

//レビューの平均点を取得
	public double getReviewAverage(ArrayList<ReviewBean> reviewList, int itemNo) {
		ReviewJdbc reviewJdbc = new ReviewJdbc();
		int reviewCount = reviewJdbc.reviewCount(itemNo);
		double reviewAverage;
		double totalReviewScore = 0;
		for(ReviewBean review: reviewList) {
			totalReviewScore += review.getReviewScore();
		}
		reviewAverage = totalReviewScore / reviewCount;
		//小数点第二位で四捨五入
		if(reviewList.size() > 0) {
			reviewAverage = ((double)Math.round(reviewAverage * 10)) / 10;
		}else{
			reviewAverage = 0.0;
		}

		return reviewAverage;
	}

	//表示用レビューを5件取得
	public ArrayList<ReviewBean> getShowReviewList(ArrayList<ReviewBean> reviewList, int selectNo){
		ArrayList<ReviewBean> returnList = new ArrayList<ReviewBean>();
		int plusNo = 5 * (selectNo - 1);
		for(int no = 0; no <= 4; no++) {
			returnList.add(reviewList.get(no + plusNo));
			if(no + plusNo == reviewList.size() - 1) {
				break;
			}
		}
		return returnList;
	}

	//ページ数を計算する
	public int getReviewTotalPageNo(ArrayList<ReviewBean> reviewList) {
		//戻り値用
		int reviewListTotalPageNo;
		if(reviewList.size() == 0) {
			//レビューが存在しない場合
			reviewListTotalPageNo = 1;
		}else {
			//レビューが1件以上存在する場合
			if(reviewList.size() % 5 == 0) {
				reviewListTotalPageNo = reviewList.size() / 5;
			}else {
				reviewListTotalPageNo = (reviewList.size() / 5) + 1;
			}
		}
		return reviewListTotalPageNo;
	}

}
