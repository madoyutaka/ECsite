package logic;

import java.util.ArrayList;

import bean.ItemBean;
import jdbc.ItemJdbc;

public class ItemSearchLogic {
	public ArrayList<ItemBean> itemSearch(String itemSearchWord) {
		ArrayList<ItemBean> itemSearchList = new ArrayList<ItemBean>();

		ItemJdbc itemJdbc = new ItemJdbc();
		itemSearchList = itemJdbc.itemSearch(itemSearchWord);

		return itemSearchList ;
	}

	public ArrayList<ItemBean> categorySearch(int categoryNo) {
		ArrayList<ItemBean> itemSearchList = new ArrayList<ItemBean>();

		ItemJdbc itemJdbc = new ItemJdbc();
		itemSearchList = itemJdbc.categorySearch(categoryNo);

		return itemSearchList ;
	}

	public ArrayList<ItemBean> getShowItemSearchList(ArrayList<ItemBean> itemSearchList, int selectNo) {
		//戻り値用
		ArrayList<ItemBean> showList = new ArrayList<ItemBean>();
		int plusNo = 6 * (selectNo - 1);
		for(int no = 0; no <= 5; no++) {
			showList.add(itemSearchList.get(no + plusNo));
			if(no + plusNo == itemSearchList.size() - 1) {
				break;
			}
		}
		return showList;
	}

	//商品一覧のページ数を計算する
	public int getItemSearchListTotalPageNo(ArrayList<ItemBean> itemSearchList) {
		//戻り値用
		int itemSearchListTotalPageNo;
		if(itemSearchList.size() == 0) {
			//存在しない場合
			itemSearchListTotalPageNo = 1;
		}else {
			//1件以上存在する場合
			if(itemSearchList.size() % 6 == 0) {
				itemSearchListTotalPageNo = itemSearchList.size() / 6;
			}else {
				itemSearchListTotalPageNo = (itemSearchList.size() / 6) + 1;
			}
		}
		return itemSearchListTotalPageNo;
	}

}
