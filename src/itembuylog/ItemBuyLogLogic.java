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
}