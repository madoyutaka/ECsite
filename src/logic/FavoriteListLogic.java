package logic;

import java.util.ArrayList;

import bean.FavoriteBean;
import jdbc.FavoriteJdbc;

public class FavoriteListLogic {


	public ArrayList<FavoriteBean> favoriteGetLogic(int loginUserNo) {
		ArrayList<FavoriteBean> returnlist = new ArrayList<FavoriteBean>();

		FavoriteJdbc faveJdbc = new FavoriteJdbc();
		returnlist = faveJdbc.getFaves(loginUserNo);

		return returnlist;
	}

	public String deleteLogic(int loginUserNo,int itemNo) {
		String returnText = null;
		FavoriteJdbc favoriteJdbc = new FavoriteJdbc();
		returnText = favoriteJdbc.daleteFaves(loginUserNo,itemNo);

		return returnText;

	}

	public String addLogic(int itemNo,int loginUserNo) {
		String returnText=null;
		FavoriteJdbc favoriteJdbc = new FavoriteJdbc();
		returnText = favoriteJdbc.addFaves(itemNo, loginUserNo);
			return returnText;

		}

	public ArrayList<FavoriteBean> getShowList(ArrayList<FavoriteBean> faveList, int selectNo) {
		//戻り値用
		ArrayList<FavoriteBean> showList = new ArrayList<FavoriteBean>();
		int plusNo = 6 * (selectNo - 1);
		for(int no = 0; no <= 5; no++) {
			showList.add(faveList.get(no + plusNo));
			if(no + plusNo == faveList.size() - 1) {
				break;
			}
		}
		return showList;
	}

	//お気に入りリストのページ数を計算する
	public int getFavoriteListTotalPageNo(ArrayList<FavoriteBean> faveList) {
		//戻り値用
		int favoriteListTotalPageNo;
		if(faveList.size() == 0) {
			//お気に入りが存在しない場合
			favoriteListTotalPageNo = 1;
		}else {
			//お気に入りが1件以上存在する場合
			if(faveList.size() % 6 == 0) {
				favoriteListTotalPageNo = faveList.size() / 6;
			}else {
				favoriteListTotalPageNo = (faveList.size() / 6) + 1;
			}
		}
		return favoriteListTotalPageNo;
	}

}