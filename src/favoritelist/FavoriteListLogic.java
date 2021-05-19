package favoritelist;

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

	public FavoriteBean deleteLogic(int loginUserNo,int itemNo) {
		FavoriteBean returnBean = null;
		FavoriteJdbc favoriteJdbc = new FavoriteJdbc();
		 returnBean = favoriteJdbc.daleteFaves(loginUserNo,itemNo);
		    System.out.println(returnBean);

		return null;

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
		int plusNo = 3 * (selectNo - 1);
		for(int no = 0; no <= 2; no++) {
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
			if(faveList.size() % 3 == 0) {
				favoriteListTotalPageNo = faveList.size() / 3;
			}else {
				favoriteListTotalPageNo = (faveList.size() / 3) + 1;
			}
		}
		return favoriteListTotalPageNo;
	}

}