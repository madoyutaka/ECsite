package itembuy;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import bean.CartBean;
import bean.ItemBean;
import jdbc.ItemBuyLogJdbc;
import jdbc.ItemJdbc;

public class ItemBuyLogic {
	//購入完了へ遷移する際に購入履歴に商品データを渡す
	//カートの中身を空にする
	public String itemBuy(ArrayList<CartBean> loginItemSession,  HashMap<Integer, Integer> itemTotalPriceHmap, int userNo) {
		ItemBuyLogJdbc itemBuyLogJdbc = new ItemBuyLogJdbc();

		//現在の日付を取得する
		Date date = new Date();
		//年-月-日の形で取得
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String stringDate = simpleDateFormat.format(date);
		//問題が無ければ、購入処理を行う。loginItemSessionにはitemNo、itemBuyCountが入っている
		return itemBuyLogJdbc.setItemBuyLog(loginItemSession, itemTotalPriceHmap, stringDate, userNo);

	}

	//購入完了へ遷移する際に在庫数を減らす
	public String itemBuyStockLogic(ArrayList<CartBean> loginItemSession){
		ItemJdbc itemJdbc = new ItemJdbc();
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
				return "購入希望数が在庫数を超えています。";
			}
		}

		//データベースに接続する。
			try {
				return itemJdbc.itemBuyStock(loginItemSession);

			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				return "購入処理中にエラーが発生しました。";
			}

	}
}