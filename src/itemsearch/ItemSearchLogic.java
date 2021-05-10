package itemsearch;

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

}
