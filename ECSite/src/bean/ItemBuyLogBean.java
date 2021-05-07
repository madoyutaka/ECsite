package bean;

import java.util.Date;

public class ItemBuyLogBean {
	int itemBuyCount;
	Date itemBuyDate;
	int itemNo;
	int userNo;
	String itemName = null;
	String itemImage = null;

	public int getItemBuyCount() {
		return itemBuyCount;
	}
	public void setItemBuyCount(int itemBuyCount) {
		this.itemBuyCount = itemBuyCount;
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

	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
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
