package bean;

import java.util.Date;

public class ItemBuyLogBean {
	int itemBuyCount;
	int itemTotalPrice;
	Date itemBuyDate;
	int itemNo;
	String itemName = null;
	String itemImage = null;

	public int getItemBuyCount() {
		return itemBuyCount;
	}
	public void setItemBuyCount(int itemBuyCount) {
		this.itemBuyCount = itemBuyCount;
	}

	public int getItemTotalPrice() {
		return itemTotalPrice;
	}
	public void setItemTotalPrice(int itemTotalPrice) {
		this.itemTotalPrice = itemTotalPrice;
	}

	public Date getItemBuyDate() {
		return itemBuyDate;
	}
	public void setItemBuyDate(Date itemBuyDate) {
		this.itemBuyDate = itemBuyDate;
	}

	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemImage() {
		return itemImage;
	}
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

}
