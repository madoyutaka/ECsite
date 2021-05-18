package itemdetail;

import bean.ItemBean;
import jdbc.ItemJdbc;

public class ItemDetailLogic {
	public ItemBean detailLogic(int ItemNo) {

			ItemBean returnBean = null;
			ItemJdbc itemjdbc = new ItemJdbc();


					//データベースに接続する。
				    returnBean = itemjdbc.getItemData(ItemNo);
				    System.out.println(returnBean);


			System.out.println(returnBean+"を返します。");
			return returnBean;
		}
}
