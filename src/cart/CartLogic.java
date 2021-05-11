package cart;

import java.util.ArrayList;

import bean.ItemBean;
import jdbc.ItemJdbc;

public class CartLogic {
	public ArrayList<ItemBean> cartIn(int setItemNo, ArrayList<ItemBean> sessionItemList) {
		ArrayList<ItemBean> itemInCartList = new ArrayList<ItemBean>();

		ItemJdbc itemJdbc = new ItemJdbc();
		ItemBean itemData = itemJdbc.getItemData(setItemNo);
		itemInCartList = sessionItemList;
		itemInCartList.add(itemData);

		return  itemInCartList ;
	}

	public ArrayList<ItemBean> remove(int setItemNo, ArrayList<ItemBean> sessionItemList) {
		ArrayList<ItemBean> itemInCartList = new ArrayList<ItemBean>();

		ItemJdbc itemJdbc = new ItemJdbc();
		ItemBean itemData = itemJdbc.getItemData(setItemNo);
		itemInCartList = sessionItemList;
		itemInCartList.remove(itemData);

		return  itemInCartList ;
	}
}
