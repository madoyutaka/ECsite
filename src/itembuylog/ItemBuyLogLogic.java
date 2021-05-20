package itembuylog;

import java.util.ArrayList;

import bean.ItemBuyLogBean;
import jdbc.ItemBuyLogJdbc;

public class ItemBuyLogLogic {
	public ArrayList<ItemBuyLogBean> itemBuyLogGetLogic(int loginUserNo) {
		ArrayList<ItemBuyLogBean> returnList = new ArrayList<ItemBuyLogBean>();
		ItemBuyLogJdbc itemBuyLogJdbc = new ItemBuyLogJdbc();
		returnList = itemBuyLogJdbc.getItemBuyLog(loginUserNo);

		return returnList;
	}


	public ArrayList<ItemBuyLogBean> getShowList(ArrayList<ItemBuyLogBean> itemBuyLogList, int selectNo) {
		//戻り値用
		ArrayList<ItemBuyLogBean> showList = new ArrayList<ItemBuyLogBean>();
		int plusNo = 6 * (selectNo - 1);
		for(int no = 0; no <= 5; no++) {
			showList.add(itemBuyLogList.get(no + plusNo));
			if(no + plusNo == itemBuyLogList.size() - 1) {
				break;
			}
		}
		return showList;
	}

	//ページ数を計算する
	public int getItemBuyLogListTotalPageNo(ArrayList<ItemBuyLogBean> itemBuyLogList) {
		//戻り値用
		int itemBuyLogListTotalPageNo;
		if(itemBuyLogList.size() == 0) {
			//存在しない場合
			itemBuyLogListTotalPageNo = 1;
		}else {
			//1件以上存在する場合
			if(itemBuyLogList.size() % 6 == 0) {
				itemBuyLogListTotalPageNo = itemBuyLogList.size() / 6;
			}else {
				itemBuyLogListTotalPageNo = (itemBuyLogList.size() / 6) + 1;
			}
		}
		return itemBuyLogListTotalPageNo;
	}

}
