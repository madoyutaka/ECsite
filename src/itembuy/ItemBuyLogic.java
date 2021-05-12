package itembuy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bean.CartBean;
import bean.ItemBean;
import jdbc.ItemBuyLogJdbc;
import jdbc.ItemJdbc;

public class ItemBuyLogic {
	//購入完了へ遷移する際に購入履歴に商品データを渡す
	//カートの中身を空にする
	public String itemBuy(ArrayList<CartBean> loginItemSession, ArrayList<ItemBean> cartItemData,  int userNo) {
		ItemJdbc itemJdbc = new ItemJdbc();
		ItemBuyLogJdbc itemBuyLogJdbc = new ItemBuyLogJdbc();
		int itemStock;
		int cartItemNo;
		int cartItemBuyCount;

		for(CartBean cartItem: loginItemSession) {
			//商品番号を取得
			cartItemNo = cartItem.getItemNo();
			//購入希望数を取得
			cartItemBuyCount = cartItem.getItemBuyCount();
			//最新の在庫情報を取得
			ItemBean item = itemJdbc.getItemData(cartItemNo);
			itemStock = item.getItemStock();
			//在庫数を比較する
			if(itemStock == 0){
				return "商品は売り切れです。";
			}else if(cartItemBuyCount > itemStock) {
				return "購入希望数が在庫数を上回っています。";
			}
		}

		//現在の日付を取得する
		Date date = new Date();
		//年-月-日の形で取得
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String stringDate = simpleDateFormat.format(date);
		//問題が無ければ、購入処理を行う。loginItemSessionにはitemNo、itemBuyCountが入っている
		return itemBuyLogJdbc.setItemBuyLog(loginItemSession, stringDate, userNo);
		
		


	}
	
	//新しく追加したもの
	//購入完了へ遷移する際に在庫数を減らす
	public ItemBean buyLogic(int setItemNo,int setItemBuyNum) {
		ItemBean returnBean = null;
		ItemJdbc itemjdbc = new ItemJdbc();

		//データベースに接続する。
	    returnBean = itemjdbc.buy(setItemNo,setItemBuyNum);
	    System.out.println(returnBean);


		System.out.println(returnBean+"を返します。");
		return returnBean;
	}
}
