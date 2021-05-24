package cart;

import java.util.ArrayList;
import java.util.HashMap;

import bean.CartBean;
import bean.ItemBean;
import jdbc.ItemJdbc;

public class CartLogic {
	public ArrayList<CartBean> cartIn(int setItemNo, int setItemBuyCount, ArrayList<CartBean> sessionItemList) {
		ArrayList<CartBean> itemInCartList = new ArrayList<CartBean>();
		itemInCartList = sessionItemList;

		//同じ商品が入っているか確認する。
		for(CartBean cart: itemInCartList) {
			if(cart.getItemNo() == setItemNo) {
				//同じ商品が入っている場合、購入希望数を増やす。
				cart.setItemBuyCount(cart.getItemBuyCount()+setItemBuyCount);
				return itemInCartList;
			}
		}

		//同じ商品が入っていない場合は、カートに商品を追加する。
		CartBean cartBean = new CartBean();
		cartBean.setItemNo(setItemNo);
		cartBean.setItemBuyCount(setItemBuyCount);
		itemInCartList.add(cartBean);

		return  itemInCartList ;
	}

	//商品情報表示用
	public ArrayList<ItemBean> getCartItemData(ArrayList<CartBean> cartList){
		ArrayList<ItemBean> cartItemData = new ArrayList<ItemBean>();
		ItemJdbc itemJdbc = new ItemJdbc();
		ItemBean itemBean = new ItemBean();
		//受け取った情報を使用して商品の詳細を取得し、ArrayList<ItemBean>へ。
		for(CartBean list: cartList) {
			itemBean = itemJdbc.getItemData(list.getItemNo());
			cartItemData.add(itemBean);
		}
		return cartItemData;
	}

	//カートから商品を削除する
	public ArrayList<CartBean> remove(int setItemNo, ArrayList<CartBean> sessionItemList) {
		ArrayList<CartBean> itemInCartList = new ArrayList<CartBean>();
		itemInCartList = sessionItemList;
		//カートから商品を削除する。
		for(CartBean list: itemInCartList) {
			if(list.getItemNo()==setItemNo) {
				itemInCartList.remove(list);
				break;
			}
		}
		return  itemInCartList ;
	}

	//カートの商品の金額が保存されたHashMapを作成する。
	public HashMap<Integer, Integer> getCartTotalPriceHmap(ArrayList<CartBean> cartData, ArrayList<ItemBean> cartItemData){
		HashMap<Integer, Integer> returnHmap = new HashMap<Integer, Integer>();
		int no = 0;
		for(ItemBean cartItem: cartItemData) {
			returnHmap.put(cartItem.getItemNo(), (cartItem.getItemPrice()*cartData.get(no).getItemBuyCount()) );
			no++;
		}

		return returnHmap;
	}

}
